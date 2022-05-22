<template>
  <b-row class="mt-4 mb-4 text-center">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
    <v-col class="sm-3">
      <v-select
        v-model="sidoCode"
        :items="sidos"
        @change="gugunList"
      ></v-select>
    </v-col>
    <v-col class="sm-3">
      <v-select
        v-model="gugunCode"
        :items="guguns"
        @change="searchApt"
      ></v-select>
    </v-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    userStore: userStore,
    apartStore: apartStore
  }  
*/
const apartStore = "apartStore";

export default {
  name: "ApartSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null
    };
  },
  computed: {
    ...mapState(apartStore, ["sidos", "guguns", "aparts"])
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(apartStore, ["getSido", "getGugun", "getApartList"]),
    ...mapMutations(apartStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      if (this.gugunCode) this.getApartList(this.gugunCode);
    }
  }
};
</script>

<style></style>
