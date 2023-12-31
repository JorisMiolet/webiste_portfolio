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
        <form action="" class="flex items-center flex-col">
          <div class="relative w-full mb-2">
            <input type="text" class="py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Naam...">
            <input type="email" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Email...">
            <input type="password" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Wachtwoord...">
            <div class="flex mt-2">
              <select class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 outline-none appearance-none bg-select-arrow bg-no-repeat bg-[length:16px_16px] bg-[right_16px_center]">
                <option value="" selected disabled>Rechten</option>
                <option value="">Admin</option>
                <option value="">Medewerker</option>
              </select>
              <select class="flex-grow ml-1 text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 outline-none appearance-none bg-select-arrow bg-no-repeat bg-[length:16px_16px] bg-[right_16px_center]">
                <option value="" selected disabled>Locatie</option>
                <option value="">Amsterdam</option>
                <option value="">Utrecht</option>
                <option value="">Rotterdam</option>
                <option value="">Eindhoven</option>
                <option value="">Arnhem</option>
              </select>
            </div>
          </div>
          <button class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
            <button @click="saveUser">Save user</button>
          </button>
        </form>
      </div>
    </div>
  </div>


<!--  <form class="w-full max-w-lg">-->
<!--    <div class="flex flex-wrap mb-6 mt-10 ml-1">-->
<!--      <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">-->
<!--        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="ArticleNR">-->
<!--          Name-->
<!--        </label>-->
<!--        <input v-model="name" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="ArticleNR" type="text" placeholder="name">-->
<!--      </div>-->
<!--      <div class="w-full px-3 md:w-1/2">-->
<!--        <label for="rechten" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">-->
<!--          Rechten-->
<!--        </label>-->
<!--        <select v-model="rechten" name="rechten" id="rechten" class="w-full px-4 py-3 mb-3 bg-gray-200 border rounded shadow-sm">-->
<!--          <option value="admin">admin</option>-->
<!--          <option value="medewerker">medewerker</option>-->
<!--        </select>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="flex flex-wrap mb-6 mt-5 ml-1">-->
<!--      <div class="w-full px-3">-->
<!--        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="email">-->
<!--          e-mail-->
<!--        </label>-->
<!--        <input v-model="email" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="email" type="email" placeholder="e-mail">-->
<!--      </div>-->
<!--    </div><div class="flex flex-wrap mb-6 mt-5 ml-1">-->
<!--    <div class="w-full px-3">-->
<!--      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="password">-->
<!--        password-->
<!--      </label>-->
<!--      <input v-model="password" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="password" type="password" placeholder="password">-->
<!--    </div>-->
<!--  </div>-->
<!--    <div class="flex flex-wrap mb-6 mt-5 ml-1">-->
<!--      <div class="w-full px-3">-->
<!--        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="locatie">-->
<!--          locatie-->
<!--        </label>-->
<!--        <select v-model="locatie" name="rechten" id="rechten" class="w-full px-4 py-3 mb-3 bg-gray-200 border rounded shadow-sm">-->
<!--          <option value="Amsterdam">Amsterdam</option>-->
<!--          <option value="Utrecht">Utrecht</option>-->
<!--          <option value="Eindhoven">Eindhoven</option>-->
<!--          <option value="Arnhem">Arnhem</option>-->
<!--        </select>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="md:flex md:items-center">-->
<!--      <div class="md:w-1/3"></div>-->
<!--      <div class="md:w-2/3">-->
<!--        <button class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">-->
<!--          <button @click="saveUser">Save user</button>-->
<!--        </button>-->
<!--      </div>-->
<!--    </div>-->
<!--  </form>-->
</template>

<style scoped>

</style>