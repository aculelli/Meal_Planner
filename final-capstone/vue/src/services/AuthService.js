import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  getUserId(userName) {
    return axios.get(`/get-user-id-from-name?name=${userName}`)
  }




}
