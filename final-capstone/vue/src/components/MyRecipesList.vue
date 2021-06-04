<template>
  <div>
    <table class="table" v-bind:key="user.id">
      <!-- <th id="my-recipes-table">My Recipes</th> -->
      <tr v-for="recipe in recipeList" v-bind:key="recipe.recipeId">
        <td>
          {{ recipe.place }} |
          <router-link
            class="my-recipes-links"
            v-bind:to="{
              name: 'myRecipeDetails',
              params: { id: recipe.recipeId },
            }"
            >{{ recipe.name }}</router-link
          >
          <!-- |
          <button
            class="delete-btn"
            v-on:click.prevent="deleteRecipe(recipe.recipeId)"
          >
            Delete
          </button> -->
        </td>
      </tr>
    </table>
    <button class="bottom-btn" @click="$router.push('createNewRecipe')">
      Create New Recipe
    </button>
    <button class="bottom-btn" @click="$router.push('/')">Back</button>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService.js";
export default {
  data() {
    return {
      recipeList: [],
      user: {
        userName: this.$store.state.user.username,
        id: this.$store.state.user.id,
      },
    };
  },
  // methods: {
  //   deleteRecipe(recipeId) {
  //     const choice = confirm("Are you sure you want to delete this recipe?");
  //     if (choice == true) {
  //       recipeService.deleteRecipe(recipeId).then(() => {
  //         this.$router.go();
  //       });
  //     }
  //   },
  // },
  created() {
    recipeService.getAllRecipes().then((recipe) => {
      this.recipeList = recipe.data;
      for (let i = 0; i < this.recipeList.length; i++) {
        this.recipeList[i].place = i + 1;
      }
    });
  },
};
</script>

<style scoped>
#my-recipes-table {
  background-color: #1a4314;
  font-size: 20pt;
}

.bottom-btn {
  margin: 20px;
  background-color: #1a4314;
}

.my-recipes-links {
  text-decoration: none;
  color: #264653;
}

td {
  text-align: left;
}
</style>