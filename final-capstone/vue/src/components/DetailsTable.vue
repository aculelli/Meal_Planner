<template>
  <!-- After clicking on RecipeList recipe name, this displays the recipe details from Spoonacular and allows you to click edit anad goes to RecipeForm -->
  <div class="recipe-details">
    <table>
      <th id="recipe-header">
        {{ recipe.name }}
      </th>
      <div>&nbsp;</div>
      <tr id="servings-data">
        {{
          recipe.numberOfServings
        }}
        Servings
      </tr>

      <th id="ingredients-header">Ingredients</th>
      <tr
        id="ingredients-data"
        v-for="ingredient in recipe.recipeIngredients"
        v-bind:key="ingredient.id"
      >
        {{
          ingredient.measurementQuantity
        }}
        {{
          ingredient.measurementType
        }}
        {{
          ingredient.ingredientName
        }}
      </tr>

      <th id="directions-header">Directions</th>
      <tr id="directions-data">
        {{
          recipe.directions
        }}
      </tr>
    </table>
    <br />

      <router-link id="edit-btn" tag="button" v-bind:to="{ name: 'addRecipe', params: { id: recipe.id } }"
        >Edit Recipe</router-link
      >

  </div>
</template>

<script>
import spoonacularService from "../services/SpoonacularService.js";
//import recipeService from "../services/RecipeService.js";

export default {
  data() {
    return {
      recipe: {},
      user: {
        userName: this.$store.state.user.username,
        id: this.$store.state.user.id,
      },
    };
  },

  methods: {
    alertLogin() {
      alert("Please log in to add to saved recipes!");
    },
  },

  created() {
    spoonacularService
      .getRecipeDetails(this.$route.params.id)
      .then((recipeData) => {
        this.recipe = recipeData.data;
        this.recipe.id = this.$route.params.id;
        this.recipe.directions = this.recipe.directions.replace(
          /<\/?[^>]+>/gi,
          ""
        );
      });
  },
};
</script>

<style>
.recipe-details {
  margin: 4em auto;
  width: 90%;
  max-width: 496px;
  text-align: center;
}

#recipe-header {
  font-size: 2em;
  text-align: center;
}

table {
  border-collapse: separate;
  border-spacing: 0 15px;
}

th {
  background-color: #1a4314;
  color: white;
}

th,
td,
tr {
  text-align: center;
  border: 1px solid black;
  padding: 5px;
}

#edit-btn {
  background-color: #1a4314;
}
</style>