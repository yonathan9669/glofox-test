<template>
  <v-stepper-content step="4">
    <v-container fill-height fluid>
      <v-row align="center" justify="center">
        <h2 v-if="path == null">Select Intention</h2>
        <h2 v-else-if="isBookingPath">Booking</h2>
        <activity-form ref="activity" v-model="activity" v-else></activity-form>
      </v-row>

      <v-card-actions v-show="canContinue">
        <v-btn color="primary" @click="finish"> Finish</v-btn>
      </v-card-actions>
    </v-container>
  </v-stepper-content>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import { Activity, PathOptions } from "@/store/app";
import ActivityForm from "@/components/forms/ActivityForm.vue";

@Component({
  components: { ActivityForm },
  methods: {},
  computed: {},
})
export default class FourthStep extends Vue {
  activity: Activity | null = null;

  @Prop({ type: String }) path!: string;

  finish(): void {
    switch (this.path) {
      case PathOptions.CREATION:
        // TODO: book
        break;
      case PathOptions.BOOKING:
        // TODO: create
        break;
    }

    this.$emit("continue");
  }

  init(): void {
    switch (this.path) {
      case PathOptions.CREATION:
        // TODO: book
        break;
      case PathOptions.BOOKING:
        // TODO: create
        break;
    }
  }

  get isBookingPath(): boolean {
    return this.path === PathOptions.BOOKING;
  }

  get canContinue(): boolean {
    //TODO: There is something wrong with this.
    return this.activity != null && !!this.activity.id;
  }
}
</script>

<style scoped></style>
