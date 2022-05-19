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

        <router-link :to="{ name: 'home' }" class="link" id="category">
          <h1>HOME</h1>
        </router-link>
        <router-link :to="{ name: '' }" class="link ml-4" id="category">
          <h1>ABOUT</h1>
        </router-link>
        <router-link :to="{ name: 'Apart' }" class="link ml-4" id="category">
          <h1>APART</h1>
        </router-link>
        <router-link :to="{ name: 'News' }" class="link ml-4" id="category">
          <h1>NEWS</h1>
        </router-link>
        <router-link :to="{ name: 'Notice' }" class="link ml-4" id="category">
          <h1>NOTICE</h1>
        </router-link>
        <router-link :to="{ name: 'QnA' }" class="link ml-4" id="category">
          <h1>Q&A</h1>
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

        <router-link :to="{ name: 'Register' }" class="link">
          <v-btn class="hidden-sm-and-down">
            회원가입
          </v-btn>
        </router-link>
        <router-link :to="{ name: 'Login' }" class="link">
          <v-btn class="hidden-sm-and-down ml-3">
            로그인
          </v-btn>
        </router-link>

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
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "CoreAppBar",

  computed: {
    ...mapGetters(["links"])
  },

  methods: {
    ...mapMutations(["toggleDrawer"]),
    onClick(e, item) {
      e.stopPropagation();

      if (item.to || !item.href) return;

      this.$vuetify.goTo(item.href.endsWith("!") ? 0 : item.href);
    }
  }
};
</script>

<style scoped>
#appbar {
  background: #0e3047;
}

#category {
  font-size: 9px;
  color: white;
  text-decoration-line: none;
}
</style>
