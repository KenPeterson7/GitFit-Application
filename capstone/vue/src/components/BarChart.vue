<template>
  <div>
    <h3 class="chartTitle">
      Weekly Caloric Intake {{ weekAgo }} - {{ today }}
    </h3>
    <Bar
      :chart-options="chartOptions"
      :chart-data="chartData"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :width="width"
      :height="height"
    />
  </div>
</template>

<script>
import { Bar } from "vue-chartjs/legacy";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";
import FoodService from "../services/FoodService.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  name: "BarChart",
  components: { Bar },
  props: {
    chartId: {
      type: String,
      default: "bar-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 25,
    },
    height: {
      type: Number,
      default: 10,
    },
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      today: new Date().toString().slice(0, 10),
      weekAgo: new Date(new Date().setDate(new Date().getDate() - 6))
        .toString()
        .slice(0, 10),
      chartData: {
        labels: this.getChartLabels(),
        datasets: [
          {
            label: "Daily Caloric Intake",
            data: this.getData(),
            backgroundColor: () => {
              let backgroundColors = [];
              let goal = this.$store.state.goal.daily_caloric_goal;
              this.chartData.datasets[0].data.forEach((dataPoint) => {
                if (dataPoint > 1.2 * goal) {
                  backgroundColors.push("rgba(225,0,0,0.7)");
                } else if (dataPoint < 0.8 * goal) {
                  backgroundColors.push("rgba(225,225,0,0.7)");
                } else {
                  backgroundColors.push("rgba(0,125,255,0.7)");
                }
              });
              return backgroundColors;
            },
            borderColor: "rgb(00,00,00)",
            borderWidth: 2,
          },
        ],
      },
      chartOptions: {
        responsive: true,
      },
    };
  },
  methods: {
    getChartLabels() {
      let days = [];
      for (let i = 6; i > -1; i--) {
        let day = new Date();
        day.setDate(day.getDate() - i);
        days.push(day.toString().slice(0, 10));
      }
      return days;
    },
    getData() {
      let data = [];
      let user = this.$store.state.user.username;
      let day = new Date().toJSON().slice(0, 10);

      FoodService.getCaloriesForWeek(user, day).then((response) => {
        if (response.status == 200) {
          let returnedData = response.data;

          for (let i = 0; i < 7; i++) {
            data.push(returnedData[i]);
          }
        }
      });

      return data;
    },
    getColors() {
      return this.barColors;
    },
  },
};
</script>

<style>
.chartTitle {
  text-align: center;
}
</style>