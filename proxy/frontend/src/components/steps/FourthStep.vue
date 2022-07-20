<template>
  <v-stepper-content step="4">
    <v-container fill-height fluid>
      <v-row align="center" justify="center">
        <h2 v-if="path == null">Select Intention</h2>
        <booking-form
          ref="booking"
          v-model="bookings"
          v-else-if="isBookingPath"
        ></booking-form>
        <activity-form ref="activity" v-model="activity" v-else></activity-form>
      </v-row>

      <v-card-actions v-if="completed">
        <v-btn color="primary" @click="finish"> Finish</v-btn>
      </v-card-actions>
    </v-container>
  </v-stepper-content>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import { Activity, Booking, PathOptions } from "@/store/app";
import ActivityForm from "@/components/forms/ActivityForm.vue";
import BookingForm from "@/components/forms/BookingForm.vue";

@Component({
  components: { BookingForm, ActivityForm },
  methods: {},
  computed: {},
})
export default class FourthStep extends Vue {
  activity: Activity | null = null;
  bookings: Booking[] | null = null;

  @Prop({ type: String }) path!: string;

  finish(): void {
    location.reload();
  }

  get isBookingPath(): boolean {
    return this.path === PathOptions.BOOKING;
  }

  get completed(): boolean {
    switch (this.path) {
      case PathOptions.BOOKING:
        return this.bookings != null && this.bookings.length > 0;
      case PathOptions.CREATION:
        return this.activity != null && !!this.activity.id;
    }

    return false;
  }
}
</script>

<style scoped></style>
