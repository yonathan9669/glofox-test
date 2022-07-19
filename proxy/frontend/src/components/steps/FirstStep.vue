<template>
  <v-stepper-content step="1">
    <v-container fill-height fluid>
      <v-row align="center" justify="center">
        <v-col>
          <v-autocomplete
            ref="users"
            v-model="user"
            :items="users"
            :loading="isLoading"
            :search-input.sync="query"
            chips
            hide-details
            hide-selected
            item-text="name"
            return-object
            label="Hello 👋 there... What's your name?"
            solo
            @keydown.enter="processUser"
            @blur="query = null"
          >
            <template v-slot:no-data>
              <v-list-item>
                <v-list-item-title v-if="noUserFound">
                  Search by your
                  <strong>Full Name</strong>
                </v-list-item-title>
                <v-list-item-title v-else>
                  It seams you are not register yet.
                  <strong>😊 don't worry! Just press 👇🏼 ENTER</strong>
                </v-list-item-title>
              </v-list-item>
            </template>
            <template v-slot:selection="{ attr, on, item, selected }">
              <v-chip
                v-bind="attr"
                :input-value="selected"
                color="blue-grey"
                class="white--text"
                v-on="on"
              >
                <v-icon left> mdi-account-circle</v-icon>
                <span v-text="item.name"></span>
              </v-chip>
            </template>
            <template v-slot:item="{ item }">
              <v-list-item-avatar
                color="indigo"
                class="text-h5 font-weight-light white--text"
              >
                {{ item.name.charAt(0) }}
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title v-text="item.name"></v-list-item-title>
                <v-list-item-subtitle
                  v-text="item.email || item.phone"
                ></v-list-item-subtitle>
              </v-list-item-content>
              <v-list-item-action>
                <v-icon>mdi-account-circle</v-icon>
              </v-list-item-action>
            </template>
          </v-autocomplete>

          <v-form v-model="valid" v-if="user && isNew">
            <v-container>
              <v-row>
                <v-col cols="12" md="4">
                  <v-text-field
                    ref="email"
                    v-model="user.email"
                    label="Email"
                    :rules="emailRules"
                  ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                  <v-text-field
                    v-model="user.phone"
                    counter
                    label="Phone"
                    :rules="phoneRules"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </v-col>
      </v-row>

      <v-card-actions>
        <v-btn
          color="success"
          v-show="user && isNew"
          class="mr-4"
          :disabled="!valid"
          @click="submit"
        >
          register
        </v-btn>

        <v-btn
          color="primary"
          @click="$emit('continue')"
          :disabled="!user || !user.id"
        >
          Continue
        </v-btn>
        <v-btn text @click="init"> Cancel</v-btn>
      </v-card-actions>
    </v-container>
  </v-stepper-content>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { mapActions, mapGetters } from "vuex";
import { UserInfo } from "@/store/app";

@Component({
  components: {},
  methods: {
    ...mapActions("app", ["searchUser", "createUser"]),
  },
  computed: {
    ...mapGetters("app", ["users"]),
  },
})
export default class firstStep extends Vue {
  isLoading = false;
  isNew = false;
  query = "";
  users!: UserInfo[];

  user: UserInfo | null = {} as UserInfo;

  createUser!: (user: UserInfo) => Promise<UserInfo>;
  searchUser!: (name: string) => Promise<void>;

  valid = false;
  emailRules = [
    (v: string | null): boolean | string =>
      !v || /.+@.+\..+/.test(v) || "E-mail must be valid",
  ];
  phoneRules = [
    (v: string | null): boolean | string =>
      !v ||
      /^(\+\d{1,3})?[-. ]?(\d{3,4})[-. ]?(\d{3,4})[-. ]?(\d{3,4})$/.test(v) ||
      "Phone must be valid",
  ];

  @Watch("query")
  async onQueryChange(val: string): Promise<void> {
    if (val?.length) {
      this.isLoading = true;
      await this.searchUser(val);
      this.isLoading = false;
    }
  }

  async processUser(): Promise<void> {
    if (this.isNew) return;

    this.isNew = true;
    this.user = { name: this.query };
    this.users.push(this.user);
    (this.$refs.users as HTMLElement).blur();
    (this.$refs.email as HTMLElement).focus();
  }

  init(): void {
    this.isLoading = false;
    this.isNew = false;
    this.query = "";
    this.users = [];
    this.user = {} as UserInfo;
  }

  get noUserFound(): boolean {
    return !this.isLoading && !this.query?.length && !this.users.length;
  }

  async submit(): Promise<void> {
    if (!this.user) return;

    this.user = await this.createUser(this.user);
    this.isNew = false;
  }
}
</script>

<style scoped></style>
