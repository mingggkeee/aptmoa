<template>
  <b-container class="bv-example-row mt-3">
    <v-row class="mb-1">
      <v-col class="text-right">
        <v-btn color="primary" variant="outline-primary" @click="moveWrite()"
          >공지사항 쓰기</v-btn
        >
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="notices.length">
        <v-data-table
          :headers="headers"
          :items="notices"
          :items-per-page="10"
          item-key="subject"
          class="elevation-1"
          @click:row="goDetail"
        ></v-data-table>
      </v-col>
      <v-col v-else class="text-center">공지사항이 없습니다.</v-col>
    </v-row>
  </b-container>
</template>

<script>
import { listNotice } from "@/api/notice.js";
import NoticeListItem from "@/components/notice/item/NoticeListItem";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardList",
  components: {
    NoticeListItem
  },
  data() {
    return {
      notices: [],
      headers: [
        { text: "번호", value: "noticeno" },
        { text: "제목", value: "subject" },
        { text: "내용", value: "content" }
      ]
    };
  },
  props: {
    noticeno: Number,
    userId: String,
    subject: String,
    hit: Number,
    regtime: String
  },
  computed: {
    ...mapState(userStore, ["userInfo"])
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null
    };
    listNotice(
      param,
      response => {
        // console.log(response.data);
        this.notices = response.data;
      },
      error => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      // alert(this.userInfo.userId);
      if (this.userInfo.userId === "admin") {
        this.$router.push({ name: "noticeRegister" });
      } else {
        alert("관리자만 작성가능합니다.");
      }
    },
    goDetail(value) {
      this.$router.push({
        name: "noticeDetail",
        params: { noticeno: value.noticeno }
      });
    }
  }
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
