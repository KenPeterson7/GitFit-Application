<template>
<div class="logWorkout">
  <h1>Workouts & Activities</h1>
  <router-link v-bind:to="{name: 'addWorkout'}"><button id="addWorkout">Add Workout</button></router-link>
  <workout />
</div>
</template>

<script>
import Workout from '../components/Workout.vue'
export default {
  components: { Workout },

}
</script>

<style>
  #addWorkout{
    position: sticky;
    margin-left: 73.5%;
    width: 130px;
    
  }

</style>