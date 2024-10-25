<template>
  <section id="all-artist-manager">
    <ul id="list-of-all-artist-manager">
        <li v-bind:class="{ chosen : chosen === 'all'}" id="all" @click="chooseChosen('all')">All</li>
        <li v-bind:class="{ chosen : chosen === 'artist'}" id="artists" @click="chooseChosen('artist')">Artists</li>
        <li v-bind:class="{ chosen : chosen === 'manager'}" id="managers" @click="chooseChosen('manager')">Managers</li>
    </ul>
  </section>
</template>

<script>
export default {
    data() {
        return {
            chosen: this.$store.state.chosen
        }
    },
    methods: {
        chooseChosen(selection) {
            this.chosen = selection;
            this.$store.commit('SET_CHOSEN', this.chosen);
        }
    }
}
</script>

<style>
#all-artist-manager {
    background-color: #9f9a7f;
    padding: 20px;
}

#all-artist-manager > ul {
    padding: 10px;
}

#all-artist-manager ul {
    height: 50%;
}

#all-artist-manager ul > li{
    text-decoration: none;
    list-style-type: none;
    font-size: 20px;
}

#all-artist-manager ul > li:hover{
    color: #8a737d;
}

#all-artist-manager ul li.chosen {
    list-style-type: circle;
}


</style>