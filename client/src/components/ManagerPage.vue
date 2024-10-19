<template>
 <section id="manager-profile">
  <img v-bind:src="managerProfile.imageUrl" alt="prof pic">
  <ul>
    <li> {{ managerProfile.name }} (Manager) </li>
    <li> Email:
        <div class="hyperlink"> 
              {{ managerProfile.emailAddress }}
        </div>
    </li>
    <li> Manager Id: {{ managerProfile.managerId }} </li>
  </ul>

 </section>
</template>

<script>
export default {
  computed : {
    managerProfile() {
      return this.$store.state.managerProfile
    }
  }

}
</script>

<style>
#manager-profile {
    background-color: #9f9a7f;
    padding: 20px;
    display:grid;
    grid-template-columns: 1fr 1fr;

    
}

#manager-profile > ul {
    margin: 0;
    width: 100%;
    padding: 0;
    
}

#manager-profile > ul > li{
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

#manager-profile > img {
    width: 86%;
    margin: 10px 10px 10px;
    
}

.hyperlink:hover {
    color: #8a737d;
}
</style>