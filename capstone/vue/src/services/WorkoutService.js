import axios from "axios";

export default {
    getWorkoutByWorkoutId(id) {
        return axios.get(`/workout/${id}`)
    },

    getListOfAllWorkoutsByUsername(username) {
        return axios.get(`/workout/user/${username}`)
    },

    getListOfAllWorkoutsByDate(date) {
        return axios.get(`/ workout / date / ${date}`)
    },
    addWorkout(workout) {
        return axios.post('/workout', workout)
    },
    updateWorkout(id, workout) {
        return axios.put(`/workout/${id}`, workout)
    },
    deleteWorkout(id) {
        return axios.delete(`/workout/${id}`)
    },
    getListOfLastThreeWorkouts(username) {
        return axios.get(`/workout/user/recent/${username}`)
    }
}