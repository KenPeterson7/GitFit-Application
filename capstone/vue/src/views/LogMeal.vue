<template>
  <div id="mealPage">
    <p id="header">
      My Meals For:
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
    <display-meals v-bind:foodList="breakfastFoods"/>
   
    <h4>LUNCH</h4>
       <display-meals v-bind:foodList="lunchFoods"/>
 
    <h4>DINNER</h4>
    <display-meals v-bind:foodList="dinnerFoods"/>
  

    <h4>SNACKS</h4>
       <display-meals v-bind:foodList="snackFoods"/>
    <router-link v-bind:to="{ name: 'recentFoods' }"
      ><button id="add">Add Food</button></router-link
    >
  </div>
</template>

<script>
import FoodService from "../services/FoodService";
import DisplayMeals from "../components/DisplayMeals.vue"

export default {
  components: { DisplayMeals },
  name: "log-meal2",

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
        //get food by username, date, mealtype
      FoodService.getFoodByUsername(this.$store.state.user.username).then((response) => {
        if (response.status == 200) {
          this.dinnerFoods = response.data;
          return this.dinnerFoods;
        }
      });
    },
    editFood(){
     
    }
  },
};
</script>

<style scoped>
#header {
  margin-top: 20px;
 text-align: center;
  color:blue;

}
#headerTable {
  margin-left: 30%;
}
#headerTable th {
  padding-right: 50px;
}

button{
  border-radius: 4px;
  color: blue;
  
}
#add{
 margin-left: 45%;
}


</style>