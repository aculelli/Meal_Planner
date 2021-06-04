import axios from 'axios';

export default {
    getAllRecipes() {

        return axios.get('/my-recipes');
    },
    getRecipe(recipeId) {
        return axios.get(`/my-recipes/${recipeId}`);
    },
    newRecipe(recipe) {
        return axios.post('/new-recipe', recipe);
    },
    updateRecipe(recipe) {
        console.log(recipe);
        return axios.put(`/update-recipe/${recipe.recipeId}`, recipe);
    },
    deleteRecipe(recipeId) {
        return axios.delete(`/delete-recipe/${recipeId}`);
    },
    addRecipeToMeal(mealId, recipeId) {
        return axios.post(`/add-recipe-to-meal/${mealId}/recipe/${recipeId}`);
    },
    removeRecipeFromMeal(mealId, recipeId) {
        return axios.delete(`/remove-recipe-from-meal/${mealId}/recipe/${recipeId}`);
    }
}