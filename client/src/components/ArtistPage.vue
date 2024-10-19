<template>
    <section id="artist-profile">
     <img v-bind:src="artistProfile.imageUrl" alt="prof pic">
     <ul>
       <li> {{ artistProfile.artistName }} (Artist) </li>
       <li> Email:
            <div class="hyperlink"> 
                 {{ artistProfile.emailAddress }}
            </div>
       </li>
       <li> Artist Id: {{ artistProfile.artistId }} </li>
       <li> PRO: {{ artistProfile.pro }}</li>
       <li class="hyperlink" @click="goToManager(artistsManager.managerId)"> Manager: {{ artistsManager.name }}</li>
     </ul>
   
    </section>
   </template>

<script>
export default {
    computed: {
        artistProfile(){
            return this.$store.state.artistProfile;
        },

        artistsManager() {
            return this.$store.state.artistsManager;
        }
    },
    methods : {
        goToManager(managerId){
            this.$store.commit("SET_CLICKED_MANAGER_ID", managerId);
            this.$router.push({ name: 'manager'})
        },
        goToArtist(artistId) {
            this.$store.commit("SET_CLICKED_ARTIST_ID", artistId);
            this.$router.push({ name: 'artist' });
        }
    }
}
</script>

<style>
.hyperlink:hover {
    color: #8a737d;
}

#artist-profile {
    background-color: #9f9a7f;
    padding: 20px;
    display:grid;
    grid-template-columns: 1fr 1fr;

    
}

#artist-profile > ul {
    margin: 0;
    width: 100%;
    padding: 0;
    
}

#artist-profile > ul > li{
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


#artist-profile > img {
    width: 86%;
    margin: 10px 10px 10px;
    
}
</style>