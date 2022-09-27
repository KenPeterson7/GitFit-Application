import axios from "axios";

export default {

    getProfile(username) {
        return axios.get(`/profile/${username}`)
    },

    updateProfile(id, profile) {
        return axios.put(`/profile/${id}`, profile)
    },

    addProfile(profile) {
        return axios.post('/profile', profile)
    }
}