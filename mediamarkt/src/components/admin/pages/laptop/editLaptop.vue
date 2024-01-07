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
    cancel(){
      const confirmReset = confirm(`are you sure you want to cancel`);
      if(confirmReset){
        this.$router.go(-1)
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
            console.log(this.laptop)
          });
    }
  },
  created() {
    this.loadLaptop();
  }
}

</script>

<template>
  <div class="p-6 flex h-screen"  v-if="dataLoaded">
    <div class="mb-6 mx-auto">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-between mb-4 items-start">
          <div class="font-medium">Edit Laptop</div>
        </div>
        <div class="flex items-center flex-col">
          <div class="relative w-full mb-2">
            <div class="flex mt-2">
              <input v-model="laptop.barcode" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 mr-2" placeholder="Article Nr">
              <input v-model="laptop.EAN" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500" placeholder="Ean">
            </div>
            <input v-model="laptop.brand" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Brand">
            <input v-model="laptop.description" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Description">
          </div>
          <div class="flex justify-between">
            <button @click="cancel" class="mr-3 shadow bg-gray-500 hover:bg-gray-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              cancel
            </button>
            <button @click="save" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Create Laptop
            </button>
            <button @click="resetLaptop" class=" mr-3 shadow bg-gray-500 hover:bg-gray-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Reset Laptop
            </button>
            <button @click="deleteImage" class="mr-3 shadow bg-gray-500 hover:bg-gray-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Delete Laptop
            </button>


          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
