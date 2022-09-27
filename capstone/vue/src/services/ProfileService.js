import axios from "axios";

export default {

    getProfile(id) {
        axios.get(`profile/${id}`)
    },

    updateProfile(id, profile) {
        axios.put(`profile/${id}`, profile)
    },

    addProfile(profile) {
        axios.post('profile', profile)
    }
}