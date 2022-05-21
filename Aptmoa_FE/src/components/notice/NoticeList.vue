<template>
  <b-container class="bv-example-row mt-3">
    <v-row>
      <v-col>
        <v-alert show><h1>공지사항 목록</h1></v-alert>
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col class="text-right">
        <v-btn color="primary" variant="outline-primary" @click="moveWrite()"
          >공지사항 쓰기</v-btn
        >
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="notices.length">
        <v-data-table
          :headers="headers"
          :items="notices"
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
      <v-col v-else class="text-center">공지사항이 없습니다.</v-col>
    </v-row>
  </b-container>
</template>

<script>
import { listNotice } from "@/api/notice.js";
import NoticeListItem from "@/components/notice/item/NoticeListItem";

export default {
  name: "BoardList",
  components: {
    NoticeListItem
  },
  data() {
    return {
      notices: [],
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
    listNotice(
      param,
      response => {
        // console.log(response.data);
        this.notices = response.data;
      },
      error => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticeRegister" });
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
