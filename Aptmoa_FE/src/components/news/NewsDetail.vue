<template>
  <b-container class="bv-example-row mt-3">
    <v-row class="mb-1">
      <v-col class="text-left">
        <v-btn variant="outline-primary" @click="listNews">목록</v-btn>
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col>
        <v-card>
          <div>
            <h3>제목 : {{ news.subject }}</h3>
          </div>
          <v-img max-height="300" max-width="300" :src="news.imgurl"></v-img>
          <div>
            <h3>내용 : {{ news.content }}</h3>
          </div>
          <div>
            <h3>작성일 : {{ news.regtime }}</h3>
          </div>
          <div>
            <h3>
              원문주소 :
              <a :href="news.newsurl">{{ news.newsurl }}</a>
            </h3>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </b-container>
</template>

<script>
import { getNews } from "@/api/news";
export default {
  name: "AptMoaNewsDetail",

  data() {
    return {
      news: {}
    };
  },
  computed: {
    message() {
      if (this.news.content) return this.news.content.split("\n").join("<br>");
      return "";
    }
  },
  created() {
    getNews(
      this.$route.params.newsno,
      response => {
        this.news = response.data;
      },
      error => {
        console.log("삭제시 에러발생!!", error);
      }
    );
  },
  methods: {
    listNews() {
      this.$router.push({ name: "newsList" });
    }
  }
};
</script>

<style scoped></style>
