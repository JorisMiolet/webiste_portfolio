<script>
import axios from "axios";

export default {
  name: 'editLaptop',
  inject: ['url'],
  data() {
    return {
      laptop: null,
      dataLoaded: false,
    }
  },
  methods: {
    deleteImage(){
      const confirmDelete = confirm(`are you sure that you want to delete laptop ${this.laptop.EAN}?`);
      if(confirmDelete){
        axios.delete(this.url + `/api/laptops/${this.laptop.EAN}`)
            .then(this.$router.go(-1));
      }
    },
    resetLaptop(){
      const confirmReset = confirm(`are you sure you want to reset this laptop?`);
      if(confirmReset) {
        this.loadLaptop();
      }
    },
    save(){
      axios.put(this.url + `/api/laptops/${this.$route.params.EAN}`, this.laptop)
          .then(() => {
            alert("Laptop saved");
            this.$router.go(-1);
          })
          .catch(error => {
            console.error("Error saving laptop:", error);
          });
    },
    loadLaptop(){
      axios.get(this.url + `/api/laptops/${this.$route.params.EAN}`)
          .then(response => {
            this.laptop = response.data;
            this.dataLoaded = true;
          });
    }
  },
  created() {
    this.loadLaptop();
  }
}

</script>

<template>
  <div>
    <template v-if="dataLoaded">
      <div class="w-full max-w-lg">
        <div class="flex flex-wrap mb-6 mt-10 ml-1">
          <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="ArticleNR">
              id
            </label>
            <input v-model="laptop['id']" disabled class="disabled:opacity-60 appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="ArticleNR" type="text" placeholder="article nummer">
          </div>
          <div class="w-full px-3 md:w-1/2">
            <label for="Brand" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
              EAN
            </label>
            <input v-model="laptop.EAN" type="text" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" placeholder="Brand" id="Brand">
          </div>
        </div>
        <div class="flex flex-wrap mb-6 mt-5 ml-1">
          <div class="w-full px-3">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="EAN">
              Barcode
            </label>
            <input v-model="laptop.Barcode" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="EAN" type="text" placeholder="EAN">
          </div>
        </div>
        <div class="flex flex-wrap mb-6 mt-10 ml-1">
          <div class="w-full px-3">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="Description">
              Brand
            </label>
            <input v-model="laptop.Brand" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="Description" type="text" placeholder="Description / Model type">
          </div>
        </div>
        <div class="flex flex-wrap mb-6 mt-10 ml-1">
          <div class="w-full px-3">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="Description">
              Description
            </label>
            <input v-model="laptop.Description" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="Description" type="text" placeholder="Description / Model type">
          </div>
        </div>
        <div class="md:flex md:items-center">
          <div class="md:w-1/3"></div>
          <div class="md:w-2/3 mb-2">
            <button @click="save" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Save
            </button>
            <button @click="deleteImage" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">delete</button>
            <button @click="resetLaptop" class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">reset</button>
          </div>
        </div>
      </div>

    </template>

    <p v-else>Loading...</p>
  </div>
</template>

<style scoped>

</style>
