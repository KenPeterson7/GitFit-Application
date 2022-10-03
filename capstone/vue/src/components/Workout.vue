<template>
  <div id="workout">
    <h1>Workouts & Activities</h1>
    <table id="workoutTable">
      <thead id="header">
        <tr>
          <th>Name of Workout:</th>
          <th>Type of Workout:</th>
          <th>Duration of Workout:</th>
          <th>Date of Workout:</th>
          <th>Calories Burned:</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="workout in mySavedWorkouts" :key="workout.id">
          <td>{{ workout.nameOfWorkout }}</td>
          <td>{{ workout.typeOfWorkout }}</td>
          <td>{{ workout.duration }}</td>
          <td>{{ workout.workoutDate }}</td>
          <td>{{ workout.caloriesBurned }}</td>
        </tr>
      </tbody>
    </table>
    <span>Total Calories Burned:</span>
  </div>
</template>

<script>
import workoutService from "../services/WorkoutService";

export default {
  name: "workout",
  data() {
    return {
      mySavedWorkouts: [],
      calories: '',
    };
  },
  created() {
    this.getWorkoutsByUsername();
  },
  methods: {
    getWorkoutsByUsername() {
      workoutService
        .getListOfAllWorkoutsByUsername(this.$store.state.user.username)
        .then((response) => {
          if (response.status == 200) {
            this.mySavedWorkouts = response.data;
          }
        });
    },
    
  },
  
  };
</script>

<style>

span {
  position: fixed;
    margin-top: 20px;
    padding-top: 425px;
}

#workout {
  height: 600px;
}
#workoutTable {
  margin-left: 22.5%;
}

#workoutTable th {
  padding-right: 50px;
}

#workoutTable td {
  text-align: left;
}
</style>