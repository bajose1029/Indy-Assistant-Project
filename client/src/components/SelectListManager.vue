<template>
  <section id="choose-artist">
    <ul id="list-of-artist">
        <li v-bind:class="{ chosen : chosen === 'artist'}" id="manager-artist-choice" @click="chooseArtist('artist')"> Artists </li>
    </ul>
</section>
</template>

<script>
export default {
    data() {
        return {
            chosen : 'artist'
        }
    },

    methods: {
        chooseArtist(selection) {
            this.chosen = selection;
            this.$store.commit('SET_MANAGER_PAGE_CHOSEN', this.chosen);
        }
    }
}
</script>

<style>
#choose-artist {
    background-color: #9f9a7f;
    padding: 20px;
}

#choose-artist > ul {
    padding: 10px;
}

#choose-artist ul {
    height: 50%;
}

#choose-artist ul > li{
    text-decoration: none;
    list-style-type: none;
    font-size: 20px;
}

#choose-artist ul > li:hover{
    color: #8a737d;
}

#choose-artist ul li.chosen {
    list-style-type: circle;
}

</style>