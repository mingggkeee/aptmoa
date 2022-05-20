<template>
  <b-container v-if="aparts && aparts.length != 0" class="bv-example-row mt-3">
    <apart-list-item
      v-for="(apart, index) in aparts"
      :key="index"
      :apart="apart"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import ApartListItem from "@/components/apart/ApartListItem.vue";
import { mapState } from "vuex";

const apartStore = "apartStore";

export default {
  name: "ApartList",
  components: {
    ApartListItem
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(apartStore, ["aparts"])
    // houses() {
    //   return this.$store.state.houses;
    // },
  }
};
</script>

<style></style>
