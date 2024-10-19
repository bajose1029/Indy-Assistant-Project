<template>
  <section id="chosen-person-profile" v-if="artistManager">
    <img v-bind:src="artistManager.imageUrl" alt="prof pic" />
    <ul v-if="artistManager.artistId">
      <li @click="goToArtist(artistManager.artistId)" class="top-info hyperlink">{{ artistManager.artistName }} (Artist)</li>
      <li >
        Email:
        <div class="hyperlink">
          {{ artistManager.emailAddress }}
        </div>
      </li>
      <li>Artist Id: {{ artistManager.artistId }}</li>
      <li>PRO: {{ artistManager.pro }}</li>
      <li class="hyperlink" @click="goToManager(artistsCurrentManager.managerId)"> Manager: {{ artistsCurrentManager.name }} </li>
    </ul>
    <ul v-else-if="artistManager.emailAddress">
      <li @click="goToManager(artistManager.managerId)" class="top-info hyperlink" >{{ artistManager.name }} (Manager)</li>
      <li >
        Email: 
        <div class="hyperlink">
            {{ artistManager.emailAddress }} 
        </div>
      </li>
      <li>Manager Id: {{ artistManager.managerId }}</li>
      <li>
        Artist(s)
        <ul>
          <li class="hyperlink" @click="goToArtist(artist.artistId)" v-for="artist in managersArtists" v-bind:key="artist.artistId">  
              {{ artist.artistName }}
          </li>
        </ul>
      </li>
    </ul>
    <ul v-else>
      <li class="top-info">{{ artistManager.name }}</li>
    </ul>
  </section>
</template>

<script>
export default {
    computed: {
        artistManager() {
            let checkUser = this.$store.state.users.find((user) => {
                return user.id === this.$store.state.currentUserId
            });

            if(checkUser) {
                if(checkUser.type === "Artist"){
                    return this.$store.state.artists.find((artist) => {
                        return artist.userId === this.$store.state.currentUserId;
                    })
                }
                else if(checkUser.type === "Manager"){
                    return this.$store.state.managers.find((manager) => {
                        return manager.userId === this.$store.state.currentUserId;
                    })
                }
                else {
                    return this.$store.state.managers.find((manager) => {
                        return manager.userId == 0;
                    })
                }
                
            }
            return undefined
            // else {
            //     return {userId: 0,
            //     managerId: 0,
            //     name: "0-Management",
            //     emailAddress: "",
            //     imageUrl: "../src/assets/profile-pics/empty.png"
            // }
        // }
            // return this.$store.state.artists.concat(this.$store.state.managers).find((artistManager) => {
            //     return artistManager.userId === this.$store.state.currentUserId
            // }
            // )
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
#chosen-person-profile {
  background-color: #9f9a7f;
  padding: 20px;
  display: grid;
  grid-template-columns: 1fr 1fr;
}

#chosen-person-profile > ul {
  margin: 0;
  width: 100%;
  padding: 0;
}

#chosen-person-profile > ul > li {
  list-style-type: none;
  padding: 0 10px 0 10px;
  margin: 10px 0 10px 0;
  width: 100%;
  font-size: 1em;
  background-color: #2e2e2e;
  border-radius: 5px;
  line-height: 50px;
  box-shadow: 2px 2px 2px #777;
  align-self: center;
  align-items: center;
}

#chosen-person-profile > img {
  width: 86%;
  margin: 10px 10px 10px;
}

.hyperlink:hover {
    color: #8a737d;
}
</style>