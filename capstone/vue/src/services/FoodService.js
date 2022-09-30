import axios from "axios";

export default {

    getFoodByUsername(username) {
        return axios.get(`/food/user/${username}`)
     
    },
    
    addFood(food){
        return axios.post('/food', food)
    }
    

}