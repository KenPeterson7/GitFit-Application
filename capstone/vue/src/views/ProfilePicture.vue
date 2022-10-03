<template>
  <div>
      <h1 id="avatarHeader">
         Select an Avatar 
      </h1>
      <div id="pictureGrid">
          <div class="avatarPic" id="pic1">
              <img 
              src="../../public/Images/avatar1.png"
              v-on:click="selectedPicture='avatar1'"
              v-bind:class="{picSelected: selectedPicture == 'avatar1'}"
              />
          </div>
          <div class="avatarPic" id="pic2">
              <img 
              src="../../public/Images/avatar2.png"
              v-on:click="selectedPicture='avatar2'"
              v-bind:class="{picSelected: selectedPicture == 'avatar2'}"
              />
          </div>
          <div class="avatarPic" id="pic3">
              <img 
              src="../../public/Images/avatar3.png"
              v-on:click="selectPicture('avatar3')"
              v-bind:class="{picSelected: selectedPicture == 'avatar3'}"
              />
          </div>
          <div class="avatarPic" id="pic4">
              <img 
              src="../../public/Images/avatar4.png"
              v-on:click="selectPicture('avatar4')"
              v-bind:class="{picSelected: selectedPicture == 'avatar4'}"
              />
          </div>
          <div class="avatarPic" id="pic5">
              <img 
              src="../../public/Images/avatar5.png"
              v-on:click="selectPicture('avatar5')"
              v-bind:class="{picSelected: selectedPicture == 'avatar5'}"
              />
          </div>
          <div class="avatarPic" id="pic6">
              <img 
              src="../../public/Images/avatar6.png"
              v-on:click="selectPicture('avatar6')"
              v-bind:class="{picSelected: selectedPicture == 'avatar6'}"
              />
          </div>
          <div class="avatarPic" id="pic7">
              <img 
              src="../../public/Images/avatar7.png"
              v-on:click="selectedPicture='avatar7'"
              v-bind:class="{picSelected: selectedPicture == 'avatar7'}"
              />
          </div>
          <div class="avatarPic" id="pic8">
              <img 
              src="../../public/Images/avatar8.png"
              v-on:click="selectedPicture='avatar8'"
              v-bind:class="{picSelected: selectedPicture == 'avatar8'}"
              />
          </div>
          <div class="avatarPic" id="pic9">
              <img 
              src="../../public/Images/avatar9.png"
              v-on:click="selectPicture('avatar9')"
              v-bind:class="{picSelected: selectedPicture == 'avatar9'}"
              />
          </div>
          <div class="avatarPic" id="pic10">
              <img 
              src="../../public/Images/avatar10.png"
              v-on:click="selectPicture('avatar10')"
              v-bind:class="{picSelected: selectedPicture == 'avatar10'}"
              />
          </div>
          <div class="avatarPic" id="pic11">
              <img 
              src="../../public/Images/avatar11.png"
              v-on:click="selectPicture('avatar11')"
              v-bind:class="{picSelected: selectedPicture == 'avatar11'}"
              />
          </div>
          <div class="avatarPic" id="pic12">
              <img 
              src="../../public/Images/avatar12.png"
              v-on:click="selectPicture('avatar12')"
              v-bind:class="{picSelected: selectedPicture == 'avatar12'}"
              />
          </div>
          <div class="avatarPic" id="pic13">
              <img 
              src="../../public/Images/avatar13.png"
              v-on:click="selectPicture('avatar13')"
              v-bind:class="{picSelected: selectedPicture == 'avatar13'}"
              />
          </div>
           
      </div>
        <div class="buttonTray">
             <button v-on:click="changePicture()">Confirm</button>
             <router-link v-bind:to="{name: 'profile'}"><button>Cancel</button></router-link>
        </div>
  </div>
</template>

<script>
import ProfileService from '../services/ProfileService.js'

export default {
    data() {
        return {
            selectedPicture: '',
        }
    },
    methods: {
        selectPicture(filePath) {
            this.selectedPicture = filePath;
            
        },
        changePicture() {
            let profile = this.$store.state.profile;
            profile.profilePic = this.selectedPicture;
            ProfileService.updateProfile(profile.profileId, profile).then((response) => {
                if (response.status == 200) {
                    this.$store.commit('UPDATE_PROFILE_PICTURE', this.selectedPicture)
                    this.$router.push('/profile')
                } 
            })

        }
    }

}
</script>

<style>
#avatarHeader{
    text-align: center;
}

#pictureGrid{
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
    
}

.avatarPic{
    max-height: 200px;
    max-width: 200px;
    margin: 10px;
}
.avatarPic img{
    max-height: 200px;
}

.buttonTray{
    display: flex;
    justify-content: center;
}

.buttonTray button{
    margin: 5px;
}

.picSelected{
    border: 5px;
    border-style: solid;
    border-color: rgb(0, 125, 255);
}

</style>