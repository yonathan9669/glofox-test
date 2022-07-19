import { ActionTree } from "vuex";

import { State as RootState } from "@/store/state";
import { App, UserInfo } from "./interfaces";
import { getTypes } from "@/store/app/queries/types";
import { findUser, insertUser } from "@/store/app/queries/user";
import { findBusinesses, insertBusiness } from "@/store/app/queries/business";

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
    { commit, rootState: { apolloClient } },
    userData
  ): Promise<UserInfo | void> => {
    if (!apolloClient) return;

    const {
      data: { user },
    } = await apolloClient.mutate({
      mutation: insertUser,
      variables: userData,
    });

    commit("setUser", user);
    return user;
  },
  // ---------------------------------------------------------------------------
  // BUSINESS
  // ---------------------------------------------------------------------------
  searchBusiness: async ({
    commit,
    state,
    rootState: { apolloClient },
  }): Promise<void> => {
    if (!apolloClient) return;

    const { data } = await apolloClient.query({
      query: findBusinesses,
      variables: { owner: state.user.id },
    });

    commit("set", data);
    // aqui falta colocar el set que corresponde no se donde iria este search ya que el loadtype carga todos los business
  },
  createBusiness: async (
    { commit, rootState: { apolloClient } },
    businessData
  ): Promise<void> => {
    if (!apolloClient) return;

    const {
      data: { business },
    } = await apolloClient.mutate({
      mutation: insertBusiness,
      variables: { business: businessData },
    });

    commit("setBusiness", business.id);
  },
};

export default actions;
