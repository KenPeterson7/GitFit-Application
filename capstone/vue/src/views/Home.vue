<template>
  <div class="home">
    <!-- <nav>
      <button v-on:click="clickHome()" v-bind:class="{selected: homeSelected}">Home</button><br>
      <button v-on:click="clickProfile()" v-bind:class="{selected: profileSelected}">Profile</button><br>
      <button v-on:click="clickLogMeal()" v-bind:class="{selected: logMealSelected}">Log a Meal</button><br>
      <button v-on:click="clickLogWorkout()" v-bind:class="{selected: logWorkoutSelected}">Log a Workout</button><br>
      <h3>Another Thing</h3>
      <h3>Something Else</h3>
    </nav> -->
    <div id="componentDiv">
      
      <home-page v-if="homeSelected" />
      <profile v-if="profileSelected"/>
      <log-meal v-if="logMealSelected"/>
      <log-workout v-if="logWorkoutSelected"/>
      
    </div>
    
  </div>
</template>

<script>
import HomePage from '../components/HomePage.vue';
import LogWorkout from './LogWorkout.vue';
import Profile from './Profile.vue';
import LogMeal from './LogMeal.vue'
export default {
  components: { Profile, HomePage, LogWorkout, LogMeal},
  name: "home",
  data() {
    return {
      homeSelected: true,
      profileSelected: false,
      logMealSelected: false,
      logWorkoutSelected: false
    }
  },
  methods: {
    clickHome(){
      this.homeSelected = true;
      this.profileSelected = false;
      this.logMealSelected = false;
      this.logWorkoutSelected = false;
    },
    clickProfile(){
      this.homeSelected = false;
      this.profileSelected = true;
      this.logMealSelected = false;
      this.logWorkoutSelected = false;
    },clickLogMeal(){
      this.homeSelected = false;
      this.profileSelected = false;
      this.logMealSelected = true;
      this.logWorkoutSelected = false;
    },clickLogWorkout(){
      this.homeSelected = false;
      this.profileSelected = false;
      this.logMealSelected = false;
      this.logWorkoutSelected = true;
    }
  }
  
};
</script>

<style scoped>
.home{
  display: flex;
  height: 700px;
}

.home nav {
  min-width: 150px;
  border-right: 1px;
  border-right-style: solid;
  background-color: rgb(0, 125, 255);
  color: whitesmoke;
  
  border-radius: 5px;
  text-align: center;
  
  
}

h3 {
  padding-left: 20px;
  padding-right: 20px;
}

#componentDiv{
  flex-grow: 8;
  background-image: url("../../public/Images/gym-background2.png");
  background-color: lightgray;
  background-blend-mode: screen;  
  background-size: cover;
  background-repeat: no-repeat;
}

.selected{
  background-color: rgb(0, 67, 127);
  color: white;
}

div {
    margin-left: 2.5px;
}

nav button {
  margin: 15px;
  width: 75%;
  color: blue;
  border-radius: 5px;
  
}
</style>