import axios from "axios";

export default {
    getMealIdByMealDetails(mealType, mealDate,pid){
        return axios.get(`/meal/${mealType}/${mealDate}/${pid}`)
    },
    addMeal(meal){
        return axios.post(`/meal`, meal)
    },

}