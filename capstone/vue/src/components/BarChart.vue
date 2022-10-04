<template>
  <div>
    <h1 class="chartTitle">
      Weekly Caloric Intake {{ weekAgo }} - {{ today }}
    </h1>
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
import FoodService from '../services/FoodService.js'

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
      default: 1000,
    },
    height: {
      type: Number,
      default: 400,
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
        // [
        //   "Sunday",
        //   "Monday",
        //   "Tuesday",
        //   "Wednesday",
        //   "Thursday",
        //   "Friday",
        //   "Saturday",
        // ],
        datasets: [
          {
            label: "Daily Caloric Intake",
            data: 
            this.getData(),
            // [1750, 1690, 1868, 2000, 1985, 1775, 1825],
            backgroundColor: "rgba(0,125,255,0.7)",
            borderColor: "rgb(0,125,255)",
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
              days.push(day.toString().slice(0,10));
          }
          return days;
      },
      getData() {
          let data = [];
          let user = this.$store.state.user.username
          for (let i = 6; i > -1; i--) {
              let day = new Date();
              day.setDate(day.getDate() - i);
              day = day.toJSON().slice(0,10);
                FoodService.getWeeklyCalories(user, day).then((response) => {
                    if(response.status == 200) {
                        data.push(response.data);
                    }
          });
          }
          
          return data;
      }
  }
};
</script>

<style>
.chartTitle {
  text-align: center;
}

</style>