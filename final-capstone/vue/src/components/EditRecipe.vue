<template>
  <div>
    <head>
      <link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
        rel="stylesheet"
      />
    </head>
    
    <form id="edit-form" v-on:submit.prevent="editRecipe()">
      <h1>Edit Recipe</h1>
      <div>
        <label for="name">Name </label>
        <div>
          <br/>
          <input
            id="name"
            type="text"
            v-model="recipe.name"
            minlength="1"
            maxlength="500"
            size="75"
          />
        </div>
        <div><br/><label for="servings">Servings </label></div>
        <div><br/>
          <input type="text" id="servings" v-model="recipe.numberOfServings" />
        </div>
        <br />
        <label for="ingredients">Ingredients</label>
        <table>
        <tr
          class="ingredients-list-boxes"
          v-for="(input, k) in inputs"
          :key="k"
        >
          <!--Looping starts for ingredients:-->
          <input
            v-model="input.measurementQuantity"
            type="text"
            placeholder="quantity"
          />
          <input
            v-model="input.measurementType"
            type="text"
            placeholder="measurement type"
          />
          <input
            v-model="input.ingredientName"
            placeholder="ingredient"
            type="text"
            minlength="1"
            maxlength="500"
            size="50"
          />
          <span>
            <i
              class="fas fa-minus-circle"
              @click="remove(k)"
              v-show="k || (!k && inputs.length > 1)"
            ></i>
            <i
              class="fas fa-plus-circle"
              @click="add(k)"
              v-show="k == inputs.length - 1"
            ></i>
          </span>
        </tr>
        </table>
        <br />
        <label for="directions">Directions</label> 
        <div style="margin-top: 10px;">  
          <textarea
            rows="20"
            cols="200"
            style="width: 500px"
            v-model="recipe.directions"
          />
        </div>
      </div>
      <br />
      <button class="submit-btn" type="submit" value="Submit">
        Save Changes
      </button>
      <button
        class="submit-btn"
        v-on:click.prevent="cancelEditRecipe"
        type="cancel"
      >
        Cancel
      </button>
    </form>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService.js";

export default {
  data() {
    return {
      recipe: {
        recipeId: "",
        name: "",
        directions: "",
        recipeIngredients: [],
        numberOfServings: "",
      },
      inputs: [
        {
          measurementQuantity: "",
          measurementType: "",
          ingredientName: "",
          recipeId: this.$route.params.id,
          recipeIngredientId: "",
        },
      ],
    };
  },
  methods: {
    editRecipe() {
      const editedRecipe = {
        recipeId: this.recipe.recipeId,
        userId: this.$store.state.user.id,
        name: this.recipe.name,
        directions: this.recipe.directions,
        recipeIngredients: this.inputs,
        numberOfServings: this.recipe.numberOfServings,
      };
      console.log(editedRecipe);
      recipeService
        .updateRecipe(editedRecipe)
        .then((response) => {
          if (response.status === 200) {
            alert("Recipe updated!");
            this.$router.push("/myRecipes");
          }
        })
        .catch((err) => {
          console.error(err + " problem updating recipe.");
        });
    },
    add() {
      this.inputs.push({
        measurementQuantity: "",
        measurementType: "",
        ingredientName: "",
        recipeId: this.recipe.recipeId,
        recipeIngredientId: "",
      });
    },
    remove(index) {
      this.inputs.splice(index, 1);
    },

    cancelEditRecipe() {
      this.$router.push("/myRecipes");
    },
  },
  created() {
    recipeService.getRecipe(this.$route.params.id).then((response) => {
      this.recipe = response.data;
      this.inputs = this.recipe.recipeIngredients;
      console.log(this.recipe);
    });
  },
};
</script>

<style scoped>
#edit-form {
  background: #94c973;
  margin: 4em auto;
  width: 90%;
  max-width: 1080px;
  text-align: center;
  padding: 5px;
}

.submit-btn {
  background-color: #1a4314;
}

tr {
  text-align: left;
}

table {
  margin-bottom: 0px;
  padding: 0px;
  border-spacing: 1px;
}
</style>