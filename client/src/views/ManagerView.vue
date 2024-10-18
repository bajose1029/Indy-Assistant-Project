<template>
  <div id="main-div">
    <section id="main-icon">
      <page-icons id="icons-for-page" />
    </section>
    <section id="manager-main-section">
        <select-list-manager id="choose-artist"/>
        <artist-list id="managers-list-of-artists" />
        <manager-page id="manager-profile" />
    </section>
</div>
</template>

<script>
import ArtistList from '../components/ArtistList.vue';
import PageIcons from '../components/PageIcons.vue';
import SelectListManager from '../components/SelectListManager.vue'
import ManagerPage from '../components/ManagerPage.vue';
import { resourceService } from '../services/ResourceService';

export default {
    data() {
    return {
      isLoading: false
    }
  },
  components: {
    PageIcons,
    SelectListManager,
    ArtistList,
    ManagerPage
  },
  created() {
    // this.isLoading = true;
    let managerId;
    
    resourceService.getManagerFromUserId(this.$store.state.user.id).then((response) => {
      let manager = response.data;
      managerId = manager.managerId;
    })


    resourceService.getManagerFromManagerId(managerId).then((response) => {
      this.$store.commit('SET_MANAGER_PROFILE', response.data);
    })

    resourceService.getArtistsFromManagerId(managerId).then((response) => {
      this.$store.commit('SET_MY_ARTISTS', response.data);
    })
    
    
    // this.isLoading = false;
  }

}
</script>

<style>
#main-div {
  display: grid;
  grid-template-rows: 0.8fr 50fr;
  grid-template-areas: 
  "icons"
  "info";
}

#main-icon {
  grid-area: icons;
}

#manager-main-section {
  grid-area: info;
  background-color: #2e2e2e;
  display : grid;
  grid-template-columns: 3fr 9fr 9fr;
  grid-template-areas:
  "select   list   profile";
  gap: 5px;
  overflow-y: auto;
}

#choose-artist {
    grid-area: select;
}

#managers-list-of-artists {
    grid-area: list;
}

#manager-profile {
    grid-area: profile;
}

@media only screen and (max-width: 450px) {
    #manager-main-section {
        grid-template-rows: 1fr 9fr 9fr;
        grid-template-columns: none;
        grid-template-areas: 
        "select"
        "list"
        "profile";
    }
}
</style>