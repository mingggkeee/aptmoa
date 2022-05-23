<template>
  <b-container v-if="aparts && aparts.length != 0" class="bv-example-row mt-3">
    <apart-list-item
      v-for="(apart, index) in aparts"
      :key="index"
      :apart="apart"
    />
    <v-data-table
      :headers="headers"
      :items="aparts"
      :items-per-page="15"
      class="elevation-1"
      @click:row="selectApart"
    ></v-data-table>
  </b-container>
  <b-container
    v-else-if="nameApts && nameApts.length != 0"
    class="bv-example-row mt-3"
  >
    <v-data-table
      :headers="headers2"
      :items="nameApts"
      :items-per-page="15"
      class="elevation-1"
      @click:row="selectApart2"
    ></v-data-table>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <v-row>
      <v-col><v-alert show>주택 목록이 없습니다.</v-alert></v-col>
    </v-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const apartStore = "apartStore";

export default {
  name: "ApartList",
  data() {
    return {
      currentPage: 1,
      headers: [
        { text: "법정동", value: "법정동" },
        { text: "거래년도", value: "년" },
        { text: "거래월", value: "월" },
        { text: "이름", value: "아파트" },
        { text: "거래금액", value: "거래금액" },
        { text: "전용면적", value: "전용면적" }
      ],
      headers2: [
        { text: "법정구", value: "gugunName" },
        { text: "법정동", value: "dong" },
        // { text: "거래월", value: "월" },
        { text: "이름", value: "apartmentName" },
        { text: "거래금액", value: "recentPrice" }
        // { text: "전용면적", value: "전용면적" }
      ]
    };
  },
  computed: {
    ...mapState(apartStore, ["aparts", "nameApts"]),
    methods: {
      ...mapActions(apartStore, ["getApartList", "getApartListByName"])
    }
  },
  methods: {
    ...mapActions(apartStore, ["detailApart", "detailApart2"]),
    selectApart(value) {
      // console.log(value);
      this.detailApart(value);
    },
    selectApart2(value) {
      // console.log(value);
      this.detailApart2(value);
    }
  }
};
</script>

<style></style>
