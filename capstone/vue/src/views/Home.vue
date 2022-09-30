<template>
  <div class="home">
    
    <div id="componentDiv">
      
      <home-page />
      
      
    </div>
    
  </div>
</template>

<script>
import HomePage from '../components/HomePage.vue';
import ProfileService from '../services/ProfileService'

export default {
  components: { HomePage },
  name: "home",
  data() {
    return {
      
    }
  },
  created() {
    this.populateStore()
  },
  methods: {
    populateStore(){
      ProfileService.getProfile(this.$store.state.user.username).then((response) => {
        
        this.$store.commit("SET_CURRENT_PROFILE", response.data);
        
        
      })
    }
    
  }
  
};
</script>

<style scoped>
.home{
  display: flex;
  height: 700px;
}

.home nav {
  min-width: 150px;
  border-right: 1px;
  border-right-style: solid;
  background-color: rgb(0, 125, 255);
  color: whitesmoke;
  
  border-radius: 5px;
  text-align: center;
  
  
}

h3 {
  padding-left: 20px;
  padding-right: 20px;
}

#componentDiv{
  flex-grow: 8;
  background-image: url("../../public/Images/gym-background2.png");
  background-color: lightgray;
  background-blend-mode: screen;  
  background-size: cover;
  background-repeat: no-repeat;
}

.selected{
  background-color: rgb(0, 67, 127);
  color: white;
}

div {
    margin-left: 2.5px;
}

nav button {
  margin: 15px;
  width: 75%;
  color: blue;
  border-radius: 5px;
  
}
</style>