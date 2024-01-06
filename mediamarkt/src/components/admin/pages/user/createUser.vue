<script>
import axios from "axios";

export default {
  inject: ['url'],
  name: "createUser",
  data() {
    return {
      name: '',
      email: '',
      password: '',
      locatie: '',
      rechten: '',
      errorMessage: null,
    }
  },
  methods: {
    cancel(){
      const confirmReset = confirm(`are you sure you want to cancel`);
      if(confirmReset){
        this.$router.go(-1)
      }
    },
    saveUser() {
      // Check if all fields are filled
      if (!this.name || !this.email || !this.password || !this.locatie || !this.rechten) {
        this.errorMessage = "Please fill in all fields.";
        return;
      }

      // Clear any previous error message
      this.errorMessage = null;

      axios.post(this.url + '/api/users/create-user', {
        username: this.name,
        email: this.email,
        password: this.password,
        locatie: this.locatie,
        rechten: this.rechten === 'admin' ? true : false,
      })
          .then(() => {
            this.$router.push("/admin/user-overview");
          })
          .catch(error => {
            console.error('Error creating user:', error);
            // Handle the error appropriately, e.g., show an error message to the user
          });
    }
  },

}
</script>

<template>
  <div class="p-6 flex h-screen">
    <div class="mb-6 mx-auto">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-between mb-4 items-start">
          <div class="font-medium">Create user</div>
        </div>
        <div class="flex items-center flex-col">
          <div class="relative w-full mb-2">
            <input v-model="name" type="text" class="py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Naam...">
            <input v-model="email" type="email" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Email...">
            <input v-model="password" type="password" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Wachtwoord...">
            <div class="flex mt-2">
              <select v-model="rechten" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 outline-none appearance-none bg-select-arrow bg-no-repeat bg-[length:16px_16px] bg-[right_16px_center] text-gray-500">
                <option value="" selected disabled>Kies rechten...</option>
                <option :value="true">Admin</option>
                <option :value="false">Medewerker</option>
              </select>

                <select v-model="locatie" class="flex-grow ml-1 text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 outline-none appearance-none bg-select-arrow bg-no-repeat bg-[length:16px_16px] bg-[right_16px_center] text-gray-500">
                  <option value="" disabled>{{ locatie ? '' : 'Kies een locatie...' }}</option>
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

            </div>
          </div>
          <div class="flex w-56 justify-around">
            <button @click="cancel" class="mr-3 shadow bg-gray-500 hover:bg-gray-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              cancel
            </button>
            <button @click="saveUser" class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
            save user
          </button>

          </div>
          <div v-if="errorMessage" class="text-red-500 mt-2">{{ errorMessage }}</div>
        </div>
        </div>
      </div>
    </div>

</template>

<style scoped>

</style>