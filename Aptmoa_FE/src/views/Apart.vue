<template>
  <v-container class="bv-example-row mt-3 text-center">
    <h3 id="category_title">
      APART SERVICE
    </h3>
    <v-container>
      <v-row>
        <apart-search-bar></apart-search-bar>
        <apart-list />
      </v-row>
    </v-container>
    <v-container>
      <apart-map></apart-map>
    </v-container>
  </v-container>
</template>

<script>
import ApartSearchBar from "@/components/apart/ApartSearchBar.vue";
import ApartList from "@/components/apart/ApartList.vue";
import ApartDetail from "@/components/apart/ApartDetail.vue";
import ApartDetail2 from "@/components/apart/ApartDetail2.vue";
import ApartMap from "@/components/apart/ApartMap.vue";

export default {
  name: "ApartView",
  components: {
    ApartSearchBar,
    ApartList,
    ApartDetail,
    ApartDetail2,
    ApartMap
  },
  data() {
    return {};
  },

  mounted() {},
  methods: {}
};
</script>

<style scoped>
#category_title {
  font-family: "Changa One", cursive;
  font-size: 50px;
  background: #f2f2f2;
  color: #364a73;
}
</style>
