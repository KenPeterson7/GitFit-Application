<template>
  <div id="quickAddMeals">
      <h3 id = "header">YESTERDAY'S MEALS</h3>
       <table id="headerTable">
      <thead>
        <th>Food-Type</th>
        <th>Calories</th>
        <th>Size</th>
        <th>Number-Of-Servings</th>
      </thead>
   </table>
    
    <h3>Breakfast</h3>
    
    
    <table>
          <tr  v-for="(food, index) in breakfastFoods"
        v-bind:key="index">
        <td id="name">{{ food.foodName }}</td>
      

          <td id = "foodType">{{ food.foodType }}</td>
          <td id = "calories">{{ food.caloricAmount }}</td>
          <td id = "size">{{ food.size }}</td>
          <td id = "servings">{{ food.numberOfServings }}</td>
        </tr>
    </table>
    <button v-on:click="assignMealType(breakfastFoods,'Breakfast')">Quick Add All Foods in Breakfast</button>
    <h3>Lunch</h3>
     <table>
          <tr  v-for="(food, index) in lunchFoods"
        v-bind:key="index">
        <td id="name">{{ food.foodName }}</td>
      

          <td id = "foodType">{{ food.foodType }}</td>
          <td id = "calories">{{ food.caloricAmount }}</td>
          <td id = "size">{{ food.size }}</td>
          <td id = "servings">{{ food.numberOfServings }}</td>
        </tr>
    </table>
     <button v-on:click="assignMealType(lunchFoods,'Lunch')">Quick Add All Foods in Lunch</button>
    <h3>Dinner</h3>
     <table>
          <tr  v-for="(food, index) in dinnerFoods"
        v-bind:key="index">
        <td id="name">{{ food.foodName }}</td>
      

          <td id = "foodType">{{ food.foodType }}</td>
          <td id = "calories">{{ food.caloricAmount }}</td>
          <td id = "size">{{ food.size }}</td>
          <td id = "servings">{{ food.numberOfServings }}</td>
        </tr>
    </table>
     <button v-on:click="assignMealType(dinnerFoods,'Dinner')">Quick Add All Foods in Dinner</button>
    <h3>Snacks</h3>
      <table>
          <tr  v-for="(food, index) in snackFoods"
        v-bind:key="index">
        <td id="name">{{ food.foodName }}</td>
      

          <td id = "foodType">{{ food.foodType }}</td>
          <td id = "calories">{{ food.caloricAmount }}</td>
          <td id = "size">{{ food.size }}</td>
          <td id = "servings">{{ food.numberOfServings }}</td>
        </tr>

    </table>
     <button v-on:click="assignMealType(snackFoods,'Snacks')">Quick Add All Foods in Snacks</button>
    <router-link v-bind:to="{ name: 'myMeals' }">
      <button id="returnBtn">Back To My Meals</button>
    </router-link>
  </div>
</template>

<script>
import FoodService from "../services/FoodService";
import MealService from "../services/MealService";
import FoodMealService from "../services/FoodMealService";
export default {
  data() {
    return {
  
      breakfastFoods: [],
      lunchFoods: [],
      dinnerFoods: [],
      snackFoods: [],
    
      date: new Date(),
      formatedDate:this.formatDate()
         ,
  
      yesterday: this.getPreviousDay(),
      meal: {
        profileId: this.$store.state.profile.profileId,
        mealType: "",
        mealDate:this.formatDate()
        },
    
      mealFoodObject: {
        mealId: 0,
        foodId: 0,
      },
    };
  },
  created() {
    this.updateBreakfastList();
    this.updateLunchList();
    this.updateDinnerList();
    this.updateSnacksList();
    this.formatDate();
  },
  
  methods: {
          assignMealType(foodList, mealType) {
            this.meal.mealType = mealType;
            
    foodList.forEach((food) => {
        console.log(food)
           
          
            MealService.getMealIdByMealDetails(
        mealType,
        this.formatedDate,
        this.$store.state.profile.profileId
      ).then((response) => {
        if (response.status == 200) {
          this.mealFoodObject.mealId = response.data;
     
          if (this.mealFoodObject.mealId === 0) {
            MealService.addMeal(this.meal).then((response) => {
              this.mealFoodObject.mealId = response.data.mealId;
          this.addFood(food.foodId);
     
            });
          } else{
           this.addFood(food.foodId);
          }
          
        }
      });

    })
     this.$router.push({name: 'recentFoods'})
     
    },
     addFood(foodId) {
        
    this.mealFoodObject.foodId = foodId;
      FoodMealService.addFoodMeal(this.mealFoodObject);

    },


      formatDate(){
      
     const notFormat = new Date();
     this.date = notFormat.setHours( notFormat.getHours()+(notFormat.getTimezoneOffset()/-60) );
    this.date = notFormat.toJSON().slice(0, 10);
    return this.date

      },
    updateBreakfastList() {
      FoodService.getFoodByUserMealDate(
        this.$store.state.user.username,
        this.yesterday,
        "Breakfast"
      ).then((response) => {
        if (response.status == 200) {
          this.breakfastFoods = response.data;
          return this.breakfastFoods;
        }
      });
    },
    updateLunchList() {
      FoodService.getFoodByUserMealDate(
        this.$store.state.user.username,
        this.yesterday,
        "Lunch"
      ).then((response) => {
        if (response.status == 200) {
          this.lunchFoods = response.data;
          return this.lunchFoods;
        }
      });
    },
    updateDinnerList() {
      FoodService.getFoodByUserMealDate(
        this.$store.state.user.username,
        this.yesterday,
        "Dinner"
      ).then((response) => {
        if (response.status == 200) {
          this.dinnerFoods = response.data;
          return this.dinnerFoods;
        }
      });
    },
    updateSnacksList() {
      FoodService.getFoodByUserMealDate(
        this.$store.state.user.username,
        this.yesterday,
        "Snacks"
      ).then((response) => {
        if (response.status == 200) {
          this.snackFoods = response.data;
          return this.snackFoods;
        }
      });
    },
    getPreviousDay() {
        let date = new Date();
      let previous = "";
      previous= date.setHours( date.getHours()+(date.getTimezoneOffset()/-60) ); 
       previous = date.setDate(date.getDate() - 1);
     previous = date.toJSON().slice(0, 10);
          
      
          
 
    
      console.log(previous)
      return previous;

    },
  },
};
</script>

<style scoped>
#header {
  margin-top: 20px;
 text-align: center;

  margin-bottom: 30px;

}
#quickAddMeals {
  margin-left: 20px;
}
button {
  border-radius: 4px;
  color: blue;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: 80%;
 
}
table{
    border-collapse: collapse;
}
tr{
     border-bottom: 1pt solid black;
   display: grid;

  grid-template-columns: 530px 140px 105px 110px 400px  ; 
  /* grid-template-columns: 30% 20% 20% 10% 20% 20% 20%; */
  grid-template-areas: "name foodType calories size servings "
  
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
#headerTable {
  margin-left: 30%;
}
#headerTable th {
  padding-right: 50px;
}
#returnBtn{
    margin-left: 0;
}
</style>