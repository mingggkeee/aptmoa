<template>
  <b-container class="bv-example-row mt-3">
    <v-row class="mb-1">
      <v-col class="text-right">
        <v-btn color="primary" variant="outline-primary" @click="moveWrite()"
          >Q&A쓰기</v-btn
        >
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="qnas.length">
        <v-data-table
          :headers="headers"
          :items="qnas"
          :items-per-page="10"
          item-key="subject"
          class="elevation-1"
          @click:row="goDetail"
        ></v-data-table>
      </v-col>
      <v-col v-else class="text-center">Q&A 목록이 없습니다.</v-col>
    </v-row>
  </b-container>
</template>

<script>
import { listQna } from "@/api/qna.js";
import QnaListItem from "@/components/qna/item/QnaListItem";

export default {
  name: "QnaList",
  components: {
    QnaListItem
  },
  props: {
    qnano: Number,
    userId: String,
    subject: String,
    hit: Number,
    regtime: String
  },
  data() {
    return {
      qnas: [],
      headers: [
        { text: "번호", value: "qnano" },
        { text: "제목", value: "subject" },
        { text: "내용", value: "content" }
      ]
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null
    };
    listQna(
      param,
      response => {
        // console.log(response.data);
        this.qnas = response.data;
      },
      error => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnaRegister" });
    },
    goDetail(value) {
      this.$router.push({
        name: "qnaDetail",
        params: { qnano: value.qnano }
      });
    }
  }
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
