<template>
  <header class="bg-red-500 p-4 fixed top-0 left-0 right-0 z-20">
    <div class="container mx-auto flex justify-between items-center">
      <router-link to="/"><h1 class="text-white text-2xl font-semibold">Mediamarkt</h1></router-link>
      <nav class="space-x-4">
        <router-link to="/" class="text-white hover:underline">Home</router-link>
        <router-link v-if="this.isAdmin" to="/admin/image-overview" class="text-white hover:underline">Admin</router-link>
        <router-link v-if="!currentUser" to="/Login" class="text-white hover:underline">Log in</router-link>
        <router-link v-else @click="logout" :to="{name: 'home'}" class="text-white hover:underline">Log out</router-link>
        <button @click="emitClicked"
                class="bg-primary py-3 px-10 rounded-2xl text-white slide"
        >
          Scan barcode
        </button>
      </nav>
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
    }
  },
  created() {
    console.log("Header created");
    this.updateUserInformation()
  },
  methods:{
    logout() {
      sessionStorage.removeItem("token");
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
    }
  }
};
</script>

<style scoped>

</style>
