<template>
  <div id="mealPage">
    <div id="myMeals" v-if="!showForm">
    <p>Your Food Diary For: {{ new Date()}} </p>
    <h4>Breakfast</h4>
      <table v-for="(food, index) in breakfastFoods" v-bind:key="index">
      {{ food.foodName }}
      {{ food.calories }}
      </table>
      <button v-on:click="addFood('Breakfast')">Add Food</button>
    <h4>Lunch</h4>
      <table v-for="(food, index) in lunchFoods" v-bind:key="index">
      {{ food.foodName }}
      {{ food.calories }}
      </table>
      <button v-on:click="addFood('Lunch')">Add Food</button>
    <h4>Dinner</h4>
      <table v-for="(food, index) in dinnerFoods" v-bind:key="index">
      {{ food.foodName }}
      {{ food.calories }}
      </table>
      <button v-on:click="addFood('Dinner')">Add Food</button>
    <h4>Snacks</h4>
      <table v-for="(food, index) in snackFoods" v-bind:key="index">
      {{ food.foodName }}
      {{ food.calories }}
      </table>
      <button v-on:click="addFood('Snacks')">Add Food</button>
      </div>

      <div id="newFoodForm" v-if="showForm">
        <form v-on:submit.prevent="displayFood(newFood)">
          <label for="foodName"> Food Name: </label>
          <input type="text" id="foodName" v-model="newFood.foodName">
          <label for="mealType"> Meal: </label>
          <select id="mealType" v-model="newFood.mealType">
            <option>Breakfast</option>
            <option>Lunch</option>
            <option>Dinner</option>
            <option>Snacks</option>
         </select>
         <label for="calories"> Calories: </label>
         <input type="text" id="calories" v-model="newFood.calories">
         <label for="size"> Size: </label>
         <select id="size" v-model="newFood.size">
           <option>8 Ounces</option>
         </select>
         <label for="servings"> Number of Servings: </label>
         <input type="number" id="servings" v-model="newFood.servings">
         <button type="submit" v-on:click="displayFood(newFood)">Save</button>
         <button v-on:click="showForm=false">Cancel</button>




        </form>

      </div>
 </div>


</template>

<script>
//import FoodService from "../services/FoodService";
export default {
  name: "log-meal",
  data(){
    
    return {
      breakfastFoods: [],
      lunchFoods: [],
      dinnerFoods: [],
      snackFoods: [],
      newFood: {},
      showForm: false,
    }
  },
  methods: {
      addFood(meal){
        this.showForm = true;
        this.newFood.mealType = meal;
      },
      displayFood(food){
        this.showForm = false;
        this.breakfastFoods.push(food);
        
      },
      logFood(newFood){
        this.FoodService.addFood(newFood).then((response) => {
          if(response.status==200){
            //maybe say food added?
          }
        })
      }
  },

}
</script>

<style>
#newFoodForm form{
    display:flex;
    flex-direction: column;
    margin: 20px;
    
}

</style>