<script>
import axios from "axios";

export default {
  name: "createUser",
  data(){
    return{
      name: '',
      email: '',
      password: '',
      locatie: '',
      rechten: '',
    }
  },
  methods:{
    saveUser(){
      axios.post(this.url  +'/api/users/create-user', {
        username: this.name,
        email: this.email,
        password: this.password,
        locatie: this.locatie,
        rechten: this.rechten === 'admin' ? this.rechten = true : this.rechten = false,
      })
          .then((data) => {
            console.log(data)
            alert('User created successfully');
            this.$router.go(-1);
          })
          .catch(error => {
            console.error('Error creating user:', error);
            // Handle the error appropriately, e.g., show an error message to the user
          });
    }
  }

}
</script>

<template>
  <form class="w-full max-w-lg">
    <div class="flex flex-wrap mb-6 mt-10 ml-1">
      <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="ArticleNR">
          Name
        </label>
        <input v-model="name" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="ArticleNR" type="text" placeholder="name">
      </div>
      <div class="w-full px-3 md:w-1/2">
        <label for="rechten" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
          Rechten
        </label>
        <select v-model="rechten" name="rechten" id="rechten" class="w-full px-4 py-3 mb-3 bg-gray-200 border rounded shadow-sm">
          <option value="admin">admin</option>
          <option value="medewerker">medewerker</option>
        </select>
      </div>
    </div>
    <div class="flex flex-wrap mb-6 mt-5 ml-1">
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="email">
          e-mail
        </label>
        <input v-model="email" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="email" type="email" placeholder="e-mail">
      </div>
    </div><div class="flex flex-wrap mb-6 mt-5 ml-1">
    <div class="w-full px-3">
      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="password">
        password
      </label>
      <input v-model="password" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="password" type="password" placeholder="password">
    </div>
  </div>
    <div class="flex flex-wrap mb-6 mt-5 ml-1">
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="locatie">
          locatie
        </label>
        <select v-model="locatie" name="rechten" id="rechten" class="w-full px-4 py-3 mb-3 bg-gray-200 border rounded shadow-sm">
          <option value="Amsterdam">Amsterdam</option>
          <option value="Utrecht">Utrecht</option>
          <option value="Eindhoven">Eindhoven</option>
          <option value="Arnhem">Arnhem</option>
        </select>
      </div>
    </div>
    <div class="md:flex md:items-center">
      <div class="md:w-1/3"></div>
      <div class="md:w-2/3">
        <button class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
          <button @click="saveUser">Save user</button>
        </button>
      </div>
    </div>
  </form>
</template>

<style scoped>

</style>