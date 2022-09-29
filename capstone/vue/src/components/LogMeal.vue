<template>
  <div id="mealPage">
    <div id="myMeals" v-if="!showForm">
      <p id="header">Your Food Diary For: {{ new Date().getMonth() + '/' + new Date().getDate() + '/' + new Date().getFullYear() }}</p>
      <table id="headerTable">
        <thead>
          <th>Food-Type</th>
          <th>Calories</th>
          <th>Size </th>
          <th>Number-Of-Servings</th>
        </thead>
      </table>
      <h4>BREAKFAST</h4>

      <table
        id="breakfastTable"
        v-for="(food, index) in breakfastFoods"
        v-bind:key="index"
      >
        <td>{{ food.foodName }}</td>
        <div id="leftColumns">
          <td>{{ food.foodType }}</td>
          <td>{{ food.caloricAmount }}</td>
          <td>{{ food.size }}</td>
          <td>{{ food.numberOfServings }}</td>
          
        </div>
      </table>
      <button v-on:click="addFood('Breakfast')">Add Food</button>
      <h4>LUNCH</h4>
      <table
        id="lunchTable"
        v-for="(food, index) in lunchFoods"
        v-bind:key="index"
      >
        <td>{{ food.foodName }}</td>
        <div id="leftColumns">
          <td>{{ food.foodType }}</td>
          <td>{{ food.caloricAmount }}</td>
          <td>{{ food.size }}</td>
          <td>{{ food.numberOfServings }}</td>
        </div>
      </table>
      <button v-on:click="addFood('Lunch')">Add Food</button>
      <h4>DINNER</h4>
      <meal-table v-bind:foodList="dinnerFoods"/>
      <table
        id="dinnerTable"
        v-for="(food, index) in dinnerFoods"
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
       <button>Edit Food</button>
        <button id="delete" >Delete Food</button>
        </div>
      </table>
      <button v-on:click="addFood('Dinner')">Add Food</button>
      <h4>SNACKS</h4>
      <table
        id="snackTable"
        v-for="(food, index) in snackFoods"
        v-bind:key="index"
      >
        <td>{{ food.foodName }}</td>
        <div id="leftColumns">
          <td>{{ food.foodType }}</td>
          <td>{{ food.caloricAmount }}</td>
          <td>{{ food.size }}</td>
          <td>{{ food.numberOfServings }}</td>
        </div>
      </table>
      <button v-on:click="addFood('Snacks')">Add Food</button>
    </div>
    <div id="newFoodForm" v-if="showForm">
      <form v-on:submit.prevent>
        <label for="foodName"> Food Name: </label>
        <input type="text" id="foodName" v-model="newFood.foodName" />
        <label for="foodType"> Food Type: </label>
        <input type="text" id="foodType" v-model="newFood.foodType" />
        <label for="mealType"> Meal: </label>
        <select id="mealType" v-model="newFood.mealType">
          <option>Breakfast</option>
          <option>Lunch</option>
          <option>Dinner</option>
          <option>Snacks</option>
        </select>
        <label for="calories"> Calories: </label>
        <input type="text" id="calories" v-model="newFood.caloricAmount" />
        <label for="size"> Size: </label>
        <input type="text" id="size" v-model="newFood.size"/>
        
        <label for="servings"> Number of Servings: </label>
        <input type="number" id="servings" v-model="newFood.numberOfServings" />
        <button type="submit" v-on:click="logFood(newFood)">Save</button>
        <button v-on:click="cancel()">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import FoodService from "../services/FoodService";
import MealTable from './MealTable.vue';

export default {
  name: "log-meal",
  components: {
    MealTable
    },

  data() {
    return {
      breakfastFoods: [],
      lunchFoods: [],
      dinnerFoods: [],
      snackFoods: [],
      showForm: false,

      newFood: {
        mealType: "",
      },
    };
  },
  created() {
   this.updateDinnerList();

  },

  methods: {

    updateDinnerList(){
      FoodService.getFood().then((response) => {
      if (response.status == 200) {
        this.dinnerFoods = response.data;
        return this.dinnerFoods;
      }
    });
    },
  
    addFood(meal) {
      this.newFood.mealType = meal;
      this.showForm = true;
    },
    logFood(newFood) {
      FoodService.addFood(newFood).then((response) => {
        if (response.status == 200) {
          this.showForm = false;
          this.updateDinnerList();

        }
      });
    },
    cancel() {
      this.showForm = false;
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
}
#newFoodForm form label {
  margin-top: 10px;
}

#newFoodForm form button {
  margin-top: 10px;
  
}
#header {
  margin-top: 20px;
  display: flex;
  color:blue;
  justify-content: center;
}
#headerTable {
  margin-left: 30%;
}
#headerTable th {
  padding-right: 50px;
}
#breakfastTable {
  display: flex;
  flex-direction: row;
}
#lunchTable {
  display: flex;
  flex-direction: row;
}
#dinnerTable {
  display: flex;
  flex-direction: row;
}
#snackTable {
  display: flex;
  flex-direction: row;
}
#leftColumns {
  display: table;
 margin-left: 21%;

}
td {
 
  padding-right: 80px;
}
#rightButtons{

margin-left: 15%;

}
#delete{
 color: red;
 
}
button{
  border-radius: 4px;
  color: blue;
  
}

</style>