<template>
  <b-container class="bv-example-row mt-3">
    <v-row>
      <v-col v-if="interests.length">
        <v-col>행을 클릭하시면 상세정보를 볼 수 있습니다.</v-col>
        <br />
        <v-data-table
          :headers="headers"
          :items="interests"
          :items-per-page="10"
          item-key="subject"
          class="elevation-1"
          @click:row="goDetail"
        ></v-data-table>
        <v-dialog v-model="dialog" persistent max-width="1800px">
          <v-card>
            <v-card-title>
              <v-col>{{ infos[0].apartmentName }}</v-col>
              <v-btn color="blue darken-1" text @click="deleteInterest">
                관심목록삭제하기
              </v-btn>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col>
                    주소 : {{ infos[0].sidoName }} {{ infos[0].gugunName }}
                    {{ infos[0].dong }} {{ infos[0].jibun }}
                  </v-col>
                </v-row>
                <v-row>
                  <v-col> 건축년도 : {{ infos[0].buildYear }}년 </v-col>
                </v-row>
              </v-container>
              <v-row><h2>가격변동보기</h2></v-row>
              <line-chart
                ref="lineChart"
                :chartData="chart.data"
                :options="chart.options"
                id="chart"
              ></line-chart>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="dialog = false">
                닫기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
      <v-col v-else class="text-center">관심 목록이 없습니다.</v-col>
    </v-row>
  </b-container>
</template>

<script>
import { listInterest, deleteInterest } from "@/api/interest.js";
import { apartListByCode } from "@/api/apart.js";
import { mapState } from "vuex";

import LineChart from "@/charts/lineChart.js";

const userStore = "userStore";
export default {
  name: "AptMoaInterestList",
  components: { LineChart },
  data() {
    return {
      interests: [],
      interest: {},
      headers: [
        { text: "아파트코드", value: "aptCode" },
        { text: "아파트이름", value: "aptName" }
      ],
      dialog: false,
      infos: [
        {
          aptCode: 11140000000027,
          apartmentName: "남산SKLEADERSVIEW",
          dongCode: null,
          dong: "회현동1가",
          sidoName: "서울특별시",
          gugunName: "중구",
          buildYear: 2010,
          jibun: "206",
          lat: null,
          lng: null,
          img: null,
          dealAmount: "97,000",
          floor: "19",
          roadName: "퇴계로",
          roadNameBonbun: null,
          roadNameBubun: null,
          area: "122.7",
          dealMonth: 2,
          dealDay: 10
        }
      ],
      chart: {
        data: {
          labels: [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023],
          datasets: [
            {
              label: "인원수",
              backgroundColor: "rgb(255,153,204, 0.5)",
              pointBackgroundColor: "hotpink",
              fill: true,
              tension: 0.1,
              borderColor: "hotpink",
              pointBorderColor: "hotpink",
              borderWidth: 1,
              pointBorderWidth: 1,
              data: [450, 300, 100, 1000, 750, 600, 900, 1500, 1200, 2000]
            }
          ]
        },
        options: {
          plugins: {
            legend: {
              display: true,
              position: "left",
              labels: {
                boxWidth: 8,
                padding: 10,
                usePointStyle: true,
                pointStyle: "circle",
                font: { size: 14 }
              },
              fullSize: true,
              align: "center"
            },
            tooltip: { boxWidth: 15, bodyFont: { size: 14 } }
          },
          scales: {
            x: { grid: { display: false }, ticks: { padding: 3 } },
            y: {
              ticks: {
                callback: (val, index) => {
                  return val !== 0
                    ? val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
                    : "";
                },
                padding: 10
              },
              grid: { drawBorder: false, color: "#323232", lineWidth: 1 }
            }
          },
          responsive: true,
          maintainAspectRatio: false,
          animation: { duration: 5000 }
        }
      }
    };
  },
  created() {
    listInterest(
      this.userInfo.userId,
      response => {
        this.interests = response.data;
      },
      error => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(userStore, ["userInfo"])
  },
  methods: {
    goDetail(value) {
      console.log(value);
      this.interest = value;
      this.dialog = true;
      apartListByCode(
        value.aptCode,
        response => {
          this.infos = response.data;
        },
        error => {
          console.log(error);
        }
      );
      console.log(this.infos);
    },
    deleteInterest() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteInterest(this.interest.no, () => {});
        this.$router.go();
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
