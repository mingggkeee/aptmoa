<template>
  <v-container>
    <v-row>
      <v-col v-if="interests.length">
        <v-col
          >행을 클릭하시면 상세정보 및 가격변동 그래프를 볼 수 있습니다.</v-col
        >
        <br />
        <v-data-table
          :headers="headers"
          :items="interests"
          :items-per-page="10"
          item-key="subject"
          class="elevation-1"
          @click:row="goDetail"
        ></v-data-table>
        <v-dialog v-model="dialog" width="50%">
          <v-card>
            <v-card-title>
              <v-col
                ><h1>{{ infos[0].apartmentName }}</h1></v-col
              >
              <v-btn color="blue darken-1" text @click="deleteInterest">
                관심목록삭제하기
              </v-btn>
              <v-btn color="blue darken-1" text @click="dialog = false">
                닫기
              </v-btn>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col>
                    <h2>
                      주소 : {{ infos[0].sidoName }} {{ infos[0].gugunName }}
                      {{ infos[0].dong }} {{ infos[0].jibun }}
                    </h2>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <h2>건축년도 : {{ infos[0].buildYear }}년</h2></v-col
                  >
                </v-row>
              </v-container>
            </v-card-text>
            <v-container
              fluid
              style="margin-bottom: 10px; width: 1000px; height: 500px"
            >
              <line-chart
                ref="lineChart"
                :chartData="chart.data"
                :options="chart.options"
                id="chart"
              ></line-chart>
            </v-container>
          </v-card>
        </v-dialog>
      </v-col>
      <v-col v-else class="text-center">관심 목록이 없습니다.</v-col>
    </v-row>
  </v-container>
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
        { text: "아파트이름", value: "aptName" },
        { text: "건축년도", value: "buildYear" }
      ],
      dialog: false,
      infos: [{}],
      chart: {
        data: {
          labels: [2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022],
          datasets: [
            {
              label: "가격",
              tension: 0.1,
              borderColor: "red",
              pointBorderColor: "red",
              borderWidth: 3,
              pointBorderWidth: 3,
              data: []
            }
          ]
        },
        options: {
          plugins: {
            tooltip: { boxWidth: 15, bodyFont: { size: 14 } }
          },
          scales: {
            x: { grid: { display: false }, ticks: { padding: 3 } },
            y: {
              ticks: {
                callback: (val, index) => {
                  return val !== 0
                    ? val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") +
                        "만원"
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
      async response => {
        this.interests = await response.data;
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
      console.log(value.aptCode);
      this.interest = value;
      apartListByCode(
        value.aptCode,
        async response => {
          console.log(response.data);
          this.infos = await response.data;
          this.inputData();
          this.dialog = true;
        },
        error => {
          console.log(error);
        }
      );
    },
    deleteInterest() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteInterest(this.interest.no, () => {});
        this.$router.go();
      }
    },
    inputData() {
      let temps = [];
      for (let i = 0; i < this.infos.length; i++) {
        // console.log(this.infos[i].dealAmount);
        temps[i] = this.infos[i].dealAmount.replace(",", "");
      }
      this.chart.data.datasets[0].data = temps;
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
