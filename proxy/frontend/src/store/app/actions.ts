import { ActionTree } from "vuex";
import axios from "axios";

import { State as RootState } from "@/store/state";
import { App, UserInfo, Event, Business, Activity } from "./interfaces";

import { getTypes } from "@/store/app/queries/types";
import { findUser, insertUser } from "@/store/app/queries/user";
import { myBusinesses, insertBusiness } from "@/store/app/queries/business";
import { findEvents, insertEvent } from "@/store/app/queries/event";
import { fetchActivities } from "@/store/app/queries/activity";

const { VUE_APP_API_URL } = process.env;

const actions: ActionTree<App, RootState> = {
  loadTypes: async ({ commit, rootState: { apolloClient } }): Promise<void> => {
    if (!apolloClient) return;

    const {
      data: { businessTypes, eventTypes, activityTypes },
    } = await apolloClient.query({ query: getTypes });

    commit("setBusinessTypes", businessTypes);
    commit("setEventTypes", eventTypes);
    commit("setActivityTypes", activityTypes);
  },
  // ---------------------------------------------------------------------------
  // USER
  // ---------------------------------------------------------------------------
  searchUser: async (
    { commit, rootState: { apolloClient } },
    name
  ): Promise<void> => {
    if (!apolloClient) return;

    const {
      data: { users },
    } = await apolloClient.query({
      query: findUser,
      variables: { name: `%${name}%` },
      fetchPolicy: "network-only",
    });

    commit("setUsers", users);
  },
  createUser: async (
    { rootState: { apolloClient } },
    userData
  ): Promise<UserInfo | null> => {
    if (!apolloClient) return null;

    const {
      data: { user },
    } = await apolloClient.mutate({
      mutation: insertUser,
      variables: userData,
    });

    return user;
  },
  selectUser: ({ commit }, user): void => {
    if (!user?.id) throw new Error("User must be in the DB");
    commit("setUser", user);
  },
  // ---------------------------------------------------------------------------
  // BUSINESS
  // ---------------------------------------------------------------------------
  myBusinesses: async ({
    commit,
    state: { user },
    rootState: { apolloClient },
  }): Promise<void> => {
    if (!apolloClient) return;

    const {
      data: { businesses },
    } = await apolloClient.query({
      query: myBusinesses,
      variables: { owner: user.id },
    });

    commit("setBusinesses", businesses);
  },
  createBusiness: async (
    { state: { user }, rootState: { apolloClient } },
    businessData
  ): Promise<Business | null> => {
    if (!apolloClient) return null;

    const {
      data: { business },
    } = await apolloClient.mutate({
      mutation: insertBusiness,
      variables: { owner: user.id, ...businessData },
    });

    return business;
  },
  // ---------------------------------------------------------------------------
  // EVENT
  // ---------------------------------------------------------------------------
  businessEvents: async (
    { rootState: { apolloClient } },
    business
  ): Promise<Event[]> => {
    if (!apolloClient) return [];

    const {
      data: { events },
    } = await apolloClient.query({
      query: findEvents,
      variables: { business: business.id },
    });

    return events;
  },
  createEvent: async (
    { rootState: { apolloClient } },
    eventData
  ): Promise<Event | null> => {
    if (!apolloClient) return null;

    const {
      data: { event },
    } = await apolloClient.mutate({
      mutation: insertEvent,
      variables: { ...eventData, business: eventData.business.id },
    });

    return event;
  },
  selectEvent: ({ commit }, event): void => {
    if (!event?.id) throw new Error("Event must be in the DB");
    commit("setEvent", event);
  },
  // ---------------------------------------------------------------------------
  // ACTIVITY
  // ---------------------------------------------------------------------------
  createActivity: async ({ state: { event } }, activityData) => {
    activityData.event = event.id;

    const {
      data: { activity },
    } = await axios.post(
      `/classes`,
      { activity: { ...activityData } },
      { baseURL: VUE_APP_API_URL }
    );

    return activity;
  },
  fetchActivities: async ({
    rootState: { apolloClient },
  }): Promise<Activity[]> => {
    if (!apolloClient) return [];

    const {
      data: { activities },
    } = await apolloClient.query({
      query: fetchActivities,
    });

    return activities;
  },
  selectActivity: ({ commit }, activity): void => {
    if (!activity?.id) throw new Error("Activity must be in the DB");
    commit("setActivity", activity);
  },
};

export default actions;
