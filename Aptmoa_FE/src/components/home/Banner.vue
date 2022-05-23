<template>
  <base-card dark>
    <v-img
      :src="require('@/assets/img/banner_city.png')"
      class="grey lighten-2"
      height="400"
      width="100%"
    >
      <v-row class="fill-height pa-3" align="center">
        <v-col cols="12" md="7" offset-md="5">
          <h1 class="display-3 font-weight-bold">
            Aptmoa
          </h1>

          <div class="subheading pl-2 pt-5">
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
