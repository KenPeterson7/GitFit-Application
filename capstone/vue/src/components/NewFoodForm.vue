<template>

   <div id="newFoodForm" v-if="!showMyMeals">
        <form v-on:submit.prevent="logFood(newFood)">
        
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
         <button type="submit" v-on:click="logFood(newFood)">Save</button>
        

       
        </form>
     
      </div>
</template>

<script>
import FoodService from "../services/FoodService"

export default {

  props: ['mealType'],
  data(){
        return {
            newFood: {
            mealType: this.mealType,
            },

        }
    },
    methods: {
        logFood(newFood){
            FoodService.addFood(newFood).then((response) => {
                if(response.FoodService==200){
                   //
                }
            })
        },
    
    }

}
</script>

<style scoped>
#newFoodForm form{
    display:flex;
    flex-direction: column;
    margin: 20px;
    
}
#newFoodForm form label {
    margin-top: 10px;
}

#newFoodForm form button {
    margin-top: 10px;
}

</style>