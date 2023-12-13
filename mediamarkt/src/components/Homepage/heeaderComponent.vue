<template>
  <header class="bg-red-500 p-4 fixed top-0 left-0 right-0 z-20">
    <div class="container mx-auto flex justify-between items-center">
      <router-link to="/"><h1 class="text-white text-2xl font-semibold">Mediamarkt</h1></router-link>
      <nav class="space-x-4">
        <router-link to="/" class="text-white hover:underline">Home</router-link>
        <router-link v-if="this.currentUser.admin" to="/admin/image-overview" class="text-white hover:underline">Admin</router-link>
        <router-link to="/Login" class="text-white hover:underline">Log in</router-link>
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
  data() {
    return {
      currentUser: null,
    }
  },
  created() {
    this.updateUserInformation()
  },
  methods:{
    async updateUserInformation() {
      let token = sessionStorage.getItem("token")

      let decodedToken = VueJwtDecode.decode(token)
      console.log(decodedToken)
      this.currentUser = await fetch((`http://localhost:8085/api/users/${decodedToken.id}`),
          {
            method: "GET",
            headers: {'Content-Type': 'application/json'}
          })
      if (this.currentUser.ok) {
        this.currentUser.id = decodedToken.id;
        this.currentUser.admin = decodedToken.admin.toLowerCase() === 'true';
        this.currentUser.exp = decodedToken.exp;
        console.log(decodedToken)
        console.log(this.currentUser)
      } else {
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
