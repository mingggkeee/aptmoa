<template>
  <v-container>
    <v-row class="mb-1">
      <v-col class="text-left">
        <v-btn variant="outline-primary" @click="listNews">목록</v-btn>
        <!-- </v-col>
      <v-col class="text-right">
        <v-btn
          variant="outline-info"
          size="sm"
          color="primary"
          @click="moveModifyNews"
          class="mr-2"
          >글수정</v-btn
        >
        <v-btn
          color="primary"
          variant="outline-danger"
          size="sm"
          @click="deleteNews"
          >글삭제</v-btn
        > -->
      </v-col>
    </v-row>
    <v-card elevation="10" outlined width="100%" class="mx-auto mt-10">
      <!-- <v-card-title>
        <span class="mr-2">Detail</span>
      </v-card-title> -->
      <v-card-text>
        <v-row>
          <v-col>
            <!-- <v-text-field label="No" readonly :value="news.newsno" /> -->
            <span id="news_num" class="float-left">{{ news.newsno }}번</span>
          </v-col>
        </v-row>
        <!-- <v-row> -->
        <v-row>
          <v-col>
            <!-- <v-text-field label="제목" readonly :value="news.subject" /> -->
            <span id="news_title" class="float-center">{{ news.subject }}</span>
          </v-col>
        </v-row>
        <!-- <v-row><br /><br /></v-row> -->
        <!-- <v-row> -->
        <v-col>
          <div class="float-center">
            <!-- <v-text-field
              label="작성 시간"
              readonly
              dense
              :value="news.regtime"
            /> -->
            <span id="news_regtime" clas="float-center">{{
              news.regtime
            }}</span>
          </div>
        </v-col>
        <!-- </v-row> -->
        <!-- </v-row> -->
        <v-row>
          <v-col align="center">
            <v-img :src="news.imgurl" height="250" width="300" align="center">
            </v-img>
          </v-col>
        </v-row>
        <v-row>
          <v-col align="right" class="mr-10">
            <v-btn :href="news.newsurl" target="_blank">
              <v-icon>mdi-alpha-n-circle</v-icon>
              <span>원문 보러가기</span>
            </v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-textarea
              label="내용"
              readonly
              dense
              :value="news.content"
              class="ml-10 mr-10"
              id="news_content"
              rows="15"
            />
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
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

<style scoped>
#news_title {
  font-weight: bold;
  font-size: 30px;
  color: black;
  margin-top: 10px;
}
#news_content {
  font-size: 13px;
}
</style>
