<template>
  <div>

    <div id="foodTable">
      <table
        id="foodTable"
       
      >
        <!-- <thead id="header">
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
        </thead> -->
        <!-- <tbody> -->
          <tr  v-for="(food, index) in foodList"
        v-bind:key="index">
        <td id="name">{{ food.foodName }}</td>
      

          <td id = "foodType">{{ food.foodType }}</td>
          <td id = "calories">{{ food.caloricAmount }}</td>
          <td id = "size">{{ food.size }}</td>
          <td id = "servings">{{ food.numberOfServings }}</td>

          <button id = "edit" v-on:click="editFood(food)">Edit Food</button>
          
            <button id="delete" v-on:click="deleteFood(food.foodId)">
              Delete Food
            </button>
          
          </tr>
        <!-- </tbody> -->
      </table>
    </div>
    <div id="editForm" v-if="showForm">
      <form v-on:submit.prevent>
        <label for="foodName"> Food Name: </label>
        <input type="text" id="foodName" v-model="clickedFood.foodName" />
        <label for="foodType"> Food Type: </label>
        <select id="foodType" v-model="clickedFood.foodType">
          <option>Dairy</option>
          <option>Meat</option>
          <option>Poultry</option>
          <option>Produce</option>
          <option>Carbs</option>
          <option>Drinks</option>
        </select>

        <label for="calories"> Calories: </label>
        <input type="text" id="calories" v-model="clickedFood.caloricAmount" />
        <label for="size"> Size: </label>
        <input type="text" id="size" v-model="clickedFood.size" />

        <label for="servings"> Number of Servings: </label>
        <input
          type="number"
          id="servings"
          v-model="clickedFood.numberOfServings"
        />

        <button type="submit" v-on:click="saveEditedFood(clickedFood)">
          Save
        </button>
        <button v-on:click="cancel()">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import MealService from "../services/MealService";
import FoodService from "../services/FoodService";
import FoodMealService from "../services/FoodMealService";
export default {
  props: ["foodList", "mealType"],
  data() {
    return {
      showForm: false,
      clickedFood: {},

      foodMeal: {
        foodId: 0,
        mealId: 0,
      },
     
      date:
     this.formatDate()
    };
  },

  methods: {
     formatDate(){
      const notFormat = new Date();
     this.date = notFormat.setHours( notFormat.getHours()+(notFormat.getTimezoneOffset()/-60) );
    this.date = notFormat.toJSON().slice(0, 10);
    return this.date
          

      },
   
    editFood(food) {
      this.showForm = true;
      this.clickedFood = food;
    },

    saveEditedFood(food) {
      FoodService.updateFood(food.foodId, food).then((response) => {
        if (response.status == 200) {
          this.showForm = false;
        }
      });
    },
    cancel() {
      this.showForm = false;
    },
    deleteFood(foodId) {
      MealService.getMealIdByMealDetails(
        this.mealType,
        this.date,
        this.$store.state.profile.profileId
      ).then((response) => {
        if (response.status == 200) {
          this.foodMeal.mealId = response.data;
          this.foodMeal.foodId = foodId;
          FoodMealService.deleteFoodMeal(
            this.foodMeal.mealId,
            this.foodMeal.foodId
       ); 
       alert('Your food has been deleted successfully')
       location.reload()
        }
      });
    },
  },
};
</script>

<style scoped>


button {

  border-radius: 4px;
  color: blue;
}
tr{
   display: grid;

  grid-template-columns: 500px 140px 105px 110px 400px 120px 120px ; 
  /* grid-template-columns: 30% 20% 20% 10% 20% 20% 20%; */
  grid-template-areas: "name foodType calories size servings edit delete"
}

#name{
  grid-area: name;
}
#foodType{
  grid-area: foodType;
}
#calories{
  grid-area: calories;
}
#size{
  grid-area: size;
}
#servings{
  grid-area: servings
}
#edit{
  grid-area: edit;
}
#delete {
  grid-area: delete;
  color: red;
}
#editForm{
  margin-left: 40%;
  margin-right: 40%
}







</style>