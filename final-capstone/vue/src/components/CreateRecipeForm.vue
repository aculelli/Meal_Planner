<template>
  <div>
    <head>
      <link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
        rel="stylesheet"
      />
    </head>
    <form class="update-form" v-on:submit.prevent="addNewRecipe()">
      <h1>Add Custom Recipe</h1>
      <br />
      <label for="title">Name </label>
      <div>
        <div class="name-box">
          <input
            id="title"
            type="text"
            placeholder="enter recipe name"
            minlength="1"
            maxlength="500"
            size="75"
            v-model="newRecipe.name"
          />
        </div>
        <br />
        <label for="servings">Servings </label>
        <div class="servings-box">
          <input
            type="text"
            id="servings"
            placeholder="enter number"
            v-model="newRecipe.numberOfServings"
          />
        </div>
        <br />
        <label for="ingredients">Ingredients</label>
        <table>
        <tr
          id="ingredients"
          class="ingredients-list-boxes"
          v-for="(input, k) in inputs"
          :key="k"
        >
          <input
            type="text"
            placeholder="quantity"
            class="form-control"
            v-model="input.measurementQuantity"
          />
          <input
            type="text"
            placeholder="measurement type"
            v-model="input.measurementType"
          />
          <input
            type="text"
            placeholder="ingredient"
            v-model="input.ingredientName"
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
        <div class="directions-box">
          <textarea
            rows="20"
            cols="200"
            style="width: 500px"
            id="directions"
            placeholder="directions go here"
            v-model="newRecipe.directions"
          />
        </div>
      </div>
      <br />
      <button class="submit-btn" type="submit" value="Submit">Submit</button>
      <button
        class="submit-btn"
        v-on:click.prevent="cancelNewRecipe"
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
      inputs: [
        {
          measurementQuantity: "",
          measurementType: "",
          ingredientName: "",
        },
      ],
      newRecipe: {
        id: "",
        name: "",
        directions: "",
        recipeIngredients: [
          {
            measurementQuantity: "",
            measurementType: "",
            ingredientName: "",
          },
        ],
        numberOfServings: "",
      },
    };
  },
  methods: {
    addNewRecipe() {
      const createdRecipe = {
        userId: this.$store.state.user.id,
        name: this.newRecipe.name,
        directions: this.newRecipe.directions,
        recipeIngredients: this.inputs,
        numberOfServings: this.newRecipe.numberOfServings,
      };
      console.log(createdRecipe.recipeIngredients);
      recipeService
        .newRecipe(createdRecipe)
        .then((response) => {
          if (response.status === 201) {
            alert("Recipe Added!");
            this.$router.push("/myRecipes");
          }
        })
        .catch((err) => {
          console.error(err + " problem adding recipe.");
        });
    },
    add() {
      this.inputs.push({
        measurementQuantity: "",
        measurementType: "",
        ingredientName: "",
      });
    },
    remove(index) {
      this.inputs.splice(index, 1);
    },

    cancelNewRecipe() {
      this.$router.push("/myRecipes");
    },
  },
};
</script>

<style scoped>
.update-form {
  text-align: center;
  border-radius: 25px;
  padding: 10px;
}

.ingredients-list-boxes {
  margin-top: 1%;
}

.servings-box {
  margin-top: 1%;
}

.name-box {
  margin-top: 1%;
}

.directions-box {
  margin-top: 1%;
}

.submit-btn {
  background-color: #1a4314;
  width: 200px;
  height: 45px;
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