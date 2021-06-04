<template>
  <div>
    <div>
      <h1>Search for Recipes</h1>
      <search-form />
      <!-- <router-link v-bind:to="{ name: 'addRecipe' }">Add Recipe</router-link> -->
    </div>
  </div>
</template>

<script>
import SearchForm from "../components/SearchForm.vue";
export default {
  components: { SearchForm },
};
</script>

<style scoped>
</style>