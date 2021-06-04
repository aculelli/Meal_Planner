import axios from 'axios';

export default {
    getMealById(mealId) {
        return axios.get(`/meals/${mealId}`);
    },
    getMealsByDailyPlanId(dpId) {
        return axios.get(`/daily-plan/${dpId}/meals`);
    },
    newMeal(meal) {
        return axios.post(`/new-meal`, meal);
    },
    updateMeal(mealId, meal) {
        return axios.put(`/meals/${mealId}`, meal);
    },
    deleteMeal(mealId) {
        return axios.delete(`/meals/${mealId}`);
    }
}