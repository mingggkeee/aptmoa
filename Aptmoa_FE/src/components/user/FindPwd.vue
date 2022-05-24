<template>
  <v-container>
    <v-row>
      <v-col>
        <h1>비밀번호 찾기</h1>
      </v-col>
    </v-row>
    <div>
      <br />
      <p id="subtitle">아래 이메일 주소로 임시 비밀번호를 알려드립니다.</p>
      <p id="subtitle">로그인 후 비밀번호를 꼭 변경해주세요.</p>
    </div>
    <br />
    <br />
    <b-row>
      <b-col></b-col>
      <b-col>
        <v-card class="text-center" style="max-width: 40rem" align="left">
          <v-form class="text-left">
            <v-col>
              <v-row class="ma-3">
                <v-text-field
                  type="text"
                  id="userid"
                  v-model="userId"
                  required
                  placeholder="아이디 입력...."
                  @keyup.enter="confirm"
                ></v-text-field>
              </v-row>
              <!-- <b-form-group label="비밀번호:" label-for="userpwd"> -->

              <v-row class="ma-3">
                <v-text-field
                  type="text"
                  id="email"
                  v-model="email"
                  required
                  placeholder="이메일 입력...."
                  @keyup.enter="confirm"
                ></v-text-field>
              </v-row>

              <div class="text-sm-center">
                <v-btn
                  type="button"
                  color="primary"
                  class="ma-2"
                  @click="sendEmail"
                  >임시 비밀번호 전송</v-btn
                >
              </div>
            </v-col>
          </v-form>
        </v-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </v-container>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "FindPwd",

  data() {
    return {
      userId: null,
      email: null
    };
  },

  mounted() {},

  methods: {
    sendEmail() {
      if (!this.userId || !this.email) {
        alert("올바른 ID와 이메일을 입력해주세요.");
      } else {
        http
          .get(`/user/send/`, {
            params: { userId: this.userId, email: this.email }
          })
          .then(({ data }) => {
            if (data === "success") {
              alert("가입된 이메일로 임시 비밀번호가 발송되었습니다.");
              this.$router.push({ name: "login" });
            } else {
              alert(
                "이메일 발송을 실패했습니다. ID와 이메일주소를 확인하세요."
              );
            }
          });
      }
    }
  }
};
</script>

<style scoped>
#subtitle {
  font-size: 18px;
}
</style>
