<template>
  <v-form @submit.prevent="submit">
    <v-autocomplete
      ref="businessList"
      v-model="business"
      :items="businesses"
      :loading="isLoading"
      :search-input.sync="query"
      chips
      hide-details
      hide-selected
      item-text="name"
      return-object
      label="Let's find the business you want to add an activity"
      solo
      @keydown.enter="processBusiness(query)"
      @blur="query = null"
      :disabled="isNew"
    >
      <template v-slot:no-data>
        <v-list-item>
          <v-list-item-title>
            It seams you dont have a business yet.
            <strong
              >😊 Please, type your business name and press 👇🏼 ENTER</strong
            >
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
          <v-icon left> {{ icons[item.type] }}</v-icon>
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
            ><strong>{{ item.type }}</strong> |
            {{ item.address }}
          </v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-action>
          <v-icon>{{ icons[item.type] }}</v-icon>
        </v-list-item-action>
      </template>
    </v-autocomplete>

    <v-container v-if="business && isNew">
      <v-row align="center">
        <v-col cols="12" md="4">
          <v-card-text>
            <span class="subheading">Select business Type</span>

            <v-chip-group
              v-model="business.type"
              mandatory
              active-class="primary--text"
            >
              <v-chip
                v-for="item in businessTypes"
                :key="item.name"
                :value="item.name"
              >
                <v-icon left> {{ icons[item.name] }}</v-icon>
                <span v-text="item.name"></span>
              </v-chip>
            </v-chip-group>
          </v-card-text>
        </v-col>

        <v-col>
          <v-text-field
            v-model="business.address"
            counter
            label="Address"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-card-actions>
        <v-row align="center" justify="end">
          <v-btn color="success" class="mr-4" type="submit"> register</v-btn>
        </v-row>
      </v-card-actions>
    </v-container>
  </v-form>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { AppType, Business } from "@/store/app";
import { mapActions, mapGetters } from "vuex";

@Component({
  components: {},
  methods: {
    ...mapActions("app", ["myBusinesses", "loadTypes", "createBusiness"]),
  },
  computed: {
    ...mapGetters("app", ["businesses", "businessTypes"]),
  },
})
export default class BusinessForm extends Vue {
  isLoading = false;
  isNew = false;
  valid = false;
  query = "";

  myBusinesses!: () => Promise<void>;
  loadTypes!: () => void;
  createBusiness!: (business: Business) => Promise<Business>;

  businesses!: Business[];
  businessTypes!: AppType[];
  business: Business | null = {} as Business;

  $refs!: {
    businessList: HTMLElement;
  };

  icons = {
    gym: "mdi-weight-lifter",
    studio: "mdi-account-group-outline",
    boutique: "mdi-store",
  };

  mounted(): void {
    this.fetchMyBusinesses();
  }

  init(): void {
    this.isLoading = false;
    this.isNew = false;
    this.valid = false;
    this.query = "";
    this.business = null;
  }

  async fetchMyBusinesses(): Promise<void> {
    this.isLoading = true;
    await this.myBusinesses();
    this.isLoading = false;
  }

  async processBusiness(name: string): Promise<void> {
    this.$refs.businessList.blur();
    if (this.business?.id) return;
    if (this.isNew) return;

    this.loadTypes();
    this.isNew = true;
    this.business = { name, type: "" };
    this.businesses.push(this.business);
  }

  async submit(): Promise<void> {
    if (!this.business || !this.isNew) return;

    this.business = await this.createBusiness(this.business);
    this.isNew = false;
    await this.fetchMyBusinesses();
  }

  @Watch("business")
  async onBusinessChange(business: Business): Promise<void> {
    this.$emit("input", business);
  }

  noBusinessFound(): boolean {
    return !this.businesses?.length;
  }
}
</script>

<style scoped></style>
