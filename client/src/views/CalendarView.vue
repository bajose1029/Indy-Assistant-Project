<template>
  <div id="main-div">
    <section id="main-icon">
      <page-icons id="icons-for-page" />
    </section>
    <section id="calendar">
      <full-calendar :options="calendarOptions" >
      </full-calendar>
    </section>
  </div>
</template>

<script>

import PageIcons from '../components/PageIcons.vue';

import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import listPlugin from '@fullcalendar/list'



import { resourceService } from '../services/ResourceService';

export default {
  data() {
    return {
      calendarOptions: {
        plugins: [ dayGridPlugin, timeGridPlugin, interactionPlugin, listPlugin ],
        initialView: 'dayGridMonth',
        weekends: true,
        events: [],
        aspectRatio: 2.5,
    },
    
    }
  },
  components : {
    PageIcons,
    FullCalendar
  },
  created() {
    let projectInfo;
    let updatedEvents;
    if(this.$store.state.user.type === 'Admin')
    {
      resourceService.getProjects().then((response) => {
        console.log(response)
        this.$store.commit('SET_CALENDAR_PROJECTS', response.data)

        projectInfo = this.$store.state.calendarProjects;
        updatedEvents = projectInfo.map((project) => {
          return {
            title: project.projectName,
            start: project.releaseDate,
            backgroundColor: '#9f9a7f',
            textColor: '#2e2e2e'
          }
        })

        this.calendarOptions = {
          ...this.calendarOptions,
          events: updatedEvents
        }
        console.log(projectInfo.releaseDate)
      })
    }
    else if (this.$store.state.user.type === 'Manager') {
      let manager = this.$store.state.managerProfile;
      resourceService.getProjectsFromManager(manager.managerId).then((response) => {
        this.$store.commit('SET_CALENDAR_PROJECTS', response.data)

        projectInfo = this.$store.state.calendarProjects;
        updatedEvents = projectInfo.map((project) => {
          return {
            title: project.projectName,
            start: project.releaseDate,
          }
        })

        this.calendarOptions = {
          ...this.calendarOptions,
          events: updatedEvents
        }
      })
    }
    else {
      let artist = this.$store.state.artistProfile;
      resourceService.getAnArtistsProjects(artist.artistId).then((response) => {
        this.$store.commit('SET_CALENDAR_PROJECTS', response.data)

        projectInfo = this.$store.state.calendarProjects;
        updatedEvents = projectInfo.map((project) => {
          return {
            title: project.projectName,
            start: project.releaseDate,
          }
      })

      this.calendarOptions = {
          ...this.calendarOptions,
          events: updatedEvents
        }
    })
  }
}
}
</script >

<style scoped>

</style>