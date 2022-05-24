<template>
  <v-container class="bv-example-row mt-3">
    <v-row>
      <v-col>
        <h1>로그인</h1>
      </v-col>
    </v-row>
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
                  <img src="@/assets/icon/kakao_login_medium_wide.png" />
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
      // main.js에 앱 키 옮김
      // if (window.Kakao.Auth.getAccessToken()) {
      //   window.Kakao.API.request({
      //     url: "/v1/user/unlink",
      //     success: function(response) {
      //       console.log(response);
      //     },
      //     fail: function(error) {
      //       console.log(error);
      //     }
      //   });
      //   window.Kakao.Auth.setAccessToken(undefined);
      // }
      // let userData = "";
      // window.Kakao.Auth.login({
      //   success: function() {
      //     window.Kakao.API.request({
      //       url: "/v2/user/me",
      //       data: {
      //         property_keys: ["kakao_account.email"]
      //       },
      //       success: async function(response) {
      //         console.log(response);
      //         userData = response;
      //       },
      //       fail: function(error) {
      //         console.log(error);
      //       }
      //     });
      //   },
      //   fail: function(error) {
      //     console.log(error);
      //   }
      // });
      // console.log("카카오 계정 정보", userData);
    },
    getKakaoAccount() {
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: res => {
          const kakao_account = res.kakao_account;
          console.log(kakao_account);
          const nickname = kakao_account.profile.nickname;
          const email = kakao_account.email;
          console.log("nickname", nickname);
          console.log("email", email);

          alert("카카오 로그인 성공!");
        },
        fail: error => {
          console.log(error);
        }
      });
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
</style>
