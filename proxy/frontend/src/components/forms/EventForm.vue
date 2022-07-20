<template>
  <v-form v-model="valid" @submit.prevent="submit">
    <v-autocomplete
      ref="eventList"
      v-model="event"
      :items="events"
      :loading="isLoading"
      :search-input.sync="query"
      chips
      hide-details
      hide-selected
      item-text="name"
      return-object
      label="Lookup for the event you are adding an activity"
      solo
      @keydown.enter="processEvent(query)"
      @blur="query = null"
      :disabled="isNew"
    >
      <template v-slot:no-data>
        <v-list-item>
          <v-list-item-title>
            It seams you dont have an event yet.
            <strong>😊 Please, type a name for it and press 👇🏼 ENTER</strong>
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
            {{ item.description }}
          </v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-action>
          <v-icon>{{ icons[item.type] }}</v-icon>
        </v-list-item-action>
      </template>
    </v-autocomplete>

    <v-container v-if="event && isNew">
      <v-col justify="center">
        <v-row justify="space-around">
          <v-card-text>
            <span class="subheading">Select event Type</span>

            <v-chip-group
              v-model="event.type"
              mandatory
              active-class="primary--text"
            >
              <v-chip
                v-for="item in eventTypes"
                :key="item.name"
                :value="item.name"
              >
                <v-icon left> {{ icons[item.name] }}</v-icon>
                <span v-text="item.name"></span>
              </v-chip>
            </v-chip-group>
          </v-card-text>
        </v-row>

        <v-row>
          <v-text-field
            v-model="event.description"
            counter
            :rules="rules"
            label="Description"
          ></v-text-field>
        </v-row>
      </v-col>

      <v-card-actions>
        <v-row align="center" justify="end">
          <v-btn color="success" class="mr-4" type="submit" :disabled="!valid">
            register
          </v-btn>
        </v-row>
      </v-card-actions>
    </v-container>
  </v-form>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from "vue-property-decorator";
import { AppType, Business, Event } from "@/store/app";
import { mapActions, mapGetters } from "vuex";

@Component({
  components: {},
  methods: {
    ...mapActions("app", ["businessEvents", "loadTypes", "createEvent"]),
  },
  computed: {
    ...mapGetters("app", ["eventTypes"]),
  },
})
export default class EventForm extends Vue {
  isLoading = false;
  isNew = false;
  valid = false;
  query = "";

  event: Event | null = null;
  @Prop({ type: Object }) business!: Business;

  events: Event[] = [];
  eventTypes!: AppType[];

  businessEvents!: (business: Business) => Promise<Event[]>;
  loadTypes!: () => void;
  createEvent!: (event: Event) => Promise<Event>;

  $refs!: {
    eventList: HTMLElement;
  };

  rules = [
    (v: string | null): boolean | string =>
      (v && v !== "") || "Description is required",
  ];

  icons = {
    course: "mdi-google-classroom",
    training: "mdi-google-fit",
    launch: "mdi-rocket-launch",
    advisory: "mdi-human-male-board",
  };

  mounted(): void {
    this.fetchEvents(this.business);
  }

  async fetchEvents(business: Business): Promise<void> {
    this.isLoading = true;
    this.events = await this.businessEvents(business);
    this.isLoading = false;
  }

  init(): void {
    this.isLoading = false;
    this.isNew = false;
    this.valid = false;
    this.query = "";
    this.event = null;
    this.events = [];
  }

  processEvent(name: string): void {
    this.$refs.eventList.blur();
    if (this.event?.id) return;
    if (this.isNew) return;

    this.loadTypes();
    this.isNew = true;
    this.event = { name, type: "", description: "" };
    this.events.push(this.event);
  }

  async submit(): Promise<void> {
    if (!this.event || !this.isNew) return;

    this.event.business = this.business;
    this.event = await this.createEvent(this.event);
    this.isNew = false;
    await this.fetchEvents(this.business);
  }

  @Watch("business")
  onBusinessChange(business: Business): void {
    this.init();
    if (!business?.id) return;
    this.fetchEvents(business);
  }

  @Watch("event")
  onEventChange(event: Event): void {
    event.business = this.business;
    this.$emit("input", event);
  }
}
</script>

<style scoped></style>
