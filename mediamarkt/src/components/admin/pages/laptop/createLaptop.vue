<template>
  <div class="p-6 flex h-screen">
    <div class="mb-6 mx-auto">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-between mb-4 items-start">
          <div class="font-medium">Create laptop</div>
        </div>
        <form action="" class="flex items-center flex-col">
          <div class="relative w-full mb-2">
            <div class="flex mt-2">
              <input v-model="newLaptop.Barcode" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 mr-2" placeholder="Barcode">
              <input v-model="newLaptop.EAN" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500" placeholder="Ean">
            </div>
            <input v-model="newLaptop.Brand" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Brand">
            <input v-model="newLaptop.Description" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Description">
          </div>
          <div class="flex justify-between">
            <button @click="saveLaptop" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Create laptop
            </button>
            <button @click="clear" class="shadow bg-gray-500 hover:bg-gray-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Clear
            </button>
          </div>
        </form>
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
    }
  },
  methods: {
    saveLaptop() {
      console.log(this.newLaptop)
      axios.post(this.url + '/api/laptops/create-laptop', {
        EAN:this.EAN,
        Barcode:this.Barcode,
        Brand:this.Brand,
        Description:this.Description
      })
          .then(() => {
            alert('Laptop created!');
            this.$router.go   (-1);
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