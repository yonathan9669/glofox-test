import { GetterTree, MutationTree } from "vuex";
import { State as RootState } from "@/store/state";
import {
  App,
  AppType,
  Business,
  UserInfo,
  Event,
  Activity,
} from "./interfaces";

export function initState(): App {
  return {
    users: [],
    user: {} as UserInfo,
    path: null,
    businesses: [],
    businessTypes: [],
    event: {} as Event,
    eventTypes: [],
    activityTypes: [],
    activity: {} as Activity,
  };
}

export const mutations: MutationTree<App> = {
  setUsers: (state, users) => {
    state.users = users;
  },
  setUser: (state, user) => {
    state.user = user;
  },
  setPath: (state, path) => {
    state.path = path;
  },
  setBusinesses: (state, businesses) => {
    state.businesses = businesses;
  },
  setBusinessTypes: (state, payload) => {
    state.businessTypes = payload;
  },
  setEventTypes: (state, payload) => {
    state.eventTypes = payload;
  },
  setEvent: (state, payload) => {
    state.event = payload;
  },
  setActivityTypes: (state, payload) => {
    state.activityTypes = payload;
  },
  setActivity: (state, payload) => {
    state.activity = payload;
  },
};

export const getters: GetterTree<App, RootState> = {
  users: ({ users }): UserInfo[] => {
    return users;
  },
  user: ({ user }): UserInfo => {
    return user;
  },
  path: ({ path }): string | null => {
    return path;
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
  activityTypes: ({ activityTypes }): AppType[] => {
    return activityTypes;
  },
  activity: ({ activity }): Activity => {
    return activity;
  },
};
