<template>
  <form class="w-full max-w-lg">
    <div class="flex flex-wrap mb-6 mt-10 ml-1">
      <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="Brand">
          Brand
        </label>
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="ArticleNR" type="text" v-model="brand" placeholder="Brand">
      </div>
    </div>
    <div class="flex flex-wrap mb-6 mt-5 ml-1">
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="Description">
          Description
        </label>
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="Description" v-model="description" type="text" placeholder="Description">
      </div>
    </div>
    <div class="md:flex md:items-center">
      <div class="md:w-1/3"></div>
      <div class="md:w-2/3">
        <button class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" @click="saveLaptop" type="button">
          <router-link :to="{name: 'laptopOverview'}">Save Laptop</router-link>
        </button>
      </div>
    </div>
  </form>
</template>

<script>
import {Laptop} from "@/laptop";
export default {
  name: "createLaptop",
  data(){
    return {
      brand: "",
      description: ""
    }
  },
  methods: {
    async saveLaptop(){
      const createdLaptop = Laptop.createSampleLaptop(0, 0, this.brand, this.description );
      await fetch('http://localhost:8085/api/laptops', {
        method: 'POST',
        body: JSON.stringify(createdLaptop),
        headers: { 'Content-Type': 'application/json'}
      }).catch(error => console.log(error));

    }
  }
}
</script>

<style scoped>

</style>
