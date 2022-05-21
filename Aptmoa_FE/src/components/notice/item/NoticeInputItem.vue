<template>
  <v-row class="mb-1">
    <v-col style="text-align: left">
      <form @submit.prevent="submit">
        <v-text-field
          v-model="notice.subject"
          label="제목"
          required
        ></v-text-field>
        <v-textarea v-model="notice.content" label="내용" required></v-textarea>
      </form>

      <v-btn
        @click="registNotice"
        variant="primary"
        class="m-1"
        v-if="this.type === 'register'"
        >작성</v-btn
      >
      <v-btn @click="modifyNotice" variant="primary" class="m-1" v-else
        >수정</v-btn
      >
      <v-btn @click="onReset" variant="danger" class="m-1">초기화</v-btn>
    </v-col>
  </v-row>
</template>

<script>
import { writeNotice, getNotice, modifyNotice } from "@/api/notice";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      notice: {
        noticeno: 0,
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
      getNotice(
        this.$route.params.noticeno,
        ({ data }) => {
          this.notice = data;
        },
        error => {
          console.log(error);
        }
      );
      this.isUserId = true;
    } else {
      this.notice.userId = this.userInfo.userId;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.notice.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.notice.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.notice.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registNotice() : this.modifyNotice();
    },
    onReset(event) {
      event.preventDefault();
      this.notice.noticeno = 0;
      this.notice.subject = "";
      this.notice.content = "";
      // this.$router.push({ name: "noticeList" });
    },
    registNotice() {
      writeNotice(
        {
          userId: this.notice.userId,
          subject: this.notice.subject,
          content: this.notice.content
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
    modifyNotice() {
      modifyNotice(
        {
          noticeno: this.notice.noticeno,
          userId: this.notice.userId,
          subject: this.notice.subject,
          content: this.notice.content
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "noticeList" });
        },
        error => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    }
  }
};
</script>

<style></style>
