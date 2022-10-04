<template>
  <div class="home">
    <div id="componentDiv">
      <h1>{{ getName() }}</h1>
      <h2>Calories Remaining: {{ getRemainingCalories() }}</h2>
      
      <br>
      <div id=chart>
      <bar-chart />
      </div>
      <div>
        <h2>Recent Meals:</h2>
        <ol id = "recentMeals">
          <li v-for="food in recentBreakfast" :key="food.id" >
            Meal Type: Breakfast
            <ul>
              <li>Food Included: {{ food.foodName }}</li>
              <li>Total Calories: {{ food.caloricAmount }}</li>
              <li>Meal Date: ??? </li>
            </ul>
            <br />
          </li>
          <li v-for="food in recentLunch" :key="food.id" >
            Meal Type: Lunch
            <ul>
              <li>Food Included: {{ food.foodName }}</li>
              <li>Total Calories: {{ food.caloricAmount }}</li>
              <li>Meal Date: ??? </li>
            </ul>
            <br />
          </li>
          <li v-for="food in recentDinner" :key="food.id" >
            Meal Type: Dinner
            <ul>
              <li>Food Included: {{ food.foodName }}</li>
              <li>Total Calories: {{ food.caloricAmount }}</li>
              <li>Meal Date: ??? </li>
            </ul>
            <br />
          </li>
        </ol>
      </div>
      <div>
        <h2>Recent Workouts:</h2>

        <ol id = "recentWorkouts">
          <li v-for="workout in recentWorkouts" :key="workout.id">
            Workout Name: {{ workout.nameOfWorkout }}
            <ul>
              <li>Workout Type: {{ workout.typeOfWorkout }}</li>
              <li>Workout Date: {{ workout.workoutDate }}</li>
            </ul>
            <br />
          </li>
        </ol>
      </div>
    </div>
  </div>
</template>

<script>
import FoodService from '../services/FoodService';
import ProfileService from "../services/ProfileService";
import WorkoutService from "../services/WorkoutService";
import BarChart from '../components/BarChart.vue'
// import FoodService from "../services/FoodService";
// import MealService from "../services/MealService";

export default {
  name: "home",
  components: {BarChart},
  data() {
    return {
       profile: "",
      name: this.$store.state.profile.displayName,
      caloriesRemaining: this.$store.state.goal.daily_caloric_goal,
      recentWorkouts: [],
      recentMeals: [],
      recentBreakfast: [],
      recentLunch: [],
      recentDinner: [],
       todaysDate:  new Date().toJSON().slice(0, 10),
      yesterdaysDate:  new Date().toJSON().slice(0, 10),

      todaysCalories: this.populateTodaysCalories(),
      yesterdaysCalories: this.populateYesterdaysCalories(),
     

  
    };
  },
  created() {
    this.populateStore();
    this.getLastThreeWorkouts();
    this.getLastBreakfast();
    this.getLastLunch();
    this.getLastDinner();
  
   
    this.setRecentStarStreak();
  },
  methods: {
     formatDate(){
      const notFormat = new Date();
     this.date = notFormat.setHours( notFormat.getHours()+(notFormat.getTimezoneOffset()/-60) );
    this.date = notFormat.toJSON().slice(0, 10);
    return this.date
     },
      getPreviousDay() {
        let date = new Date();
      let previous = "";
      previous= date.setHours( date.getHours()+(date.getTimezoneOffset()/-60) ); 
       previous = date.setDate(date.getDate() - 1);
     previous = date.toJSON().slice(0, 10);

      return previous;

    },
    populateStore() {
      ProfileService.getProfile(this.$store.state.user.username).then(
        (response) => {
          this.$store.commit("SET_CURRENT_PROFILE", response.data);
          if (this.$store.state.profile == "") {
            this.$router.push("/profile");
          } else {
            this.populateGoal();
          }
        }
      );
      this.profile = this.$store.state.profile;
    },
    populateGoal() {
      ProfileService.getGoal(this.$store.state.profile.profileId).then(
        (response) => {
          this.$store.commit("SET_CURRENT_GOAL", response.data);
        }
      );
    },
    getName() {
      return this.$store.state.profile.displayName;
    },
    getRemainingCalories() {
      return this.$store.state.goal.daily_caloric_goal;
    },
    getLastThreeWorkouts() {
      WorkoutService.getListOfLastThreeWorkouts(
        this.$store.state.user.username
      ).then((response) => {
        if (response.status == 200) {
          this.recentWorkouts = response.data;
        }
      });
    },
    getLastBreakfast() {
      FoodService.getLastMeal(this.$store.state.user.username, "Breakfast").then((response) => {
        if (response.status == 200) {
          this.recentBreakfast = response.data;
    }
     });
  },
  getLastLunch() {
      FoodService.getLastMeal(this.$store.state.user.username, "Lunch").then((response) => {
        if (response.status == 200) {
          this.recentLunch = response.data;
    }
     });
  },
  getLastDinner() {
      FoodService.getLastMeal(this.$store.state.user.username, "Dinner").then((response) => {
        if (response.status == 200) {
          this.recentDinner = response.data;
    }
     });
  },
  populateTodaysCalories(){
    FoodService.getWeeklyCalories(this.$store.state.user.username, this.todaysDate).then((response)=>{
      if(response.status == 200){
        this.todaysCalories = response.data;
      }
    })
   

  },
  populateYesterdaysCalories(){
 FoodService.getWeeklyCalories(this.$store.state.user.username, this.yesterdaysDate).then((response)=> {
      if(response.status == 200){
        this.yesterdaysCalories = response.data;
      }
    })
  },
  setRecentStarStreak(){
    console.log(this.todaysCalories)
    if (this.todaysCalories ===0 && this.yesterdaysCalories === 0){
         this.profile.starStreak = 0;
             this.$store.commit('SET_CURRENT_PROFILE', this.profile)
              ProfileService.updateProfile(this.profile.profileId, this.profile)
    }
  }
}
}

</script>

<style scoped>
.home {
  display: flex;
  /* height: 700px; */
}

/* .home nav {
  min-width: 150px;
  border-right: 1px;
  border-right-style: solid;
  background-color: rgb(0, 125, 255);
  color: whitesmoke;

  border-radius: 5px;
  text-align: center;
} */

h1 {
  text-decoration: underline;
}

h3 {
  padding-left: 20px;
  padding-right: 20px;
}

#chart{
  
}

/* #componentDiv {
  flex-grow: 8; 
  background-image: url("../../public/Images/gym-background2.png");
  background-color: lightgray;
  background-blend-mode: screen;
  background-size: cover;
  background-repeat: no-repeat;
} */

/* .selected {
  background-color: rgb(0, 67, 127);
  color: white;
} */

div {
  margin-left: 2.5px;
}

/* nav button {
  margin: 15px;
  width: 75%;
  color: blue;
  border-radius: 5px;
} */

#recentWorkouts{
  font-weight: bold;
}

#recentMeals{
  font-weight: bold;
}

</style>
