import axios from "axios";

export default {

    getProfile(username) {
        axios.get(`profile/${username}`)
    },

    updateProfile(id, profile) {
        axios.put(`profile/${id}`, profile)
    },

    addProfile(profile) {
        axios.post('profile', profile)
    }
}