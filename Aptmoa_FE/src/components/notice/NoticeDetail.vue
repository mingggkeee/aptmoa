<template>
  <b-container class="bv-example-row mt-3">
    <v-row>
      <v-col>
        <v-alert show
          ><h1>{{ notice.noticeno }}번 글보기</h1></v-alert
        >
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col class="text-left">
        <v-btn variant="outline-primary" @click="listNotice">목록</v-btn>
      </v-col>
      <v-col class="text-right">
        <v-btn
          variant="outline-info"
          size="sm"
          color="primary"
          @click="moveModifyNotice"
          class="mr-2"
          >글수정</v-btn
        >
        <v-btn
          color="primary"
          variant="outline-danger"
          size="sm"
          @click="deleteNotice"
          >글삭제</v-btn
        >
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col>
        <v-card>
          <div>
            <h3>작성자 : {{ notice.userId }}</h3>
          </div>
          <div>
            <h3>제목 : {{ notice.subject }}</h3>
          </div>
          <div>
            <h3>내용 : {{ notice.content }}</h3>
          </div>
          <div>
            <h3>작성일 : {{ notice.regtime }}</h3>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getNotice, deleteNotice } from "@/api/notice";

export default {
  name: "NoticeDetail",
  data() {
    return {
      notice: {}
    };
  },
  computed: {
    message() {
      if (this.notice.content)
        return this.notice.content.split("\n").join("<br>");
      return "";
    }
  },
  created() {
    getNotice(
      this.$route.params.noticeno,
      response => {
        this.notice = response.data;
      },
      error => {
        console.log("삭제시 에러발생!!", error);
      }
    );
  },
  methods: {
    listNotice() {
      this.$router.push({ name: "noticeList" });
    },
    moveModifyNotice() {
      this.$router.replace({
        name: "noticeModify",
        params: { noticeno: this.notice.noticeno }
      });
      //   this.$router.push({ path: `/notice/modify/${this.notice.noticeno}` });
    },
    deleteNotice() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteNotice(this.notice.noticeno, () => {
          this.$router.push({ name: "noticeList" });
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
