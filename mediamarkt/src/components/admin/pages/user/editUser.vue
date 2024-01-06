<script>
  import axios from "axios";

  export default {
    inject: ['url'],
    data() {
      return {
        user: null,
        dataLoaded: false,
        selectedRole: '',
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
        // Check if the password is not empty before sending it
        if (this.user.password !== '') {
          axios.put(this.url + `/api/users/${this.$route.params.id}`, this.user)
              .then(() => {
                alert("User saved");
                this.$router.go(-1);
              })
              .catch(error => {
                console.error("Error saving user:", error);
                // Handle the error appropriately, e.g., show an error message to the user
              });
        } else {
          // If password is empty, exclude it from the user object before sending
          const userWithoutPassword = { ...this.user };
          delete userWithoutPassword.password;

          axios.put(this.url + `/api/users/${this.$route.params.id}`, userWithoutPassword)
              .then(() => {
                alert("User saved");
                this.$router.go(-1);
              })
              .catch(error => {
                console.error("Error saving user:", error);
                // Handle the error appropriately, e.g., show an error message to the user
              });
        }
      },
      getUser() {
        axios.get(this.url + `/api/users/${this.$route.params.id}`)
            .then(response => {
              this.user = response.data;
              this.user.password = '';
              this.dataLoaded = true;
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
  <div class="p-6 flex h-screen">
    <div class="mb-6 mx-auto">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-between mb-4 items-start">
          <div class="font-medium">Edit user</div>
        </div>
        <div v-if="dataLoaded" class="flex items-center flex-col">
          <div class="relative w-full mb-2">
            <input v-model="user.username" type="text" class="py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Naam...">
            <input v-model="user.email"  type="email" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Email...">
            <input v-model="user.password" type="password" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Wachtwoord...">
            <div class="flex mt-2">
              <select v-model="user.admin" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 outline-none appearance-none bg-select-arrow bg-no-repeat bg-[length:16px_16px] bg-[right_16px_center]">
                <option :value="true">Admin</option>
                <option :value="false">Medewerker</option>
              </select>
              <div class="relative">
                <select v-model="user.locatie" class="flex-grow ml-1 text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 outline-none appearance-none bg-select-arrow bg-no-repeat bg-[length:16px_16px] bg-[right_16px_center]">
                  <option value="" disabled selected>Kies een locatie</option>
                  <option value="Amsterdam">MediaMarkt Amsterdam</option>
                  <option value="Rotterdam">MediaMarkt Rotterdam</option>
                  <option value="Utrecht">MediaMarkt Utrecht</option>
                  <option value="Den Haag">MediaMarkt Den Haag</option>
                  <option value="Eindhoven">MediaMarkt Eindhoven</option>
                  <option value="Groningen">MediaMarkt Groningen</option>
                  <option value="Tilburg">MediaMarkt Tilburg</option>
                  <option value="Breda">MediaMarkt Breda</option>
                  <option value="Arnhem">MediaMarkt Arnhem</option>
                  <option value="Nijmegen">MediaMarkt Nijmegen</option>
                </select>
                <span class="absolute inset-y-0 left-0 flex items-center pl-4 pointer-events-none text-sm text-gray-500" v-if="!user.locatie">Kies een locatie...</span>
              </div>

            </div>
          </div>
          <button class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
            <button @click="save">Save user</button>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>