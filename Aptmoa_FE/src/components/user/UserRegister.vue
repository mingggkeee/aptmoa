<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card class="text-center mt-3" style="max-width:40rem" align="left">
            <b-form class="text-left">
            </b-form>
          
          </b-card>

        </b-col>
      </b-row>


    </b-row>
  </b-container>
</template>

<script>
// import http from "@/util/http-common";

export default {
  name: "UserRegister",

  data() {
    return {};
  },

  mounted() {},

  methods: {}
};
</script>

<style scoped>
#registTitle {
  margin-top: 30px;
}
</style>
