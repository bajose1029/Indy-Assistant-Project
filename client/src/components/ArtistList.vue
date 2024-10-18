<template>
  <section id="#managers-list-of-artists">
    <ul>
        <li v-for="managersArtist in managersArtists" v-bind:key="managersArtist.artistId" @click="selectedArtist(managersArtist.artistId)">
            {{ managersArtist.name }}
            <img v-bind:src="managersArtist.imageUrl" alt="prof pic" />
        </li>
    </ul>
  </section>
</template>

<script>
export default {

    computed: {
        managersArtists() {
                return this.$store.state.myArtists
        }
    },
    // methods: {
    //     selectedArtist(artistId) {
    //         this.$store.commit('')
    //     }
    // }
}
</script>

<style>
#managers-list-of-artists {
    background-color: #9f9a7f;
    padding: 20px;
}

#managers-list-of-artists > ul {
    padding: 0;
}

#managers-list-of-artists ul li {
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

#managers-list-of-artists ul li img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    
}

#managers-list-of-artists ul li.clicked-on {
    color: #8a737d;
}
</style>