<template>
  <div>
    <head>
      <link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
        rel="stylesheet"
      />
    </head>
    <form class="meal-plan-form" v-on:submit.prevent="addNewMealPlan">
      <!-- <h1>New Meal Plan</h1> -->
      <div class="new-meal-title">
        <!-- <label for="title">Name </label> -->
        <div>
          <input
            id="title"
            type="text"
            placeholder="enter name of meal plan here"
            minlength="1"
            maxlength="500"
            size="75"
            v-model="newMealPlan.mealPlanName"
          />
        </div>
      </div>
      <button class="meal-plan-btns" type="submit" value="Submit">
        Submit
      </button>
      <button
        class="meal-plan-btns"
        v-on:click.prevent="cancelAddRecipe()"
        type="cancel"
      >
        Go Back to Meal Plans
      </button>
    </form>
    <!-- <button type="submit" v-bind:to="addNewMealPlan()">Save</button> -->
    <!-- <form v-if="displayForm" v-on:submit.prevent="appendNewMealPlan"> -->
    <br />
    <div
      class="daily-plan"
      v-for="dailyPlan in newMealPlan.dailyPlanList"
      v-bind:key="dailyPlan.dpId"
    >
      {{ dailyPlan.weekday }}
      <div v-for="meal in dailyPlan.meals" v-bind:key="meal.mealId">
        {{ meal.mealType }}
      </div>
    </div>
    <!-- <h2>Add Recipes to Days</h2> -->
  </div>
</template>

<script>
import mealPlanService from "../services/MealPlanService.js";
import recipeService from "../services/RecipeService.js";
export default {
  data() {
    return {
      // displayForm: false,
      newMealPlan: {
        mealPlanId: "",
        userId: "",
        mealPlanName: "",
        dailyPlanList: [
          {
            dpId: "",
            weekday: "",
            meals: [
              {
                recipes: [],
              },
            ],
            mealPlanId: "",
          },
        ],
      },
      myRecipes: [],
      user: {
        userName: this.$store.state.user.username,
        id: this.$store.state.user.id,
      },
    };
  },
  methods: {
    addNewMealPlan() {
      const createdMealPlan = {
        userId: this.$store.state.user.id,
        mealPlanName: this.newMealPlan.mealPlanName,
        dailyPlanList: [],
      };

      mealPlanService.newMealPlan(createdMealPlan).then((response) => {
        if (response.status === 201) {
          mealPlanService
            .getMealPlanByName(this.newMealPlan.mealPlanName)
            .then((response) => {
              this.newMealPlan = response.data;
              this.$router.push(
                `/mealPlanDetails/${this.newMealPlan.mealPlanId}`
              );
            });

          // alert("Meal Plan Added!");
          //console.log(this.newMealPlan.mealPlanId);
          //  this.$router.push(`/mealPlanDetails/${this.newMealPlan.mealPlanId}`);
        }
      });
    },
    cancelAddRecipe() {
      this.$router.push("/myMealPlans");
    },

    // appendNewMealPlan() {},
  },
  created() {
    recipeService.getAllRecipes().then((response) => {
      this.myRecipes = response.data;
    });
  },
};
</script>

<style scoped>
.meal-plan-btns {
  background-color: #1a4314;
  width: 250px;
  height: 45px;
  margin: 20px;
  padding: 0px;
}

.meal-plan-form {
  padding-top: 50px;
}
</style>