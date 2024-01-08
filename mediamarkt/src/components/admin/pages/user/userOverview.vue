<script>
  import axios from "axios";
  import VueJwtDecode from "vue-jwt-decode";

  export default {
    inject: ['url'],
    props: {
      isAdmin: Boolean,
    },
    data(){
      return {
        users:[],
        selectedUser:null,
        searchFilter: null,
        currentPage: 1,
        rowsPerPage: 10,
      }
    },
    created() {
      this.validateAdmin()
      this.loadAllUsers()
    },
    methods:{
      filterUsers(){
        const urlWithQuery = `${this.url}/api/users/search?Filter=${this.searchFilter}`;
        axios.get(urlWithQuery)
            .then(response => this.users = response.data)
      },
      loadAllUsers(){
        axios.get(this.url + "/api/users/all")
            .then(response => this.users = response.data)

      },
      deleteUser(user){
        const confirmDelete = confirm(`are you sure you want to delete user ${user.username}`);
        if(confirmDelete){
          axios.delete(this.url + `/api/users/${user.uuid}`)
              .then(this.loadAllUsers);
        }
      },
      validateAdmin(){
        let token = sessionStorage.getItem("token")
        let decodedToken = VueJwtDecode.decode(token)
        if (!JSON.parse(decodedToken.admin)){
          this.$router.push("/")
        }
      },
      activeUsers(){
        axios.get(this.url + "/api/users/active")
            .then(response => this.users = response.data)
      },
      disabledUsers(){
        axios.get(this.url + "/api/users/disabled")
            .then(response => this.users = response.data)
      },
      prevPage() {
        if (this.currentPage > 1) {
          this.currentPage--;
        }
      },
      nextPage() {
        if (this.currentPage < this.totalPages) {
          this.currentPage++;
        }
      },
    },
    watch: {
      searchFilter() {
        this.filterUsers();
      },
    },
    computed: {
      totalPages(){
        return Math.ceil(this.users.length / this.rowsPerPage)
      },
      paginatedUsers() {
        const startIndex = (this.currentPage - 1) * this.rowsPerPage;
        const endIndex = startIndex + this.rowsPerPage;
        return this.users.slice(startIndex, endIndex);
      },
    },
  }
</script>

<template>
  <div class="p-6">
    <div class="mb-6">
      <div class="mt-40 md:mt-0 bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-center sm:justify-between mb-4 items-start">
          <div class="font-medium">Manage users</div>
        </div>
        <div class="flex flex-col sm:flex-row items-center mb-4 order-tab">
          <button @click="loadAllUsers" type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 rounded-tl-md rounded-bl-md hover:text-gray-600 active">all</button>
          <button @click="activeUsers" type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 hover:text-gray-600">active</button>
          <button @click="disabledUsers" type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 rounded-tr-md rounded-br-md hover:text-gray-600">disabled</button>
          <button type="button" class="sm:ml-auto bg-red-500 text-sm font-medium text-white py-2 px-4 rounded-tr-md rounded hover:bg-red-600"><router-link :to="{name: 'createUser'}">Create user</router-link></button>
        </div>

        <div class="flex items-center mb-4">
          <div class="relative w-full mr-2">
            <input v-model="searchFilter" type="text" class="py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Search...">
            <i class="ri-search-line absolute top-1/2 left-4 -translate-y-1/2 text-gray-400"></i>
          </div>
        </div>
        <div class="overflow-x-auto">
          <table class="w-full min-w-[540px]">
            <thead>
            <tr>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tl-md rounded-bl-md">Name</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tl-md rounded-bl-md">email</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Rol</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Locatie</th>
              <th v-if="isAdmin" class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tr-md rounded-br-md">actions</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(user) in paginatedUsers" :key="user.uuid">
              <td class="py-2 px-4 border-b border-b-gray-50">
                <div class="flex items-center">
                  <span class="text-[13px] font-medium text-gray-800">{{user.username}}</span>
                </div>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <div class="flex items-center">
                  <span class="text-[13px] font-medium text-gray-800">{{user.email}}</span>
                </div>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="text-[13px] font-medium text-gray-800"><div v-if="user.admin">admin</div><div v-else>medewerker</div></span>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="text-[13px] font-medium text-gray-800">{{user.locatie}}</span>
              </td>
              <td v-if="isAdmin" class="py-2 px-4 border-b border-b-gray-50">
                <span class="inline-block p-1 rounded bg-emerald-500/10 text-emerald-500 font-medium text-[12px] leading-none">
                  <router-link :to="{name: 'editUser', params: {id: user.uuid}}" exact>edit</router-link>
                </span>
                <span class="inline-block ml-2 p-1 rounded bg-red-600/10 text-red-600 font-medium text-[12px] leading-none">
                  <span @click="deleteUser(user)">delete</span>
                </span>
              </td>
            </tr>
            </tbody>
          </table>
          <div class="flex justify-center mt-4">
            <button @click="prevPage" :disabled="currentPage === 1" :class="{'disabled': currentPage === 1}" class="bg-red-500 text-sm font-medium text-white py-2 px-4 rounded hover:bg-red-600">
              Previous
            </button>
            <div class="mx-4">
              Page {{ currentPage }} of {{ totalPages }}
            </div>
            <button @click="nextPage" :disabled="currentPage === totalPages" :class="{'disabled': currentPage === totalPages}" class="bg-red-500 text-sm font-medium text-white py-2 px-4 rounded hover:bg-red-600">
              Next
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
