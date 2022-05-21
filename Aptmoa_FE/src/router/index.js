import Vue from "vue";
import Router from "vue-router";
import Home from "../views/Home.vue";
import User from "@/views/User.vue";

// import store from "@/store/index.js";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/user",
      name: "User",
      component: User,
      children: [
        {
          path: "register",
          name: "register",
          component: () => import("@/components/user/UserRegister.vue")
        },
        {
          path: "login",
          name: "login",
          component: () => import("@/components/user/UserLogin.vue")
        },
        {
          path: "mypage",
          name: "mypage",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/UserMyPage.vue")
        },
        {
          path: "update/:userId",
          name: "userupdate",
          component: () => import("@/components/user/UserUpdate.vue")
        }
      ]
    },
    {
      path: "/apart",
      name: "apart",
      component: () => import("@/views/Apart.vue")
    },
    {
      path: "/notice",
      name: "Notice",
      component: () => import("@/views/Notice.vue"),
      redirect: "/notice/list",
      children: [
        {
          path: "list",
          name: "noticeList",
          component: () => import("@/components/notice/NoticeList.vue")
        },
        {
          path: "write",
          name: "noticeRegister",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/notice/NoticeRegister.vue")
        },
        {
          path: "detail/:noticeno",
          name: "noticeDetail",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/notice/NoticeDetail.vue")
        },
        {
          path: "modify/:noticeno",
          name: "noticeModify",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/notice/NoticeModify.vue")
        }
      ]
    },
    {
      path: "/qna",
      name: "QnA",
      component: () => import("@/views/QnA.vue"),
      redirect: "/qna/list",
      children: [
        {
          path: "list",
          name: "qnaList",
          component: () => import("@/components/qna/QnaList.vue")
        },
        {
          path: "write",
          name: "qnaRegister",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/qna/QnaRegister.vue")
        },
        {
          path: "detail/:qnano",
          name: "qnaDetail",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/qna/QnaDetail.vue")
        },
        {
          path: "modify/:qnano",
          name: "qnaModify",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/qna/QnaModify.vue")
        }
      ]
    },
    {
      path: "/news",
      name: "News",
      component: () => import("@/views/News.vue"),
      redirect: "/news/list",
      children: [
        {
          path: "list",
          name: "newsList",
          component: () => import("@/components/news/NewsList.vue")
        },
        {
          path: "detail/:newsno",
          name: "newsDetail",
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/news/NewsDetail.vue")
        }
      ]
    }
  ]
});
