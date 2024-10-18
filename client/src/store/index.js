import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      all: [],
      artists: [],
      myArtists: [],
      managerProfile: {},
      artistProfile: {},
      managers: [],
      artistProjects: [],
      currentUserId: 1,
      // currentArtistId:
      chosen : "all",
      managerPageChosen : "artist",
      iconChosen: "artist",
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_ALL(state, all) {
        state.all = all;
      },
      
      SET_MANAGER_PROFILE(state, manager) {
        state.managerProfile = manager;
      },

      SET_MANAGER_PAGE_CHOSEN(state, managerPageChosen) {
        state.managerPageChosen = managerPageChosen
      },

      SET_CHOSEN_ICON(state, iconChosen) {
        state.iconChosen = iconChosen;
      },

      SET_CHOSEN(state, chosen){
        state.chosen = chosen;
      },

      SET_CURRENT_USER_ID(state, currentUserId) {
        state.currentUserId = currentUserId;
      },

      SET_ARTISTS(state, artists){
        state.artists = artists;
      },

      SET_MY_ARTISTS(state, myArtists) {
        state.myArtists = myArtists;
      },

      SET_MANAGERS(state, managers){
        state.managers = managers;
      },
      SET_PROJECTS(state, projects){
        state.artistProjects = projects;
      },

      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },

  })
  return store;
}