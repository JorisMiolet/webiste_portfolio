<script>
  import HeaderComponent from "@/components/Homepage/heeaderComponent.vue";

  export default {
    name:"admin-overview",
    components: {HeaderComponent},
    data(){
      return {

      }
    }, watch: {
      $route() {
        // Scroll to the top when the route changes
        window.scrollTo(0, 0);
      },
    },
  }
</script>

<template>
  <header-component/>
  <!-- start: Sidebar -->
  <div class="fixed left-0 top-0 w-64 h-full bg-red-500 p-4 mt-20 sidebar-menu transition-transform">
    <ul class="mt-4">
      <li class="mb-1 group">
        <router-link  :to="{name: 'userOverview'}" class="flex items-center py-2 px-4 text-white hover:bg-red-700 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100 sidebar-dropdown-toggle">
          <i class="ri-instance-line mr-3 text-lg"></i>
          <span class="text-sm">Users</span>
        </router-link>
      </li>
      <li class="mb-1 group">
        <router-link :to="{name: 'imageOverview'}" class="flex items-center py-2 px-4 text-white hover:bg-red-700 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100 sidebar-dropdown-toggle">
          <i class="ri-flashlight-line mr-3 text-lg"></i>
          <span class="text-sm">Images</span>
        </router-link>
      </li>
      <li class="mb-1 group">
        <router-link :to="{name: 'laptopOverview'}" class="flex items-center py-2 px-4 text-white hover:bg-red-700 hover:text-gray-100 rounded-md group-[.active]:bg-gray-800 group-[.active]:text-white group-[.selected]:bg-gray-950 group-[.selected]:text-gray-100">
          <i class="ri-settings-2-line mr-3 text-lg"></i>
          <span class="text-sm">Laptops</span>
        </router-link>
      </li>
    </ul>
  </div>
  <!-- end: Sidebar -->

  <main class="w-full md:w-[calc(100%-256px)] md:ml-64 bg-gray-50 min-h-screen transition-all main mt-20">
      <router-view/>
  </main>
</template>

<style scoped>
    .wrapper {
      display: grid;
      grid-template-columns: 1fr 6fr;
    }
</style>