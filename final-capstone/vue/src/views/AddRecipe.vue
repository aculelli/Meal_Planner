<template>
  <div>
    <h1>Add Recipe to My Recipes</h1>
    <recipe-form></recipe-form>
  </div>
</template>

<script>
import RecipeForm from "../components/RecipeForm.vue";

export default {
  name: "AddRecipe",
  components: { RecipeForm },
};
</script>

<style>
</style>