<template>
  <v-container>
    <h1>Available Activities</h1>
    <v-row>
      <v-col cols="4" v-for="item in activities" :key="item.id">
        <activity-card
          @reserve="selectActivity"
          :activity="item"
        ></activity-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import ActivityCard from "@/components/ActivityCard.vue";
import { mapActions } from "vuex";
import { Activity } from "@/store/app";

@Component({
  components: { ActivityCard },
  methods: {
    ...mapActions("app", ["fetchActivities"]),
  },
  computed: {},
})
export default class ActivitySelector extends Vue {
  isLoading = false;
  activity: Activity | null = null;
  activities: Activity[] | null = null;

  fetchActivities!: () => Promise<Activity[]>;

  mounted(): void {
    this.updateActivities();
  }

  init(): void {
    this.activity = null;
    this.updateActivities();
  }

  async updateActivities(): Promise<void> {
    this.isLoading = true;
    this.activities = await this.fetchActivities();
    this.isLoading = false;
  }

  selectActivity(activity: Activity): void {
    this.activity = activity;
    this.activities = [activity];
    this.$emit("input", activity);
  }
}
</script>

<style scoped></style>
