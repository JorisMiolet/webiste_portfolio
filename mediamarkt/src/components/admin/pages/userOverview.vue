<script>
  import axios from "axios";

  export default {
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
        axios.get("http://localhost:8085/api/users/all")
            .then(response => this.users = response.data);
      }
    }
  }
</script>

<template>
  <table class="table-auto text-left">
    <caption>
      User Overview
      <button>
        <router-link :to="{name: 'createUser'}">create user</router-link>
      </button>
    </caption>
    <thead class="border-b font-medium dark:border-neutral-500">
      <tr>
        <th scope="col" class="px-6 py-4">uuid</th>
        <th scope="col" class="px-6 py-4">name</th>
        <th scope="col" class="px-6 py-4">edit</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(user) in users" :key="user.uuid" class="border-b dark:border-neutral-500">
        <td class="whitespace-nowrap px-6 py-4 font-medium">{{user.uuid}}</td>
        <td class="whitespace-nowrap px-6 py-4">{{user.username}}</td>
        <td class="whitespace-nowrap px-6 py-4">
          <button class="bg-red-500 hover:bg-red-800 py-2 px-4 rounded text-white">
            <router-link :to="{name: 'editUser', params: {id: user.uuid}}" exact>edit</router-link>
          </button>
          <button>delete</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>

</style>