<template>
<div id="login" class="login-background">
  <div class="login-container" id="login-container">
    <form v-on:submit.prevent="login">
      <h1>Welcome to Indie Assistant!</h1>
      <div id="fields">
        <div>
          <label for="username">Username</label>
          <br>
          <input
            type="text"
            id="username"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div>
          <label for="password">Password</label>
          <br>
          <input
            type="password"
            id="password"
            placeholder="Password"
            v-model="user.password"
            required
          />
        </div>
        <div><button type="submit">Sign in</button></div>
      </div>
      <hr/>
      <p> Need an account?</p> <router-link v-bind:to="{ name: 'register' }">Register!</router-link>
    </form>
  </div>
</div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            if(!response.data.user.enabled) {
              this.$router.push({ name: "login"})
              alert("Account Currently Disable - Contact Admin");
            }
            else {
              this.$store.commit("SET_AUTH_TOKEN", response.data.token);
              this.$store.commit("SET_USER", response.data.user);
              if(this.$store.state.user.type === 'Admin') {
               this.$router.push({ name: 'home' })
              }
              else if(this.$store.state.user.type === 'Manager') {
                this.$router.push({ name: 'manager' })
              }
              else if(this.$store.state.user.type === 'Artist') {
                this.$router.push({ name: 'artist' })
              }
            }
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
h1 {
  color: #2e2e2e;
}

p {
  color: #2e2e2e;
}


#login {
  position: relative;
  height: 100vh;
  width: 100%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}



#login-container {
  background-color: #9f9a7f;;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0px 4 px 10px rgba(0, 0, 0, 0.2);
  max-width: 400px;
  width: 100%;
  box-sizing: border-box;
}

#fields {
  display: grid;
}

label {
  color: #2e2e2e
}
</style>
