<template>
  <v-tr>
    <v-td>{{ qnano }}</v-td>
    <v-th class="text-left">
      <router-link :to="{ name: 'qnaDetail', params: { qnano: qnano } }">{{
        subject
      }}</router-link>
    </v-th>
    <v-td>{{ hit }}</v-td>
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
