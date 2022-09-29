<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }"
        ><img src="../public/Images/LogoImg.png" /><img
          src="../public/Images/LogoText.png"
      /></router-link>
      <!-- &nbsp;|&nbsp; -->
      <router-link
        id="logoutBtn"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        ><h3>Logout</h3></router-link
      >
    </div>
    <div class="main-view">
      <nav v-if="isLoggedIn()">
        <router-link v-bind:to="{name: 'home'}">
        <button
          v-on:click="clickHome()"
          v-bind:class="{ selected: homeSelected }"
        >
          Home
        </button>
        </router-link>
        <router-link v-bind:to="{name: 'profile'}">
        <button
          v-on:click="clickProfile()"
          v-bind:class="{ selected: profileSelected }"
        >
          Profile
        </button>
        </router-link>
        <router-link v-bind:to="{name: 'myMeals'}">
        <button
          v-on:click="clickLogMeal()"
          v-bind:class="{ selected: logMealSelected }"
        >
          Log a Meal
        </button>
        </router-link>
        <router-link v-bind:to="{name: 'workout'}">
        <button
          v-on:click="clickLogWorkout()"
          v-bind:class="{ selected: logWorkoutSelected }"
        >
          Log a Workout
        </button>
        </router-link>
      </nav>
      <div class="router-view">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    isLoggedIn() {
      return this.$store.state.loggedIn;
    },
  },
};
</script>

<style scoped>
#nav {
  display: flex;
  justify-content: space-between;
}

.main-view {
  display: flex;
}

.main-view nav {
  display: flex;
  flex-direction: column;
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