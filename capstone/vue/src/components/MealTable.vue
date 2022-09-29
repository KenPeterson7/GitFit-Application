<template>
  <div id="mealTable">
  <table
        id="mealTable"
        v-for="(food, index) in foodList"
        v-bind:key="index"
      >
        <td>{{ food.foodName }}</td>
        <div id="leftColumns">
          <td>{{ food.foodType }}</td>
          <td>{{ food.caloricAmount }}</td>
          <td>{{ food.size }}</td>
          <td>{{ food.numberOfServings }}</td>
          
        </div>

          <div id="rightButtons">
       <button>Edit Food</button>
        <button id="delete" >Delete Food</button>
        </div>
      </table>
    


  </div>
</template>

<script>
export default {
props: ['foodList'],

}


</script>

<style>

</style>