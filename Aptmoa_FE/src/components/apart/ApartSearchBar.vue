<template>
  <v-row class="mt-4 mb-4 text-center">
    <v-col class="sm-3">
      <v-select
        v-model="sidoCode"
        :items="sidos"
        @change="gugunList"
      ></v-select>
    </v-col>
    <v-col class="sm-3">
      <v-select v-model="gugunCode" :items="guguns" label="지역"></v-select>
    </v-col>
    <v-col class="sm-3">
      <v-select v-model="year" :items="years" label="Year"></v-select>
    </v-col>
    <v-col class="sm-3">
      <v-select v-model="month" :items="months" label="Month"></v-select>
    </v-col>
    <v-col class="text-right">
      <v-btn variant="outline-primary" @click="searchApt">조회</v-btn>
    </v-col>
  </v-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import eventBus from "./eventBus";
const apartStore = "apartStore";

export default {
  name: "ApartSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      year: "null",
      month: null,
      date: null,
      datanum: 100,
      years: [
        { value: 2022, text: "2022년" },
        { value: 2021, text: "2021년" },
        { value: 2020, text: "2020년" },
        { value: 2019, text: "2019년" },
        { value: 2022, text: "2018년" },
        { value: 2017, text: "2017년" },
        { value: 2016, text: "2016년" },
        { value: 2015, text: "2015년" },
        { value: 2014, text: "2014년" },
        { value: 2013, text: "2013년" },
        { value: 2012, text: "2012년" },
        { value: 2011, text: "2011년" },
        { value: 2010, text: "2010년" },
        { value: 2009, text: "2009년" },
        { value: 2008, text: "2008년" },
        { value: 2007, text: "2007년" },
        { value: 2006, text: "2006년" }
      ],
      months: [
        { value: "01", text: "1월" },
        { value: "02", text: "2월" },
        { value: "03", text: "3월" },
        { value: "04", text: "4월" },
        { value: "05", text: "5월" },
        { value: "06", text: "6월" },
        { value: "07", text: "7월" },
        { value: "08", text: "8월" },
        { value: "09", text: "9월" },
        { value: 10, text: "10월" },
        { value: 11, text: "11월" },
        { value: 12, text: "12월" }
      ]
    };
  },
  computed: {
    ...mapState(apartStore, ["sidos", "guguns"])
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(apartStore, ["getSido", "getGugun", "getApartList"]),
    ...mapMutations(apartStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      eventBus.$emit("getMap", param);

      console.log(this.gugunCode);
      if (!this.sidoCode || !this.gugunCode)
        alert("조회할 지역을 선택해주세요.");
      else if (!this.year || !this.month) alert("조회할 년-월을 선택해주세요.");
      let date = this.year + this.month;
      console.log(date);
      console.log(this.datanum);
      const param = {
        page: 1,
        date: date,
        rows: this.datanum,
        gugunCode: this.gugunCode
      };
      if (this.gugunCode) this.getApartList(param);
    }
  }
};
</script>

<style></style>
