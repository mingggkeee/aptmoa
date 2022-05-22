<template>
  <b-container v-if="aparts && aparts.length != 0" class="bv-example-row mt-3">
    <apart-list-item
      v-for="(apart, index) in aparts"
      :key="index"
      :apart="apart"
    />
    <b-pagination
      v-model="currentPage"
      :total-rows="pagination.rows"
      :per-page="pagination.perPage"
      first-text="First"
      prev-text="Prev"
      next-text="Next"
      last-text="Last"
      @change="searchApt"
    ></b-pagination>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <v-row>
      <v-col><v-alert show>주택 목록이 없습니다.</v-alert></v-col>
    </v-row>
  </b-container>
</template>

<script>
import ApartListItem from "@/components/apart/ApartListItem.vue";
import { tsImportEqualsDeclaration } from "@babel/types";
import { mapState, mapActions } from "vuex";

const apartStore = "apartStore";

export default {
  name: "ApartList",
  components: {
    ApartListItem
  },
  data() {
    return {
      currentPage: 1
    };
  },
  computed: {
    ...mapState(apartStore, ["aparts", "pagination"]),
    changePage: function() {
      console.log(this.currentPage);
      return this.currentPage;
    },
    // houses() {
    //   return this.$store.state.houses;
    // },
    methods: {
      ...mapActions(apartStore, ["getApartList"]),

      searchApt(pageNum) {
        console.log(pageNum);
        if (!this.pagination.gugunCode) alert("조회할 지역을 선택해주세요.");
        else if (!this.pagination.date) alert("조회할 년-월을 선택해주세요.");

        let page = pageNum;
        if (!pageNum) page = this.currentPage;
        const param = {
          page: page,
          date: this.pagination.date,
          rows: this.pagination.perPage,
          gugunCode: this.pagination.gugunCode
        };
        if (this.pagination.gugunCode) this.getApartList(param);
      }
    }
  }
};
</script>

<style></style>
