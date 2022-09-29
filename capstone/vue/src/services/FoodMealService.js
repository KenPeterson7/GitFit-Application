import axios from "axios";

export default {
    addFoodMeal(foodmeal){
        return axios.post('/foodmeal', foodmeal)
    }

}