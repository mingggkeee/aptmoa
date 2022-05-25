<template>
  <v-container class="bv-example-row mt-3">
    <div>
      <h3 id="category_title">
        SIGN UP
      </h3>
      <br />
      <br />
      <v-row>
        <v-col class="col-3"></v-col>
        <v-col class="col-6">
          <v-card class="text-center">
            <v-form>
              <v-col>
                <v-row class="ma-3">
                  <v-text-field
                    v-model="userId"
                    label="ID"
                    required
                    id="userId"
                    name="userId"
                    @keyup="idcheck"
                  ></v-text-field>
                </v-row>
                <div class="mt-1" style="font-size: 10px">{{ idresult }}</div>

                <v-row class="ma-3">
                  <v-text-field
                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                    v-model="userPwd"
                    label="PASSWORD"
                    :type="show1 ? 'text' : 'password'"
                    @click:append="show1 = !show1"
                    required
                    id="userPwd"
                    name="userPwd"
                  ></v-text-field>
                </v-row>

                <v-row class="ma-3">
                  <v-text-field
                    :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                    label="CHECK PASSWORD"
                    :type="show2 ? 'text' : 'password'"
                    @click:append="show2 = !show2"
                    required
                    v-model="pwdcheck"
                    id="pwdcheck"
                    name="pwdcheck"
                  ></v-text-field>
                </v-row>
                <v-row class="ma-3">
                  <v-text-field
                    v-model="userName"
                    label="NAME"
                    required
                  ></v-text-field>
                </v-row>

                <v-row class="ma-3">
                  <v-text-field
                    v-model="email"
                    label="E-mail"
                    required
                  ></v-text-field>
                </v-row>
                <div class="text-sm-center">
                  <v-btn
                    color="primary"
                    elevation="2"
                    id="btn_register"
                    @click="checkValue"
                    >회원가입</v-btn
                  >
                  <v-btn
                    color="second"
                    elevation="2"
                    id="btn_reset"
                    type="reset"
                    >초기화</v-btn
                  >
                </div>
              </v-col>
            </v-form>
          </v-card>
        </v-col>
        <v-col class="col-3"></v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "UserRegister",

  data() {
    return {
      show1: false,
      show2: false,
      userName: "",
      userId: "",
      email: "",
      userPwd: "",
      pwdcheck: "",
      isId: false,
      isPwd: false,
      idresult: ""
    };
  },

  mounted() {},

  methods: {
    idcheck() {
      //id체크하는 api 필요
      http.get(`/user/idcheck/${this.userId}`).then(({ data }) => {
        if (data === "success") {
          this.idresult = "사용가능한 아이디입니다.";
          this.isId = true;
        } else {
          this.idresult = "사용중인 아이디입니다.";
          this.isId = false;
        }
      });
    },
    checkValue() {
      let err = true;
      let msg = "";
      err &&
        this.userPwd != this.pwdcheck &&
        ((msg = "입력하신 비밀번호가 다릅니다."), (err = false));

      if (!err) alert(msg);
      else this.registUser();
    },

    registUser() {
      http
        .post("/user/", {
          userId: this.userId,
          userPwd: this.userPwd,
          userName: this.userName,
          email: this.email
        })
        .then(({ data }) => {
          let msg = "회원가입 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "회원가입이 완료되었습니다!";
          }
          alert(msg);
          this.$router.push({ name: "login" });
        });
    }
  }
};
</script>

<style scoped>
#registTitle {
  margin-top: 10px;
}
#btn_register {
  width: 300px;
  height: 45px;
  margin-top: 10px;
  margin-right: 5px;
  margin-left: 5px;
}
#btn_reset {
  width: 300px;
  height: 45px;
  margin-top: 10px;
  margin-left: 5px;
  margin-right: 5px;
}
#category_title {
  font-family: "Changa One", cursive;
  font-size: 50px;
  background: #f2f2f2;
  color: #364a73;
}
</style>
