import { GetterTree, MutationTree } from "vuex";
import { State as RootState } from "@/store/state";
import { App, AppType, UserInfo } from "./interfaces";

export function initState(): App {
  return {
    businessTypes: [],
    eventTypes: [],
    activityTypes: [],
    users: [],
    user: { name: "" } as UserInfo,
    businessId: null,
  };
}

export const mutations: MutationTree<App> = {
  setBusiness: async (state, id) => {
    state.businessId = id;
  },
  setUser: async (state, user) => {
    state.user = user;
  },
  setUsers: async (state, users) => {
    state.users = users;
  },
  setBusinessTypes: async (state, payload) => {
    state.businessTypes = payload;
  },
  setEventTypes: async (state, payload) => {
    state.eventTypes = payload;
  },
  setActivityTypes: async (state, payload) => {
    state.activityTypes = payload;
  },
};

export const getters: GetterTree<App, RootState> = {
  users: ({ users }): UserInfo[] => {
    return users;
  },
  user: ({ user }): UserInfo => {
    return user;
  },
  businessTypes: ({ businessTypes }): AppType[] => {
    return businessTypes;
  },
  eventTypes: ({ eventTypes }): AppType[] => {
    return eventTypes;
  },
};
