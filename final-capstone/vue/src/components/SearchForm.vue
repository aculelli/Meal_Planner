<template>
  <div>
    <br />
    <br />
    <form class="search-form" v-on:submit.prevent="submitSearch()">
      Enter ingredient(s) separated by a comma with no spaces
      <br />(banana,peanut butter,bread): <br /><br /><input
        size="50"
        type="text"
        placeholder="Ingredients"
        v-model="ingredients"
        required
      />
      <br />
      <br />
      <br />
      Limit to
      <input
        type="number"
        min="1"
        max="100"
        v-model="limitNumber"
        placeholder="0"
        required
      />
      results. <br /><br />
      <br />
      <br />
      <button class="submit-btn" type="submit" value="Submit">Submit</button>
      <button
        class="submit-btn"
        v-on:click.prevent="cancelSearch"
        type="cancel"
      >
        Cancel
      </button>
    </form>
    <div class="table" v-show="searchSubmitted === true">
      <table>
        <th>Search Results</th>

        <tr v-for="recipe in recipeList" v-bind:key="recipe.id">
          <td>
            {{ recipe.place }} |
            <!-- <button class="search-results-btn"> -->
            <router-link
              class="link"
              v-bind:to="{
                name: 'recipeDetails',
                params: { id: recipe.id },
              }"
              >{{ recipe.title }}</router-link
            >
            <!-- </button> -->
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import spoonacularService from "../services/SpoonacularService.js";

export default {
  data() {
    return {
      ingredients: "",
      limitNumber: "",
      recipeList: [],
      searchSubmitted: false,
    };
  },
  methods: {
    submitSearch() {
      spoonacularService
        .getSearchResults(this.ingredients, this.limitNumber)
        .then((recipe) => {
          this.recipeList = recipe.data;
          if (this.recipeList.length === 0) {
            alert("No recipes found");
          }
          for (let i = 0; i < this.recipeList.length; i++) {
            this.recipeList[i].place = i + 1;
          }
        });
      this.searchSubmitted = true;
    },
    cancelSearch() {
      this.$router.push("/");
    },
  },
  computed: {},
};
</script>

<style scoped>
form {
  background-color: #94c973;
  border-radius: 25px;
  padding: 25px;
  font-size: 16pt;
}

.search-form {
  margin-top: 0%;
}

.submit-btn {
  background-color: #1a4314;
}

th {
  background-color: #1a4314;
}

td {
  width: 75%;
}

.table {
  background-color: #94c973;
  border-radius: 25px;
  margin: 15px 0px 0px 0px;
  padding: 15px;
}

.search-results-btn {
  padding: 25px;
  width: 50%;
}

table {
  margin-left: auto;
  margin-right: auto;
  font-size: 16pt;
}

.link {
  text-decoration: none;
}
</style>