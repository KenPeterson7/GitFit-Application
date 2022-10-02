<template>
  <div>
    <div id="profileMain" v-if="!editing && profileExists">
      <div id="profileHeader">
        <router-link v-bind:to="{name: 'profilePicture'}">
        <img
          id="profilePic"
          src="../../public/Images/blank-profile-picture.webp"
          v-if="profile.profilePic==''"
        />
        <img
          id="profilePic"
          src="../../public/Images/avatar1.png"
          v-if="profile.profilePic=='avatar1'"
        />
        <img
          id="profilePic"
          src="../../public/Images/avatar2.png"
          v-if="profile.profilePic=='avatar2'"
        />
        <img
          id="profilePic"
          src="../../public/Images/avatar3.png"
          v-if="profile.profilePic=='avatar3'"
        />
        <img
          id="profilePic"
          src="../../public/Images/avatar4.png"
          v-if="profile.profilePic=='avatar4'"
        />
        <img
          id="profilePic"
          src="../../public/Images/avatar5.png"
          v-if="profile.profilePic=='avatar5'"
        />
        <img
          id="profilePic"
          src="../../public/Images/avatar6.png"
          v-if="profile.profilePic=='avatar6'"
        />


        </router-link>
        <div>
          <h1>Name: {{ profile.displayName }}</h1>
          <div id="star-container">
            <img
              class="star"
              v-for="n in numberOfStarsToDisplay"
              v-bind:key="n"
              v-bind:class="{numDisplay: numberOfStarsToDisplay==1}"
              src="../../public/Images/star.png"
            />
            <p id="starNumber">{{ starNumber }}</p>
          </div>
          <h2>Longest Streak: {{ profile.highStarStreak }}</h2>
        </div>
      </div>
      <div id="profileDetails">
        <h3>Daily Calorie Target: {{ goal.daily_caloric_goal }}</h3>
        <h3>Current Weight: {{ profile.currentWeight }}</h3>
        <h3>Goal Weight: {{ profile.desiredWeight }}</h3>
        <h3>Activity Level: {{ profile.activityLevel }}</h3>
        <h3>Height (in inches): {{ profile.height }}</h3>
        <h3>Gender: {{ profile.gender }}</h3>
        <h3>Age: {{ profile.age }}</h3>
        <h3>DOB: {{ profile.birthday }}</h3>
        <div class="buttonTray">
          <button v-on:click="editProfile()">Edit Profile</button>
          <router-link v-bind:to="{name: 'changePassword'}"><button>Change Password</button></router-link>
        </div>
      </div>
    </div>
    <div id="editForm" v-if="editing && profileExists">
      <form v-on:submit.prevent="updateProfile(editedProfile)">
        <label for="displayName">Display Name: </label>
        <input
          type="text"
          id="displayName"
          v-model="editedProfile.displayName"
        />
        <label for="birthday">Birthday: </label>
        <input type="date" id="birthday" v-model="editedProfile.birthday" />
        <label for="currentWeight">Current Weight: </label>
        <input
          type="number"
          id="currentWeight"
          v-model="editedProfile.currentWeight"
        />
        <label for="desiredWeight">Goal Weight: </label>
        <input
          type="number"
          id="desiredWeight"
          v-model="editedProfile.desiredWeight"
        />
        <label for="activityLevel">Activity Level: </label>
        <select id="activityLevel" v-model="editedProfile.activityLevel">
          <option>-</option>
          <option>Sedentary</option>
          <option>Light</option>
          <option>Moderate</option>
          <option>High</option>
          <option>Extreme</option>
        </select>
        <label for="calorieGoal">Daily Calorie Target: </label>
        <input
          type="number"
          id="calorieGoal"
          v-model="editedGoal.daily_caloric_goal"
        />
        <h6>Suggested Calorie Goal: {{ calorieGoal() }}</h6>
        <button type="submit">Save</button>
        <button v-on:click="editing = false">Cancel</button>
      </form>
    </div>
    <div v-if="!profileExists">
      <create-profile />
    </div>
  </div>
</template>

<script>


import CreateProfile from "../components/CreateProfile.vue";
import ProfileService from "../services/ProfileService";

export default {
  components: { CreateProfile },
  data() {
    return {
      profile: {},
      editedProfile: {},
      editing: false,
      goal: {},
      editedGoal: {},
    };
  },
  created() {
    let username = this.$store.state.user.username;
    ProfileService.getProfile(username).then((response) => {
      if (response.status == 200) {
        this.profile = response.data;
        this.getGoal();
      }
    });
  },
  computed: {
    numberOfStarsToDisplay() {
      let stars = this.profile.starStreak;
      if (stars < 6 && stars > 0) {
        return stars;
      } else {
        return 1;
      }
    },
    starNumber() {
      let stars = this.profile.starStreak;
      if (stars < 6 && stars > 0) {
        return "";
      } else {
        return stars;
      }
    },
    profileExists() {
      return Object.keys(this.profile).length !== 0;
    },
  },
  methods: {
    editProfile() {
      this.editing = true;
      this.editedProfile = this.profile;
      this.editedGoal = this.goal;
    },
    updateProfile(profile) {
      ProfileService.updateProfile(profile.profileId, profile).then(
        (response) => {
          if (response.status == 201) {
            this.profile = this.editedProfile;
            
          }
          this.$store.commit("SET_CURRENT_PROFILE",profile)
          ProfileService.updateGoal(
            this.profile.profileId,
            this.editedGoal
          ).then((response) => {
            if (response.status == 201) {
              this.goal = this.editedGoal;
              
            }
            this.$store.commit("SET_CURRENT_GOAL", this.goal)
          });
          this.editing = false;
        }
      );
    },
    getGoal() {
      ProfileService.getGoal(this.profile.profileId).then((response) => {
        if (response.status == 200) {
          this.goal = response.data;
        }
      });
    },
    calorieGoal() {
      let goal;
      let BMR;
      let activityLevel = this.editedProfile.activityLevel;
      if (this.editedProfile.gender == "M") {
        BMR =
          66.5 +
          6.1875 * this.editedProfile.currentWeight +
          12.7 * this.editedProfile.height -
          6.75 * this.editedProfile.age;
      }
      if (this.profile.gender == "F") {
        BMR =
          655.1 +
          4.3 * this.editedProfile.currentWeight +
          4.7 * this.editedProfile.height -
          4.676 * this.editedProfile.age;
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

      return Math.round(goal);
    },
    getProfilePic() {
      return this.profile.profilePic;
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
  margin-left: 10px;
  margin-right: 10px;
}

#star-container {
  height: 75px;
}

.star {
  height: 75px;
  margin-bottom: 5px;
}

.numDisplay{
  position:absolute;
}

#star-container p {
  width: 75px;
  margin-top: 10px;
  position: absolute;
  text-align: center;
  font-size: 40px;
}

#profileDetails {
  margin-left: 20px;
  margin-bottom: 20px;
}

#editForm form {
  display: flex;
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