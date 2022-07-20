<template>
  <v-col>
    <business-form ref="business" v-model="business"></business-form>
    <v-divider class="mx-4"></v-divider>
    <event-form
      v-if="businessActive"
      ref="event"
      v-model="event"
      :business="business"
    ></event-form>
  </v-col>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { Business, Event } from "@/store/app";
import BusinessForm from "@/components/forms/BusinessForm.vue";
import EventForm from "@/components/forms/EventForm.vue";

@Component({
  components: {
    BusinessForm,
    EventForm,
  },
  methods: {},
  computed: {},
})
export default class EventSelector extends Vue {
  event: Event | null = null;
  business: Business | null = null;

  $refs!: {
    business: BusinessForm;
    event: EventForm;
  };

  init(): void {
    this.$refs.business.init();
    this.business = null;
    this.event = null;
  }

  @Watch("event")
  async onEventChange(event: Event): Promise<void> {
    this.$emit("input", event);
  }

  get businessActive(): boolean {
    return !!this.business?.id;
  }
}
</script>

<style scoped></style>
