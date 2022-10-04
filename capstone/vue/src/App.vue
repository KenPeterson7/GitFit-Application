<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }"
        ><img src="../public/Images/LogoImg.png" /><img
          src="../public/Images/LogoText.png"
      /></router-link>
      <!-- &nbsp;|&nbsp; -->
      <!-- <router-link
        id="logoutBtn"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        ><h3>Logout</h3></router-link
      > -->
    </div>
    <div class="main-view">
      <nav v-if="Object.keys(this.$store.state.user).length != 0">
        <router-link v-bind:to="{ name: 'home' }">
          <button
            v-bind:class="{ selected: this.$router.currentRoute.path == '/' }"
          >
            Home
          </button>
        </router-link>
        <router-link v-bind:to="{ name: 'profile' }">
          <button
            v-bind:class="{
              selected: this.$router.currentRoute.path == '/profile',
            }"
          >
            Profile
          </button>
        </router-link>
        <router-link v-bind:to="{ name: 'myMeals' }">
          <button
            v-bind:class="{
              selected: this.$router.currentRoute.path == '/myMeals',
            }"
          >
            Log a Meal
          </button>
        </router-link>
        <router-link v-bind:to="{ name: 'workout' }">
          <button
            v-bind:class="{
              selected: this.$router.currentRoute.path == '/workout',
            }"
          >
            Log a Workout
          </button>
        </router-link>
        <router-link v-bind:to="{ name: 'logout' }">
          <button>Logout</button>
        </router-link>
      </nav>
      <div class="router-view">
        <div class="inner-router-view">
          <router-view />
        </div>
        
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
#nav {
  display: flex;
  justify-content: space-between;
  height: 7vh;
}

.main-view {
  display: flex;
}

.main-view nav {
  display: flex;
  flex-direction: column;
  height: 93vh;

  min-width: 180px;
  border-right: 1px;
  border-right-style: solid;
  background-color: rgb(0, 125, 255);
  color: whitesmoke;

  border-radius: 5px;
  text-align: center;
}

.router-view {
  flex-grow: 1;
  background-image: url("../public/Images/gym-background2.png");
  background-color: lightgray;
  background-blend-mode: screen;
   background-size: cover; 
     height: 93vh;
 
  display:flex;
  flex-direction: column;
  overflow-y: scroll;
}
.inner-router-view{
  height: fit-content;
}

nav button {
  margin: 15px;
  width: 75%;
  color: blue;
  border-radius: 5px;
}

.selected {
  background-color: rgb(0, 67, 127);
  color: white;
}

#logoutBtn {
  justify-self: right;
}

img {
  height: 50px;
}
</style>