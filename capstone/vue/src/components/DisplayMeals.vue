<template>
  <div>
    <div id="foodTable">
      <table
        id="foodTable"
        v-for="(food, index) in foodList"
        v-bind:key="index"
      >
        <td>{{ food.foodName }}</td>
        <div id="leftColumns">
          <td>{{ food.foodType }}</td>
          <td>{{ food.caloricAmount }}</td>
          <td>{{ food.size }}</td>
          <td>{{ food.numberOfServings }}</td>
        </div>
        <div id="rightButtons">
          <button v-on:click="editFood(food)">Edit Food</button>
          <button id="delete" v-on:click="deleteFood(food.foodId)">
            Delete Food
          </button>
        </div>
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
import FoodService from "../services/FoodService";
export default {
  props: ["foodList"],
  data() {
    return {
      showForm: false,
      clickedFood: {},

      foodMeal: {
        foodId: 0,
        mealId: 0,
      },
    };
  },

  methods: {
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
      //check and see if meal has an entry for that meal type, profile id, date
      // if yes, assign that returned meal id to foodmeal.mealId
      //if not enter an entry to the meal table and return that id
      //delete where mealId =foodmeal.mealId and foodId= foodId
      this.foodMeal.foodId=foodId
    },
  },
};
</script>

<style scoped>
table {
  display: flex;
  flex-direction: row;
}
#leftColumns {
  display: table;
  margin-left: 22%;
}
td {
  padding-right: 80px;
}
#rightButtons {
  margin-left: 10%;
}
#delete {
  color: red;
}
button {
  border-radius: 4px;
  color: blue;
}
</style>