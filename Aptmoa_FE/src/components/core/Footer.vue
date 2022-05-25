<template>
  <v-footer class="py-4" dark height="auto" id="footer">
    <v-container class="mx-auto">
      <v-row>
        <v-col cols="5">
          <base-btn
            v-for="(item, i) in items"
            :key="i"
            :href="item.href"
            class="ml-0 mr-3"
            color="primary"
            square
            target="_blank"
          >
            <v-icon v-text="item.icon" />
          </base-btn>
        </v-col>

        <v-col cols="6">
          <div id="logo2">
            © 2022.
            <v-img
              :src="require('@/assets/logo2.jpg')"
              class="mr-5"
              contain
              height="50"
              width="50"
              max-width="100"
            />
            all rights reserved.
          </div>
        </v-col>

        <v-spacer />

        <base-btn
          class="mr-0"
          square
          title="Go to top"
          @click="$vuetify.goTo(0)"
        >
          <v-icon>mdi-chevron-up</v-icon>
        </base-btn>
      </v-row>
    </v-container>
  </v-footer>
</template>

<script>
export default {
  name: "CoreFooter",

  data: () => ({
    items: [
      {
        href: "https://twitter.com/?lang=ko",
        icon: "mdi-twitter"
      },
      {
        href: "https://www.instagram.com/",
        icon: "mdi-instagram"
      },
      {
        href: "https://www.facebook.com/",
        icon: "mdi-facebook"
      },
      {
        href: "https://www.reddit.com/",
        icon: "mdi-reddit"
      },
      {
        href: "https://discord.com/",
        icon: "mdi-discord"
      },
      {
        href: "https://www.pinterest.co.kr/",
        icon: "mdi-pinterest"
      }
    ]
  })
};
</script>

<style scoped>
#footer {
  background: #0e3047;
  font-family: "Noto Sans KR", sans-serif;
}
#logo2 {
  display: flex;
  font-size: 30px;
  font-family: "'Noto Sans KR', sans-serif;";
}
</style>
