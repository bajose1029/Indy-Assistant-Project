<template>
  <div></div>
</template>

<script>
export default {

}
</script>

<style>

</style>





<!-- <template>
  <div class="container">
    <gantt-component :tasks="tasks" class="main-container"></gantt-component>
  </div>
</template>

<script>
import GanttComponent from '../components/GanttComponent.vue'

export default {
  components: { GanttComponent },
  data() {
    return {
      tasks: {
        data: [{id: null,
          text: "",
          start_date: null,
          progress: 0,
          duration: 0}],
        links: [{id: 1, source: 1, target: 2, type: '0'}]
      },
      project: {},
    }
  },
  methods: {
    setTasks(taskList) {
      let index = 0;
      taskList.forEach((task) => {
        index = index + 1;
        let start = new Date(this.$store.state.chosenProject.releaseDate);
        start.setDate(start.getDate() - ((task.weeksBeforeRelease) * 7));
        let end = new Date(start);
        
        end.setDate(end.getDate() + ((task.weeksToComplete) * 7));

        console.log(start)
        console.log(end)


        // let ganttTask = {
        //   id: index,
        //   text: task.name,
        //   start_date: start,
        //   end_date: end,
        //   progress: 0,
        //   skipped: false,
        //   link: task.link,
        //   note: task.notes,
        //   duration: task.duration,
        //   parent: 0,
        //   status: 'normal'
        // };

        let ganttTask = {
          id: index,
          text: task.name,
          start_date: start,
          end_date: end,
          progress: 0,
          duration: task.duration
        };

        this.tasks.data.push(ganttTask);
      })
      this.checkTaskStatus();
    },
    markAllTasksAsCompleted(){
      this.tasks.data.forEach((task) => {
        task.progress = 100;
      })
    },
    checkTaskStatus(){
      const today = new Date();
      this.tasks.data.forEach((task) => {
        if(task.progress !== 100 && today > task.end_date){
          task.status = 'overdue';
        }
      })
    }
  },
  watch: {
    '$store.state.chosenProject.completed': function(newValue){
      if(newValue){
        this.markAllTasksAsCompleted();
      }
    }
  },
  created(){
    this.project = this.$store.state.chosenProject;
    let taskList = [
      { name: "Finalize Mastered Track" , weeksBeforeRelease: 8, link: "", weeksToComplete: 1, notes:"", duration: 7},
      { name: "Finalize Cover Art", weeksBeforeRelease: 8, link: "", weeksToComplete: 1, notes:"", duration: 7 },
      { name: "Finalize Lyric Video and/or Official Music Video (Youtube) - Optional", weeksBeforeRelease: 8, link: "https://www.youtube.com", weeksToComplete: 2, notes:"", duration: 14 },
      { name: "Finalize Songwriter Splits", weeksBeforeRelease: 7, link: "../src/assets/Resources/SplitSheet.pdf", weeksToComplete: 1, notes:"", duration: 7},
      { name: "Upload to Music Distributor (i.e DistroKid)", weeksBeforeRelease: 7, link: "https://distrokid.com", weeksToComplete: 1, notes: "Take advantage of all the functionality your distributor allows such as adding lyrics!", duration: 7},
      { name: "Upload on SoundCloud (Private) - Optional", weeksBeforeRelease: 7, link: "https://soundcloud.com/", weeksToComplete: 1, notes: "Allow your biggest of fans to have access to your track before anyone else!", duration: 7},
      { name: "Upload on Bandcamp (Private) - Optional", weeksBeforeRelease: 7, link: "https://bandcamp.com/", weeksToComplete: 1, notes: "Allow your biggest of fans to have access to your track before anyone else!", duration: 7},
      { name: "Submit for Spotify Playlisting (Spotify for Artists)", weeksBeforeRelease: 6, link: "https://artists.spotify.com/home", weeksToComplete: 1, notes:"", duration: 7},
      { name: "Launch Pre-Save/Pre-Order Link", weeksBeforeRelease: 6, link: "", weeksToComplete: 1, notes:"", duration: 7},
      { name: "Update Smart-Link (i.e LinkTree) with Pre-Save Link", weeksBeforeRelease: 6, link: "https://linktr.ee/", weeksToComplete: 1, notes:"", duration: 7 },
      { name: "Pitch to Spotify Playlists (Playlister on DistroKid)", weeksBeforeRelease: 5, link: "", weeksToComplete: 1, notes:"", duration: 7},
      //attach content, brainstorming excel sheet
      { name: "Fill out Content Release Schedule/Timeline", weeksBeforeRelease: 5, link: "", weeksToComplete: 1, notes:"", duration: 7}, 
      { name: "Release Hype Content for Release", weeksBeforeRelease: 5, link: "", weeksToComplete: 2, notes:"", duration: 14},
      { name: "Finalize Day of Release Content", weeksBeforeRelease: 4, link: "", weeksToComplete: 1, notes:"", duration: 7},
      { name: "Batch Content for Release", weeksBeforeRelease: 4, link: "", weeksToComplete: 3, notes:"", duration: 21},
      { name: "Schedule out Social Media Releases using a Social Media Schedular (i.e Pallyy)", weeksBeforeRelease: 4, link: "https://pallyy.com/", weeksToComplete: 2, notes:"", duration: 14},
      { name: "Initiate Spotify For Artists Campaign", weeksBeforeRelease: 3, link: "", weeksToComplete: 1, notes:"", duration: 7},
      { name: "Finalize Social Media Content", weeksBeforeRelease: 2, link: "", weeksToComplete: 1, notes:"", duration: 7}
    ];

    this.setTasks(taskList);
    console.log(this.project);
    console.log(this.tasks.data);
  }

}
</script>

<style scoped>
  html, body {
    height: 100%;
    margin: 0;
    padding: 0;
  }
  .container {
    height: 100vh;
    width: 100%;
  }
  .main-container {
    overflow: hidden;
    position: relative;
    height: 100%;
  }
</style> -->