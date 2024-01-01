<script>
  import axios from "axios";

  export default {
    inject: ['url'],
    data(){
      return {
        users:[],
        selectedUser:null,
      }
    },
    created() {
      this.loadAllUsers()
    },
    methods:{
      loadAllUsers(){
        axios.get(this.url + "/api/users/all")
            .then(response => this.users = response.data)
            .then(console.log(this.users));
      },
      deleteUser(user){
        const confirmDelete = confirm(`are you sure you want to delete user ${user.username}`);
        if(confirmDelete){
          axios.delete(this.url + `/api/users/${user.uuid}`)
              .then(this.loadAllUsers);
        }
      },
    }
  }
</script>

<template>
  <div class="p-6">
    <div class="mb-6">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-between mb-4 items-start">
          <div class="font-medium">Manage users</div>
        </div>
        <div class="flex items-center mb-4 order-tab">
          <button type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 rounded-tl-md rounded-bl-md hover:text-gray-600 active">all</button>
          <button type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 hover:text-gray-600">active</button>
          <button type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 rounded-tr-md rounded-br-md hover:text-gray-600">disabled</button>
          <button type="button" class="ml-auto bg-red-500 text-sm font-medium text-white py-2 px-4 rounded-tr-md rounded hover:bg-red-600"><router-link :to="{name: 'createUser'}">Create user</router-link></button>
        </div>

        <form action="" class="flex items-center mb-4">
          <div class="relative w-full mr-2">
            <input type="text" class="py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Search...">
            <i class="ri-search-line absolute top-1/2 left-4 -translate-y-1/2 text-gray-400"></i>
          </div>
        </form>
        <div class="overflow-x-auto">
          <table class="w-full min-w-[540px]" data-tab-for="order" data-page="active">
            <thead>
            <tr>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tl-md rounded-bl-md">Name</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Rol</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Locatie</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tr-md rounded-br-md">actions</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(user) in users" :key="user.uuid">
              <td class="py-2 px-4 border-b border-b-gray-50">
                <div class="flex items-center">
                  <span class="text-[13px] font-medium text-gray-800">{{user.username}}</span>
                </div>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="text-[13px] font-medium text-gray-800">{{user.admin}}</span>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="text-[13px] font-medium text-gray-800">{{user.locatie}}</span>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="inline-block p-1 rounded bg-emerald-500/10 text-emerald-500 font-medium text-[12px] leading-none">
                  <router-link :to="{name: 'editUser', params: {id: user.uuid}}" exact>edit</router-link>
                </span>
                <span class="inline-block ml-2 p-1 rounded bg-emerald-500/10 text-emerald-500 font-medium text-[12px] leading-none">
                  <router-link :to="{name: 'editUser', params: {id: user.uuid}}" exact>delete</router-link>
                </span>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>