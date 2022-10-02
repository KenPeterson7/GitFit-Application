<template>
  <div id="workout">Coming Soon
      <table id="workoutTable">
          <thead id="header">
              <tr>
                <th>Name of Workout</th>
                <th>Type of Workout</th>
                <th>Duration of Workout</th>
                <th>Date of Workout</th>
            </tr>
          </thead>
          <tbody>
              <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
              </tr>

          </tbody>
      </table>
  </div>
</template>

<script>
import workoutService from "../services/WorkoutService"

export default {
    name: "workout",
    data(){
        return{
            mySavedWorkouts: [],
        }
    },
    created() {
        this.getWorkoutsByUsername();
    },
    methods: {
        getWorkoutsByUsername() {
        workoutService.getListOfAllWorkoutsByUsername(this.$store.state.user.username).then((response) => {
        if (response.status == 200) {
          this.mySavedWorkouts = response.data;
        }
      });
    }
    }
}
</script>

<style>
    #workoutTable {
  margin-left: 25%;
}

    #workoutTable th {
  padding-right: 50px;
}
</style>