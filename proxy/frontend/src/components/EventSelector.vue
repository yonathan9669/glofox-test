<template>
  <v-col>
    <business-form ref="business" v-model="business"></business-form>
    <h2 v-if="businessActive">EVENTS for {{ business.name }}</h2>
  </v-col>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { Business, Event } from "@/store/app";
import BusinessForm from "@/components/forms/BusinessForm.vue";

@Component({
  components: {
    BusinessForm,
  },
  methods: {},
  computed: {},
})
export default class EventSelector extends Vue {
  event: Event | null = null;
  business: Business | null = null;

  $refs!: {
    business: BusinessForm;
  };

  init(): void {
    this.$refs.business.init();
    this.business = null;
    this.event = null;
  }

  get businessActive(): boolean {
    return !!this.business?.id;
  }
}
</script>

<style scoped></style>
