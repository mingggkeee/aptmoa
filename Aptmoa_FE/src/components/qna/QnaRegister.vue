<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h1>Q&A작성</h1></b-alert>
      </b-col>
    </b-row>
    <qna-input-item type="register" />
  </b-container>
</template>

<script>
import QnaInputItem from "@/components/qna/item/QnaInputItem.vue";

export default {
  name: "QnaWrite",
  components: {
    QnaInputItem
  }
};
</script>

<style></style>
