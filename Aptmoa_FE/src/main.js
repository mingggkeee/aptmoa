import Vue from "vue";
import vuetify from "./plugins/vuetify";
import "./plugins/base";
import App from "./App.vue";
import router from "./router";
import store from "./store";

Vue.config.productionTip = false;

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
// import "bootstrap/dist/css/bootstrap.css";
// import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount("#app");

window.Kakao.init("b2c30ed4ecb0bcb26c3807c56a8be980"); // Kakao Developers에서 요약 정보 -> JavaScript 키