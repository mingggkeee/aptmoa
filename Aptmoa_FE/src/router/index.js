import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import User from "@/views/User.vue";

// import store from "@/store/index.js";

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: "/user",
      name: "User",
      component: User,
      children: [
        {
          path: "register",
          name: "register",
          component:() => import("@/components/user/UserRegister.vue"),
        },
        {
          path: "login",
          name: "login",
          component:() => import("@/components/user/UserLogin.vue"),
        },
        {
          path: "mypage",
          name: "mypage",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/UserMyPage.vue"),
        },
      ]
    },
    {
      path: "/apart",
      name: "apart",
      component: () => import("@/views/Apart.vue"),
    },
    {
      path: "/news",
      name: "News",
      component: () => import("@/views/News.vue"),
    },
    {
      path: "/notice",
      name: "Notice",
      component: () => import("@/views/Notice.vue"),
    },
    {
      path: "/qna",
      name: "QnA",
      component: () => import("@/views/QnA.vue"),
    },
  ],
})
