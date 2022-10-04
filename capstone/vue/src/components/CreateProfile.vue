<template>
  <div>
    <form id="newProfileForm" v-on:submit.prevent="saveProfile()">
      <div class="form-group">
        <label for="name">Name: </label>
        <input id="name" type="text" v-model="newProfile.displayName" required/>
      </div>
      <div class="form-group">
        <label for="age">Age: </label>
        <input id="age" type="number" v-model="newProfile.age" required/>
      </div>
      <div class="form-group">
        <label for="birthday">Birthday: </label>
        <input id="birthday" type="date" v-model="newProfile.birthday" required/>
      </div>
      <div class="form-group">
        <label for="height">Height (in inches): </label>
        <input id="height" type="number" v-model="newProfile.height" required/>
      </div>
      <div class="form-group">
        <label for="gender">Gender Assigned at Birth: </label>
        <select id="gender" v-model="newProfile.gender" required>
          <option>-</option>
          <option>M</option>
          <option>F</option>
        </select>
        <p>
          <strong
            >**This is not used for identification, only for calculating calorie
            goals**</strong
          >
        </p>
      </div>
      <div class="form-group">
        <label for="currentWeight">Current Weight (in lbs): </label>
        <input
          id="currentWeight"
          type="number"
          v-model="newProfile.currentWeight"
          required
        />
      </div>
      <div class="form-group">
        <label for="desiredWeight">Goal Weight (in lbs): </label>
        <input
          id="desiredWeight"
          type="number"
          v-model="newProfile.desiredWeight"
          required
        />
      </div>
      <div class="form-group">
        <label for="activityLevel">Activity Level: </label>
        <select id="activityLevel" v-model="newProfile.activityLevel" required>
          <option>-</option>
          <option>Sedentary</option>
          <option>Light</option>
          <option>Moderate</option>
          <option>High</option>
          <option>Extreme</option>
        </select>
      </div>
      <div id="form-group">
        <label for="calorieGoal">Daily Calorie Goal: </label>
        <input
          id="calorieGoal"
          type="number"
          v-model="newGoal.daily_caloric_goal"
          required
        />
        <h6 v-if="requiredDataEntered()" v-on:click="calculateCalorieGoal()">
          Calculate Suggested Calorie Goal
        </h6>
      </div>
      <button type="submit">Save Profile</button>
    </form>
  </div>
</template>

<script>
import ProfileService from "../services/ProfileService";
export default {
  data() {
    return {
      newProfile: {
        displayName: "",
        age: "",
        birthday: "",
        height: "",
        gender: "-",
        currentWeight: "",
        desiredWeight: "",
        activityLevel: "-",
        starStreak: 0,
        highStarStreak: 0,
        profilePic: "",
        username: this.$store.state.user.username,
      },
      newGoal: {
        profile_id: "",
        daily_caloric_goal: "",
      },
    };
  },
  methods: {
    saveProfile() {
        ProfileService.addProfile(this.newProfile).then((response) => {
            if (response.status == 201) {
                this.newGoal.profile_id = response.data.profileId;
                
            }
            ProfileService.createGoal(this.newGoal).then((response) => {
                    if (response.status == 200) {
                        this.$router.push('/')
                    }
                })
        })

    },

    requiredDataEntered() {
      return (
        this.newProfile.currentWeight != "" &&
        this.newProfile.height != "" &&
        this.newProfile.age != "" &&
        this.newProfile.gender != "-" &&
        this.newProfile.activityLevel != "-"
      );
    },

    calculateCalorieGoal() {
      if (!this.requiredDataEntered()) {
          this.newGoal.daily_caloric_goal = 0
         return;
      }
      let goal;
      let BMR;
      let activityLevel = this.newProfile.activityLevel;
      if (this.newProfile.gender == "M") {
        BMR =
          66.5 +
          6.1875 * this.newProfile.currentWeight +
          12.7 * this.newProfile.height -
          6.75 * this.newProfile.age;
      }
      if (this.newProfile.gender == "F") {
        BMR =
          655.1 +
          4.3 * this.newProfile.currentWeight +
          4.7 * this.newProfile.height -
          4.676 * this.newProfile.age;
      }
      if (activityLevel == "Sedentary") {
        goal = BMR * 1.2;
      }
      if (activityLevel == "Light") {
        goal = BMR * 1.375;
      }
      if (activityLevel == "Moderate") {
        goal = BMR * 1.55;
      }
      if (activityLevel == "High") {
        goal = BMR * 1.725;
      }
      if (activityLevel == "Extreme") {
        goal = BMR * 1.9;
      }
      this.newGoal.daily_caloric_goal = Math.round(goal);

    //   return Math.round(goal);
    },
  },
};
</script>

<style scoped>
#newProfileForm {
  display: flex;
  flex-direction: column;
  margin: 20px;
}

label {
  margin-right: 5px;
}

h6 {
  text-align: center;
  border: 3px;
  border-style: solid;
  padding: 3px;
  background-color: rgb(0, 125, 255);
  color: lightgray;
}
</style>