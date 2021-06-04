<template>
<!-- Displays editable recipe from Spoonacular API and allows you to save to MyRecipes -->
  <div>
    <br />
    <h1>Edit Recipe</h1>
    <form class="update-form" v-on:submit.prevent="addNewRecipe()">
      <br/>
      <div>
        <label for="name">Name</label>
        <br/>
        <input
          id="name"
          type="text"
          v-model="recipe.name"
          minlength="1"
          maxlength="500"
          size="75"
        />
        <br />
        <label for="servings">Servings</label>
        <br/>
        <div>
          
          <input type="text" id="servings" v-model="recipe.numberOfServings" />
        </div>
        <br />
        <label>Ingredients</label>
        <div
          class="ingredients-list-boxes"
          v-for="ingredient in recipe.recipeIngredients"
          v-bind:key="ingredient.id"
        >
          <!--Looping starts for ingredients:-->
          <!-- <textarea v-model="recipe.recipeIngredients" /> -->
          <input v-model="ingredient.measurementQuantity" type="text" />
          <input v-model="ingredient.measurementType" type="text" />
          <input
            v-model="ingredient.ingredientName"
            type="text"
            minlength="1"
            maxlength="500"
            size="50"
          />
        </div>
        <br />
        <br />
        <label>Directions</label>
        <div>
          
          <textarea
          id="textarea"
            rows="20"
            cols="200"
            style="width: 500px"
            v-model="recipe.directions"
          />
        </div>
      </div>
      <br />
      <button class="submit-btn" type="submit" value="Submit">
        Save to My Recipes
      </button>
      <button
        class="submit-btn"
        v-on:click.prevent="cancelAddRecipe()"
        type="cancel"
      >
        Go Back to Search
      </button>
    </form>
  </div>
</template>

<script>
import spoonacularService from "../services/SpoonacularService.js";
import recipeService from "../services/RecipeService.js";

export default {
  data() {
    return {
      //recipe: {},
      recipe: {
        id: "",
        name: "",
        directions: "",
        recipeIngredients: [],
        numberOfServings: "",
      },
      input: {
        name: "",
        numberOfServings: "",
        recipeIngredients: [],
        directions: "",
      },
    };
  },
  methods: {
    addNewRecipe() {
      const newRecipe = {
        userId: this.$store.state.user.id,
        name: this.recipe.name,
        directions: this.recipe.directions,
        recipeIngredients: this.recipe.recipeIngredients,
        numberOfServings: this.recipe.numberOfServings,
      };

      recipeService
        .newRecipe(newRecipe)
        .then((response) => {
          if (response.status === 201) {
            alert("Recipe Added!");
            this.$router.push("/myRecipes");
          } else if (response.status === 401) {
            alert("You must be logged in to add recipe!");
            this.$router.push("/login");
          }
        })
        .catch((err) => {
          console.error(err + " problem adding recipe.");
        });
    },
    cancelAddRecipe() {
      this.$router.push("/searchRecipes");
    },
  },
  created() {
    spoonacularService
      .getRecipeDetails(this.$route.params.id)
      .then((recipeData) => {
        this.recipe = recipeData.data;
        console.log(this.recipe);
        this.recipe.directions = this.recipe.directions.replace(
          /<\/?[^>]+>/gi,
          ""
        );
      });
  },
};
</script>

<style scoped>
.update-form {
  background: #94c973;
  margin: 4em auto;
  width: 90%;
  max-width: 1080px;
  text-align: center;
}

.ingredients-list-boxes {
  margin-top: 0.5%;
}

.submit-btn {
  background-color: #1a4314;
}

</style>