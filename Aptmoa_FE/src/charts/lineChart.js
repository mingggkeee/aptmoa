import "@/util/global";
import { Line } from "vue-chartjs/legacy";

export default {
  extends: Line,
  props: ["chartData", "options"],
  mounted() {
    this.renderChart(this.chartData, this.options);
  }
};
