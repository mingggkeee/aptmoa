<template>
  <b-container v-if="aparts && aparts.length != 0" class="bv-example-row mt-3">
    <v-data-table
      :headers="headers"
      :items="aparts"
      :items-per-page="15"
      class="elevation-1"
      @click:row="selectApart"
    ></v-data-table>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <v-row>
      <v-col><v-alert show>주택 목록이 없습니다.</v-alert></v-col>
    </v-row>
  </b-container>
</template>

<script>
import ApartListItem from "@/components/apart/ApartListItem.vue";
import { mapState, mapActions } from "vuex";

const apartStore = "apartStore";

export default {
  name: "ApartList",
  components: {
    ApartListItem
  },
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
      ]
    };
  },
  computed: {
    ...mapState(apartStore, ["aparts"]),
    methods: {
      ...mapActions(apartStore, ["getApartList"]),

      searchApt(pageNum) {
        console.log(pageNum);
        if (!this.pagination.gugunCode) alert("조회할 지역을 선택해주세요.");
        else if (!this.pagination.date) alert("조회할 년-월을 선택해주세요.");
      }
    }
  },
  methods: {
    ...mapActions(apartStore, ["detailApart"]),
    selectApart(value) {
      // console.log(value);
      this.detailApart(value);
    }
  }
};
</script>

<style></style>
