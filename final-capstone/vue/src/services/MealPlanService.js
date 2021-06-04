import axios from 'axios';


export default {
    getAllMealPlans() {
        return axios.get('/meal-plans');
    },
    getMealPlanByName(mealPlanName) {
        return axios.get('/my-meal-plan', { params: { mealPlanName } });
    },
    getMealPlanById(mealPlanId) {
        return axios.get(`/meal-plans/${mealPlanId}`);
    },
    getIngredientsByMealPlanId(mealPlanId) {
        return axios.get(`/grocery-list/${mealPlanId}`);
    },
    newMealPlan(mealPlan) {
        return axios.post('/add-new-meal-plan', mealPlan);
    },
    updateMealPlan(mealPlanId, mealPlan) {
        return axios.put(`/update-meal-plan/${mealPlanId}`, mealPlan);
    },
    deleteMealPlan(mealPlanId) {
        return axios.delete(`/delete-meal-plan/${mealPlanId}`);
    }
}