<template>
  <v-tr>
    <v-td>{{ commentno }}</v-td>
    <v-th class="text-left">
      <router-link :to="{ name: 'qnaDetail', params: { qnano: qnano } }">{{
        qnano
      }}</router-link>
    </v-th>
    <v-td>{{ content }}</v-td>
    <v-td>{{ userId }}</v-td>
    <v-td>{{ regtime | dateFormat }}</v-td>
  </v-tr>
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
