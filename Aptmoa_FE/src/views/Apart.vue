<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h2 class="underline-orange">
      <b-icon icon="journals"></b-icon> 아파트 조회
    </h2>
    <v-row>
      <v-col>
        <apart-search-bar></apart-search-bar>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="6" align="left">
        <apart-list />
      </v-col>
      <v-col cols="6">
        <apart-detail />
      </v-col>
    </v-row>
  </b-container>
</template>

<script>
import ApartSearchBar from "@/components/apart/ApartSearchBar.vue";
import ApartList from "@/components/apart/ApartList.vue";
import ApartDetail from "@/components/apart/ApartDetail.vue";

export default {
  name: "ApartView",
  components: {
    ApartSearchBar,
    ApartList,
    ApartDetail
  }
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
