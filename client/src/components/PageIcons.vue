<template>
  <div>
    <section id="icons-for-page">
      <ul id="list-of-icons">
        
        <li><img src="../assets/user-icon.png" alt="artist icon" class="icon" v-bind:class="{ selectedIcon : iconChosen === 'artist' }" @click="chosenIcon('artist')"></li>
        <li><img src="../assets/calendar.png" alt="calender icon" class="icon" v-bind:class="{ selectedIcon : iconChosen === 'calender' }" @click="chosenIcon('calender')"></li>
        <li><img src="../assets/email.png" alt="email icon" class="icon" v-bind:class="{ selectedIcon : iconChosen === 'email' }" @click="chosenIcon('email')"></li>
      </ul>
      <form>
        <input type="text" name="query" id="query" placeholder="Search..." v-model="search.name">
        <button type="submit">Search</button>
      </form>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search : { name: ''},
      iconChosen : this.$store.state.iconChosen
    }
  },

  methods : {
    chosenIcon(selected) {
      this.iconChosen = selected;
      this.$store.commit('SET_CHOSEN_ICON', this.iconChosen);
      if(this.iconChosen === "artist") {
        if(this.$store.state.user.type === 'Admin') {
          this.$router.push({name: 'home'});
        }
        else if (this.$store.state.user.type === 'Manager'){
          this.$router.push({name: 'manager'});
        }
        else {
          this.$router.push({name: 'artist'});
        }
      }
      else if(this.iconChosen === "calender") {
        this.$router.push({name: 'calender'});
      }
      else {
        this.$router.push({name: 'email'});
      }
    }
  }

};
</script>

<style>
#icons-for-page {
    background-color: #9f9a7f;
    margin-bottom: 5px;
    margin-top: 5px;
    display: grid;
    grid-template-columns: 100fr 1fr;
    grid-template-areas:
    "guide   search";
    align-items: center;
}


#icons-for-page form {
    grid-area: search;
    width: 100%;
    display: flex;
    justify-content: flex-end;
    gap: 5px;
    padding: 5px;

}

.icon {
    width: 30px;
    height: 30px;
}

#list-of-icons li {
    text-decoration: none;
    list-style-type: none;
    margin: 0 0 0 10px;
}

#list-of-icons {
    margin: 0;
    padding: 5px;
    list-style: none;
    display: flex;
    align-items: center;
    justify-content: start;
    gap: 100px;
    grid-area: guide;
}

#list-of-icons > li > img.selectedIcon {
    width: 45px;
    height: 45px;
}

</style>