<template>
  <div>
      <div id="profileHeader">
          <img id="profilePic" src="../../public/Images/blank-profile-picture.webp">
          <div>
            <h1>Username : {{profile.username}}</h1>
            <img class="star" src='../../public/Images/star.png'/>
            <h2>Longest Streak: {{profile.longestStreak}} </h2>
          </div>
      </div>
      <div id="profileDetails">
          <h3>Daily Calory Target: #### </h3>
          <h3>Current Weight: {{profile.currentWeight}} </h3>
          <h3>Goal Weight: {{profile.targetWeight}} </h3>
          <h3>Height: {{profile.height}}</h3>
          <h3>Age: {{profile.age}}</h3>
          <h3>DOB: {{profile.birthday}}</h3>
          <button>Edit Profile</button>
      </div>
  </div>
</template>

<script>
import ProfileService from '../services/ProfileService'
export default {
    data() {
        return{
            profile: {}
        }
    },
    created() {
        let username = this.$store.state.user.username;
        ProfileService.getProfile(username).then((response) => {
            if(response.status==200) {
                this.profile = response.data;
            }
        })

    }

}
</script>

<style>

#profileHeader{
    display: flex;
}


#profilePic{
    max-height: 200px;
    padding:10px;

}

.star {
    height: 75px;
}

#profileDetails{
    margin-left: 20px;
    margin-bottom: 20px;
}

</style>