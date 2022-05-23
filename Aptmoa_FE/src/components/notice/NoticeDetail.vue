<template>
  <!-- <b-container class="bv-example-row mt-3">
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
  </b-container> -->
  <v-container>
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
    <v-card elevation="10" outlined width="100%" class="mx-auto">
      <v-card-title>
        <span class="mr-2">Detail</span>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col>
            <v-text-field label="제목" readonly :value="notice.subject" />
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field
              label="작성자"
              readonly
              dense
              :value="notice.userId"
            />
          </v-col>
          <v-col>
            <v-text-field
              label="작성 시간"
              readonly
              dense
              :value="notice.regtime"
            />
          </v-col>
          <!-- <v-col>
            <v-text-field label="View" readonly dense :value="notice.hit" />
          </v-col> -->
        </v-row>
        <v-row>
          <v-col>
            <v-text-field label="내용" readonly dense :value="notice.content" />
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
// import moment from "moment";
import { getNotice, deleteNotice } from "@/api/notice";
import { mapState } from "vuex";

const userStore = "userStore";

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
    },
    ...mapState(userStore, ["userInfo"])
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
      if (this.userInfo.userId === "admin") {
        this.$router.replace({
          name: "noticeModify",
          params: { noticeno: this.notice.noticeno }
        });
      } else {
        alert("관리자만 수정 가능합니다.");
      }

      //   this.$router.push({ path: `/notice/modify/${this.notice.noticeno}` });
    },
    deleteNotice() {
      if (this.userInfo.userId === "admin") {
        if (confirm("정말로 삭제하시겠습니까?")) {
          deleteNotice(this.notice.noticeno, () => {
            this.$router.push({ name: "noticeList" });
          });
        }
      } else {
        alert("관리자만 삭제 가능합니다.");
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
