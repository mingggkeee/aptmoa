<template>
  <v-app-bar app flat height="100" id="appbar">
    <v-app-bar-nav-icon class="hidden-md-and-up" @click="toggleDrawer" />

    <v-container class="mx-auto py-0">
      <v-row align="center">
        <router-link :to="{ name: 'home' }" class="link">
          <v-img
            :src="require('@/assets/logo.jpg')"
            class="mr-5"
            contain
            height="100"
            width="100"
            max-width="100"
          />
        </router-link>

        <router-link :to="{ name: 'home' }" class="link" id="appbar">
          HOME
        </router-link>
        <router-link :to="{ name: 'apart' }" class="link ml-4" id="appbar">
          APART
        </router-link>
        <router-link :to="{ name: 'News' }" class="link ml-4" id="appbar">
          NEWS
        </router-link>
        <router-link :to="{ name: 'Notice' }" class="link ml-4" id="appbar">
          NOTICE
        </router-link>
        <router-link :to="{ name: 'QnA' }" class="link ml-4" id="appbar">
          Q&A
        </router-link>

        <v-spacer />

        <div id="detail">
          <div v-if="userInfo">
            <a class="mr-4" id="username"
              >{{ userInfo.userName }}({{ userInfo.userId }})님</a
            >
            <router-link
              v-if="userInfo.userId === 'admin'"
              :to="{ name: 'manage' }"
              class="link align-self-center mr-4"
              id="appbar"
              >회원관리</router-link
            >
            <router-link
              :to="{ name: 'mypage' }"
              class="link align-self-center mr-4"
              id="appbar"
              >내정보보기</router-link
            >
            <router-link
              :to="{ name: 'Interest' }"
              class="link align-self-center mr-4"
              id="appbar"
              >관심목록 조회</router-link
            >
            <a
              class="link align-self-center"
              @click.prevent="onClickLogout"
              id="appbar"
              >로그아웃</a
            >
          </div>
          <div v-else>
            <router-link
              :to="{ name: 'register' }"
              class="link mr-4"
              id="appbar"
              ><b-icon icon="person-circle" class="mr-1"></b-icon>
              회원가입</router-link
            >
            <router-link :to="{ name: 'login' }" class="link" id="appbar"
              ><b-icon icon="key" class="mr-1"></b-icon> 로그인</router-link
            >
          </div>
        </div>
      </v-row>
    </v-container>
  </v-app-bar>
</template>

<script>
// Utilities
import { mapState, mapGetters, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "CoreAppBar",

  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapGetters(["links"])
  },

  methods: {
    ...mapMutations(["toggleDrawer"]),
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClick(e, item) {
      e.stopPropagation();

      if (item.to || !item.href) return;

      this.$vuetify.goTo(item.href.endsWith("!") ? 0 : item.href);
    },
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");

      //kakao 캐시 지우기
      window.Kakao.API.request({
        url: "/v1/user/unlink",
        success: function(response) {
          console.log(response);
        },
        fail: function(error) {
          console.log(error);
        }
      });

      if (this.$route.path != "/") this.$router.push({ name: "home" });
    }
  }
};
</script>

<style scoped>
#appbar {
  background: #0e3047;
}

#appbar {
  font-size: 18px;
  color: white;
  text-decoration-line: none;
}

#detail {
  display: flex;
  color: white;
}

#username {
  font-size: 18px;
  color: #f0f4c3;
  text-decoration-line: none;
}
</style>
