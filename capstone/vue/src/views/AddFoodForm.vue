<template>
  <div id="newFoodForm">
    <h3>NEW FOOD</h3>
    <form v-on:submit.prevent>
      <label for="foodName"> Food Name: </label>
      <input type="text" id="foodName" v-model="newFood.foodName" />
      <label for="foodType"> Food Type: </label>
      <select id="foodType" v-model="newFood.foodType">
        <option>Dairy</option>
        <option>Meat</option>
        <option>Poultry</option>
        <option>Produce</option>
        <option>Carbs</option>
        <option>Drinks</option>
      </select>

      <label for="calories"> Calories: </label>
      <input type="text" id="calories" v-model="newFood.caloricAmount" />
      <label for="size"> Size: </label>
      <input type="text" id="size" v-model="newFood.size" />

      <label for="servings"> Number of Servings: </label>
      <input type="number" id="servings" v-model="newFood.numberOfServings" />

      <label for="selection"
        >Please Choose The Meal You Would Like To Add Your Food To:</label
      >
      <select id="selection" v-model="meal.mealType">
        <option>Breakfast</option>
        <option>Lunch</option>
        <option>Dinner</option>
        <option>Snacks</option>
      </select>
      <button v-on:click="assignMealType()">Confirm</button>
      <button v-on:click="logFood(newFood)">Confirm 2</button>
      <button type="submit" v-on:click="addNewFood()">Save</button>
      <button v-on:click="cancel()">Cancel</button>
    </form>
  </div>
</template>

<script>
import MealService from "../services/MealService";
import FoodService from "../services/FoodService";
import FoodMealService from "../services/FoodMealService";
export default {
  data() {
    return {
      newFood: {},

      mealFoodObject: {
        foodId: 0,
        mealId: 0,
      },
      meal: {
        profileId: this.$store.state.profile.profileId,
        mealType: "",
        mealDate:
          new Date().getFullYear() +
          "-" +
          this.getMonth() +
          "-" +
          0 +
          new Date().getDate(),
      },
    };
  },
  methods: {
    getMonth() {
      this.month = new Date().getMonth();
      return this.month + 1;
    },
    logFood(newFood) {
      FoodService.addFood(newFood).then((response) => {
        if (response.status === 201) {
          this.mealFoodObject.foodId = response.data.foodId;
          console.log(this.mealFoodObject.foodId);
          //check and see if meal has an entry for that meal type, profile id, date
          // if yes, assign that returned meal id to foodmeal.mealId
          //if not enter an entry to the meal table and return that id
          //send in the food id and meal id to the meal_food table
        }
      });
    },
    assignMealType() {
      MealService.getMealIdByMealDetails(
        this.meal.mealType,
        this.meal.mealDate,
        this.$store.state.profile.profileId
      ).then((response) => {
        if (response.status == 200) {
          this.mealFoodObject.mealId = response.data;

          if (this.mealFoodObject.mealId === 0) {
            MealService.addMeal(this.meal).then((response) => {
              this.mealFoodObject.mealId = response.data.mealId;
            });
          }
        }
      });
    },
    addNewFood() {
      FoodMealService.addFoodMeal(this.mealFoodObject);

      this.$router.push({ name: "recentFoods" });
    },
    cancel() {
      this.$router.push({ name: "recentFoods" });
    },
  },
};
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
h3 {
  margin-top: 20px;
  text-align: center;
}
</style>