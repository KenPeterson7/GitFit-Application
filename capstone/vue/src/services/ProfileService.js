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
    },
    getGoal(profileId){
        return axios.get(`/profile/goal/${profileId}`)
    },

    createGoal(goal){
        return axios.post('/profile/goal', goal)
    },

    updateGoal(profileId,goal){
        return axios.put(`/profile/goal/${profileId}`, goal)
    },

    deleteGoal(profileId){
        return axios.delete(`/profile/goal/${profileId}`)
    }
}