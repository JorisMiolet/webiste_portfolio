<script>
  import axios from "axios";

  export default {
    data(){
      return {
        user: null,
      }
    },
    created() {
      this.getUser(this.$route.params.id)
    },
    methods: {
      getUser(uuid){
        axios.get(`http://127.0.0.1:8085/api/users/${uuid}`)
            .then(response => this.user = response.data)
            .then(console.log(this.user));
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
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="ArticleNR" type="text" placeholder="name">
      </div>
      <div class="w-full px-3 md:w-1/2">
        <label for="rechten" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
          Rechten
        </label>
        <select name="rechten" id="rechten" class="w-full px-4 py-3 mb-3 bg-gray-200 border rounded shadow-sm">
          <option value="admin">admin</option>
          <option value="medewerker">medewerker</option>
        </select>
      </div>
    </div>
    <div class="flex flex-wrap mb-6 mt-5 ml-1">
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="EAN">
          e-mail
        </label>
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="EAN" type="email" placeholder="e-mail">
      </div>
    </div>
    <div class="md:flex md:items-center">
      <div class="md:w-1/3"></div>
      <div class="md:w-2/3">
        <button class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">Edit user</button>
      </div>
    </div>
  </form>
</template>

<style scoped>

</style>