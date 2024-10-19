<template>
<div id="register">
  <div class="login-container" id="login-container">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
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
      <div>
          <label for="confirmPassword">Confirm password</label>
          <br>
          <input
            type="password"
            id="confirmPassword"
            placeholder="Confirm Password"
            v-model="user.confirmPassword"
            required
          />
      </div>
      <div>
          <label for="type">Are you an Artist or Manager:</label>
          <br>
          <select id="type" v-model="user.type">
            <option disabled value="">Please select one</option>
            <option value="Artist">Artist</option>
            <option value="Manager">Manager</option>
          </select>
      </div>

      <div v-if="user.type === 'Artist'" id="artistProfilePage">
        <div> 
          <label for="artistName">Artist Name</label>
            <br>
            <input
              type="text"
              id="artistName"
              placeholder="Artist Name"
              v-model="artist.artistName"
              required
            />
        </div> 
        <div>
            <label for="emailAddress">Email Address</label>
            <br>
            <input
              type="text"
              id="emailAddress"
              placeholder="Email Address"
              v-model="artist.emailAddress"
              required
            />
        </div>
        <div>
            <label for="imageUrl">Image</label>
            <br>
            <input
              type="text"
              id="imageUrl"
              placeholder="Image"
              v-model="artist.imageUrl"
            />
          </div>
          <div>
            <label for="pro">PRO</label>
            <br>
            <input
              type="text"
              id="pro"
              placeholder="PRO"
              v-model="artist.pro"
            />
        </div>
      </div>

      <div v-else id="ManagerProfilePage">
        <div>
            <label for="name"></label>
            <br>
            <input
              type="text"
              id="name"
              placeholder="Name"
              v-model="manager.name"
              required
            />
        </div>
        <div>
            <label for="emailAddress"></label>
            <br>
            <input
              type="text"
              id="emailAddress"
              placeholder="Email Address"
              v-model="manager.emailAddress"
              required
            />
        </div>
        <div>
              <label for="imageUrl"></label>
              <br>
              <input
                type="text"
                id="imageUrl"
                placeholder="Image"
                v-model="manager.imageUrl"
              />
        </div>
      </div>


        <div></div>
        <div>
          <br>
          <button type="submit">Create Account</button>
        </div>
      </div>
      <hr />
      <p> Have an account? </p>
      <router-link v-bind:to="{ name: 'login' }">Sign in!</router-link>
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
        confirmPassword: "",
        role: "user",
        type: ""
      },
      artist: {
        artistName: "",
        managerId: 0,
        emailAddress: "",
        userId: 7,
        imageUrl: "../src/assets/profile-pics/Franz.jpg",
        pro: ""
      },

      manager: {
        name: "",
        emailAddress: "",
        imageUrl: "../src/assets/profile-pics/SamBonyo.jpg",
        userId: 7
      }
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              if(response.data.type === 'Artist')
              {
                this.artist.userId = response.data.id
                console.log(this.artist)
                authService.registerArtist(this.artist).then((response) => {
                  if (response.status == 201){
                    this.success("Artist Profile Successfully Created! Thank you for registering, please sign in.")
                    this.$router.push({
                    path: "/login",
                  });
                  }
                })
              }
              else{
                this.manager.userId = response.data.id;
                authService.registerManager(this.manager).then((response) => {
                  if (response.status == 201){
                    this.success("Manager Profile Successfully Created! Thank you for registering, please sign in.")
                    this.$router.push({
                    path: "/login",
                    });
                  }
                })
              }
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                // Show the validation errors
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}':${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
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
#register{
  position: relative;
  height: 100vh;
  width: 100%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}
#login-container {
  background-color: #9f9a7f;
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
  color: #2e2e2e;
}

</style>
