<template>
  <div id="recentFoods">
    <h1>Saved Foods</h1>
    <div id="table">
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
    </div>
    <div id="navBtns">
      <router-link v-bind:to="{ name: 'myMeals' }">
        <button id="returnBtn">Back To My Meals</button>&nbsp;
      </router-link>
      <router-link v-bind:to="{ name: 'addFoodForm' }">
        <button id="addBtn">Add New Food</button>&nbsp;
      </router-link>
      <router-link v-bind:to="{name: 'quickAddMeals'}">
      <button>Quick Add Yesterday's Meals</button>&nbsp;
      </router-link>
    </div>

    <div id="mealSelection" v-if="showMealSelection">
      <label for="selection"
        >Please Choose The Meal You Would Like To Add Your Food To</label
      >
      <select id="selection" v-model="meal.mealType">
        <option>Breakfast</option>
        <option>Lunch</option>
        <option>Dinner</option>
        <option>Snacks</option>
      </select>
 
    </div>
    <div id="addCancel" v-if="showAddCancelBtns">
      <button v-on:click="assignMealType()">Save Food</button>&nbsp;
      <button v-on:click="cancel()">Cancel</button>
    </div>
    <div id="foodBanner" v-if="addFoodBanner">
      <h3>
        You do not have any food saved. Click ADD FOOD to begin your journey!
      </h3>
    </div>
  </div>
</template>

<script>
import MealService from "../services/MealService";
import FoodService from "../services/FoodService";
import FoodMealService from "../services/FoodMealService";
import ProfileService from '../services/ProfileService';
export default {
  name: "recentFoods",
  data() {
    return {
       profile: this.$store.state.profile,
      mySavedFoods: [],
      showMealSelection: false,
      showAddCancelBtns: false,

      mealFoodObject: {
        mealId: 0,
        foodId: 0,
      },
      addFoodBanner: false,
      date:
       this.formatDate(),
      meal: {
        profileId: this.$store.state.profile.profileId,
        mealType: "",
        mealDate:this.formatDate()}
    };
  },
  created() {
    this.updateList();
  },
  methods: {
   formatDate(){
      const notFormat = new Date();
     this.date = notFormat.setHours( notFormat.getHours()+(notFormat.getTimezoneOffset()/-60) );
    this.date = notFormat.toJSON().slice(0, 10);
    return this.date
          

      },
    updateList() {
      FoodService.getFoodByUsername(this.$store.state.user.username).then(
        (response) => {
          if (response.status == 200) {
            this.mySavedFoods = response.data;
            let foodArray= [];
           this.mySavedFoods = this.mySavedFoods.filter((food) => {
              if (foodArray.includes(food.foodId)){
                  return false;
              } else{
                foodArray.push(food.foodId);
                console.log(food)
                console.log(foodArray)
                return true;
              }

            })

           
          }
          if (this.mySavedFoods.length < 1) {
            this.addFoodBanner = true;
          }
        }
      );
    },
    addSavedFood(foodId) {
      this.showMealSelection = true;
      this.showAddCancelBtns = true;
      this.mealFoodObject.foodId = foodId;
    },
    assignMealType() {
      MealService.getMealIdByMealDetails(
        this.meal.mealType,
        this.date,
        this.$store.state.profile.profileId
      ).then((response) => {
        if (response.status == 200) {
          this.mealFoodObject.mealId = response.data;
     
          if (this.mealFoodObject.mealId === 0) {
            MealService.addMeal(this.meal).then((response) => {
              this.mealFoodObject.mealId = response.data.mealId;
                 if(this.$store.state.profile.highStarStreak === this.$store.state.profile.starStreak){
                   this.profile.highStarStreak = this.profile.highStarStreak +1;
              }
                this.profile.starStreak = this.profile.starStreak +1;
             this.$store.commit('SET_CURRENT_PROFILE', this.profile)
              ProfileService.updateProfile(this.profile.profileId, this.profile)
          this.addFood();
        
            });
          } else{
           this.addFood();
          }
        }
      });
    },
    addFood() {

      //check and see if there is a meal id for this profile, date, meal type. if not,
      //insert profileid, mealtype, and date and return meal id
      //set the returned meal id to mealFoodObject.mealId
      this.showMealSelection = false;
      this.showAddCancelBtns = false;
      FoodMealService.addFoodMeal(this.mealFoodObject);

    },
    cancel() {
      this.showMealSelection = false;
      this.showAddCancelBtns = false;
    },
  },

};
</script>


<style scoped>
table{
    border-collapse: collapse;
}
tr{
     border-bottom: 1pt solid black;
}
h3 {
  margin-top: 20px;
  text-align: center;
}
th {
  padding-right: 30px;
  padding-top: 20px;
}
#navBtns {
   display: flex;
  justify-content: center;
  margin-top: 20px;

  padding: 10px;
  
}

button {
  border-radius: 4px;
  margin-left: 7px;

}
#table{
  display: flex;
  justify-content: center;
}
tr:nth-child(odd){
   background-color: rgba(173, 216, 230, 0.7)
}
#mealSelection{
  margin-top: 20px;
  text-align: center;
}
#addCancel{
   text-align: center;
}
</style>