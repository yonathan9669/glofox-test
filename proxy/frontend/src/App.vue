<template>
  <v-app>
    <v-row class="body" align="center">
      <v-col>
        <v-row justify="center">
          <v-stepper class="stepper" v-model="step" vertical>
            <v-stepper-step :complete="step > 1" step="1">
              Identification
              <small>Create if needed</small>
            </v-stepper-step>

            <first-step @continue="nextStep"></first-step>

            <v-stepper-step :complete="step > 2" step="2">
              Select intention
            </v-stepper-step>

            <second-step @continue="nextStep" />

            <v-stepper-step :complete="step > 3" step="3">
              Make your pick
              <small>Create if needed</small>
            </v-stepper-step>

            <third-step :path="path" @continue="nextStep"></third-step>

            <v-stepper-step step="4">
              Action Fulfillment
              <small>Fill data in</small>
            </v-stepper-step>

            <fourth-step :path="path"></fourth-step>
          </v-stepper>
        </v-row>
      </v-col>
    </v-row>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { PathOptions } from "@/store/app";

import FirstStep from "@/components/steps/FirstStep.vue";
import SecondStep from "@/components/steps/SecondStep.vue";
import ThirdStep from "@/components/steps/ThirdStep.vue";
import FourthStep from "@/components/steps/FourthStep.vue";
import { mapGetters } from "vuex";

@Component({
  components: {
    FirstStep,
    SecondStep,
    ThirdStep,
    FourthStep,
  },
  computed: {
    ...mapGetters("app", ["path"]),
  },
})
export default class App extends Vue {
  step = 1;
  path!: PathOptions;

  nextStep(): void {
    this.step = this.step === 4 ? 1 : this.step + 1;
  }
}
</script>

<style scoped>
.stepper {
  min-width: 75vw;
  min-height: 80vh;
}

.body {
  background: orange;
}
</style>
