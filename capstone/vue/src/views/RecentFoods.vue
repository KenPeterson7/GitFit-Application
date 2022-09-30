<template>
  <div id="recentFoods">
    <h3>SAVED FOODS</h3>

    <table id="headerTable">
      <thead>
        <th>Name</th>
        <th>Food-Type</th>
        <th>Calories</th>
        <th>Size</th>
        <th>Number-Of-Servings</th>
        <th></th>
      </thead>
      <tbody>
        <tr v-for="food in mySavedFoods" v-bind:key="food.id">
          <td>{{ food.foodName }}</td>
          <td>{{ food.foodType }}</td>
          <td>{{ food.caloricAmount }}</td>
          <td>{{ food.size }}</td>
          <td>{{ food.numberOfServings }}</td>
          <button v-on:click="addSavedFood(food.foodId)">Add Food</button>
        </tr>
      </tbody>
    </table>
    <div id="navBtns">
   <router-link v-bind:to="{name: 'myMeals'}">
<button id="returnBtn">Back To My Meals</button>
    </router-link>
    <router-link v-bind:to="{name: 'addFoodForm'}">
    
    <button id="addBtn">Add New Food</button>
    </router-link>
    </div>
 
    <div id="mealSelection" v-if="showMealSelection">
      <label for="selection"
        >Please Choose The Meal You Would Like To Add Your Food To</label
      >
      <select id="selection" v-model="mealType" v-on:click="assignMealType()">
        <option>Breakfast</option>
        <option>Lunch</option>
        <option>Dinner</option>
        <option>Snacks</option>
      </select>
    </div>
    <div id="addCancel" v-if="showAddCancelBtns">
        <button v-on:click="addFood()">Save Food</button>
        <button v-on:click="cancel()">Cancel</button>
    </div>
    <div id="foodBanner" v-if="addFoodBanner">
        <h3>You do not have any food saved. Click ADD FOOD to begin your journey!</h3>
    </div>
  </div>
</template>

<script>
import FoodService from "../services/FoodService";
import FoodMealService from "../services/FoodMealService"
export default {
  name: "recentFoods",
  data() {
    return {
      mySavedFoods: [],
      showMealSelection: false,
      showAddCancelBtns: false,
        mealType: "",
      mealFoodObject: {
          mealId: 0, 
          foodId: 0,
      },
      addFoodBanner: false,

    };

  },
  created() {
    this.updateList();
  },
  methods: {
    updateList() {
      FoodService.getFoodByUsername(this.$store.state.user.username).then((response) => {
        if (response.status == 200) {
          this.mySavedFoods = response.data;
        }
        if(this.mySavedFoods.length < 1){
            this.addFoodBanner = true
        }
      });
    },
    addSavedFood(foodId){
        this.showMealSelection=true;
        this.mealFoodObject.foodId = foodId;

    } ,
    assignMealType(){
      this.showAddCancelBtns = true;
      if (this.mealType ==='Breakfast'){
          this.mealFoodObject.mealId = 5001
      }
      else if (this.mealType === "Lunch"){
          this.mealFoodObject.mealId = 5002
      }
      else if(this.mealType === 'Dinner'){
          this.mealFoodObject.mealId = 5003
      }
      else if(this.mealType === 'Snacks'){
          this.mealFoodObject.mealId = 5004
      }
  },
  addFood(){
       this.showMealSelection= false
       this.showAddCancelBtns = false
       FoodMealService.addFoodMeal(this.mealFoodObject).then((response) => {
           if(response.status === 200){
               this.mealFoodObject.mealId =0
               this.mealFoodObject.foodId=0
           }
       })
  },
  cancel(){
       this.showMealSelection= false
       this.showAddCancelBtns = false
  }
  },
 
};
</script>


<style scoped>
h3 {
  text-align: center;
}
th {
  padding-right: 30px;
  padding-top: 20px;
}
#navBtns{
    margin-top: 20px;
    margin-left: 76%;
    padding: 10px;
}

button{
  border-radius: 4px;
  color: blue;
  
}
</style>