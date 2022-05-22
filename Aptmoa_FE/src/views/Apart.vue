<template>
  <b-container class="bv-example-row mt-3 text-center">
    <v-row>
      <v-container id="map"></v-container>
    </v-row>
    <v-row>
      <v-col cols="100">
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
  },
  data() {
    return {
      map: null
    };
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
      script.addEventListener("load", () => {
        console.log("loaded", window.kakao);
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      console.log("이미 로딩 완료: ", window.kakao);
      this.initMap();
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      console.log(container);
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      };
      this.map = new kakao.maps.Map(container, options);
    }
  }
};
</script>
<style scoped>
#map {
  width: 3000px;
  height: 1000px;
}
</style>
