<template>
  <div>
    <h1>My Meal Plans</h1>

    <div>
      <button class="bottom-btn" @click="$router.push('createMealPlan')">
        Create Meal Plan
      </button>
    </div>
    <div>
      <button class="home-btns" @click="$router.push('myRecipes')">
        View My Recipes
      </button>
    </div>
    <!-- <div>
      <router-link :to="{ name: 'groceries' }">My Grocery List</router-link>
    </div> -->

    <div class="my-meal-plans-table">
      <table id="table" v-show="mealPlanList.length > 0">
        <!-- <th >My Meal Plans</th> -->
        <tr v-for="mealPlan in mealPlanList" v-bind:key="mealPlan.mealPlanId">
          <td>
            {{ mealPlan.place }} |
            <router-link
              class="meal-plan-link"
              v-bind:to="{
                name: 'mealPlanDetails',
                params: { mealPlanId: mealPlan.mealPlanId },
              }"
            >
              {{ mealPlan.mealPlanName }}
            </router-link>
            |
            <button
              class="delete-btn"
              v-on:click.prevent="deleteMealPlan(mealPlan.mealPlanId)"
            >
              Delete
            </button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import mealPlanService from "../services/MealPlanService.js";
export default {
  data() {
    return {
      mealPlanList: [],
      user: {
        userName: this.$store.state.user.username,
        id: this.$store.state.user.id,
      },
    };
  },
  methods: {
    deleteMealPlan(mealPlanId) {
      const choice = confirm("Are you sure you want to delete meal plan?");
      if (choice == true) {
        mealPlanService.deleteMealPlan(mealPlanId).then(() => {
          this.$router.go();
        });
      }
    },
  },
  created() {
    mealPlanService.getAllMealPlans().then((response) => {
      this.mealPlanList = response.data;
      for (let i = 0; i < this.mealPlanList.length; i++) {
        this.mealPlanList[i].place = i + 1;
      }
      console.log(this.mealPlanList);
    });
  },
};
</script>

<style scoped>
/* .my-meal-plans-table {
  
} */

td {
  border: hidden;
  background-color: rgba(255, 253, 253, 0.472);
  border-radius: 25px;
  padding: 8px;
  padding-left: 15px;
  padding-right: 15px;
  display: inline-block;
}

.meal-plan-link {
  text-decoration: none;
}

.delete-btn {
  background-color: #1a4314;
  width: 75px;
  height: 10%;
  padding: 0px;
  font-size: 12pt;
}
</style>