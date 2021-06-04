import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import SearchRecipes from '../views/SearchRecipes.vue'
import RecipeDetails from '../views/RecipeDetails.vue'
import UpdateRecipe from '../views/UpdateRecipe.vue'
import AddRecipe from '../views/AddRecipe.vue'
import MyRecipes from '../views/MyRecipes.vue'
import MyRecipeDetails from '../views/MyRecipeDetails.vue'
import CreateNewRecipe from '../views/CreateNewRecipe.vue'
import MyMealPlans from '../views/MyMealPlans.vue'
import CreateMealPlan from '../views/CreateMealPlan.vue'
import MealPlanDetails from '../views/MealPlanDetails.vue'
import MealDetails from '../components/Meal.vue'
import Groceries from '../views/Groceries.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/searchRecipes",
      name: "searchRecipes",
      component: SearchRecipes,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/recipeDetails/:id",
      name: "recipeDetails",
      component: RecipeDetails,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/updateRecipe/:id",
      name: "updateRecipe",
      component: UpdateRecipe,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/addRecipe",
      name: "addRecipe",
      component: AddRecipe,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/myRecipes",
      name: "myRecipes",
      component: MyRecipes,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/myRecipes/:id",
      name: "myRecipeDetails",
      component: MyRecipeDetails,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/createNewRecipe",
      name: "createNewRecipe",
      component: CreateNewRecipe,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/myMealPlans",
      name: "myMealPlans",
      component: MyMealPlans,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/createMealPlan",
      name: "createMealPlan",
      component: CreateMealPlan,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/mealPlanDetails/:mealPlanId",
      name: "mealPlanDetails",
      component: MealPlanDetails,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/mealPlanDetails/:mealPlanId/meals/:mealId",
      name: "mealDetails",
      component: MealDetails,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/groceryList/:mealPlanId",
      name: "groceries",
      component: Groceries,
      meta: {
        requiresAuth: true,
      }
    },

  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
