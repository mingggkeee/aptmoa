<template>
  <v-container class="bv-example-row mt-3">
    <h3 id="category_title">
      LOGIN
    </h3>
    <br />
    <br />
    <v-row>
      <v-col></v-col>
      <v-col>
        <v-card class="text-center" style="max-width: 40rem" align="left">
          <v-form>
            <v-col>
              <v-alert
                color="red"
                dense
                outlined
                type="warning"
                v-if="isLoginError"
                >아이디 또는 비밀번호를 확인하세요.</v-alert
              >
            </v-col>
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

              <br />
              <div class="text-sm-center">
                <v-btn
                  type="button"
                  color="primary"
                  @click="confirm"
                  id="btn_login"
                  >로그인</v-btn
                >
              </div>
              <div class="text-sm-center">
                <v-btn
                  type="button"
                  variant="success"
                  @click="movePage"
                  id="btn_register"
                  >회원가입</v-btn
                >
              </div>
              <div class="text-sm-center">
                <v-btn type="button" @click="movePwFindPage" id="btn_findpw"
                  >비밀번호찾기</v-btn
                >
              </div>
              <div class="text-sm-center">
                <button
                  type="button"
                  @click="kakaoLoginBtn"
                  id="btn_kakaologin"
                >
                  <img src="@/assets/img/kakao_login_medium_wide.png" />
                </button>
              </div>
            </v-col>
          </v-form>
        </v-card>
      </v-col>
      <v-col></v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/util/http-common";
import { nullLiteral } from "@babel/types";

const userStore = "userStore";

export default {
  name: "UserLogin",

  data() {
    return {
      user: {
        userId: null,
        userPwd: null,
        kakaoId: null,
        kakaoNickname: null,
        kakaoId: null
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
      console.log("token : ", token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "register" });
    },
    movePwFindPage() {
      this.$router.push({ name: "findpwd" });
    },
    kakaoLoginBtn() {
      console.log(window.Kakao);
      window.Kakao.Auth.login({
        scope: "profile_nickname, account_email",
        success: this.getKakaoAccount
      });
    },
    getKakaoAccount() {
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: res => {
          const kakao_account = res.kakao_account;
          console.log(kakao_account);
          this.kakaoNickname = kakao_account.profile.nickname;
          this.kakaoEmail = kakao_account.email;
          console.log("kakaoNickname", this.kakaoNickname);
          console.log("kakaoEmail", this.kakaoEmail);

          const makeId = this.kakaoEmail.split("@");
          this.kakaoId = makeId[0];
          console.log("kakaoId", this.kakaoId);

          this.kakaoIdCheck();
        },
        fail: error => {
          console.log(error);
        }
      });
    },
    kakaoIdCheck() {
      http.get(`/user/idcheck/${this.kakaoId}`).then(({ data }) => {
        if (data === "success") {
          console.log("소셜 회원가입");
          this.kakaoRegistUser();
        } else {
          this.kakaoLogin();
        }
      });
    },
    kakaoRegistUser() {
      console.log("kakaoId:", this.kakaoId);
      http
        .post("/user/", {
          userId: this.kakaoId,
          userPwd: "1234",
          userName: this.kakaoNickname,
          email: this.kakaoEmail
        })
        .then(({ data }) => {
          if (data === "success") {
            this.kakaoLogin();
          } else {
            let msg = "회원가입 처리시 문제가 발생했습니다.";
            alert(msg);
          }
          // this.$router.push({ name: "login" });
        });
    },
    kakaoLogin() {
      console.log("소셜 로그인으로 이동");
      this.user.userId = this.kakaoId;
      this.user.userPwd = "1234";
      console.log(this.user.userId);
      console.log(this.user.userPwd);
      this.confirm();
    }
  }
};
</script>

<style scoped>
#btn_login {
  width: 300px;
  height: 45px;
  margin-top: 10px;
}
#btn_register {
  width: 300px;
  height: 45px;
  margin-top: 10px;
}
#btn_findpw {
  width: 300px;
  height: 45px;
  margin-top: 10px;
}
#btn_kakaologin {
  width: 300px;
  margin-top: 10px;
}
#category_title {
  font-family: "Changa One", cursive;
  font-size: 50px;
  background: #f2f2f2;
  color: #364a73;
}
</style>
