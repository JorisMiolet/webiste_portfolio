<template>
  <table class="table-auto text-left mt-12 ml-3">
    <caption class="text-left">
        User Overview
        <button>
          <router-link :to="{name: 'createLaptop'}" class="bg-red-800 hover:bg-red-500 text-white font-medium mt-2 py-2 px-4 rounded">create Laptop</router-link>
        </button>

      <button class="bg-red-800 hover:bg-red-500 text-white font-medium mt-2 py-2 px-4 rounded" @click="exportToExcel"
      >
        Export data
      </button>
      <div>
        <input type="file" class="hidden" @change="handleFileUpload" accept=".csv">
        <button @click="handleImportCSV" class="bg-red-800 hover:bg-red-500 text-white font-medium mt-2 py-2 px-4 rounded">Import CSV</button>
      </div>

    </caption>
    <thead class="border-b font-medium dark:border-neutral-500">
    <tr>
      <th scope="col" class="px-6 py-4">EAN</th>
      <th scope="col" class="px-6 py-4">Barcode</th>
      <th scope="col" class="px-6 py-4">Brand</th>
      <th scope="col" class="px-6 py-4">Description</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(laptop, index) in paginatedlaptops"
        :key="index"
        class="border-b border-black text-center leading-10"
        :class="{'selected': laptop === selectedLaptop}"
        @click="onSelect(laptop)"
    >
      <td v-for="(value, index) in laptop"
      :key="index">
        {{value}}
      </td>
    </tr>
    </tbody>
  </table>
  <div class="flex justify-center mt-4">
    <button @click="prevPage" :disabled="currentPage === 1"
            :class="{'disabled': currentPage === 1}"
            class="bg-primary text-white font-bold p-2 rounded-2xl">
      Previous
    </button>
    <div class="mx-4">
      Page {{ currentPage }} of {{ totalPages }}
    </div>
    <button @click="nextPage" :disabled="currentPage === totalPages"
            :class="{'disabled': currentPage === totalPages}"
            class="bg-primary text-white font-bold p-2 rounded-2xl">
      Next</button>
  </div>
  <router-view :key="$route.fullPath"/>
</template>

<script>


export default {
  name: "imageOverview",
  data() {
    return {
      selectedLaptop: null,
      laptops: [],
      currentPage: 1,
      rowsPerPage: 10,
    }
  },
  async created() {
    const laptopsResponse = await fetch('http://localhost:8085/api/laptops/getAll', {method: 'GET'});
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
    async importFromCSV(file) {
      const formData = new FormData();
      formData.append('file', file);

      try {
        const response = await fetch('http://localhost:8085/api/laptops/importLaptops', {
          method: 'POST',
          body: formData,
        });

        if (response.ok) {
          alert('Laptops imported successfully');
          const laptopsResponse = await fetch('http://localhost:8085/api/laptops/getAll');
          const laptopsData = await laptopsResponse.json(); // Parse the JSON data
          this.laptops = laptopsData; // Set the updated list to the component's data
          // Optionally update your frontend display after successful import
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

      if (this.selectedLaptop === laptop) {
        this.selectedLaptop = null;
        this.$router.push(this.$route.matched[1].path);
      } else {
        this.selectedLaptop = laptop;
        this.$router.push(this.$route.matched[1].path +  "/" + laptop.EAN);
      }
    },
  }
}
</script>

<style scoped>

</style>
