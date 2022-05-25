<template>
  <v-container class="bv-example-row mt-3">
    <v-row class="mb-1">
      <v-col class="text-left">
        <v-btn color="primary" variant="outline-primary" @click="goCrawl"
          >오늘의 뉴스 가져오기</v-btn
        >
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="news.length">
        <v-data-table
          :headers="headers"
          :items="news"
          :items-per-page="8"
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
  </v-container>
</template>

<script>
import { listNews, newsCrawl } from "@/api/news.js";
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
      ],
      newstime: ""
    };
  },
  props: {
    newsno: Number,
    subject: String,
    regtime: String,
    type: { type: String }
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
    goCrawl() {
      const today = new Date();
      let year = today.getFullYear();
      let month = ("0" + (today.getMonth() + 1)).slice(-2);
      let day = ("0" + today.getDate()).slice(-2);

      let getNewsDate = year + month + day;
      console.log(getNewsDate);
      if (confirm("오늘의 뉴스를 크롤링하시겠습니까?")) {
        newsCrawl(
          (getNewsDate *= 1),
          ({ data }) => {
            console.log(data);
            let msg = "크롤링 중 문제가 발생하였습니다.";
            if (data === "success") {
              msg = "오늘의 뉴스를 가져오는 데 성공했습니다!";
            }
            alert(msg);
            this.$router.go();
          },
          error => {
            console.log(error);
          }
        );
      }
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
