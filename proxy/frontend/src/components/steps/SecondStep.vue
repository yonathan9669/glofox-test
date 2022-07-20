<template>
  <v-stepper-content step="2">
    <v-container>
      <v-row class="container" align="center" justify="space-around">
        <v-btn
          elevation="4"
          x-large
          color="primary"
          @click="selectPath(options.creation)"
        >
          Activity Creation
        </v-btn>

        <v-btn
          elevation="4"
          x-large
          color="success"
          @click="selectPath(options.booking)"
        >
          Activity Booking
        </v-btn>
      </v-row>
    </v-container>
  </v-stepper-content>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { PathOptions } from "@/store/app";
import { mapActions } from "vuex";

@Component({
  methods: {
    ...mapActions("app", ["activatePath"]),
  },
})
export default class SecondStep extends Vue {
  activatePath!: (path: string) => void;

  options = { creation: PathOptions.CREATION, booking: PathOptions.BOOKING };

  selectPath(option: PathOptions): void {
    this.activatePath(option);
    this.$emit("continue");
  }
}
</script>

<style scoped>
.container {
  min-height: 150px;
}
</style>
