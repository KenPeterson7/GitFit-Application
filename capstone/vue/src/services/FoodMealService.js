import axios from "axios";

export default {
    addFoodMeal(foodmeal){
        return axios.post('/foodmeal', foodmeal)
    },
    deleteFoodMeal( mealId, foodId){
        return axios.delete(`/${mealId}/${foodId}`)
    }

}