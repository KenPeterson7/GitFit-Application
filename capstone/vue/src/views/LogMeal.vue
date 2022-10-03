<template>
  <div id="mealPage">
    <h3 id="header">
      My Meals For:
      {{
        new Date().getMonth()+1 +
        "/" + 0 +
        new Date().getDate() +
        "/" + 
        new Date().getFullYear()
      }}
    </h3>
    <table id="headerTable">
      <thead>
        <th>Food-Type</th>
        <th>Calories</th>
        <th>Size</th>
        <th>Number-Of-Servings</th>
      </thead>
    </table>
    <h4>BREAKFAST</h4>
    <display-meals v-bind:foodList="breakfastFoods" v-bind:mealType="'Breakfast'"/>
      
    <h4>LUNCH</h4>
       <display-meals v-bind:foodList="lunchFoods" v-bind:mealType="'Lunch'"/>
 
    <h4>DINNER</h4>
    <display-meals v-bind:foodList="dinnerFoods" v-bind:mealType="'Dinner'"/>
  

    <h4>SNACKS</h4>
       <display-meals v-bind:foodList="snackFoods" v-bind:mealType="'Snacks'"/>
    <router-link v-bind:to="{ name: 'recentFoods' }"
      ><button id="add">Add Food</button></router-link
    >
    <button v-on:click="updateCalories()"> See Calories Consumed:</button>
    <h3 v-if="seeCalories">  {{this.calories}}</h3>
  </div>
</template>

<script>
import FoodService from "../services/FoodService";
import DisplayMeals from "../components/DisplayMeals.vue"


export default {
  components: { DisplayMeals },
  name: "log-meal2",

  data() {
    return {
      seeCalories: false,
      calories: 0,
      breakfastFoods: [],
      lunchFoods: [],
      dinnerFoods: [],
      snackFoods: [],
       month: 0,
      date:
        new Date().getFullYear() +
        "-" +
        
        this.getMonth() +
        "-" + 0 +
        new Date().getDate(),
    };
  },
  created() {
    this.updateBreakfastList();
    this.updateLunchList();
    this.updateDinnerList();
    this.updateSnacksList();
   
 
  },

  methods: {
     getMonth() {
      this.month = new Date().getMonth();
      return this.month + 1;
    },
    updateBreakfastList() {
      FoodService.getFoodByUserMealDate(this.$store.state.user.username, this.date, 'Breakfast').then((response)=>{
        if (response.status == 200){
          this.breakfastFoods = response.data;
          return this.breakfastFoods;
        }
      })
       
    },
    updateLunchList() {
      FoodService.getFoodByUserMealDate(this.$store.state.user.username, this.date, 'Lunch').then((response)=>{
        if (response.status == 200){
          this.lunchFoods = response.data;
          return this.lunchFoods;
        }
      })
       
    },
    updateDinnerList() {
      FoodService.getFoodByUserMealDate(this.$store.state.user.username, this.date, 'Dinner').then((response)=>{
        if (response.status == 200){
          this.dinnerFoods = response.data;
          return this.dinnerFoods;
        }
      })
       
    },
    updateSnacksList() {
      FoodService.getFoodByUserMealDate(this.$store.state.user.username, this.date, 'Snacks').then((response)=>{
        if (response.status == 200){
          this.snackFoods = response.data;
          return this.snackFoods;
        }
      })
       
    },
    updateCalories(){
      this.breakfastFoods.forEach((food)=> {
        this.calories += food.caloricAmount
       
      })
      this.lunchFoods.forEach((food)=> {
        this.calories+= food.caloricAmount
      })
       this.dinnerFoods.forEach((food)=> {
        this.calories+= food.caloricAmount
      })
       this.snackFoods.forEach((food)=> {
        this.calories+= food.caloricAmount
      })
      this.seeCalories = true
    }
 
  },
};
</script>

<style scoped>
#header {
  margin-top: 20px;
 text-align: center;
  color:blue;

}
#headerTable {
  margin-left: 30%;
}
#headerTable th {
  padding-right: 50px;
}

button{
  border-radius: 4px;
  color: blue;
  
}
#add{
 margin-left: 45%;
 margin-top: 30px;
}
h3{
  text-align: center;
}


</style>