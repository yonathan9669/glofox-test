import { ActionTree } from "vuex";

import { State as RootState } from "@/store/state";
import { App, UserInfo } from "./interfaces";
import { getTypes } from "@/store/app/queries/types";
import { findUser, insertUser } from "@/store/app/queries/user";
import { myBusinesses, insertBusiness } from "@/store/app/queries/business";

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
  ): Promise<UserInfo | void> => {
    if (!apolloClient) return;

    const {
      data: { user },
    } = await apolloClient.mutate({
      mutation: insertUser,
      variables: userData,
    });

    return user;
  },
  selectUser: async ({ commit }, user): Promise<void> => {
    if (!user?.id) throw new Error("User must be in the DB");

    await commit("setUser", user);
    return;
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
  ): Promise<void> => {
    if (!apolloClient) return;

    const {
      data: { business },
    } = await apolloClient.mutate({
      mutation: insertBusiness,
      variables: { owner: user.id, ...businessData },
    });

    return business;
  },
};

export default actions;
