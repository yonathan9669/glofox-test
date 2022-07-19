import Vue from "vue";
import App from "./App.vue";

import "./registerServiceWorker";
import vuetify from "./plugins/vuetify";
import { createProvider, initApolloClient } from "./plugins/apollo";

import store from "./store";

Vue.config.productionTip = false;
const apolloProvider = createProvider();

initApolloClient(store, apolloProvider);

new Vue({
  store,
  vuetify,
  apolloProvider,
  render: (h) => h(App),
}).$mount("#app");
