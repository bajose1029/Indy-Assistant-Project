<template>
  <section id="list-of-artist-manager">
    <ul>
        <li v-bind:class="{ clicked : choose === artistManager.userId}" v-for="artistManager in artistManagers" v-bind:key="artistManager.userId" @click="selected(artistManager.userId)">
            {{ artistManager.name }}
            <img v-bind:src="artistManager.imageUrl" alt="profile pic" />
        </li>
    </ul>
  </section>
</template>

<script>
export default {
    data() {
        return {
            choose : 0
        }
    },
    
    computed : {
        artistManagers() {
            if(this.$store.state.chosen === "manager") {
                return this.$store.state.managers
            }
            else if (this.$store.state.chosen === "artist"){
                return this.$store.state.artists
            }
            else {
                return this.$store.state.artists.concat(this.$store.state.managers).sort((a,b) => {
                    return a.name.localeCompare(b.name);
                    }); 
            }
        }
    },

    methods: {
        selected(userId) {
            this.choose = userId
            this.$store.commit('SET_CURRENT_USER_ID', userId);

        }
    }

}
</script>

<style>
#list-of-artist-manager {
    background-color: #9f9a7f;
    padding: 20px;
}

#list-of-artist-manager > ul {
    padding: 10px;
}

#list-of-artist-manager ul li {
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

#list-of-artist-manager ul li img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    
}

#list-of-artist-manager ul li.clicked {
    color: #8a737d;
}

#list-of-artist-manager ul > li:hover{
    color: #8a737d;
}

</style>