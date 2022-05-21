<template>
  <v-row class="mb-1">
    <v-col style="text-align: left">
      <form @submit.prevent="submit">
        <v-text-field
          v-model="qna.subject"
          label="제목"
          required
        ></v-text-field>
        <v-textarea v-model="qna.content" label="내용" required></v-textarea>
      </form>

      <v-btn
        @click="registQna"
        variant="primary"
        class="m-1"
        v-if="this.type === 'register'"
        >작성</v-btn
      >
      <v-btn @click="modifyQna" variant="primary" class="m-1" v-else
        >수정</v-btn
      >
      <v-btn @click="onReset" variant="danger" class="m-1">초기화</v-btn>
    </v-col>
  </v-row>
</template>

<script>
import { writeQna, getQna, modifyQna } from "@/api/qna";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "QnaInputItem",
  data() {
    return {
      qna: {
        // 유저아이디 어떻게 가져오지?
        userId: "",
        subject: "",
        content: ""
      }
    };
  },
  props: {
    type: { type: String }
  },
  computed: {
    ...mapState(userStore, ["userInfo"])
  },
  created() {
    if (this.type === "modify") {
      getQna(
        this.$route.params.qnano,
        ({ data }) => {
          this.qna = data;
        },
        error => {
          console.log(error);
        }
      );
      this.isUserId = true;
    } else {
      this.qna.userId = this.userInfo.userId;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.qna.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
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
      // this.$router.push({ name: "qnaList" });
    },
    registQna() {
      writeQna(
        {
          userId: this.qna.userId,
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
          userId: this.qna.userId,
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
