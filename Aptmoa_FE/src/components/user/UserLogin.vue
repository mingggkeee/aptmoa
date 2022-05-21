<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h1>로그인</h1></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col>
        <v-card class="text-center" style="max-width: 40rem" align="left">
          <v-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <!-- <b-form-group label="아이디:" label-for="userid"> -->
            <v-col>
              <v-row class="ma-3">
                <v-text-field
                  id="userid"
                  v-model="user.userId"
                  required
                  placeholder="아이디 입력...."
                  @keyup.enter="confirm"
                ></v-text-field>
              </v-row>
              <!-- <b-form-group label="비밀번호:" label-for="userpwd"> -->

              <v-row class="ma-3">
                <v-text-field
                  type="password"
                  id="userpwd"
                  v-model="user.userPwd"
                  required
                  placeholder="비밀번호 입력...."
                  @keyup.enter="confirm"
                ></v-text-field>
              </v-row>

              <div class="text-sm-center">
                <v-btn
                  type="button"
                  color="primary"
                  class="m-1 ma-3"
                  @click="confirm"
                  elevation="2"
                  >로그인</v-btn
                >
                <v-btn
                  type="button"
                  variant="success"
                  class="m-1 ma-3"
                  @click="movePage"
                  elevation="2"
                  >회원가입</v-btn
                >
              </div>
            </v-col>
          </v-form>
        </v-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserLogin",

  data() {
    return {
      user: {
        userId: null,
        userPwd: null
      }
    };
  },

  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"])
  },

  mounted() {},

  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "register" });
    }
  }
};
</script>

<style scoped></style>
