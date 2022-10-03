<template>
  <div id="quickAddMeals">
     <h3>My Recent Meals</h3>
     <h3>Breakfast</h3>
     <h3>Lunch</h3>
     <h3>Dinner</h3>
     <h3>Snacks</h3>

  </div>
</template>

<script>
import FoodService from '../services/FoodService'
export default {
    data(){
        return{
            breakfastList: [],
            lunchList: [],
            dinnerList: [],
            snacksList: [],

      
        }
    },
    created(){
        this.updateBreakfast()

    },
    methods: {
        updateBreakfast(){
            FoodService.getPastFiveMeals(this.$store.state.user.username, 'Breakfast').then((response)=> {
                if (response.status == 200){
                    this.breakfastList = response.data
                }
            })
        }

    }

}
</script>

<style>

</style>