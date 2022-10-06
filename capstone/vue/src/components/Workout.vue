<template>
  <div>
    
    <div id="workout">
      
      <table id="workoutTable">
        
        <thead id="header">
          <tr>
            <th>Name of Workout:</th>
            <th>Type of Workout:</th>
            <th>Duration of Workout:</th>
            <th>Date of Workout:</th>
            <th>Calories Burned:</th>
            <th>Update Workout: </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="workout in mySavedWorkouts" :key="workout.id">
            <td>{{ workout.nameOfWorkout }}</td>
            <td>{{ workout.typeOfWorkout }}</td>
            <td>{{ workout.duration }}</td>
            <td>{{ workout.workoutDate }}</td>
            <td>{{ workout.caloriesBurned }}</td>
            <td>
              <button id="editWorkout" v-on:click="editWorkout(workout)">
                Modify Workout
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div id="editedWorkout" v-if="showForm">
      <form v-on:submit.prevent>
        <label for="nameOfWorkout">Name of Workout: </label>&nbsp;
        <input
          type="text"
          id="nameOfWorkout"
          v-model="myModifiedWorkout.nameOfWorkout"
        />&nbsp;<br>
        <label for="typeOfWorkout">Type of Workout: </label>&nbsp;
        <input
          type="text"
          id="typeOfWorkout"
          v-model="myModifiedWorkout.typeOfWorkout"
        />&nbsp;<br>
        <label for="durationOfWorkout">Duration of Workout: </label>&nbsp;
        <input
          type="text"
          id="durationOfWorkout"
          v-model="myModifiedWorkout.duration"
        />&nbsp;<br>
        <label for="dateOfWorkout">Date of Workout: </label>&nbsp;
        <input
          class="js-sort-date"
          type="date"
          id="dateOfWorkout"
          v-model="myModifiedWorkout.workoutDate"
        />&nbsp;<br>
        <label for="caloriesBurned">Calories Burned: </label>&nbsp;
        <input
          type="text"
          id="caloriesBurned"
          v-model="myModifiedWorkout.caloriesBurned"
        />&nbsp;<br>
        <button id="saveBtn" type="submit" v-on:click="saveEditedWorkout(myModifiedWorkout)">
          Save
        </button>&nbsp;
        <button id="cancelBtn" v-on:click="cancel()">Cancel</button>
      </form>
    </div>
  <span id="totalCalories"><strong>Total Calories Burned: </strong>{{total}}</span><br><br>
    <span id="dailyCalories"><strong>Daily Calorie Burn Average: </strong>{{daily}}</span>
    
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
  beforeMount() {
    
  },
  methods: {
    getWorkoutsByUsername() {
      workoutService
        .getListOfAllWorkoutsByUsername(this.$store.state.user.username)
        .then((response) => {
          if (response.status == 200) {
            this.mySavedWorkouts = response.data;
            this.sortArray();
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
            location.reload()
          }
        });
    },
    cancel() {
      this.showForm = false;
    },
    sortArray() {
      this.mySavedWorkouts.sort(function(a,b){
        console.log(Date(b.workoutDate) - new Date(a.workoutDate))
        console.log(a.workoutDate)
        console.log(b.workoutDate)
        console.log(new Boolean(new Date(b.workoutDate) - new Date(a.workoutDate)))
  // Turn your strings into dates, and then subtract them
  // to get a value that is either negative, positive, or zero.
  return new Date(a.workoutDate) - new Date(b.workoutDate);

      })
      console.log(this.mySavedWorkouts)
    }
  },
  computed: {
    total: function () {
      let sum = 0;
      for (let i = 0; i < this.mySavedWorkouts.length; i++) {
        sum += this.mySavedWorkouts[i].caloriesBurned;
      }
      return sum.toFixed(2);
    },
    daily: function() {
      let avg = 0;
      let avgLength = this.mySavedWorkouts.length; 
      for (let i = 0; i < avgLength; i++) {
        avg += this.mySavedWorkouts[i].caloriesBurned;
      }

      return (avg/avgLength).toFixed(2);
    }
  },
};

</script>

<style>

#nameOfWorkout {
  margin-left: 30px;
  
}

#typeOfWorkout {
  margin-left: 39px;
}

#durationOfWorkout {
  margin-left: 10px;
}

#dateOfWorkout {
  margin-left: 39px;
  border: 2px solid;
  width: 55.4%
}

#caloriesBurned {
  margin-left: 45px;
}

#saveBtn{
  margin-left: 145px;
}

#editedWorkout {
  border: 3px solid;
  padding: 5px;
  display: flex;
  width: 400px;
  flex-wrap: wrap;
  margin-left: 600px;
  position: relative;
  background-color: rgba(173, 216, 230, 0.7);
  top: -10px;
  
}


input[type="text"] {
  padding: 10px;
  border: 2px solid #212;
  border-radius: 2px;
  box-shadow: #212121;
  height: 30px;
}

#editWorkout {
  height: 30px;
  margin-left: 25px;
}

 strong {
  font-size: 22px;
  margin-top: 175px;
  margin-left: 10px;
  
} 

 #totalCalories {
   display: fixed;
  position: fixed;
  top: 50;
  
  
}


#dailyCalories {
  display: fixed;
  position: fixed;
  
 
}


#workout {
  height: 45vh;
  
}

#workoutTable {
  margin-left: 17%;
  margin-top: 10px;
}

#workoutTable th {
  padding-right: 50px;
}

#workoutTable td {
  text-align: left;
}

tr:nth-child(odd){
    background-color: rgba(173, 216, 230, 0.7);
}

 #header th {
  border: 1px solid;
  background-color: lightgray;
}

#workoutTable tbody tr td {
  border: 1px solid;
}
button{
  border-radius: 4px;
}
span{
    margin-left: 14.7%;
}
</style>