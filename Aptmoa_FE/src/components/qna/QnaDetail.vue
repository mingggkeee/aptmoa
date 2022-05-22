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
    <h2 class="underline-hotpink"><b-icon icon="journals"></b-icon>댓글</h2>
    <v-row>
      <v-col v-if="comments.length">
        <v-data-table
          :headers="headers"
          :items="comments"
          :items-per-page="5"
          item-key="subject"
          class="elevation-1"
          @click:row="deleteComment"
        ></v-data-table>
      </v-col>
      <v-col v-else class="text-center">댓글 목록이 없습니다.</v-col>
    </v-row>
    <v-row>
      <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn color="primary" dark v-bind="attrs" v-on="on">
            댓글 작성하기
          </v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="text-h5">댓글 작성</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col>
                  <v-textarea
                    v-model="comment.content"
                    label="댓글을 작성해주세요."
                    required
                  ></v-textarea>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">
              닫기
            </v-btn>
            <v-btn color="blue darken-1" text @click="commentSave">
              댓글 저장
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getQna, deleteQna } from "@/api/qna";
import { listComment, writeComment, deleteComment } from "@/api/comment";
import { mapState } from "vuex";

const userStore = "userStore";
import CommentListItem from "@/components/qna/item/CommentListItem";

export default {
  name: "QnaDetail",
  components: {
    CommentListItem
  },
  props: {
    commentno: Number,
    userId: String,
    content: String,
    qnano: Number,
    regtime: String
  },
  data() {
    return {
      qna: {},
      comments: [],
      comment: {
        userId: "",
        qnano: "",
        content: ""
      },
      headers: [
        { text: "번호", value: "commentno" },
        { text: "ID", value: "userId" },
        { text: "내용", value: "content" },
        { text: "작성시간", value: "regtime" }
      ],
      dialog: false
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.qna.content) return this.qna.content.split("\n").join("<br>");
      return "";
    }
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null
    };
    getQna(
      this.$route.params.qnano,
      response => {
        this.qna = response.data;
      },
      error => {
        console.log("삭제시 에러발생!!", error);
      }
    );
    listComment(
      this.$route.params.qnano,
      response => {
        this.comments = response.data;
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
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteQna(this.qna.qnano, () => {
          this.$router.push({ name: "qnaList" });
        });
      }
    },
    deleteComment(value) {
      console.log(value.commentno);
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteComment(value.commentno, () => {
          this.$router.push({ name: "deleteComment" });
        });
        this.listQna();
      }
      // this.$router.push({
      //   name: "deleteComment",
      //   params: { commentno: value.commentno }
      // });
    },
    commentSave() {
      console.log("댓글저장");
      writeComment(
        {
          userId: this.userInfo.userId,
          qnano: this.qna.qnano,
          content: this.comment.content
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
        }
      );
      this.dialog = false;
    }
  }
};
</script>

<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
