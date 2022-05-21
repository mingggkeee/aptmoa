<template>
  <b-tr>
    <b-td>{{ newsno }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'newsDetail', params: { newsno: newsno } }">{{
        subject
      }}</router-link>
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ newsurl }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
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
