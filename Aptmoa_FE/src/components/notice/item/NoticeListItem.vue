<template>
  <b-tr>
    <b-td>{{ noticeno }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'noticeDetail', params: { noticeno: noticeno } }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ userId }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    noticeno: Number,
    userId: String,
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
