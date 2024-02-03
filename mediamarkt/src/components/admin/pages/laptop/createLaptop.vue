<template>
  <div class="p-6 flex h-screen mt-60 md:mt-0">
    <div class="mb-6 mx-auto">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-between mb-4 items-start">
          <div class="font-medium">Create Laptop</div>
        </div>
        <div class="flex items-center flex-col">
          <div class="relative w-full mb-2">
            <div class="flex mt-2">
              <input v-model="newLaptop.Barcode" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 mr-2" placeholder="Article Nr">
              <input v-model="newLaptop.EAN" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500" placeholder="Ean">
            </div>
            <input v-model="newLaptop.Brand" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Brand">
            <input v-model="newLaptop.Description" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Description">
          </div>
          <div class="flex justify-between">
            <button @click="cancel" class="mr-3 shadow bg-gray-500 hover:bg-gray-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              cancel
            </button>
            <button @click="saveLaptop" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Create Laptop
            </button>
            <button @click="clear" class="mr-3 shadow bg-gray-500 hover:bg-gray-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Clear
            </button>
            <div v-if="errorMessage" class="text-red-500 mt-2">{{ errorMessage }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  inject: ['url'],
  name: "createLaptop",
  data(){
    return {
      newLaptop: {
        id: 0,
        EAN: null,
        Barcode: null,
        Brand: null,
        Description: null,
      },
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
    saveLaptop() {
      // Check if all fields are filled
      if (!this.newLaptop.EAN || !this.newLaptop.Barcode || !this.newLaptop.Brand || !this.newLaptop.Description) {
        this.errorMessage = "Please fill in all fields.";
        return;
      }

      // Clear any previous error message
      this.errorMessage = null;

      const newLaptop = {
        "EAN": this.newLaptop.EAN,
        "barcode": this.newLaptop.Barcode,
        "brand": this.newLaptop.Brand,
        "description": this.newLaptop.Description,
        "images": null
      };


      axios.post(this.url + '/api/laptops/create-laptop', newLaptop)
          .then(() => {
            alert('Laptop created!');
            this.$router.go(-1);
          })
          .catch(error => {
            console.error('Error creating laptop:', error);
            // Handle the error appropriately, e.g., show an error message to the user
          });
    },
    clear(){
      this.newLaptop.EAN = '';
      this.newLaptop.Barcode = '';
      this.newLaptop.Brand = '';
      this.newLaptop.Description = '';
    }
  }
}
</script>

<style scoped>

</style>
