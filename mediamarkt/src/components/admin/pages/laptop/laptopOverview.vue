<template>
  <div class="p-6">
  <div class="mb-6">
    <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
      <div class="flex justify-between mb-4 items-start">
        <div class="font-medium">Manage laptops</div>
      </div>
      <div class="flex items-center mb-4 order-tab">
        <input type="file" @change="handleFileUpload" @input="handleFileUpload" class="bg-red-500 text-sm font-medium text-white py-2 pl-4 rounded active" accept=".csv">
        <button type="button" class="ml-auto bg-red-500 text-sm font-medium text-white py-2 px-4 rounded hover:bg-red-600"><router-link :to="{name: 'createLaptop'}">Create Laptop</router-link></button>
      </div>

      <div class="flex items-center mb-4">
        <div class="relative w-full mr-2">
          <input v-model="searchFilter" type="text" class="py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Search...">
          <i class="ri-search-line absolute top-1/2 left-4 -translate-y-1/2 text-gray-400"></i>
        </div>
      </div>
      <div class="overflow-x-auto">
        <table class="w-full min-w-[540px]" data-tab-for="order" data-page="active">
          <thead>
          <tr>
            <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tl-md rounded-bl-md">EAN</th>
            <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Brand</th>
            <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Barcode</th>
            <th v-if="isAdmin" class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tr-md rounded-br-md">actions</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(laptop, index) in paginatedlaptops" :key="index" @click="onSelect(laptop)">
            <td class="py-2 px-4 border-b border-b-gray-50">
              <div class="flex items-center">
                <span class="text-[13px] font-medium text-gray-800">{{laptop.EAN}}</span>
              </div>
            </td>
            <td class="py-2 px-4 border-b border-b-gray-50">
              <span class="text-[13px] font-medium text-gray-800">{{laptop.brand}}</span>
            </td>
            <td class="py-2 px-4 border-b border-b-gray-50">
              <span class="text-[13px] font-medium text-gray-800">{{laptop.barcode}}</span>
            </td>
            <td v-if="isAdmin" class="py-2 px-4 border-b border-b-gray-50">
                <span class="inline-block p-1 rounded bg-emerald-500/10 text-emerald-500 font-medium text-[12px] leading-none">
                  <router-link :to="{name:'editLaptop', params: {EAN: laptop['id']}}" @click="onSelect(laptop)">
                    edit
                  </router-link>
                </span>
              <span class="inline-block p-1 ml-2 rounded bg-red-600/10 text-red-600 font-medium text-[12px] leading-none">
                  <router-link :to="{name:'editLaptop', params: {EAN: laptop['id']}}" @click="onSelect(laptop)">
                    delete
                  </router-link>
                </span>
            </td>
          </tr>
          </tbody>
        </table>
        <div class="flex justify-center mt-4">
          <button @click="prevPage" :disabled="currentPage === 1" :class="{'disabled': currentPage === 1}" class="bg-red-500 text-sm font-medium text-white py-2 px-4 rounded hover:bg-red-600">
            Previous
          </button>
          <div class="mx-4">
            Page {{ currentPage }} of {{ totalPages }}
          </div>
          <button @click="nextPage" :disabled="currentPage === totalPages" :class="{'disabled': currentPage === totalPages}" class="bg-red-500 text-sm font-medium text-white py-2 px-4 rounded hover:bg-red-600">
            Next
          </button>
        </div>
      </div>

    </div>
  </div>
</div>
  <router-view :key="$route.fullPath"/>
</template>
<script>


import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

export default {
  inject: ['url'],
  name: "laptopOverview",
  props: {
    isAdmin: Boolean,
  },
  data() {
    return {
      selectedLaptop: null,
      laptops: [],
      currentPage: 1,
      rowsPerPage: 10,
      searchFilter: null,
    }
  },
  async created() {
    this.validateAdmin()
    const laptopsResponse = await fetch(this.url + '/api/laptops/all', {method: 'GET'});
    this.laptops = await laptopsResponse.json();
  },
  computed: {
    totalPages(){
      return Math.ceil(this.laptops.length / this.rowsPerPage)
    },
    paginatedlaptops() {
      const startIndex = (this.currentPage - 1) * this.rowsPerPage;
      const endIndex = startIndex + this.rowsPerPage;
      return this.laptops.slice(startIndex, endIndex);
    },
  },
  methods: {
    async filterLaptops() {
      if (this.searchFilter === null || this.searchFilter === "") {
        const laptopsResponse = await fetch(this.url + '/api/laptops/all', {method: 'GET'});
        this.laptops = await laptopsResponse.json();
        return;
      }
      const urlWithQuery = `${this.url}/api/laptops/search?Filter=${this.searchFilter}`;
      axios.get(urlWithQuery)
          .then(response => this.laptops = response.data)
    },
    validateAdmin(){
     let token = sessionStorage.getItem("token")
      let decodedToken = VueJwtDecode.decode(token)
      if (!JSON.parse(decodedToken.admin)){
        this.$router.push("/")
      }
    },
    async importFromCSV(file) {
      const formData = new FormData();
      formData.append('file', file);

      try {
        const response = await fetch(`${this.url}/api/laptops/importLaptops`, {
          method: 'POST',
          body: formData,
        });
        if (response.ok) {
          alert('Laptops imported successfully');
          const laptopsResponse = await fetch(`${this.url}/api/laptops/all`);
          const laptopsData = await laptopsResponse.json(); // Parse the JSON data
          // Update laptops only if it's not empty
          if (Array.isArray(laptopsData) && laptopsData.length > 0) {
            this.laptops = laptopsData;
          }
        } else {
          const errorMessage = await response.text();
          alert(`Error: ${errorMessage}`);
        }
      } catch (error) {
        alert('Error importing laptops');
        console.error(error);
      }
    },
    handleFileUpload(event) {
      const file = event.target.files[0];
      if (file) {
        this.importFromCSV(file);
        event.target.value = null;
      }
    },
    handleImportCSV() {
      const fileInput = document.querySelector('input[type="file"]');
      if (fileInput) {
        fileInput.click();
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    async onSelect(laptop) {
      console.log(laptop)
      // if (this.selectedLaptop === laptop) {
      //   this.selectedLaptop = null;
      //   this.$router.push(this.$route.matched[1].path);
      // } else {
      //   this.selectedLaptop = laptop;
      //   this.$router.push(this.$route.matched[1].path +  "/" + laptop.EAN);
      // }
    },
  },
  watch: {
    laptops: {
      handler: function () {
        this.currentPage = 1; // Reset to the first page when laptops change
        this.$nextTick(() => {
          // Using $nextTick to ensure the DOM has been updated before recalculating totalPages
          this.$forceUpdate(); // Force update to trigger recomputation of computed properties
        });
      },
      deep: true, // Watch nested changes in the laptops array
    },
    searchFilter() {
      this.filterLaptops();
    },
  },
}
</script>

<style scoped>
</style>
