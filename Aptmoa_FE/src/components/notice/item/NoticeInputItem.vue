<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            disabled
            v-model="notice.userid"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="notice.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="notice.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeNotice, getNotice, modifyNotice } from "@/api/notice";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      notice: {
        noticeno: 0,
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
      getNotice(
        this.$route.params.noticeno,
        ({ data }) => {
          // this.notice.noticeno = data.notice.noticeno;
          // this.notice.userid = data.notice.userid;
          // this.notice.subject = data.notice.subject;
          // this.notice.content = data.notice.content;
          this.notice = data;
        },
        error => {
          console.log(error);
        }
      );
      this.isUserid = true;
    } else {
      this.notice.userid = this.userInfo.userid;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.notice.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
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
      this.$router.push({ name: "noticeList" });
    },
    registNotice() {
      writeNotice(
        {
          userid: this.notice.userid,
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
          userid: this.notice.userid,
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
