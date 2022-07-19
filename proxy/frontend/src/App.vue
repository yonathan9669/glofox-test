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

            <second-step v-model="path" @continue="nextStep" />

            <v-stepper-step :complete="step > 3" step="3">
              Make your pick
              <small>Create if needed</small>
            </v-stepper-step>

            <third-step :path="path" @continue="nextStep"></third-step>

            <v-stepper-step step="4">
              Action Fulfillment
              <small>Fill data in</small>
            </v-stepper-step>

            <v-stepper-content step="4"></v-stepper-content>
          </v-stepper>
        </v-row>
      </v-col>
    </v-row>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { PathOptions, UserInfo } from "@/store/app";

import FirstStep from "@/components/steps/FirstStep.vue";
import SecondStep from "@/components/steps/SecondStep.vue";
import ThirdStep from "@/components/steps/ThirdStep.vue";

@Component({
  components: {
    FirstStep,
    SecondStep,
    ThirdStep,
  },
})
export default class App extends Vue {
  step = 1;

  selectedUser: UserInfo = { id: 0, name: "", phone: "", email: "" };
  path: PathOptions | null = null;

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
