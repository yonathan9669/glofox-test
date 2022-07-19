import Vue from "vue";
import Vuex, { StoreOptions } from "vuex";

import App from "@/store/app";
import { State, initState, mutations, getters } from "./state";

Vue.use(Vuex);

const store: StoreOptions<State> = {
  state: initState,
  mutations,
  getters,
  modules: {
    app: App,
  },
};

export default new Vuex.Store(store);
