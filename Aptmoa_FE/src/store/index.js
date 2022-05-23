import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import userStore from "@/store/modules/userStore.js";
import apartStore from "@/store/modules/apartStore.js";
import noticeStore from "@/store/modules/noticeStore.js";

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
    apartStore,
    noticeStore
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage
    })
    // new webpack.definePlugin({
    //   VUE_APP_APT_DEAL_API_KEY: JSON.stringify(env.VUE_APP_APT_DEAL_API_KEY),
    // }),
  ]
});
