import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      all: [],
      clickedManagerId: 0,
      clickedArtistId: 0,
      clickedProjectId: 0,
      artistsManager: {},
      artists: [],
      users: [],
      myArtists: [],
      managerProfile: {},
      artistProfile: {},
      managers: [],
      allProjects: [],
      completedProjects: [],
      inProcessProjects: [],
      artistProjects: [],
      chosenProject: {},
      currentUserId: 0,
      // currentArtistId:
      chosen : "all",
      projectChosen: "all",
      managerPageChosen : "artist",
      iconChosen: "artist",
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_CHOSEN_PROJECT(state, project) {
        state.chosenProject = project;
      },
      SET_COMPLETED_PROJECTS(state, projects) {
        state.completedProjects = projects;
      },
      SET_IN_PROCESS_PROJECTS(state, projects){
        state.inProcessProjects = projects;
      },
      SET_ARTISTS_MANAGER(state, manager){
        state.artistsManager = manager;
      },
      SET_PROJECT_CHOSEN(state, projectChosen){
        state.projectChosen = projectChosen;
      },
      SET_ARTIST_PROJECTS(state, projects){
        state.artistProjects = projects;
      },
      SET_CLICKED_ARTIST_ID(state, artistId){
        state.clickedArtistId = artistId;
      },
      
      SET_CLICKED_PROJECT_ID(state, projectId){
        state.clickedProjectId = projectId
      },

      SET_CLICKED_MANAGER_ID(state, managerId){
        state.clickedManagerId = managerId;
      },
      SET_ALL(state, all) {
        state.all = all;
      },

      SET_USERS(state, users) {
        state.users = users;
      },

      SET_MANAGER_PROFILE(state, manager) {
        state.managerProfile = manager;
      },
      SET_ARTIST_PROFILE(state, artist) {
        state.artistProfile = artist;
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
      SET_ALL_PROJECTS(state, allProjects) {
        state.allProjects = allProjects;
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