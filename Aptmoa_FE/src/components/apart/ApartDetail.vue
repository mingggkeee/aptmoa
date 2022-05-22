<template>
  <b-container v-if="apart" class="bv-example-row">
    <v-row>
      <v-col
        ><h3>{{ apart.아파트 }}</h3></v-col
      >
    </v-row>
    <v-row class="mb-1 mt-1">
      <v-col
        ><v-img :src="require('@/assets/apt.jpg')" fluid-grow></v-img
      ></v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-alert show variant="primary"
          >아파트 이름 : {{ apart.아파트 }}
        </v-alert>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-alert show variant="info">법정동 : {{ apart.법정동 }} </v-alert>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-alert show variant="warning">층수 : {{ apart.층 }}층</v-alert>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-alert show variant="danger"
          >거래금액 :
          {{
            (parseInt(apart.거래금액.replace(",", "")) * 10000) | price
          }}원</v-alert
        >
      </v-col>
    </v-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const apartStore = "apartStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(apartStore, ["apart"])
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  }
};
</script>

<style></style>
