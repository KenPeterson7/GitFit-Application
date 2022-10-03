<template>
<div>
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
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="workout in mySavedWorkouts" :key="workout.id">
          <td>{{ workout.nameOfWorkout }}</td>
          <td>{{ workout.typeOfWorkout }}</td>
          <td>{{ workout.duration }}</td>
          <td>{{ workout.workoutDate }}</td>
          <td>{{ workout.caloriesBurned }}</td>
          <td><button id="editWorkout" v-on:click="editWorkout(workout)">Modify Workout</button></td>
        </tr>
      </tbody>
    </table>
    
  </div>
  <div id="editedWorkout" v-if="showForm" >
    <form v-on:submit.prevent>
      <label for="nameOfWorkout">Name of Workout: </label>
      <input type="text" id="nameOfWorkout" v-model="myModifiedWorkout.nameOfWorkout" />
      <label for="typeOfWorkout">Type of Workout: </label>
      <input type="text" id="typeOfWorkout" v-model="myModifiedWorkout.typeOfWorkout" />
      <label for="durationOfWorkout">Duration of Workout: </label>
      <input type="text" id="durationOfWorkout" v-model="myModifiedWorkout.duration" />
      <label for="dateOfWorkout">Date of Workout: </label>
      <input type="date" id="dateOfWorkout" v-model="myModifiedWorkout.workoutDate" />
      <label for="caloriesBurned">Calories Burned: </label>
      <input type="text" id="caloriesBurned" v-model="myModifiedWorkout.caloriesBurned" />
      <button type="submit" v-on:click="saveEditedWorkout(myModifiedWorkout)">Save</button>
      <button v-on:click="cancel()">Cancel</button>
    </form>
  </div>
  <span><strong>Total Calories Burned: </strong>{{ total }}</span>
  <button id="addWorkout">Add Workout</button>
</div>
</template>

<script>
import workoutService from "../services/WorkoutService";

export default {
  name: "workout",
  data() {
    return {
      showForm: false,
      myModifiedWorkout: {},
      mySavedWorkouts: [
        {
          profileId: "",
          nameOfWorkout: "",
          typeOfWorkout: "",
          duration: "",
          workoutDate: "",
          caloriesBurned: "",
        },
      ],
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
    editWorkout(workout) {
      this.showForm = true;
      this.myModifiedWorkout = workout;
    },
    saveEditedWorkout(workout) {
      workoutService
        .updateWorkout(workout.workoutId, workout)
        .then((response) => {
          if (response.status == 200) {
            this.showForm = false;
          }
        });
    },
    cancel() {
      this.showForm = false;
    }
  },
  computed: {
    total: function () {
      let sum = 0;
      for (let i = 0; i < this.mySavedWorkouts.length; i++) {
        sum += this.mySavedWorkouts[i].caloriesBurned;
      }
      return sum;
    },
  },
};
</script>

<style>
#addWorkout {
  margin-top: 75px;
   margin-left: 1000px;
   margin-bottom: 520px;
}

#editedWorkout{
  background: lightskyblue;
}

input[type=text]{
  padding: 10px;
  border: 2px solid #212;
  border-radius: 2px;
  box-shadow: #212121;
  height: 30px;
}

 #editWorkout {
  height: 30px;
}

strong {
  font-size: 22px;
}

#workout {
  margin-bottom: 50px;
  
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