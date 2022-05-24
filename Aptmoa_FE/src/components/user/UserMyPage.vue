<template>
  <b-container class="mt-4" v-if="userInfo">
    <v-row>
      <v-col>
        <v-alert show><h1>내 정보</h1></v-alert>
      </v-col>
    </v-row>
    <v-row>
      <v-col></v-col>
      <v-col cols="8">
        <b-jumbotron>
          <template #lead> 내 정보 확인페이지입니다. </template>
          <hr class="my-4" />
          <b-container class="mt-4">
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="end">아이디</v-col
              ><v-col cols="4" align-self="start">{{ userInfo.userId }}</v-col>
              <v-col cols="2"></v-col>
            </v-row>
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="end">이름</v-col
              ><v-col cols="4" align-self="start">{{
                userInfo.userName
              }}</v-col>
              <v-col cols="2"></v-col>
            </v-row>
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="end">이메일</v-col
              ><v-col cols="4" align-self="start">{{ userInfo.email }}</v-col>
              <v-col cols="2"></v-col>
            </v-row>
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="end">가입일</v-col
              ><v-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</v-col>
              <v-col cols="2"></v-col>
            </v-row>
          </b-container>
          <hr class="my-4" />

          <v-btn
            variant="primary"
            href="#"
            class="mr-1"
            color="primary"
            @click="moveModifyUserInfo"
            >정보수정</v-btn
          >
          <v-btn variant="danger" href="#" @click="removeUserInfo"
            >회원탈퇴</v-btn
          >
        </b-jumbotron>
      </v-col>
      <v-col></v-col>
    </v-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import httpCommon from "../../util/http-common";

const userStore = "userStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"])
  },

  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    moveIndex() {
      this.$router.push({ name: "home" });
    },
    moveModifyUserInfo() {
      this.$router.replace({
        name: "userupdate",
        params: { userId: this.userInfo.userId }
      });
    },
    removeUserInfo() {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        httpCommon.delete(`/user/${this.userInfo.userId}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "회원 탈퇴가 완료되었습니다.";
          }
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          alert(msg);

          this.$router.push({ name: "home" });
        });
      }
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
