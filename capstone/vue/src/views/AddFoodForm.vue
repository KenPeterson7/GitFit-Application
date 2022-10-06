<template>
  <div id="newFoodForm">
    <h1>New Food</h1>
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
      <button v-on:click="assignMealType(newFood)">Save</button>
  
      <button v-on:click="cancel()">Cancel</button>
    </form>
  </div>
</template>

<script>
import MealService from "../services/MealService";
import FoodService from "../services/FoodService";
import FoodMealService from "../services/FoodMealService";
import ProfileService from '../services/ProfileService';
export default {
  data() {
    return {
      profile: this.$store.state.profile,
      newFood: {},

      mealFoodObject: {
        foodId: 0,
        mealId: 0,
      },
      meal: {
        profileId: this.$store.state.profile.profileId,
        mealType: "",
        mealDate: this.formatDate()
      },
    };
  },
  methods: {
     formatDate(){
      const notFormat = new Date();
      let date = "";
     date = notFormat.setHours( notFormat.getHours()+(notFormat.getTimezoneOffset()/-60) );
    date = notFormat.toJSON().slice(0, 10);
    return date
          

      },
  
    logFood(newFood) {
      FoodService.addFood(newFood).then((response) => {
        if (response.status === 201) {
          this.mealFoodObject.foodId = response.data.foodId;
          console.log(this.mealFoodObject)
       
          
           this.addNewFood(this.mealFoodObject);
          //check and see if meal has an entry for that meal type, profile id, date
          // if yes, assign that returned meal id to foodmeal.mealId
          //if not enter an entry to the meal table and return that id
          //send in the food id and meal id to the meal_food table
        }
      });
    },
    assignMealType(newFood) {
    
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
              if(this.$store.state.profile.highStarStreak === this.$store.state.profile.starStreak){
                   this.profile.highStarStreak = this.profile.highStarStreak +1;
              }
             this.profile.starStreak = this.profile.starStreak +1;
             this.$store.commit('SET_CURRENT_PROFILE', this.profile)
              ProfileService.updateProfile(this.profile.profileId, this.profile)

               this.logFood(newFood)
           
            });
          } else{
         
         this.logFood(newFood)
          }
        }
      });
    },
    addNewFood(object) {
    console.log(object)
      FoodMealService.addFoodMeal(object);

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
h1 {
  margin-top: 20px;
  text-align: center;
}
button{
  border-radius: 4px;
}

</style>