<template>
  <base-card dark>
    <v-img
      :src="require('@/assets/img/banner_city.png')"
      class="grey lighten-2"
      height="400"
      width="100%"
    >
      <v-row class="fill-height " align="center">
        <v-col>
          <p class="text-center" id="banner_title">
            Aptmoa
          </p>

          <div class="text-subtitle-2 text-center" id="banner_subtitle">
            나에게 맞는 아파트를 찾아보세요. <br />
            찾는 아파트의 주변 인프라 정보도 확인해보세요.
          </div>
        </v-col>
      </v-row>
    </v-img>
  </base-card>
</template>

<script>
export default {
  name: "HomeBanner"
};
</script>

<style>
#banner_title {
  font-family: "Changa One", cursive;
  font-size: 80px;
}
#banner_subtitle {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 20px;
}
</style>
