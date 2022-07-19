import Vue from "vue";
import VueApollo from "vue-apollo";

Vue.use(VueApollo);

// @ts-ignore
import { createApolloClient } from "vue-cli-plugin-apollo/graphql-client";
import { ApolloClient } from "apollo-client";
import { SubscriptionClient } from "subscriptions-transport-ws";
import { InMemoryCache } from "apollo-cache-inmemory";
import { Store } from "vuex";
import { State } from "@/store/state";

export type VueApolloClient = ApolloClient<InMemoryCache> & {
  wsClient: SubscriptionClient;
};

// Name of the localStorage item
const AUTH_TOKEN = "apollo-token";
const httpEndpoint = process.env.VUE_APP_GRAPHQL_HTTP;

const defaultOptions = {
  httpEndpoint,
  wsEndpoint: process.env.VUE_APP_GRAPHQL_WS || "ws://localhost:4000/graphql",
  tokenName: AUTH_TOKEN,
  persisting: false,
  websocketsOnly: false,
  ssr: false,
};

export function createProvider(options = {}): VueApollo {
  const { apolloClient, wsClient } = createApolloClient({
    ...defaultOptions,
    ...options,
  });
  apolloClient.wsClient = wsClient;

  return new VueApollo({
    defaultClient: apolloClient,
    defaultOptions: {
      $query: {
        // fetchPolicy: 'cache-and-network',
      },
    },
    errorHandler(error) {
      // eslint-disable-next-line no-console
      console.log(
        "%cError",
        "background: red; color: white; padding: 2px 4px; border-radius: 3px; font-weight: bold;",
        error.message
      );
    },
  });
}

export function initApolloClient(
  store: Store<State>,
  provider: VueApollo
): void {
  store.state.apolloClient = provider.defaultClient as VueApolloClient;
}
