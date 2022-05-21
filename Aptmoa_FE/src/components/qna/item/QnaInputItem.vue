<template>
  <b-row>
    <b-col class="col-3"></b-col>
    <b-col class="col-6">
      <v-card class="text-center">
        <v-form>
          <v-col>
            <v-row class="ma-3">
              <v-text-field
                v-model="qna.subject"
                placeholder="제목"
                required
                id="userId"
                name="userId"
                @keyup="idcheck"
              ></v-text-field>
            </v-row>
            <v-row class="ma-3">
              <v-textarea
                v-model="qna.content"
                required
                placeholder="내용 입력..."
                rows="10"
                max-rows="15"
              ></v-textarea>
            </v-row>
            <div class="text-sm-center">
              <v-btn
                color="primary"
                elevation="2"
                class="m-1 ma-3"
                id="submitbtn"
                @click="onSubmit"
                v-if="this.type === 'register'"
                >글 작성</v-btn
              >
              <v-btn
                color="primary"
                elevation="2"
                class="m-1 ma-3"
                v-else
                @click="onSubmit"
                >글수정</v-btn
              >
              <v-btn
                color="second"
                elevation="2"
                class="m-1 ma-3"
                id="resetBrn"
                type="reset"
                >초기화</v-btn
              >
            </div>
          </v-col>
        </v-form>
      </v-card>
    </b-col>
  </b-row>
</template>

<script>
import { writeQna, getQna, modifyQna } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnaInputItem",
  data() {
    return {
      qna: {
        // 유저아이디 어떻게 가져오지?
        userid: "",
        subject: "",
        content: ""
      }
    };
  },
  props: {
    type: { type: String }
  },
  computed: {
    ...mapState(memberStore, ["userInfo"])
  },
  created() {
    if (this.type === "modify") {
      getQna(
        this.$route.params.qnano,
        ({ data }) => {
          // this.qna.qnano = data.qna.qnano;
          // this.qna.userid = data.qna.userid;
          // this.qna.subject = data.qna.subject;
          // this.qna.content = data.qna.content;
          this.qna = data;
        },
        error => {
          console.log(error);
        }
      );
      this.isUserid = true;
    } else {
      this.qna.userid = this.userInfo.userid;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.qna.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.qna.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.qna.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registQna() : this.modifyQna();
    },
    onReset(event) {
      event.preventDefault();
      this.qna.qnano = 0;
      this.qna.subject = "";
      this.qna.content = "";
      this.$router.push({ name: "qnaList" });
    },
    registQna() {
      writeQna(
        {
          userid: this.qna.userid,
          subject: this.qna.subject,
          content: this.qna.content
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        error => {
          console.log(error);
        }
      );
    },
    modifyQna() {
      modifyQna(
        {
          qnano: this.qna.qnano,
          userid: this.qna.userid,
          subject: this.qna.subject,
          content: this.qna.content
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "qnaList" });
        },
        error => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "qnaList" });
    }
  }
};
</script>

<style></style>
