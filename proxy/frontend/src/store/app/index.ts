import { Module } from "vuex";

import { App } from "./interfaces";
import { State as RootState } from "@/store/state";
import { initState, mutations, getters } from "./state";
import actions from "./actions";

const module: Module<App, RootState> = {
  namespaced: true,
  state: initState,
  mutations: mutations,
  getters: getters,
  actions: actions,
};

export default module;
export * from "./interfaces";
