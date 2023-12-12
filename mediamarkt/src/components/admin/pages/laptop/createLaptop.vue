<template>
    <form class="w-full max-w-lg">
      <div class="flex flex-wrap mb-6 mt-10 ml-1">
        <div class="w-full px-3 md:w-1/2">
          <label for="Brand" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
            EAN
          </label>
          <input v-model="newLaptop.EAN" type="number" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" placeholder="Brand" id="Brand">
        </div>
      </div>
      <div class="flex flex-wrap mb-6 mt-5 ml-1">
        <div class="w-full px-3">
          <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="EAN">
            Barcode
          </label>
          <input v-model="newLaptop.Barcode" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="EAN" type="number" placeholder="EAN">
        </div>
      </div>
      <div class="flex flex-wrap mb-6 mt-10 ml-1">
        <div class="w-full px-3">
          <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="Description">
            Brand
          </label>
          <input v-model="newLaptop.Brand" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="Description" type="text" placeholder="Description / Model type">
        </div>
      </div>
      <div class="flex flex-wrap mb-6 mt-10 ml-1">
        <div class="w-full px-3">
          <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="Description">
            Description
          </label>
          <input v-model="newLaptop.Description" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="Description" type="text" placeholder="Description / Model type">
        </div>
      </div>
      <div class="md:flex md:items-center">
        <div class="md:w-1/3"></div>
        <div class="md:w-2/3 mb-2">
          <button @click="saveLaptop" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
            Save
          </button>
        </div>
      </div>
    </form>
</template>

<script>
import axios from "axios";

export default {
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
      axios.post('http://localhost:8085/api/laptops/create-laptop', {
        EAN:this.EAN,
        Barcode:this.Barcode,
        Brand:this.Brand,
        Description:this.Description
      })
          .then(() => {
            alert('Laptop created!');
            this.$router.go(-1);
          });
    }
  }
}
</script>

<style scoped>

</style>