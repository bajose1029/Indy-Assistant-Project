<template>
  <div id="main-div">
    <section id="main-icon">
      <page-icons id="icons-for-page" />
    </section>
    <section id="main-section">
      <select-list-admin id="all-artist-manager"/>
      <user-list id="list-of-artist-manager"/>
      <profile-page id="chosen-person-profile"/>
    </section>
  </div>
</template>

<script>
import PageIcons from '../components/PageIcons.vue';
import SelectListAdmin from '../components/SelectListAdmin.vue';
import UserList from '../components/UserList.vue';
import ProfilePage from '../components/ProfilePage.vue';
import { resourceService } from '../services/ResourceService';

export default {
  data() {
    return {
      isLoading: false
    }
  },

  components : {
    PageIcons,
    SelectListAdmin,
    UserList,
    ProfilePage
  },
  created() {
    // this.isLoading = true;

    // Promise.all([resourceService.getArtists(), resourceService.getManagers(), resourceService.getUsers()]).then(([artistResponse, managerResponse, userResponse]) => {
    //   this.$store.commit('SET_ARTISTS', artistResponse.data);
    //   this.$store.commit('SET_MANAGERS', managerResponse.data);
    //   this.$store.commit('SET_USERS', userResponse.data);
    // }).error((error) => {
    //   console.log(error);
    // }).finally(()=> {
    //   this.isLoading = false;
    // })

    resourceService.getArtists().then((response) => {
      this.$store.commit('SET_ARTISTS', response.data);
    })


    resourceService.getManagers().then((response) => {
      this.$store.commit('SET_MANAGERS', response.data);
    })

    resourceService.getUsers().then((response) => {
      this.$store.commit('SET_USERS', response.data);
    })


    
    
    // this.$store.commit('SET_ALL', );
    // this.isLoading = false;
  }
}
</script>

<style scoped>
#main-div {
  height: 100%;
  display: grid;
  grid-template-rows: 75px 1fr;
  grid-template-areas: 
  "icons"
  "info";
}

#main-icon {
  grid-area: icons;
  margin: 0 0 0 0;
  padding: 0 0 5px 0;
}

#main-section {
  grid-area: info;
  background-color: #2e2e2e;
  display : grid;
  grid-template-columns: 3fr 9fr 9fr;
  grid-template-areas:
  "select   list   profile";
  gap: 5px;
  overflow-y: auto;
}

#all-artist-manager {
    grid-area: select;
}

#list-of-artist-manager {
    grid-area: list;
}

#chosen-person-profile {
    grid-area: profile;
}

@media only screen and (max-width: 450px) {
    #main-section {
        grid-template-rows: 1fr 9fr 9fr;
        grid-template-columns: none;
        grid-template-areas: 
        "select"
        "list"
        "profile";
    }
}
</style>