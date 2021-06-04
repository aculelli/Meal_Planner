import axios from 'axios';


export default {
    getRecipeIngredientsByRecipeId(recipeId) {
        return axios.get(`/recipe-ingredient/${recipeId}`);
    },
    getRecipeIngredientByIngredientId(recipeIngredientId) {
        return axios.get(`/recipe-ingredient/${recipeIngredientId}`);
    },
    newRecipeIngredient(recipeIngredient) {
        return axios.post('/new-recipe-ingredient', recipeIngredient);
    },
    updateRecipeIngredient(recipeIngredientId, recipeIngredient) {
        return axios.put(`/update-recipe-ingredient/${recipeIngredientId}`, recipeIngredient);
    },
    deleteRecipeIngredient(recipeIngredientId) {
        return axios.delete(`/delete-recipe-ingredient/${recipeIngredientId}`);
    },
    getAllMeasurementTypes() {
        return axios.get('/measurement-types');
    }
}