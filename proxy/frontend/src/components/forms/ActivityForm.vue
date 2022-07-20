<template>
  <div>
    <v-form v-model="valid" @submit.prevent="submit" v-show="!created">
      <h1 align="center">{{ title }}</h1>
      <v-divider></v-divider>
      <v-container fluid>
        <v-col>
          <v-row justify="space-around" align="center">
            <v-chip-group
              v-model="activity.type"
              mandatory
              active-class="primary--text"
            >
              <v-chip
                v-for="item in activityTypes"
                :key="item.name"
                :value="item.name"
              >
                <v-icon left> {{ icons[item.name] }}</v-icon>
                <span v-text="item.name"></span>
              </v-chip>
            </v-chip-group>
          </v-row>

          <v-text-field
            v-model="activity.name"
            :rules="rules"
            label="Name"
          ></v-text-field>
          <v-text-field
            v-model="activity.description"
            :rules="rules"
            label="Description"
          ></v-text-field>

          <div>
            <v-row justify="space-around" align="center">
              <v-col style="width: 350px; flex: 0 1 auto">
                <v-text-field
                  v-model="activity.capacity"
                  :rules="rules"
                  label="Capacity"
                  type="number"
                ></v-text-field>
              </v-col>

              <v-col style="width: 350px; flex: 0 1 auto">
                <v-checkbox
                  v-model="activity.overbooking"
                  label="Allow Overbooking"
                ></v-checkbox>
              </v-col>
            </v-row>
          </div>

          <div>
            <h2>TIMEFRAME</h2>
            <v-row justify="space-around" align="center">
              <v-col style="width: 350px; flex: 0 1 auto">
                <v-dialog
                  ref="startAt"
                  v-model="modal.start"
                  :return-value.sync="activity.startAt"
                  persistent
                  width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="activity.startAt"
                      :rules="rules"
                      label="Start"
                      prepend-icon="mdi-calendar"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-model="activity.startAt"
                    :max="activity.endAt"
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="modal.start = false">
                      Cancel
                    </v-btn>
                    <v-btn
                      text
                      color="primary"
                      @click="$refs.startAt.save(activity.startAt)"
                    >
                      OK
                    </v-btn>
                  </v-time-picker>
                </v-dialog>
              </v-col>

              <v-col style="width: 350px; flex: 0 1 auto">
                <v-dialog
                  ref="endAt"
                  v-model="modal.end"
                  :return-value.sync="activity.endAt"
                  persistent
                  width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="activity.endAt"
                      :rules="rules"
                      label="End"
                      prepend-icon="mdi-calendar"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-model="activity.endAt"
                    :min="activity.startAt"
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="modal.end = false">
                      Cancel
                    </v-btn>
                    <v-btn
                      text
                      color="primary"
                      @click="$refs.endAt.save(activity.endAt)"
                    >
                      OK
                    </v-btn>
                  </v-time-picker>
                </v-dialog>
              </v-col>
            </v-row>
          </div>

          <v-dialog
            ref="range"
            v-model="modal.range"
            :return-value.sync="activity.dateRange"
            persistent
            width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="activity.dateRange"
                :rules="rules"
                label="Date Range"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker v-model="activity.dateRange" range scrollable>
              <v-spacer></v-spacer>
              <v-btn text color="primary" @click="modal.range = false">
                Cancel
              </v-btn>
              <v-btn
                text
                color="primary"
                @click="$refs.range.save(activity.dateRange)"
              >
                OK
              </v-btn>
            </v-date-picker>
          </v-dialog>

          <v-row style="margin: 8px" align="center" justify="end">
            <v-btn
              color="success"
              class="mr-4"
              type="submit"
              :disabled="!valid"
            >
              create
            </v-btn>
          </v-row>
        </v-col>
      </v-container>
    </v-form>

    <v-row style="height: 200px" align="center" justify="center" v-if="created">
      <v-icon color="green" x-large>mdi-check-outline</v-icon>
      <h1>Activity Successfully created</h1>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { Activity, AppType, Event } from "@/store/app";
import { mapActions, mapGetters } from "vuex";
import { DateTime } from "luxon";

@Component({
  components: {},
  methods: {
    ...mapActions("app", ["loadTypes", "createActivity"]),
  },
  computed: {
    ...mapGetters("app", ["event", "activityTypes"]),
  },
})
export default class ActivityForm extends Vue {
  activity: Activity | null = {} as Activity;
  created = false;

  event!: Event;
  activityTypes!: AppType[];

  loadTypes!: () => void;
  createActivity!: (activity: Activity) => Promise<Activity>;

  valid = false;
  modal = { start: false, end: false, range: false };
  icons = {
    class: "mdi-weight-lifter",
    session: "mdi-account-group-outline",
    therapy: "mdi-store",
    show: "mdi-store",
    sales: "mdi-store",
  };

  rules = [(v: string): boolean | string => !!v || "Required"];

  mounted(): void {
    this.loadTypes();
  }

  async submit(): Promise<void> {
    const { activity, formatTime, createActivity } = this;
    if (!activity) return;

    const dto = {
      ...activity,
      dateRange: `[${activity.dateRange.toString()}]`,
      startAt: formatTime(activity.startAt),
      endAt: formatTime(activity.endAt),
      capacity: Number(activity.capacity),
    };

    const res = await createActivity(dto);
    activity.id = res.id;
    this.created = true;
  }

  formatTime(time: string): string {
    return DateTime.fromISO(`2016-05-25T${time}`).toFormat("HH:mmZZ");
  }

  @Watch("activity", { deep: true, immediate: true })
  onActivityChange(activity: Activity): void {
    this.$emit("input", activity);
  }

  get title(): string {
    const { name, type, business } = this.event;
    return `${name} | ${type} (${business?.name})`;
  }
}
</script>

<style scoped></style>
