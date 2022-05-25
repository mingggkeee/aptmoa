<template>
  <v-container>
    <v-row>
      <v-col>
        <h3 id="category_title">Member Management</h3>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="users.length">
        <v-data-table
          :headers="headers"
          :items="users"
          :items-per-page="10"
          item-key="subject"
          class="elevation-1"
          @click:row="removeUser"
        ></v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { getUserList } from "@/api/user.js";
import httpCommon from "../../util/http-common";

export default {
  name: "ManageUser",

  data() {
    return {
      users: [],
      headers: [
        { text: "아이디", value: "userId" },
        { text: "이름", value: "userName" },
        { text: "이메일", value: "email" },
        { text: "가입일", value: "joindate" },
        { text: "비밀번호", value: "userPwd" }
      ]
    };
  },

  created() {
    getUserList(
      response => {
        this.users = response.data;
      },
      error => {
        console.log(error);
      }
    );
  },

  methods: {
    removeUser(value) {
      if (confirm("정말로 탈퇴시키겠습니까?")) {
        httpCommon.delete(`/user/${value.userId}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "탈퇴처리가 완료되었습니다.";
          }
          alert(msg);
          this.$router.go();
        });
      }
    }
  }
};
</script>

<style scoped></style>
