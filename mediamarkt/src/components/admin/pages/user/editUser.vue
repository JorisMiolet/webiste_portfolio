<script>
  import axios from "axios";

  export default {
    data() {
      return {
        user: null,
        dataLoaded: false,
        selectedRole: '',
        url: process.env.VUE_APP_API_URL,
      }
    },
    created() {
      this.getUser()
    },
    methods: {
      deleteUser() {
        const confirmDelete = confirm(`are you sure you want to delete user ${this.user.username}`);
        if (confirmDelete) {
          axios.delete(this.url + `/api/users/${this.$route.params.uuid}`)
              .then(this.$router.go(-1));
        }
      },
      resetUser() {
        const confirmReset = confirm(`are you sure you want to reset the user`);
        if (confirmReset) {
          this.getUser();
        }
      },
      save() {
        axios.put(this.url + `/api/users/${this.$route.params.id}`, this.user)
            .then(() => {
              alert("User saved");
              this.$router.go(-1);
            })
            .catch(error => {
              console.error("Error saving image:", error);
              // Handle the error appropriately, e.g., show an error message to the user
            });
      },
      getUser() {
        axios.get(this.url + `/api/users/${this.$route.params.id}`)
            .then(response => {
              this.user = response.data;
              this.dataLoaded = true;
              console.log(this.user)
            })
      }
    },
    computed: {
      userRole() {
        return this.user.admin ? 'admin' : 'medewerker';
      }
    },
    watch: {
      'user.admin'(newAdminStatus) {
        this.selectedRole = newAdminStatus ? 'admin' : 'medewerker';
      },
    }
  }
</script>

<template>

  <form v-if="dataLoaded" class="w-full max-w-lg">
    <div class="flex flex-wrap mb-6 mt-10 ml-1">
      <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="ArticleNR">
          Name
        </label>
        <input v-model="user.username" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="ArticleNR" type="text" placeholder="name">
      </div>
      <div class="w-full px-3 md:w-1/2">
        <label for="rechten" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
          Rechten
        </label>
        <select name="rechten" id="rechten" class="w-full px-4 py-3 mb-3 bg-gray-200 border rounded shadow-sm" v-model="selectedRole">
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
        <input v-model="user.email" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="EAN" type="email" placeholder="e-mail">
      </div>
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="EAN">
          locatie
        </label>
        <select v-model="user.locatie" name="rechten" id="rechten" class="w-full px-4 py-3 mb-3 bg-gray-200 border rounded shadow-sm">
          <option value="Amsterdam">Amsterdam</option>
          <option value="Utrecht">Utrecht</option>
          <option value="Eindhoven">Eindhoven</option>
          <option value="Arnhem">Arnhem</option>
        </select></div>
    </div>
    <div class="md:flex md:items-center">
      <div class="md:w-1/3">
        <button @click="save" class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">Save user</button>
      </div>
      <div class="md:w-2/3">
        <button @click="resetUser" class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">reset user</button>
        <button @click="deleteUser" class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">delete user</button>
      </div>
    </div>
  </form>
  <div v-else>
    <p>Loading...</p>
  </div>
</template>

<style scoped>

</style>