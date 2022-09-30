<template>
     <div id="newFoodForm">
      <h3>NEW FOOD</h3>
      <form v-on:submit.prevent>
        <label for="foodName"> Food Name: </label>
        <input type="text" id="foodName" v-model="newFood.foodName" />
        <label for="foodType"> Food Type: </label>
        <select id="foodType" v-model="newFood.foodType">
            <option >Dairy</option>
            <option >Meat</option>
            <option >Poultry</option>
            <option >Produce</option>
            <option >Carbs</option>
            <option >Drinks</option>
        </select>
    
    
        <label for="calories"> Calories: </label>
        <input type="text" id="calories" v-model="newFood.caloricAmount" />
        <label for="size"> Size: </label>
        <input type="text" id="size" v-model="newFood.size"/>
        
        <label for="servings"> Number of Servings: </label>
        <input type="number" id="servings" v-model="newFood.numberOfServings" />
     
         <label for="selection"
        >Please Choose The Meal You Would Like To Add Your Food To:</label
      >
      <select id="selection" v-model="mealType" v-on:click="assignMealType()">
        <option>Breakfast</option>
        <option>Lunch</option>
        <option>Dinner</option>
        <option>Snacks</option>
      </select>
         <button type="submit" v-on:click="logFood(newFood)">Save</button>
        <button v-on:click="cancel()">Cancel</button>
      </form>
    </div>
</template>

<script>
import FoodService from "../services/FoodService";
export default {
    data(){
        return{
            newFood:{

            },
            mealType: "",
            foodMeal:{
                foodId : 0,
                mealId: 0,
            }
        }
    },
    methods: {
        logFood(newFood){
            FoodService.addFood(newFood).then((response) => {
                if (response.status === 200){
                    //add food to food_meal table
                }
            })
        },
          assignMealType(){
      if (this.mealType ==='Breakfast'){
          this.foodMeal.mealId = 5001
      }
      else if (this.mealType === "Lunch"){
          this.foodMeal.mealId = 5002
      }
      else if(this.mealType === 'Dinner'){
          this.foodMeal.mealId = 5003
      }
      else if(this.mealType === 'Snacks'){
          this.foodMeal.mealId = 5004
      }
  },
  cancel(){
      this.$router.push({name: 'recentFoods'})
  },
    }

}
</script>

<style scoped>
#newFoodForm form {
  display: flex;
  flex-direction: column;
  margin-top: 50px;
  margin-right: 400px;
  margin-left: 400px;
  margin-bottom: 50px;
}
#newFoodForm form label {
  margin-top: 10px;
}

#newFoodForm form button {
  margin-top: 10px;
  
}
h3{
    text-align: center;
}
</style>