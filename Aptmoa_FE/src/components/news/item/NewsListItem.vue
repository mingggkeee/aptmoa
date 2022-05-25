<template>
  <v-tr>
    <v-td>{{ newsno }}</v-td>
    <v-th class="text-left">
      <router-link :to="{ name: 'newsDetail', params: { newsno: newsno } }">{{
        subject
      }}</router-link>
    </v-th>
    <v-td>{{ hit }}</v-td>
    <v-td>{{ newsurl }}</v-td>
    <v-td>{{ regtime | dateFormat }}</v-td>
  </v-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NewsListItem",
  props: {
    newsno: Number,
    newsurl: String,
    subject: String,
    hit: Number,
    regtime: String
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    }
  }
};
</script>

<style></style>
