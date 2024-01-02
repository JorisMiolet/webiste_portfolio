<template>
  <header class="bg-red-500 p-4 fixed top-0 left-0 right-0 z-20">
    <div class="container mx-auto flex justify-between items-center" :class="{'nav-show': hamburgerClicked}">
      <router-link to="/"><h1 class="text-white text-2xl font-semibold">Mediamarkt</h1></router-link>
      <nav class="space-x-4 hidden md:block" :class="{'show-inner': hamburgerClicked}">
        <router-link to="/" class="text-white hover:underline">Home</router-link>
        <router-link to="/admin/image-overview" v-if="loggedIn" class="text-white hover:underline">Dashboard</router-link>
        <router-link v-if="!currentUser" to="/Login" class="text-white hover:underline">Log in</router-link>
        <router-link v-else @click="logout" :to="{name: 'home'}" class="text-white hover:underline">Log out</router-link>
        <button @click="emitClicked"
                class="bg-primary py-3 px-10 rounded-2xl text-white slide"
        >
          Scan barcode
        </button>
      </nav>
      <div class="w-8 h-2 sm-screen-button cursor-pointer md:hidden" @click="setHamburgerButton" :class="{'clicked': hamburgerClicked}">

      </div>
    </div>
  </header>
</template>

<script>
import VueJwtDecode from "vue-jwt-decode";

export default {
  name: "headerComponent",
  inject: ['url'],
  data() {
    return {
      currentUser: null,
      isAdmin: false,
      loggedIn: false,
      hamburgerClicked: false
    }
  },
  created() {
    console.log("Header created");
    this.updateUserInformation()
  },
  methods:{
    logout() {
      sessionStorage.removeItem("token");
      sessionStorage.removeItem('user_id');
      sessionStorage.removeItem('isAdmin');
      this.currentUser = null;
      this.isAdmin = false;
    },
    async updateUserInformation() {
      let token = sessionStorage.getItem("token")

      if(!token){
        return; // Token not available
      }

      try {
        let decodedToken = VueJwtDecode.decode(token)
        this.currentUser = await fetch((`${this.url}/api/users/${decodedToken.id}`),
            {
              method: "GET",
              headers: {'Content-Type': 'application/json'}
            })
        if (this.currentUser.ok) {
          this.loggedIn = true;
          this.currentUser.id = decodedToken.id;
          this.currentUser.admin = decodedToken.admin.toLowerCase() === 'true';
          this.currentUser.exp = decodedToken.exp;

          if(this.currentUser.admin){
            this.isAdmin = true;
          }
        }
      } catch (error) {
        console.error("Error decoding token or fetching user information:", error);
        this.currentUser = null;
      }
    },
    emitClicked(){
      this.$emit("scan-clicked")
    },
    setHamburgerButton(){
      this.hamburgerClicked = !this.hamburgerClicked;
    }
  }
};
</script>

<style scoped>
    .sm-screen-button{
      border-top: 4px solid #ce3938
    }
    .sm-screen-button::before{
      content: "";
      display: block;
      border-top: 4px solid #ce3938;
      transform: translateY(12px);
    }
    .sm-screen-button::after{
      content: "";
      display: block;
      border-top: 4px solid #ce3938;
    }
    .sm-screen-button.clicked{
      transform: rotate(45deg);
    }
    .sm-screen-button.clicked::before{
      transform: rotate(-90deg) translate(4px,0);
    }
    .sm-screen-button.clicked::after{
      transform:  translate(0, -8px);
    }
    .nav-show{
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: 100vh;
      width: 100vw;
      opacity: 0.8;
      z-index: 99;
    }
    .show-inner{
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    }
</style>
