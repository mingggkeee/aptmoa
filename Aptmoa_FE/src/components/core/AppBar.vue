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

        <!-- <b-navbar-toggle target="nav-collapse"></b-navbar-toggle> -->

        <router-link :to="{ name: 'home' }" class="link" id="category">
          HOME
        </router-link>

        <router-link :to="{ name: '' }" class="link ml-4" id="category">
          ABOUT
        </router-link>
        <router-link :to="{ name: 'apart' }" class="link ml-4" id="category">
          APART
        </router-link>
        <router-link :to="{ name: 'News' }" class="link ml-4" id="category">
          NEWS
        </router-link>
        <router-link :to="{ name: 'Notice' }" class="link ml-4" id="category">
          NOTICE
        </router-link>
        <router-link :to="{ name: 'QnA' }" class="link ml-4" id="category">
          Q&A
        </router-link>

        <!-- <v-btn
          v-for="(link, i) in links"
          :key="i"
          v-bind="link"
          class="hidden-sm-and-down"
          text
          @click="onClick($event, link)"
          color="white"
        >
          {{ link.text }}
        </v-btn> -->

        <v-spacer />

        <div id="detail">
          <div v-if="userInfo">
            <a class="mr-4" id="username"
              >{{ userInfo.username }}({{ userInfo.userid }})님</a
            >
            <router-link
              :to="{ name: 'mypage' }"
              class="link align-self-center mr-4"
              id="category"
              >내정보보기</router-link
            >
            <a
              class="link align-self-center"
              @click.prevent="onClickLogout"
              id="category"
              >로그아웃</a
            >
          </div>
          <div v-else>
            <router-link
              :to="{ name: 'register' }"
              class="link mr-4"
              id="category"
              ><b-icon icon="person-circle" class="mr-1"></b-icon>
              회원가입</router-link
            >
            <router-link :to="{ name: 'login' }" class="link" id="category"
              ><b-icon icon="key" class="mr-1"></b-icon> 로그인</router-link
            >
          </div>
        </div>
        <!-- <v-text-field
          append-icon="mdi-magnify"
          flat
          hide-details
          solo-inverted
          style="max-width: 300px;"
        /> -->
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
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    }
  }
};
</script>

<style scoped>
#appbar {
  background: #0e3047;
}

#category {
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
