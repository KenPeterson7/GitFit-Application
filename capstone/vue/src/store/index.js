import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));
const currentProfile = JSON.parse(localStorage.getItem('profile'));
const currentGoal = JSON.parse(localStorage.getItem('goal'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    profile: currentProfile || {},
    goal: currentGoal || {},
    calories: 0
    
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
      
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      localStorage.removeItem('profile');
      localStorage.removeItem('goal');
      state.token = '';
      state.user = {};
      state.profile = null;
      state.goal = null;
      state.calories = 0;
      axios.defaults.headers.common = {};
      
    },
    SET_CURRENT_PROFILE(state, profile) {
      state.profile = profile;
      localStorage.setItem('profile',JSON.stringify(profile));
    },
    SET_CURRENT_GOAL(state, goal) {
      state.goal = goal; 
      localStorage.setItem('goal',JSON.stringify(goal));
    },
    UPDATE_PROFILE_PICTURE(state, picture) {
      state.profile.profilePic = picture;
    },
    UPDATE_CALORIES(state, calories) {
      state.calories = calories;
    }
    
  }
})
