<template>
  <div id="main-div">
      <section id="main-icon">
        <page-icons id="icons-for-page" />
      </section>
      <section id="artist-main-section">
        <select-list-artist id="choose-project"/>
        <project-list id="artist-list-of-projects"/>
        <artist-page id="artist-profile"/>
      </section>
  </div> 
</template>

<script>
import PageIcons from '../components/PageIcons.vue';
import SelectListArtist from '../components/SelectListArtist.vue';
import ProjectList from '../components/ProjectList.vue';
import ArtistPage from '../components/ArtistPage.vue';
import { resourceService } from '../services/ResourceService';

export default {
  data() {
    return {
      isLoading: false
    }
  },
  components: {
    PageIcons,
    SelectListArtist,
    ProjectList,
    ArtistPage
  },
  created() {

    if(this.$store.state.user.type === "Admin" || this.$store.state.user.type === 'Manager')
    {
      resourceService.getArtistFromArtistId(this.$store.state.clickedArtistId).then((response) => {
        this.$store.commit('SET_ARTIST_PROFILE', response.data);
      });

      resourceService.getAnArtistsProjects(this.$store.state.clickedArtistId).then((response) => {
        this.$store.commit('SET_ARTIST_PROJECTS', response.data);

        let completed = response.data.filter((project) => {
          return project.completed === true;
        });

        let inProcess = response.data.filter((project) => {
          return project.completed === false;
        })

        this.$store.commit('SET_COMPLETED_PROJECTS', completed);
        this.$store.commit('SET_IN_PROCESS_PROJECTS', inProcess);
      })

      resourceService.getManagerFromArtistId(this.$store.state.clickedArtistId).then((response) => {
        this.$store.commit('SET_ARTISTS_MANAGER', response.data);
      })
    }
    else 
    {
      resourceService.getArtistFromUserId(this.$store.state.user.id).then((response) => {
        let artist = response.data;
        let artistId = artist.artistId;

        resourceService.getManagerFromArtistId(artistId).then((response) => {
          this.$store.commit('SET_ARTISTS_MANAGER', response.data)
        });

        resourceService.getArtistFromArtistId(artistId).then((response) => {
        this.$store.commit('SET_ARTIST_PROFILE', response.data)
        });

        resourceService.getAnArtistsProjects(artistId).then((response) => {
        this.$store.commit('SET_ARTIST_PROJECTS', response.data)

        let completed = response.data.filter((project) => {
          return project.completed === true;
        });

        let inProcess = response.data.filter((project) => {
          return project.completed === false;
        })

        this.$store.commit('SET_COMPLETED_PROJECTS', completed);
        this.$store.commit('SET_IN_PROCESS_PROJECTS', inProcess);
        });
      
      })
    } 
  }
}
</script>

<style scoped>
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

#artist-main-section {
  grid-area: info;
  background-color: #2e2e2e;
  display : grid;
  grid-template-columns: 3fr 9fr 9fr;
  grid-template-areas:
  "select   list   profile";
  gap: 5px;
  overflow-y: hidden;
}

#choose-project {
    grid-area: select;
}

#artist-list-of-projects {
    grid-area: list;
}

#artist-profile {
    grid-area: profile;
}

@media only screen and (max-width: 450px) {
    #artist-main-section {
        grid-template-rows: 1fr 9fr 9fr;
        grid-template-columns: none;
        grid-template-areas: 
        "select"
        "list"
        "profile";
    }
}
</style>