<template>
  <div>
    <div id="foodTable">
      <table
        id="foodTable"
        v-for="(food, index) in foodList"
        v-bind:key="index"
      >
        <thead id="header">
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
        </thead>
        <tbody>
          <td>{{ food.foodName }}</td>

          <td>{{ food.foodType }}</td>
          <td>{{ food.caloricAmount }}</td>
          <td>{{ food.size }}</td>
          <td>{{ food.numberOfServings }}</td>

          <td><button v-on:click="editFood(food)">Edit Food</button></td>
          <td>
            <button id="delete" v-on:click="deleteFood(food.foodId)">
              Delete Food
            </button>
          </td>
        </tbody>
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
      month: 0,
      date:
        new Date().getFullYear() +
        "-" +
        0 +
        this.getMonth() +
        "-" +
        new Date().getDate(),
    };
  },

  methods: {
    getMonth() {
      this.month = new Date().getMonth();
      return this.month + 1;
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
table {
  display: flex;
  flex-direction: row;
}

#delete {
  color: red;
}
button {
  border-radius: 4px;
  color: blue;
}
td {
  margin-left: 20%;
}
</style>