<template>
  <div>
      <div id="profileMain" v-if="!editing">
      <div id="profileHeader">
          <img id="profilePic" src="../../public/Images/blank-profile-picture.webp">
          <div>
            <h1>Name: {{profile.displayName}}</h1>
            <img class="star" v-for="n in numberOfStarsToDisplay" v-bind:key="n" src='../../public/Images/star.png'/>
            <h2>Longest Streak: {{profile.highStarStreak}} </h2>
          </div>
      </div>
      <div id="profileDetails">
          <h3>Daily Calorie Target: {{profile.calorieGoal}} </h3>
          <h3>Current Weight: {{profile.currentWeight}} </h3>
          <h3>Goal Weight: {{profile.desiredWeight}} </h3>
          <h3>Activity Level: {{profile.activityLevel}}</h3>
          <h3>Height (in inches): {{profile.height}}</h3>
          <h3>Gender: {{profile.gender}}</h3>
          <h3>Age: {{profile.age}}</h3>
          <h3>DOB: {{profile.birthday}}</h3>
          <div>
            <button v-on:click="editProfile()">Edit Profile</button>
            <button>Change Password</button>
          </div>
      </div>
      
      </div>
      <div id="editForm" v-if="editing">
          <form v-on:submit.prevent="updateProfile(updateProfile)">
              <label for="displayName">Display Name: </label>
              <input type="text" id="displayName" v-model="editedProfile.displayName" />
              <label for="birthday">Birthday: </label>
              <input type="date" id="birthday" v-model="editedProfile.birthday" />
              <label for="currentWeight">Current Weight: </label>
              <input type="number" id="currentWeight" v-model="editedProfile.currentWeight" />
              <label for="desiredWeight">Goal Weight: </label>
              <input type="number" id="desiredWeight" v-model="editedProfile.desiredWeight" />
              <label for="activityLevel">Activity Level: </label>
              <select id="activityLevel" v-model="editedProfile.activityLevel">
              <option>-</option>
              <option>Sedentary</option>
              <option>Light</option>
              <option>Moderate</option>
              <option>Active</option>
              </select>
              <label for="calorieGoal">Daily Calorie Target: </label>
              <input type="text" id="calorieGoal" v-model="editedProfile.calorieGoal" />
              <button type="submit">Save</button>
              <button v-on:click="editing=false">Cancel</button>
          </form>
      </div>
  </div>
</template>

<script>
import ProfileService from "../services/ProfileService";
export default {
  data() {
    return {
      profile: {},
      editedProfile: {},
      editing: false,
    };
  },
  created() {
    let username = this.$store.state.user.username;
    ProfileService.getProfile(username).then((response) => {
      if (response.status == 200) {
        this.profile = response.data;
      }
    });
  },
  computed: {
    numberOfStarsToDisplay() {
      let stars = this.profile.starStreak;
      if (stars < 6) {
        return stars;
      } else {
        return 1;
      }
    },
    calorieGoals() {
      let options = {};
      let BMR;
      if (this.profile.gender == "male") {
        BMR =
          66.5 +
          6.1875 * this.profile.currentWeight +
          12.7 * this.profile.height -
          6.75 * this.profile.age;
      }
      if (this.profile.gender == "female") {
        BMR =
          655.1 +
          4.3 * this.profile.currentWeight +
          4.7 * this.profile.height -
          4.676 * this.profile.age;
      }
      options.sedentary = 1.2 * BMR;
      options.light = 1.375 * BMR;
      options.moderate = 1.55 * BMR;
      options.active = 1.725 * BMR;
      return options;
    },
  },
  methods: {
    addProfile() {},
    editProfile() {
        this.editing = true;
        this.editedProfile = this.profile;
    },
    updateProfile(profile) {
        this.ProfileService.updateProfile(profile.id, profile).then((response)=>{
            if(response.status==200) {
                //do something
            }
        })
    }
  },
};
</script>

<style>
#profileHeader {
  display: flex;
}

#profilePic {
  max-height: 200px;
  padding: 10px;
}

.star {
  height: 75px;
  margin-bottom: 5px;
}

#profileDetails {
  margin-left: 20px;
  margin-bottom: 20px;
}

#editForm form{
    display:flex;
    flex-direction: column;
    margin: 20px;
    
}

#editForm form label {
    margin-top: 10px;
}

#editForm form button {
    margin-top: 10px;
}
</style>