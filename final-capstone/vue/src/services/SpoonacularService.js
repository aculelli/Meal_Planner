import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});


export default {
    // spoonacular API
    getSearchResults(ingredients, limitNumber) {
        return http.get(`/recipes?ingredients=${ingredients}&limitNumber=${limitNumber}`);
    },

    getRecipeDetails(id) {
        return http.get(`/recipes/${id}`);
    },





}

