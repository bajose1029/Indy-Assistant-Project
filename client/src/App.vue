<template>
  <div id="artist-app">
    <header>
      <a id="link-back" href="/" v-if="this.$store.state.user.type === 'Admin'">
        <img src="./assets/apple-touch-icon.png" alt="Indy Assistant logo" id="logo" />
      </a>
      <a id="link-back" href="/manager" v-else-if="this.$store.state.user.type === 'Manager'">
        <img src="./assets/apple-touch-icon.png" alt="Indy Assistant logo" id="logo" />
      </a>
      <a id="link-back" href="/artist" v-else>
        <img src="./assets/apple-touch-icon.png" alt="Indy Assistant logo" id="logo" />
      </a>
      <h1>Indie <br /> Assistant
      </h1>
      <!-- v-if="this.$store.state.token" -->
      <nav v-if="this.$store.state.token">
        <section>
          <router-link v-bind:to="{ name: 'home' }" v-if="this.$store.state.user.type === 'Admin'">
            Home
          </router-link>
          <router-link v-bind:to="{ name: 'manager' }" v-else-if="this.$store.state.user.type === 'Manager'">
            Home
          </router-link>
          <router-link v-bind:to="{ name: 'artist' }" v-else>
            Home
          </router-link>


          <router-link v-bind:to="{ name: 'about' }">
            About
          </router-link>

          <router-link v-bind:to="{ name: 'logout' }">
            Logout
          </router-link>
        </section>
      </nav>
      
      <!-- v-else -->
      <nav v-else>
        <section>
          <router-link v-bind:to="{ name: 'login' }">
            Login
          </router-link>
        </section>
      
      </nav>
    </header>
    <main>
      <router-view />
    </main>
    <footer>&copy; 2024 All Rights Reserved</footer>
  </div>
</template>

<script>

</script>


<style >

#artist-app {
    margin: 0;
    padding: 0 0 0 0 ;
    font-family: 'Poppins', Arial, Helvetica, sans-serif;
    font-weight: 400;
    font-style: normal;
    color: #fedcc7;
    background-color: #2e2e2e;
    height: 100%;
    display: grid;
    grid-template-rows: 55px 1fr 30px;
    grid-template-areas:
    "header"
    "main"
    "footer";
}

header {

    grid-area: header;
    display : flex;
    align-items: center;

    /* position: fixed; */
    background-color: #2e2e2e;
    width:100%;
    /* z-index: 1000; */
    padding: 5px 0 0 0;
}


header h1 {
    font-size: 16px;
    margin: 0 0 0 5px;
    align-self: center;
}

#logo {
    width: 45px;
    height: 45px;
    
}

#link-back {
    margin-left: 5px;
}

a {
    text-decoration: none;
    color: #fedcc7;
}

a:hover {
    color: #8a737d;
}

#link-back{
    text-decoration: none;
} 

nav {
    flex-grow: 1;
}

nav section a {
    padding: 10px;
    border-right: 1px #9f9a7f solid;
}

nav section {
    margin: 0;
    display: flex;
    justify-content: end;
}

section a:nth-last-child(1){
    border: none;
}


main {
    background-color: #2e2e2e;
    grid-area: main;
    /* overflow-y: auto; */
    /* display : grid;
    grid-template-columns: 3fr 9fr 9fr;
    grid-template-areas:
    "select   list   profile";
    gap: 5px;
    overflow-y: auto;
    margin-left: 5px; */
}

footer {
    grid-area: footer;
}

footer {
  /* position: fixed; */
  width: 100%;
  background-color: #2e2e2e;
  /* z-index: 1000; */
  padding: 5px 0 5px 5px;
}

@media only screen and (max-width: 450px) {
    #artist-app {
        grid-template-rows: 1fr 30fr 1fr;
    }
}
</style>