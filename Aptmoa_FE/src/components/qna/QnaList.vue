<template>
  <b-container class="bv-example-row mt-3">
    <v-row>
      <v-col>
        <v-alert show><h1>Q&A목록</h1></v-alert>
      </v-col>
    </v-row>
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
          :items-per-page="5"
          item-key="subject"
          class="elevation-1"
          :footer-props="{
            showFirstLastPage: true,
            firstIcon: 'mdi-arrow-collapse-left',
            lastIcon: 'mdi-arrow-collapse-right',
            prevIcon: 'mdi-minus',
            nextIcon: 'mdi-plus'
          }"
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
  name: "BoardList",
  components: {
    QnaListItem
  },
  data() {
    return {
      qnas: [],
      headers: [
        {
          text: "제목",
          align: "start",
          value: "subject"
        },
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
