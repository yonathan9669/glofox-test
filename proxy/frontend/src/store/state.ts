import { GetterTree, MutationTree } from "vuex";
import { VueApolloClient } from "@/plugins/apollo";

export interface State {
  apolloClient?: VueApolloClient;
}

export function initState(): State {
  return {};
}

export const mutations: MutationTree<State> = {};

export const getters: GetterTree<State, State> = {};
