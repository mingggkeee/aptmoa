<template>
  <v-container v-if="aparts && aparts.length != 0" class="bv-example-row mt-3">
    <apart-list-item
      v-for="(apart, index) in aparts"
      :key="index"
      :apart="apart"
    />
    <v-data-table
      :headers="headers"
      :items="aparts"
      :items-per-page="5"
      class="elevation-1"
      @click:row="selectApart"
    ></v-data-table>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <v-col>{{ apart.아파트 }}</v-col>
          <v-btn color="blue darken-1" text @click="interestSave">
            관심아파트 저장
          </v-btn>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col>
                거래일시 : {{ apart.년 }}년 {{ apart.월 }}월 {{ apart.일 }}일
                거래
              </v-col>
            </v-row>
            <v-row>
              <v-col> 건축년도 : {{ apart.건축년도 }} </v-col>
            </v-row>
            <v-row>
              <v-col> 주소 : {{ apart.법정동 }} {{ apart.도로명 }} </v-col>
            </v-row>
            <v-row>
              <v-col>
                거래금액 :
                {{ apart.거래금액 }} 만원
              </v-col>
            </v-row>
            <v-row>
              <v-col> 면적 : {{ apart.전용면적 }} </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
  <v-container
    v-else-if="nameApts && nameApts.length != 0"
    class="bv-example-row mt-3"
  >
    <v-data-table
      :headers="headers2"
      :items="nameApts"
      :items-per-page="5"
      class="elevation-1"
      @click:row="selectApart2"
    ></v-data-table>
    <v-dialog v-model="dialog2" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <v-col>{{ apart2.apartmentName }}</v-col>
          <v-btn color="blue darken-1" text @click="interestSave2">
            관심아파트 저장
          </v-btn>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col>
                상세주소 : {{ apart2.sidoName }} {{ apart2.gugunName }}
                {{ apart2.dong }} {{ apart2.jibun }}
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                도로명주소 : {{ apart2.sidoName }} {{ apart2.gugunName }}
                {{ apart2.roadName }}
              </v-col>
            </v-row>
            <v-row>
              <v-col> 건축년도 : {{ apart2.buildYear }} </v-col>
            </v-row>
            <v-row>
              <v-col>
                최근거래금액 :
                {{ apart2.dealAmount }} 만원
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog2 = false">
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
  <v-container v-else class="bv-example-row mt-3">
    <v-row>
      <v-col><v-alert show>주택 목록이 없습니다.</v-alert></v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { writeInterest } from "@/api/interest";
import http from "@/util/http-common";

const apartStore = "apartStore";
const userStore = "userStore";

export default {
  name: "ApartList",
  data() {
    return {
      currentPage: 1,
      headers: [
        { text: "법정동", value: "법정동" },
        { text: "거래년도", value: "년" },
        { text: "거래월", value: "월" },
        { text: "이름", value: "아파트" },
        { text: "거래금액", value: "거래금액" },
        { text: "전용면적", value: "전용면적" }
      ],
      headers2: [
        { text: "법정구", value: "gugunName" },
        { text: "법정동", value: "dong" },
        { text: "이름", value: "apartmentName" },
        { text: "거래금액", value: "dealAmount" }
      ],
      dialog: false,
      dialog2: false,
      apart: {},
      apart2: {}
    };
  },
  computed: {
    ...mapState({
      aparts: state => state.apartStore.aparts,
      nameApts: state => state.apartStore.nameApts,
      userInfo: state => state.userStore.userInfo
    }),
    methods: {
      ...mapActions(apartStore, ["getApartList", "getApartListByName"])
    }
  },
  methods: {
    ...mapActions(apartStore, ["detailApart", "detailApart2"]),
    selectApart(value) {
      console.log(value);
      this.apart = value;
      this.dialog = true;
      console.log(this.dialog);
    },
    selectApart2(value) {
      console.log(value);
      this.apart2 = value;
      this.dialog2 = true;
      console.log(this.dialog);
    },
    interestSave() {
      http
        .get(`/interest/dupcheck`, {
          params: {
            userId: this.userInfo.userId,
            aptName: this.apart.아파트,
            dong: this.apart.법정동.replace(" ", "")
          }
        })
        .then(({ data }) => {
          if (data === "success") {
            writeInterest(
              {
                userId: this.userInfo.userId,
                aptName: this.apart.아파트,
                buildYear: this.apart.건축년도,
                dong: this.apart.법정동.replace(" ", "")
              },
              ({ data }) => {
                let msg = "등록 처리시 문제가 발생했습니다.";
                if (data === "success") {
                  msg = "등록이 완료되었습니다.";
                }
                alert(msg);
              }
            );
          } else {
            alert("이미 관심목록에 있는 아파트입니다.");
          }
        });
    },
    interestSave2() {
      http
        .get(`/interest/dupcheck`, {
          params: {
            userId: this.userInfo.userId,
            aptName: this.apart2.apartmentName,
            dong: this.apart2.dong
          }
        })
        .then(({ data }) => {
          if (data === "success") {
            writeInterest(
              {
                userId: this.userInfo.userId,
                aptName: this.apart2.apartmentName,
                buildYear: this.apart2.buildYear,
                dong: this.apart2.dong
              },
              ({ data }) => {
                let msg = "등록 처리시 문제가 발생했습니다.";
                if (data === "success") {
                  msg = "등록이 완료되었습니다.";
                }
                alert(msg);
              }
            );
          } else {
            alert("이미 관심목록에 있는 아파트입니다.");
          }
        });
    }
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  }
};
</script>

<style></style>
