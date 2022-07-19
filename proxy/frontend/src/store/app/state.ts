import { GetterTree, MutationTree } from "vuex";
import { State as RootState } from "@/store/state";
import { App, AppType, Business, UserInfo, Event } from "./interfaces";

export function initState(): App {
  return {
    users: [],
    user: {} as UserInfo,
    businesses: [],
    businessTypes: [],
    event: {} as Event,
    eventTypes: [],
    activityTypes: [],
  };
}

export const mutations: MutationTree<App> = {
  setUsers: async (state, users) => {
    state.users = users;
  },
  setUser: async (state, user) => {
    state.user = user;
  },
  setBusinesses: async (state, businesses) => {
    state.businesses = businesses;
  },
  setBusinessTypes: async (state, payload) => {
    state.businessTypes = payload;
  },
  setEventTypes: async (state, payload) => {
    state.eventTypes = payload;
  },
  setEvent: async (state, payload) => {
    state.event = payload;
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
  businesses: ({ businesses }): Business[] => {
    return businesses;
  },
  businessTypes: ({ businessTypes }): AppType[] => {
    return businessTypes;
  },
  eventTypes: ({ eventTypes }): AppType[] => {
    return eventTypes;
  },
  event: ({ event }): Event => {
    return event;
  },
};
