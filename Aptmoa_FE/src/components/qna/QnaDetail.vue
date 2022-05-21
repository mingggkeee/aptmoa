<template>
  <b-container class="bv-example-row mt-3">
    <v-row>
      <v-col>
        <v-alert show
          ><h1>{{ qna.qnano }}번 글보기</h1></v-alert
        >
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col class="text-left">
        <v-btn variant="outline-primary" @click="listQna">목록</v-btn>
      </v-col>
      <v-col class="text-right">
        <v-btn
          variant="outline-info"
          size="sm"
          color="primary"
          @click="moveModifyQna"
          class="mr-2"
          >글수정</v-btn
        >
        <v-btn
          color="primary"
          variant="outline-danger"
          size="sm"
          @click="deleteQna"
          >글삭제</v-btn
        >
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col>
        <v-card>
          <div>
            <h3>작성자 : {{ qna.userId }}</h3>
          </div>
          <div>
            <h3>제목 : {{ qna.subject }}</h3>
          </div>
          <div>
            <h3>내용 : {{ qna.content }}</h3>
          </div>
          <div>
            <h3>작성일 : {{ qna.regtime }}</h3>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getQna, deleteQna } from "@/api/qna";

export default {
  name: "QnaDetail",
  data() {
    return {
      qna: {}
    };
  },
  computed: {
    message() {
      if (this.qna.content) return this.qna.content.split("\n").join("<br>");
      return "";
    }
  },
  created() {
    getQna(
      this.$route.params.qnano,
      response => {
        this.qna = response.data;
      },
      error => {
        console.log("삭제시 에러발생!!", error);
      }
    );
  },
  methods: {
    listQna() {
      this.$router.push({ name: "qnaList" });
    },
    moveModifyQna() {
      this.$router.replace({
        name: "qnaModify",
        params: { qnano: this.qna.qnano }
      });
      //   this.$router.push({ path: `/qna/modify/${this.qna.qnano}` });
    },
    deleteQna() {
      if (confirm("정말로 삭제?")) {
        deleteQna(this.qna.qnano, () => {
          this.$router.push({ name: "qnaList" });
        });
      }
    }
  }
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
