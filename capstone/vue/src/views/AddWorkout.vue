<template>
<div class="test">
<form v-on:submit.prevent>
  <div class="field">
    <label for="nameOfWorkout">Name of Workout: </label>
    <input type="text" id="nameOfWorkout" v-model="workout.nameOfWorkout" />
  </div>
  <div class="field">
    <label for="typeOfWorkout">Type of Workout: </label>
    <input type="text" id="typeOfWorkout" v-model="workout.typeOfWorkout" />
  </div>
  <div class="field">
    <label for="durationOfWorkout">Duration of Workout: </label>
    <input type="text" id="durationOfWorkout" v-model="workout.duration" />
  </div>
  <div class="field">
    <label for="dateOfWorkout">Date of Workout: </label>
    <input type="date" id="dateOfWorkout" v-model="workout.workoutDate" />
  </div>
  <div class="field">
    <label for="caloriesBurned">Calories Burned: </label>
    <input type="text" id="caloriesBurned" v-model="workout.caloriesBurned" />
  </div>
  <div class="actions">
    <button type="button" v-on:click="cancel()">Cancel</button>&nbsp;
    <button type="submit" v-on:click="saveWorkout()">Save Workout</button>
  </div>
</form>
<div class="image">
  <img src="../../public/Images/swole-guy1.png" alt="swole-guy">
</div>
</div>
</template>

<script>
import workoutService from "../services/WorkoutService"

export default {
  name: "add-workout",
  data() {
    return {
      workout: {
          profileId: this.$store.state.profile.profileId,
          nameOfWorkout: "",
          typeOfWorkout: "",
          duration: "",
          workoutDate: "",
          caloriesBurned: "",
      }
    }
  }, 
  methods: {
    saveWorkout() {
      workoutService.addWorkout(this.workout).then(response => {
        if(response.status === 201){
          this.$router.push("/workout")
        }
      })
      .catch(error => {
        console.error(error);
      })
    },
    cancel() {
      this.$router.push("/workout")
    }
  }

}
</script>

<style scoped>

.test {
  display: flex;
align-items: center;
justify-content: center;
}

 .image{
 /* margin-bottom: 500px; */
 margin-left: 50px;
  
} 
  
  #nameOfWorkout {
  margin-left: 0px;
}

#typeOfWorkout {
  margin-left: 0px;
}

#durationOfWorkout {
  margin-left: 0px;
}

#dateOfWorkout {
  margin-left: 0px;
  width: 360px;
  height: 30px;
}

#caloriesBurned {
  margin-left: 0px;
}

form {
  padding: 20px;
  font-size: 16px;
  width: 400px;
  margin-left: 0px;
  margin-right: 50px;
  height: 40vh;
}
form * {
  box-sizing: border-box;
  line-height: 1.5;
}
.field {
  display: flex;
  flex-direction: column;
}
.field label {
  margin: 4px 0;
  font-weight: bold;
}
.field input,
.field textarea {
  padding: 8px;
}
.field textarea {
  height: 300px;
}
.actions {
  text-align: right;
  padding: 10px 0;
}

</style>