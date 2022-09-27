<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }"><img src='../public/Images/LogoImg.png'/><img src='../public/Images/LogoText.png'/></router-link>
      <!-- &nbsp;|&nbsp; -->
      <router-link id="logoutBtn" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"><h3>Logout</h3></router-link>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
#nav {
  display: flex;
  justify-content: space-between;
  
}

#logoutBtn {
  justify-self: right;
}

img {
  height: 50px;
}
</style>