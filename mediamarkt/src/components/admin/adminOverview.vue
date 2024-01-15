<script>
import HeaderComponent from "@/components/Homepage/heeaderComponent.vue";
import VueJwtDecode from "vue-jwt-decode"
export default {
  name:"admin-overview",
  components: {HeaderComponent},
  data(){
    return {
      isAdmin: false,
    }
  }, watch: {
    $route() {
      // Scroll to the top when the route changes
      window.scrollTo(0, 0);

      if(sessionStorage.getItem("isAdmin")){
        this.isAdmin = true;
      }

    },
  },
  created() {
    this.adminCheck()
  },
  methods: {
    adminCheck() {
      let token = sessionStorage.getItem("token")
      let decodedtoken = VueJwtDecode.decode(token)
      if (JSON.parse(decodedtoken.admin)){
        this.isAdmin = true

      }else {
        this.isAdmin = false

      }
    }
  }
}
</script>

<template>
  <header-component/>
  <!-- start: Sidebar -->
  <div class="md:fixed absolute w-full left-0 top-0 md:w-64 md:h-full bg-red-500 p-4 mt-16 sidebar-menu transition-transform">
    <ul class="mt-4">
      <li class="mb-1 group" v-if="this.isAdmin">
        <router-link :to="{name: 'userOverview'}"
                     class="flex items-center py-2 px-4 text-white hover:bg-red-700 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100 sidebar-dropdown-toggle">
          <i class="ri-instance-line mr-3 text-lg"></i>
          <span class="text-sm">Users</span>
        </router-link>
      </li>
      <li class="mb-1 group">
        <router-link :to="{name: 'imageOverview'}"
                     class="flex items-center py-2 px-4 text-white hover:bg-red-700 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100 sidebar-dropdown-toggle">
          <i class="ri-flashlight-line mr-3 text-lg"></i>
          <span class="text-sm">Images</span>
        </router-link>
      </li>
      <li class="mb-1 group" v-if="this.isAdmin">
        <router-link :to="{name: 'laptopOverview'}"
                     class="flex items-center py-2 px-4 text-white hover:bg-red-700 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100">
          <i class="ri-settings-2-line mr-3 text-lg"></i>
          <span class="text-sm">Laptops</span>
        </router-link>
      </li>
    </ul>
  </div>
  <!-- end: Sidebar -->

  <main class="w-full md:w-[calc(100%-256px)] md:ml-64 bg-gray-50 min-h-screen transition-all main mt-16">
    <router-view :isAdmin="isAdmin"/>
  </main>
</template>

<style scoped>
.wrapper {
  display: grid;
  grid-template-columns: 1fr 6fr;
}
</style>
