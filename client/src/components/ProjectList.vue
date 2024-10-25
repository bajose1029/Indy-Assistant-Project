<template>
    <section id="artist-list-of-projects">
        <div id="add-container" v-if="this.$store.state.user.type === 'Artist' || this.$store.state.user.type === 'Manager'">
            <font-awesome-icon @click="addProject()" icon="fa-solid fa-plus" id="add-button"/>
        </div>
        <ul>
            <li class="hyperlink" v-for="artistProject in artistProjects" v-bind:key="artistProject.projectId" @click="selectedProject(artistProject.projectId)">
                {{ artistProject.projectName }}
                <div id="release-date">
                    {{ artistProject.releaseDate }}
                </div>
            </li>
        </ul>
    </section>
</template>

<script>
export default {
    computed : {
        artistProjects(){
            if(this.$store.state.projectChosen === "all"){
                return this.$store.state.artistProjects;
            }
            else if(this.$store.state.projectChosen === "completed") {
                return this.$store.state.completedProjects;
            }
            else {
                return this.$store.state.inProcessProjects
            }
        }
    },
    methods: {
        selectedProject(projectId){
            console.log(projectId)
            this.$store.commit('SET_CLICKED_PROJECT_ID', projectId);
            this.$router.push({ name: 'project'});
        },
        addProject(){
            this.$router.push( { name: 'addProject'});
        }
        
    }

}
</script>

<style scoped>
#artist-list-of-projects {
    background-color: #9f9a7f;
    padding: 20px;
}

#artist-list-of-projects > ul {
    padding: 0;
}

#artist-list-of-projects ul li {
    list-style-type: none;
    padding: 0 10px 0 10px;
    margin: 10px 0 10px 0;
    width: 100%;
    font-size: 1em;
    background-color: #2e2e2e;
    border-radius: 5px;
    line-height: 50px;
    box-shadow: 2px 2px 2px #777;
    align-self:center;
    display: flex;
    justify-content: space-between;
    align-items: center;

}

#artist-list-of-projects ul li img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    
}

#artist-list-of-projects ul li.clicked-on {
    color: #8a737d;
}

.hyperlink:hover {
    color: #8a737d;
}

#add-button{
    font-size: 25px;
    color: #2e2e2e;
}
#add-container{
    display: flex;
    flex-grow: 1;
    justify-content: end;
}
</style>