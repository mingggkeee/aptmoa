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
        <router-link :to="{ name: 'Apart' }" class="link ml-4" id="category">
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
          <b-navbar-nav v-if="userInfo">
            <b-nav-item class="align-self-center"
              ><b-avatar
                variant="light"
                v-text="userInfo ? userInfo.userid.charAt(0).toUpperCase() : ''"
              ></b-avatar
              >{{ userInfo.username }}({{ userInfo.userid }})님</b-nav-item
            >
            <b-nav-item class="align-self-center"
              ><router-link
                :to="{ name: 'mypage' }"
                class="link align-self-center"
                >내정보보기</router-link
              ></b-nav-item
            >
            <b-nav-item
              class="link align-self-center"
              @click.prevent="onClickLogout"
              >로그아웃</b-nav-item
            >
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item-dropdown right>
              <template #button-content>
                <b-icon icon="people" font-scale="2" color="white"></b-icon>
              </template>
              <b-dropdown-item href="#"
                ><router-link :to="{ name: 'register' }" class="link"
                  ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
                ></b-dropdown-item
              >
              <b-dropdown-item href="#"
                ><router-link :to="{ name: 'login' }" class="link"
                  ><b-icon icon="key"></b-icon> 로그인</router-link
                ></b-dropdown-item
              >
            </b-nav-item-dropdown>
          </b-navbar-nav>
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
</style>
