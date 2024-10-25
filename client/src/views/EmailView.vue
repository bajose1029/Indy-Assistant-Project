<template>
  <div id="main-div">
    <section id="main-icon">
      <page-icons id="icons-for-page" />
    </section>
    <div id="email-page">
      <section id="container">
        <form @submit.prevent="sendEmail" >
          <h1>Contact Us</h1>
          <div>
            <label for="firstName">First Name:</label>
            <br>
            <input placeholder="First Name" type="text" id="firstName" v-model="newEmail.firstName" required />
          </div>
          <div>
            <label for="lastName">Last Name:</label>
            <br>
            <input placeholder="Last Name" type="text" id="lastName" v-model="newEmail.lastName" required />
          </div>
          <div>
            <label for="email">Email:</label>
            <br>
            <input placeholder="example.com" type="email" id="email" v-model="newEmail.email" required />
          </div>
          <div>
            <label for="subject">Subject:</label>
            <br>
            <input placeholder="Subject" type="text" id="subject" v-model="newEmail.subject" required />
          </div>
          <div>
            <label for="message">Message:</label>
            <br>
            <textarea id="message" v-model="newEmail.message" required></textarea>
          </div>
          <div>
            <button type="submit">Send Email</button>
          </div>
        </form>
        
      </section>
    </div>
  </div>
</template>

<script>
import PageIcons from '../components/PageIcons.vue';

export default {
  components : {
    PageIcons
  },
  data() {
    return {
      newEmail: {
        firstName: '',
        lastName: '',
        email: '',
        subject: '',
        message: ''
      }
    }
  },
  methods : {
    sendEmail(){

    }
  },
}
</script >

<style scoped>
h1 {
  color: #2e2e2e;
  font-size: 2em;
  margin-top: 0px;
}
label{
  color: #2e2e2e;
}
#email-page {
  position: relative;
  height: 100vh;
  width: 100%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

#container {
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
button{
    margin-top: 10px;
}
#message{
  width: 90%;
  height: 90px;
}
</style>