<template>
  <div>
    <v-form
      @submit.prevent="submit"
      v-model="valid"
      v-if="activity && activity.id && !bookings.length"
    >
      <h1 class="text-capitalize text-center">{{ title }}</h1>
      <v-divider></v-divider>
      <v-container>
        <v-row align="center" justify="center">
          <v-col cols="12">
            <v-combobox
              v-model="dates"
              multiple
              chips
              small-chips
              label="Pick your booking dates"
              prepend-icon="mdi-calendar"
              readonly
              :rules="rules"
            ></v-combobox>
          </v-col>

          <v-col align="center">
            <v-date-picker
              ref="dates"
              v-model="dates"
              landscape
              elevation="4"
              :min="range.min"
              :max="range.max"
              :range="isRange"
              :multiple="!isRange"
            >
              <v-checkbox v-model="isRange" label="Date Range"></v-checkbox>
              <v-spacer></v-spacer>
              <v-btn text color="primary" type="submit" :disabled="!valid"
                >RESERVE
              </v-btn>
            </v-date-picker>
          </v-col>
        </v-row>
      </v-container>
    </v-form>

    <v-row
      style="height: 200px"
      align="center"
      justify="center"
      v-if="bookings.length"
    >
      <v-icon color="green" x-large>mdi-check-outline</v-icon>
      <h1>Your booking is confirmed! 🥳</h1>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { mapActions, mapGetters } from "vuex";
import { Activity, Booking, BookingDto } from "@/store/app";
import { DateTime } from "luxon";

@Component({
  components: {},
  methods: {
    ...mapActions("app", ["createBooking"]),
  },
  computed: {
    ...mapGetters("app", ["activity"]),
  },
})
export default class BookingForm extends Vue {
  activity!: Activity;
  bookings: Booking[] = [];

  createBooking!: (bookings: BookingDto) => Promise<Booking[]>;

  dates: string[] = [];
  isRange = true;
  valid = false;
  rules = [
    (v: string[]): boolean | string =>
      !!v.length || "At least one date is required to book",
  ];

  async submit(): Promise<void> {
    const { isRange, dates } = this;
    const bookings = {} as BookingDto;

    dates.sort();
    if (isRange) {
      const lower = dates.shift();
      const upper = dates.length ? dates.pop() : lower;
      bookings.dateRange = `[${lower},${upper}]`;
    } else {
      bookings.dates = dates;
    }

    this.bookings = await this.createBooking(bookings);
    this.$emit("input", this.bookings);
  }

  get title(): string {
    const { name, type, event } = this.activity;
    return `${event?.name} ${type} | ${name} (${event?.business?.name})`;
  }

  get range(): DateRange {
    const { activity } = this;
    if (!activity.id) return { min: DateTime.now().toISODate() } as DateRange;

    const { dateRange: range, startAt } = activity;
    const [lower, upper] = range.replaceAll(/[\\[)]/gi, "").split(",");

    const now = DateTime.now();
    const min = DateTime.fromISO(`${lower}T${startAt}`);
    const max = DateTime.fromISO(`${upper}T${startAt}`).minus({ day: 1 });

    return {
      min: (now > min ? now : min).toISODate(),
      max: max.toISODate(),
      range,
    } as DateRange;
  }
}

interface DateRange {
  min: string;
  max: string;
  range: string;
}
</script>

<style scoped></style>
