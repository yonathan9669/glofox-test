<template>
  <v-stepper-content step="3">
    <v-container fill-height fluid>
      <v-row align="center" justify="center">
        <h2 v-if="path == null">Select Intention</h2>
        <h2 v-else-if="isBookingPath">Booking</h2>
        <event-selector ref="event" v-model="event" v-else></event-selector>
      </v-row>

      <v-card-actions>
        <v-btn color="primary" @click="nextStep" :disabled="!canContinue">
          Continue
        </v-btn>
        <v-btn text @click="init"> Cancel</v-btn>
      </v-card-actions>
    </v-container>
  </v-stepper-content>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import { PathOptions, Event, Activity } from "@/store/app";
import EventSelector from "@/components/EventSelector.vue";
import { mapActions } from "vuex";

@Component({
  components: {
    EventSelector,
  },
  methods: {
    ...mapActions("app", ["selectEvent"]),
  },
  computed: {},
})
export default class thirdStep extends Vue {
  event: Event | null = null;
  activity: Activity | null = null;

  @Prop({ type: String }) path!: string;

  selectEvent!: (event: Event | null) => void;

  $refs!: {
    event: EventSelector;
  };

  nextStep(): void {
    if (this.isBookingPath) {
      // TODO: select Activity
    } else this.selectEvent(this.event);

    this.$emit("continue");
  }

  init(): void {
    if (this.isBookingPath) {
      // TODO: init booking
    } else {
      this.$refs.event.init();
      this.event = null;
    }
  }

  get isBookingPath(): boolean {
    return this.path === PathOptions.BOOKING;
  }

  get canContinue(): boolean {
    const value = this.event || this.activity;
    return value != null && !!value.id;
  }
}
</script>

<style scoped></style>
