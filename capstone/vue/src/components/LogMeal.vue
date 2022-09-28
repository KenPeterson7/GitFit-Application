<template>
  <div id="mealpage">
    <p>Your Food Diary For: {{ new Date()}} </p>
    <h4>Breakfast</h4>
      <table v-for="(food, index) in breakfastFoods" v-bind:key="index">
      {{ food.foodName }}
      {{ food.caloricAmount }}
      </table>
      <button v-on:click="goToAddFood('Breakfast')">Add Food</button>
    <h4>Lunch</h4>
      <table v-for="(food, index) in lunchFoods" v-bind:key="index">
      {{ food.foodName }}
      {{ food.caloricAmount }}
      </table>
      <button >Add Food</button>
    <h4>Dinner</h4>
      <table v-for="(food, index) in dinnerFoods" v-bind:key="index">
      {{ food.foodName }}
      {{ food.caloricAmount }}
      </table>
      <button>Add Food</button>
    <h4>Snacks</h4>
      <table v-for="(food, index) in snackFoods" v-bind:key="index">
      {{ food.foodName }}
      {{ food.caloricAmount }}
      </table>
      <button>Add Food</button>
    
    
    </div>
</template>

<script>
export default {
  name: "log-meal",
  data(){
    
    return {
      breakfastFoods: [
        {
          foodName: 'cereal',
          caloricAmount: 200
        },
        {
          foodName:'milk',
          caloricAmount:100
        }
      ],
      lunchFoods: [],
      dinnerFoods: [],
      snackFoods: [],
    }
  },
  methods: {
      goToAddFood(meal){

      }
  }

}
</script>

<style>

</style>