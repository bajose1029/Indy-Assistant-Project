<template>
  <section id="choose-project">
    <ul id="list-of-project">
        <li v-bind:class="{ chosen : projectChosen === 'all'}" id="all-project" @click="chooseProject('all')"> All </li>
        <li v-bind:class="{ chosen : projectChosen === 'completed'}" id="completed-project" @click="chooseProject('completed')"> Completed </li>
        <li v-bind:class="{ chosen : projectChosen === 'inProcess'}" id="in-Process-project" @click="chooseProject('inProcess')"> In-Process </li>
    </ul>
</section>
</template>

<script>
export default {
    data() {
        return {
            projectChosen: "all"
        }
    },
    methods: {
        chooseProject(selection) {
            this.projectChosen = selection;
            this.$store.commit('SET_PROJECT_CHOSEN', this.projectChosen);
        }
    }
}
</script>

<style>
#choose-project {
    background-color: #9f9a7f;
    padding: 20px;
}

#choose-project > ul {
    padding: 10px;
}

#choose-project ul {
    height: 50%;
}

#choose-project ul > li{
    text-decoration: none;
    list-style-type: none;
    font-size: 20px;
}

#choose-project ul > li:hover{
    color: #8a737d;
}

#choose-project ul li.chosen {
    list-style-type: circle;
}
</style>