<template>
  <div id="main-div">
    <section id="main-icon">
      <page-icons id="icons-for-page" />
    </section>
    <div id="about-page">
      <section id="about-container">
        <h1>About</h1>
        <p class="about-sentence">Welcome to Indy Assistant, your all-in-one management tool designed specifically for 
          independent artists. Whether you're working on an album, a single, or a project release, 
          Indy Assistant helps you take full control of your creative journey without the need for 
          a record label. Our platform is built to simplify the project management process, ensuring 
          that artists like you can focus on what matters most—making great music.</p>
        
        <p class="about-sentence">At Indy Assistant, we believe in empowering independent artists to thrive in a music industry 
          that is constantly evolving. No more waiting for major labels or relying on external management—take 
          control of your artistry and release your projects on your terms.</p>
      </section>
    </div>
  </div>
  
</template>

<script>
import PageIcons from '../components/PageIcons.vue';

export default {
  components : {
    PageIcons
  }
}
</script>

<style scoped>
  h1 {
  margin-top: 0px;
  font-size: 2em
}

#about-page {
  position: relative;
  height: 100vh;
  width: 100%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

#about-container{
  background-color: #9f9a7f;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0px 4 px 10px rgba(0, 0, 0, 0.2);
  max-width: 400px;
  width: 100%;
  box-sizing: border-box;
}

.about-sentence{
  color: #2e2e2e;
}
</style>