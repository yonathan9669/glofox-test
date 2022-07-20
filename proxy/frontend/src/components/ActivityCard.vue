<template>
  <v-card class="mx-auto my-12" max-width="374">
    <v-card-title>
      <v-icon color="primary">
        {{ icons[activity.event.business.type] }}
      </v-icon>
      <v-col> {{ activity.event.business.name }}</v-col>
    </v-card-title>
    <v-card-subtitle>
      <v-row>
        <v-col class="text-capitalize">{{ activity.event.name }}</v-col>
        <v-col cols="3">
          <strong>{{ activity.event.type }}</strong>
        </v-col>
      </v-row>
    </v-card-subtitle>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>
      <v-row>
        <v-col class="text-capitalize">{{ activity.name }}</v-col>
        <v-col cols="2">
          <v-icon color="secondary">{{ icons[activity.type] }}</v-icon>
        </v-col>
      </v-row>
    </v-card-title>

    <v-card-subtitle>
      <v-container>
        <v-row justify="space-between">
          <v-col><strong>Capacity: </strong> {{ activity.capacity }}</v-col>
          <v-col>
            <v-icon small :color="activity.overbooking ? 'success' : 'error'">
              mdi-account-{{ activity.overbooking ? "check" : "cancel" }}
            </v-icon>
            <span>Overbooking</span>
          </v-col>
        </v-row>
      </v-container>
    </v-card-subtitle>

    <v-card-text>
      <p class="font-italic">{{ activity.description }}</p>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>Schedule</v-card-title>

    <v-card-text>
      <div>
        <v-row>
          <v-col>
            <v-text-field
              :value="formatTime(activity.startAt)"
              label="Start"
              prepend-icon="mdi-clock-outline"
              readonly
            ></v-text-field>
          </v-col>
          <v-col>
            <v-text-field
              :value="formatTime(activity.endAt)"
              label="End"
              prepend-icon="mdi-clock-time-five"
              readonly
            ></v-text-field>
          </v-col>
        </v-row>
      </div>

      <v-text-field
        :value="dateRange"
        label="Date Range"
        prepend-icon="mdi-calendar"
        readonly
      ></v-text-field>
    </v-card-text>

    <v-card-actions>
      <v-btn
        text
        color="deep-purple lighten-2"
        @click="$emit('reserve', activity)"
      >
        Reserve
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import { Activity } from "@/store/app";
import { DateTime } from "luxon";

@Component({
  components: {},
  methods: {},
  computed: {},
})
export default class ActivityCard extends Vue {
  @Prop({ type: Object }) activity!: Activity;

  icons = {
    gym: "mdi-weight-lifter",
    studio: "mdi-account-group-outline",
    boutique: "mdi-store",

    course: "mdi-school",
    training: "mdi-google-fit",
    launch: "mdi-rocket-launch",
    advisory: "mdi-account-tie",

    class: "mdi-google-classroom",
    session: "mdi-book-edit",
    therapy: "mdi-human-male-board",
    show: "mdi-play-box",
    sales: "mdi-shopping",
  };

  formatTime(time: string): string {
    const dt = DateTime.fromISO(`2022-07-18T${time}`);
    return dt.toLocaleString(DateTime.TIME_WITH_SHORT_OFFSET);
  }

  get dateRange(): string {
    const {
      activity: { dateRange },
    } = this;

    return dateRange.replace(",", " ~ ").replaceAll(/[\\[)]/gi, "");
  }
}
</script>

<style scoped></style>
