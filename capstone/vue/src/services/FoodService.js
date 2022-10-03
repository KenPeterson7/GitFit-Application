import axios from "axios";

export default {

    getFoodByUsername(username) {
        return axios.get(`/food/user/${username}`)
     
    },
    
    addFood(food){
        return axios.post('/food', food)
    },
    updateFood(id, food){
        return axios.put(`/food/${id}`, food)
    },
    getFoodByUserMealDate(username, md, mt){
        return axios.get(`/food/${username}/${md}/${mt}`)
    },
    getPastFiveMeals(username, mt){
        return axios.get(`/food/user/recent/${username}/${mt}`)
    }

    

}