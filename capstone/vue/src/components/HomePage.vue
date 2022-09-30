<template>
  <div id="homepage">
    <h1>{{name}}</h1>
    <h2>Calories Remaining: {{caloriesRemaining}}</h2>
    <div>
      <h2>Recent Meals:</h2>
      <ul>
          <li>Meal A</li>
          <li>Meal B</li>
          <li>Meal C</li>
      </ul>
    </div>
    <div>
        <h2>Recent Activities:</h2>
        <ul>
            <li>Activity A</li>
            <li>Activity B</li>
            <li>Activity C</li>
        </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: this.$store.state.profile.displayName,
      caloriesRemaining: this.$store.state.goal.daily_caloric_goal
    }
  }
};
</script>

<style scoped>
#homepage{
  margin-left: 20px;
}

h1{
  text-decoration: underline;   
}
</style>