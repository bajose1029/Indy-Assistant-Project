<template>
  <section id="chosen-person-profile">
        <img v-bind:src="artistManager.imageUrl" alt="prof pic">
        <ul v-if="artistManager.artistId">
            <li class="top-info"> {{ artistManager.artistName }} (Artist)</li>
            <li> Email: 
                <router-link v-bind:to="{ name: 'home' }"> 
                    {{ artistManager.emailAddress }}
                </router-link>
            </li>
            <li> Artist Id: {{ artistManager.artistId }}</li>
            <li> PRO: {{ artistManager.pro }}</li>
            <li> 
                Manager: 
                <router-link v-bind:to="{ name: 'home' }">
                    {{ artistsCurrentManager.name }}
                </router-link>
            </li>
        </ul>
        <ul v-else-if="artistManager.emailAddress">
            <li class="top-info"> {{ artistManager.name }} (Manager) </li>
            <li> Email:
                <router-link v-bind:to="{ name: 'home' }"> 
                    {{ artistManager.emailAddress }}
                </router-link>
            </li>
            <li> Manager Id: {{ artistManager.managerId }} </li>
            <li> Artist(s)
                <ul>
                    <li v-for="artist in managersArtists" v-bind:key="artist.artistId"> 
                        <router-link v-bind:to="{ name: 'home' }">
                            {{ artist.artistName }}
                        </router-link>
                    </li>
                </ul>
            </li>
        

        </ul>
        <ul v-else>
            <li class="top-info"> {{ artistManager.name }}</li>
        </ul>

  </section>
</template>

<script>
export default {
    computed: {
        artistManager() {
            return this.$store.state.artists.concat(this.$store.state.managers).find((artistManager) => {
                return artistManager.userId === this.$store.state.currentUserId
            })
        },

        managersArtists() {
            return this.$store.state.artists.filter((artist) => {
                let manager = this.$store.state.managers.find((manager) => 
                {
                    return manager.userId === this.$store.state.currentUserId;
                })
                return artist.managerId === manager.managerId;
            })
        },

        artistsCurrentManager() {
            return this.$store.state.managers.find((manager) => {
                let artist = this.$store.state.artists.find((artist) =>
                {
                    return artist.userId === this.$store.state.currentUserId;
                })

                return manager.managerId === artist.managerId;
            })
        }
    }
}
</script>

<style>
#chosen-person-profile {
    background-color: #9f9a7f;
    padding: 20px;
    display:grid;
    grid-template-columns: 1fr 1fr;

    
}

#chosen-person-profile > ul {
    margin: 0;
    width: 100%;
    padding: 0;
    
}

#chosen-person-profile > ul > li{
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
    align-items: center;
    
}


#chosen-person-profile > img {
    width: 86%;
    margin: 10px 10px 10px;
    
}


</style>