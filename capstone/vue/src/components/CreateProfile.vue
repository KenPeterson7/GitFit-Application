<template>
  <div>
      <form id="newProfileForm" v-on:submit="saveProfile(newProfile)">
          
          <div class="form-group">
              <label for="name">Name: </label>
          <input id="name" type="text" v-model="newProfile.displayName" />
          </div>
          <div class="form-group">
              <label for="age">Age: </label>
              <input id="age" type="number" v-model="newProfile.age" />
          </div>
          <div class="form-group">
              <label for="birthday">Birthday: </label>
              <input id="birthday" type="date" v-model="newProfile.birthday" />
          </div>
          <div class="form-group">
              <label for="height">Height (in inches): </label>
              <input id="height" type="number" v-model="newProfile.height" />
          </div>
          <div class="form-group">
              <label for="gender">Gender Assigned at Birth: </label>
              <select id="gender" v-model="newProfile.gender">
                <option>-</option>
                <option>M</option>
                <option>F</option>
              </select>
              <p><strong>**This is not used for identification, only for calculating calorie goals**</strong></p>
          </div>
          <div class="form-group">
              <label for="currentWeight">Current Weight (in lbs): </label>
          <input id="currentWeight" type="number" v-model="newProfile.currentWeight" />
          </div>
          <div class="form-group">
              <label for="desiredWeight">Goal Weight (in lbs): </label>
          <input id="desiredWeight" type="number" v-model="newProfile.desiredWeight" />
          </div>
          <div class="form-group">
              <label for="activityLevel">Activity Level: </label>
          <select id="activityLevel" v-model="newProfile.activityLevel">
              <option>-</option>
              <option>Sedentary</option>
              <option>Low</option>
              <option>Moderate</option>
              <option>High</option>
              <option>Extreme</option>
          </select>
          </div>
          <button type="submit">Save Profile</button>
      </form>
  </div>
</template>

<script>
import ProfileService from '../services/ProfileService'
export default {
    data() {
        return {
            newProfile: {
                displayName: '',
                age: '',
                birthday: '',
                height: '',
                gender: '',
                currentWeight: '',
                desiredWeight: '',
                activityLevel: '',
                starStreak: 0,
                highStarStreak: 0,
                profilePic: '',
                username: this.$store.state.user.username
            }
        }
    },
    methods: {
        saveProfile(profile) {
            ProfileService.addProfile(profile).then((response) => {
                if(response==201) {
                    this.$router.push('/');
                }
            })
        }
    }
}
</script>

<style>
#newProfileForm{
    display: flex;
    flex-direction: column;
    margin:20px;
}

label {
    margin-right: 5px;
}
</style>