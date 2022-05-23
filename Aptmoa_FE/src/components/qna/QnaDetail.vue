<template>
  <v-container>
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
    <v-card elevation="10" outlined width="100%" class="mx-auto">
      <v-card-title>
        <span class="mr-2">Detail</span>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col>
            <v-text-field label="제목" readonly :value="qna.subject" />
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field label="작성자" readonly dense :value="qna.userId" />
          </v-col>
          <v-col>
            <v-text-field
              label="작성 시간"
              readonly
              dense
              :value="qna.regtime"
            />
          </v-col>
          <!-- <v-col>
            <v-text-field label="View" readonly dense :value="qna.hit" />
          </v-col> -->
        </v-row>
        <v-row>
          <v-col>
            <v-text-field label="내용" readonly dense :value="qna.content" />
          </v-col>
        </v-row>
        Reply ({{ comments.length }})<br />
        <v-simple-table dense>
          <tbody>
            <tr v-for="(comment, index) in comments" :key="index">
              <td style="width:110px; padding: 0;" v-if="comments.length">
                <v-icon small>
                  mdi-account
                </v-icon>
                {{ comment.userId }}
              </td>
              <td style="width:40px; padding: 0;" v-else>
                <Tooltip
                  bottom
                  iconName="mdi-account"
                  title="작성자"
                  :content="userInfo.userId"
                />
              </td>
              <td style="padding: 0;">{{ comment.content }}</td>
              <td style="width:140px; padding: 0;" v-if="comments.length">
                {{ comment.regtime }}
              </td>
              <td style="width:40px; padding: 0;" v-else>
                <Tooltip
                  bottom
                  iconName="mdi-clock-outline"
                  title="작성일시"
                  :content="comment.regtime"
                />
              </td>
              <td style="width:30px; padding: 0;">
                <v-btn
                  depressed
                  small
                  color="warning"
                  variant="outline-danger"
                  size="xsm"
                  @click="deleteComment(comment.commentno, $event)"
                  >댓글삭제</v-btn
                >
              </td>
            </tr>
          </tbody>
        </v-simple-table>
        <v-divider></v-divider>
        <v-row>
          <v-col cols="12" md="11" style="padding: 0px 12px;">
            <v-textarea
              clearable
              clear-icon="mdi-close-circle"
              rows="2"
              no-resize
              full-width
              v-model="comment.content"
            ></v-textarea>
          </v-col>
          <v-col md="1" align-self="center" style="padding: 0px 10px;">
            <v-btn
              color="primary"
              variant="outline-danger"
              size="sm"
              @click="commentSave('comment.commentno')"
              >댓글 작성</v-btn
            >
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <!-- <v-row> <br /><br /> </v-row>
    <h2 class="underline-hotpink">댓글</h2>
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
    </v-row> -->
  </v-container>
  <!-- </b-container> -->
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
      console.log(value);
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteComment(value, () => {
          this.$router.push({ name: "deleteComment" });
        });
        this.$router.go();
      }
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
          this.$router.go();
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
