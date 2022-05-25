<template>
  <v-container class="bv-example-row mt-3 text-center">
    <v-row>
      <v-col>
        <apart-map></apart-map>
      </v-col>
      <v-col>
        <apart-search-bar></apart-search-bar>
        <apart-list />
      </v-col>
    </v-row>
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
    return {
      map: null
    };
  },

  mounted() {},
  methods: {}
};
</script>
<style scoped>
#map {
  width: 3000px;
  height: 1000px;
}
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
