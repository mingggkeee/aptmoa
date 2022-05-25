import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import userStore from "@/store/modules/userStore.js";
import apartStore from "@/store/modules/apartStore.js";

export default new Vuex.Store({
  state: {
    articles: require("@/data/articles.json"),
    drawer: false,
    items: [
      {
        text: "Home",
        href: "/"
      }
    ]
  },
  mutations: {
    setDrawer: (state, payload) => (state.drawer = payload),
    toggleDrawer: state => (state.drawer = !state.drawer)
  },
  actions: {},
  modules: {
    userStore,
    apartStore
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage
    })
  ]
});
