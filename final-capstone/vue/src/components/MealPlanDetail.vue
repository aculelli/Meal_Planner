<template>
  <div>
    <h1 id="recipe-header">Meal Plan Details: {{ mealPlan.mealPlanName }}</h1>
    <div>
      <div>
        <router-link
          v-bind:to="{
            name: 'groceries',
            params: { mealPlanId: mealPlan.mealPlanId },
          }"
          tag="button"
          >Create Grocery List</router-link
        >
      </div>
    </div>
    <div>
      <router-link
        v-bind:to="{
          name: 'myMealPlans',
          params: { mealPlanId: mealPlan.mealPlanId },
        }"
        tag="button"
        >Go Back to Meal Plans</router-link
      >
    </div>
    <div
      id="weekday"
      v-for="dailyPlan in mealPlan.dailyPlanList"
      v-bind:key="dailyPlan.dpId"
    >
      {{ dailyPlan.weekday }}

      <div
        id="mealType"
        v-for="meal in dailyPlan.meals"
        v-bind:key="meal.mealId"
      >
        <router-link
        id="weekday-meal"
          v-bind:to="{
            name: 'mealDetails',
            params: { mealId: meal.mealId },
          }"
          >{{ meal.mealType }}</router-link
        >

        <router-link
          id="plan-details"
          v-bind:to="{
            name: 'mealDetails',
            params: { mealPlanId: dailyPlan.mealPlanId, mealId: meal.mealId },
          }"
        >
          Add Recipe</router-link
        >
        <div
          class="recipe-name"
          v-for="recipe in meal.recipes"
          v-bind:key="recipe.recipeId"
        >
          <router-link
            class="recipe-name-link"
            v-bind:to="{
              name: 'myRecipeDetails',
              params: { id: recipe.recipeId },
            }"
          >
            {{ recipe.name }}</router-link
          >
          |
          <button
            class="remove-recipe-btn"
            v-on:click.prevent="deleteRecipe(meal.mealId, recipe.recipeId)"
          >
            Remove
          </button>
        </div>
      </div>
    </div>

    <!-- <div v-for="dailyPlan in dailyPlans" v-bind:key="dailyPlan.dpId">
      {{ dailyPlan.weekday }}
      <div v-for="meal in dailyPlan.meals" v-bind:key="meal.mealId">
        <router-link
          :to="{ name: 'mealDetails', params: { mealId: meal.Id } }"
          >{{ meal.mealType }}</router-link
        >
      </div>
    </div> -->
  </div>
</template>

<script>
import mealPlanService from "../services/MealPlanService.js";
import recipeService from "../services/RecipeService.js";
export default {
  data() {
    return {
      mealPlan: {},
      // dailyPlans: [],
      user: {
        userName: this.$store.state.user.username,
        id: this.$store.state.user.id,
      },
    };
  },
  methods: {
    deleteRecipe(mealId, recipeId) {
      console.log(mealId, recipeId);
      recipeService.removeRecipeFromMeal(mealId, recipeId).then(() => {
        // console.log(mealId, recipeId);
        // this.$router.push(`/mealPlanDetails/${this.mealPlan.mealPlanId}`);
        this.$router.go();
      });
    },
  },
  created() {
    // dailyPlanService
    //   .getAllDailyPlans(this.$route.params.mealPlanId)
    //   .then((response) => {
    //     this.dailyPlans = response.data;
    //   });
    mealPlanService
      .getMealPlanById(this.$route.params.mealPlanId)
      .then((response) => {
        this.mealPlan = response.data;
      });
  },
};
</script>

<style scoped>
#weekday {
  font-size: 24pt;
  background-color: rgba(255, 253, 253, 0.609);
  border-radius: 25px;
  text-align: center;
  padding-left: 10px;
  margin: auto;
  margin-top: 10px;
  color: #1a4314;
  font-weight: bold;
  padding-bottom: 7px;
  width: 65%;
}

#mealType {
  font-size: 18pt;
  background-color: rgba(255, 253, 253, 0.726);
  border-radius: 25px;
  margin: 10px;
  color: black;
}

#plan-details {
  font-weight: normal;
  text-decoration: none;
  font-size: 14pt;
}

.recipe-name {
  font-size: 14pt;
  text-decoration: none;
}

.remove-recipe-btn {
  width: 70px;
  height: 10%;
  padding: 0px;
  font-size: 12pt;
}

#recipe-header {
  font-size: 28pt;
}

.recipe-name-link {
  text-decoration: none;
  color: #264653;
}

#weekday-meal {
  color: black;
}
</style>