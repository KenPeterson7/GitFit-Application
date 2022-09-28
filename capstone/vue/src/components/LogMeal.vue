<template>
  <div id="mealPage">
    <div id="myMeals">
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
      <div id="newFood">
        <new-food-form v-if="showForm" v-bind:mealType="mealType"/>
      </div>
      <div id="cancelBtn" v-if="showForm">
        <button v-on:click="cancel()">Cancel</button>
      </div>

     
 </div>


</template>

<script>
import NewFoodForm from './NewFoodForm.vue';
export default {
  components: { NewFoodForm },
  name: "log-meal",
  data(){
    
    return {
      breakfastFoods: [],
      lunchFoods: [],
      dinnerFoods: [],
      snackFoods: [],
      showForm: false,
      mealType: "",
      
     
    
    }
  },
  methods: {
      addFood(meal){
        this.mealType = meal;
        this.showForm=true;
      },
      cancel(){
        this.showForm=false;
      }

  },

}
</script>

<style>

</style>