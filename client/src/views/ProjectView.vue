<template>
  <div id="selectedProject">
    <div class="container" id="container">
        <form v-on:submit.prevent="sendProject">
            <h1>{{ project.projectName }}</h1>
            <div id="fields">
                <div>
                    <label for="projectName">Project Name</label>
                        <br>
                        <input
                            type="text"
                            id="projectName"
                            placeholder="Project Name"
                            v-model="project.projectName"
                            required
                        />
                </div>
                <div>
                    <label for="releaseDate">Release Date</label>
                        <br>
                        <input
                            type="text"
                            id="releaseDate"
                            placeholder="Release Date"
                            v-model="project.releaseDate"
                            required
                        />
                </div>
                <div>
                    <label for="description">Album or Single</label>
                        <br>
                        <select id="description" v-model="project.description">
                            <option disabled value="">Please select one</option>
                            <option value="Album">Album</option>
                            <option value="Single">Single</option>
                        </select>
                </div>
                <div>
                    <label for="checkForComplete">Completed?</label>
                        <input
                            type="checkbox"
                            id="checkForComplete"
                            v-model="project.completed"
                        />
                </div>
                <div></div>
                <div id="button">
                    <button type="submit">Update</button>
                    <button id="delete" @click="deleteProject">Delete</button>
                </div>
            </div>
            
        </form>
    </div>

  </div>
</template>

<script>
import { resourceService } from '../services/ResourceService';
export default {
    data(){
        return {
            project: {   

            }
        }
    },
    created(){
        let theeProject = this.$store.state.artistProjects.find((project) => {
                return project.projectId === this.$store.state.clickedProjectId;

            })

            // this.$store.commit('SET_CHOSEN_PROJECT', theeProject);

            // this.project.projectName = this.$store.state.chosenProject.projectName;
            // this.project.releaseDate = this.$store.state.chosenProject.releaseDate;
            // this.project.description = this.$store.state.chosenProject.description;
            // this.project.completed = this.$store.state.chosenProject.completed;
            this.project = theeProject;
            console.log(this.project)
    },
    methods : {
        error(msg) {
            alert(msg);
        },
        success(msg) {
            alert(msg);
        },
        deleteProject(){
            resourceService.deleteProject(this.project.projectId).then((response) => {
                if(response.status == 200 || response.status == 204){
                    this.success("Project Deleted!")
                }
                this.$router.push({name: 'artist'})
            })
        },
        sendProject(){
            resourceService.updateProject(this.project, this.project.projectId).then((response) => {
                if(response.status == 200 || response.status == 204){
                    this.success("Project updated!")
                }
                this.$router.push({ name: 'artist'})
            });
        }
    }

}
</script>

<style scoped>
h1 {
  color: #2e2e2e;
  font-size: 2em;
}
#selectedProject {
    position: relative;
  height: 100vh;
  width: 100%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

#container {
  background-color: #9f9a7f;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0px 4 px 10px rgba(0, 0, 0, 0.2);
  max-width: 400px;
  width: 100%;
  box-sizing: border-box;
}
#fields {
  display: grid;
}
#button{
    display: flex;
}
#delete{
    justify-self: end;
}
label {
  color: #2e2e2e;
}
</style>