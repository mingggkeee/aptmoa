<template>
  <b-container class="bv-example-row mt-3">
    <v-row class="mb-1">
      <v-col class="text-left">
        <v-btn variant="outline-primary" @click="newsCrawl"
          >오늘의 뉴스 가져오기</v-btn
        >
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-alert show><h1>뉴스 목록</h1></v-alert>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="news.length">
        <v-data-table
          :headers="headers"
          :items="news"
          :items-per-page="10"
          item-key="subject"
          class="elevation-1"
          sort-by="regtime"
          :sort-desc="true"
          @click:row="goDetail"
        >
          >
          <v-toolbar-title>My CRUD</v-toolbar-title>
        </v-data-table>
      </v-col>
      <v-col v-else class="text-center">뉴스가 없습니다.</v-col>
    </v-row>
  </b-container>
</template>

<script>
import { listNews } from "@/api/news.js";
import NewsListItem from "@/components/news/item/NewsListItem";
import moment from "moment";

export default {
  name: "BoardList",
  components: {
    NewsListItem
  },
  data() {
    return {
      news: [],
      headers: [
        { text: "날짜", value: "regtime" },
        { text: "제목", value: "subject" }
        // { text: "내용", value: "content" }
      ]
    };
  },
  props: {
    newsno: Number,
    subject: String,
    regtime: String
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null
    };
    listNews(
      param,
      response => {
        console.log(response);
        this.news = response.data;
      },
      error => {
        console.log(error);
      }
    );
  },
  methods: {
    goDetail(value) {
      this.$router.push({
        name: "newsDetail",
        params: { newsno: value.newsno }
      });
    },
    newsCrawl() {
      const today = new Date();
      let year = today.getFullYear();
      let month = ("0" + (today.getMonth() + 1)).slice(-2);
      let day = ("0" + today.getDate()).slice(-2);

      const getNewsDate = year + month + day;
      console.log(getNewsDate);
    }
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
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
