import axios from "axios";

export default {

    getFood() {
        return axios.get('/food')
    },
    
    addFood(food){
        return axios.post('/food', food)
    }

}