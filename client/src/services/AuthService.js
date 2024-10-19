import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  registerArtist(artist){
    return axios.post('/artists', artist)
  },

  registerManager(manager) {
    return axios.post('/managers', manager)
  }

}
