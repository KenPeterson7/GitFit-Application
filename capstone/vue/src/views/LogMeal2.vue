<template>
  <div id="mealPage">
    <p id="header">
      Your Food Diary For:
      {{
        new Date().getMonth() +
        "/" +
        new Date().getDate() +
        "/" +
        new Date().getFullYear()
      }}
    </p>
    <table id="headerTable">
      <thead>
        <th>Food-Type</th>
        <th>Calories</th>
        <th>Size</th>
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
      <div id="rightButtons">
        <button>Edit Food</button>
        <button id="delete">Delete Food</button>
      </div>
    </table>
   
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
      <div id="rightButtons">
        <button>Edit Food</button>
        <button id="delete">Delete Food</button>
      </div>
    </table>
 
    <h4>DINNER</h4>
    <meal-table v-bind:foodList="dinnerFoods" />
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
        <button id="delete">Delete Food</button>
      </div>
    </table>

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
      <div id="rightButtons">
        <button>Edit Food</button>
        <button id="delete">Delete Food</button>
      </div>
    </table>
    <router-link v-bind:to="{ name: 'recentFoods' }"
      ><button id="add">Add Food</button></router-link
    >
  </div>
</template>

<script>
import FoodService from "../services/FoodService";
export default {
  name: "log-meal",

  data() {
    return {
      breakfastFoods: [],
      lunchFoods: [],
      dinnerFoods: [],
      snackFoods: [],
    };
  },
  created() {
    this.updateDinnerList();
  },

  methods: {
    updateDinnerList() {
      FoodService.getFood().then((response) => {
        if (response.status == 200) {
          this.dinnerFoods = response.data;
          return this.dinnerFoods;
        }
      });
    },
  },
};
</script>

<style scoped>
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
#add{
 margin-left: 45%;
}


</style>