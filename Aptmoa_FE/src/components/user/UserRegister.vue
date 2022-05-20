<template>
  <b-container class="bv-example-row mt-3">
    <div>
      <b-row>
        <b-col>
          <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col class="col-3"></b-col>
        <b-col class="col-6">
          <v-card class="text-center">
            <v-form>
              <v-col>
                <v-row class="ma-3">
                  <v-text-field
                    v-model="userid"
                    :counter="10"
                    label="ID"
                    required
                  ></v-text-field>
                </v-row>

                <v-row class="ma-3">
                  <v-text-field
                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                    v-model="userpwd"
                    :rules="passRules"
                    :counter="15"
                    label="PASSWORD"
                    :type="show1 ? 'text' : 'password'"
                    @click:append="show1 = !show1"
                    required
                  ></v-text-field>
                </v-row>

                <v-row class="ma-3">
                  <v-text-field
                    :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                    :rules="passRules"
                    :counter="15"
                    label="CHECK PASSWORD"
                    :type="show2 ? 'text' : 'password'"
                    @click:append="show2 = !show2"
                    required
                  ></v-text-field>
                </v-row>

                <!-- 이름 한글 입력이 안됨,, -->
                <v-row class="ma-3">
                  <v-text-field
                    v-model="username"
                    label="NAME"
                    type="String"
                    required
                  ></v-text-field>
                </v-row>

                <v-row class="ma-3">
                  <v-text-field
                    v-model="email"
                    :rules="emailRules"
                    label="E-mail"
                    required
                  ></v-text-field>
                </v-row>
                <div class="text-sm-center">
                  <v-btn color="primary" elevation="2" class="m-1 ma-3"
                    >회원가입</v-btn
                  >
                  <v-btn
                    color="second"
                    elevation="2"
                    class="m-1 ma-3"
                    id="resetBrn"
                    type="reset"
                    >초기화</v-btn
                  >
                </div>
              </v-col>
            </v-form>
          </v-card>
        </b-col>
        <b-col class="col-3"></b-col>
      </b-row>
    </div>
  </b-container>
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
      // http.get(`/user/`)
    }
  }
};
</script>

<style scoped>
#registTitle {
  margin-top: 30px;
}
</style>
