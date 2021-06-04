<template>
  <div>
    <h1>My Recipes</h1>
    <!-- <router-link v-bind:to=" { name: 'createNewRecipe'}">Add Custom Recipe</router-link> -->
    <my-recipes-list />
  </div>
</template>

<script>
import MyRecipesList from "../components/MyRecipesList.vue";

export default {
  components: { MyRecipesList },
};
</script>

<style>
</style>