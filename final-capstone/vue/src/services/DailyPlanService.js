import axios from 'axios';

export default {


    getAllDailyPlans(mealPlanId) {
        return axios.get('/my-daily-plans', mealPlanId);
    },
    getDailyPlanById(dpId) {
        return axios.get(`/daily-plans/${dpId}`);
    },
    newDailyPlan(dailyPlan) {
        return axios.post('/add-new-daily-plan', dailyPlan);
    },
    updateDailyPlan(dpId, dailyPlan) {
        return axios.put(`/update-daily-plan/${dpId}`, dailyPlan)
    },
    deleteDailyPlan(dpId) {
        return axios.delete(`/delete-daily-plan/${dpId}`);
    }
}