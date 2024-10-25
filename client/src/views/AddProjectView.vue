<template>
  <div id="add-project-page">
    <div class="container" id="container">
        <form @submit.prevent="validateReleaseDate()">
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
                            type="date"
                            id="releaseDate"
                            placeholder="Release Date"
                            v-model="project.releaseDate"
                            required
                        />
                </div>

                <div>
                    <label for="description" required>Album or Single</label>
                        <br>
                        <select id="description" v-model="project.description">
                            <option disabled value="">Please select one</option>
                            <option value="Album">Album</option>
                            <option value="Single">Single</option>
                        </select>
                </div>
                <div id="submit-button">
                    <br>
                    <button type="submit">Add Project</button>
                </div>
                <p v-if="errorMessage" style="color: whitesmoke;">{{ errorMessage }}</p>
            </div>
        </form>
    </div>
  </div>
</template>

<script>
import { formatDate } from '@fullcalendar/core';
import { resourceService } from '../services/ResourceService';

export default {
    data() {
        return {
            project: {
                projectName: "",
                releaseDate: "",
                description: "",
                completed: false
            },
            errorMessage: ''
        }
    },
    methods : {
        validateReleaseDate(){
            const today = new Date();
            const projectReleaseDate = new Date(this.project.releaseDate);
            const eightWeeksCheck = new Date();
            eightWeeksCheck.setDate(today.getDate() + 56);

            if(projectReleaseDate < eightWeeksCheck) {
                this.errorMessage = "The release date must be at least 8 weeks from today!"
            }
            else {
                this.errorMessage = '';
                this.project.releaseDate = this.formatDate(projectReleaseDate);
                console.log(this.project.releaseDate);
                resourceService.addArtistsProject(this.project).then((response) => {
                    alert(response.data.projectName + " has been added successfully");

                    this.$router.push({ name: 'artist' })
                })
            }
        },
        formatDate(date) {
            const year = date.getFullYear();
            let month = (date.getMonth() + 1).toString();
            let day = date.getDate().toString(); 

            if (month.length < 2) 
            {
                month = '0' + month;
            }
            if (day.length < 2) 
            {
                day = '0' + day;
            }
            return `${year}-${month}-${day}`;
        }

    }
}
</script>

<style scoped>
#add-button{
    flex-grow: 1;
}
h1 {
  color: #2e2e2e;
  font-size: 2em;
  margin-top: 0px;
}

#add-project-page{
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

label {
  color: #2e2e2e;
}
</style>