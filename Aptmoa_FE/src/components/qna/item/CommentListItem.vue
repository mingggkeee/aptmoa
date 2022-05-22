<template>
  <b-tr>
    <b-td>{{ commentno }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'qnaDetail', params: { qnano: qnano } }">{{
        qnano
      }}</router-link>
    </b-th>
    <b-td>{{ content }}</b-td>
    <b-td>{{ userId }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "QnaListItem",
  props: {
    qnano: Number,
    userId: String,
    content: String,
    commentno: Number,
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
