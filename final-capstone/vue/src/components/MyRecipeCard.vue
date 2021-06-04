<template>
  <div class="my-recipe-details">
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
    <router-link
      class="edit-recipe-btn"
      v-bind:to="{ name: 'updateRecipe', params: { id: recipe.id } }"
      tag="button"
      >Edit Recipe</router-link
    >
    <button
      class="recipe-card-bottom-btns"
      v-on:click.prevent="deleteRecipe(recipe.recipeId)"
    >
      Delete Recipe
    </button>
    <!-- <button
      class="recipe-card-bottom-btns"
      @click="$router.go('/recipeSearch')"
    >
      Search Recipes
    </button> -->
    <button class="recipe-card-bottom-btns" @click="$router.go(-1)">
      Back
    </button>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService.js";

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
    deleteRecipe(recipeId) {
      const choice = confirm("Are you sure you want to delete this recipe?");
      if (choice == true) {
        recipeService.deleteRecipe(recipeId).then(() => {
          this.$router.push("/myRecipes");
        });
      }
    },
  },
  created() {
    recipeService.getRecipe(this.$route.params.id).then((recipeData) => {
      this.recipe = recipeData.data;
      this.recipe.id = this.$route.params.id;
    });
  },
};
</script>

<style>
.my-recipe-details {
  background: #94c973;
  margin: 4em auto;
  width: 65%;
  text-align: center;
  border-radius: 25px;
}

#recipe-header {
  font-size: 2em;
  text-align: center;
}

.edit-recipe-btn {
  background-color: #1a4314;
  margin-bottom: 30px;
}

.recipe-card-bottom-btns {
  background-color: #1a4314;
}

th {
  border-radius: 25px;
}
</style>