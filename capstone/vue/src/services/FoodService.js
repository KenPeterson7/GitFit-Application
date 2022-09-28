import axios from "axios";

export default {

    getFood() {
        axios.get('Food')
    },

    addFood(food){
        axios.post('/food', food)
    }

}