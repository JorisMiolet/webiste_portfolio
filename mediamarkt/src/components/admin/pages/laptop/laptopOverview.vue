<template>
  <div class="p-6">
  <div class="mb-6">
    <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
      <div class="flex justify-between mb-4 items-start">
        <div class="font-medium">Manage laptops</div>
      </div>
      <div class="flex items-center mb-4 order-tab">
        <input type="file" class="hidden" @change="handleFileUpload" accept=".csv">
        <button type="button" @click="handleFileUpload" class="bg-red-500 text-sm font-medium text-white py-2 px-4 rounded active">import laptops</button>
        <button type="button" class="ml-auto bg-red-500 text-sm font-medium text-white py-2 px-4 rounded hover:bg-red-600"><router-link :to="{name: 'createLaptop'}">Create Laptop</router-link></button>
      </div>

      <form action="" class="flex items-center mb-4">
        <div class="relative w-full mr-2">
          <input type="text" class="py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Search...">
          <i class="ri-search-line absolute top-1/2 left-4 -translate-y-1/2 text-gray-400"></i>
        </div>
      </form>
      <div class="overflow-x-auto">
        <table class="w-full min-w-[540px]" data-tab-for="order" data-page="active">
          <thead>
          <tr>
            <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tl-md rounded-bl-md">EAN</th>
            <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Brand</th>
            <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Barcode</th>
            <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tr-md rounded-br-md">actions</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(laptop, index) in this.laptops" :key="index" @click="onSelect(laptop)">
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
            <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="inline-block p-1 rounded bg-emerald-500/10 text-emerald-500 font-medium text-[12px] leading-none">
                  <router-link :to="{name:'editLaptop', params: {EAN: laptop['id']}}" @click="onSelect(laptop)">
                    edit
                  </router-link>
                </span>
              <span class="inline-block p-1 ml-2 rounded bg-emerald-500/10 text-emerald-500 font-medium text-[12px] leading-none">
                  <router-link :to="{name:'editLaptop', params: {EAN: laptop['id']}}" @click="onSelect(laptop)">
                    delete
                  </router-link>
                </span>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>


<!--  <table class="table-auto text-left mt-12 ml-3">-->
<!--    <caption class="text-left">-->
<!--        <button>-->
<!--          <router-link :to="{name: 'createLaptop'}" class="bg-red-800 hover:bg-red-500 text-white font-medium mt-2 py-2 px-4 rounded">create Laptop</router-link>-->
<!--        </button>-->
<!--      <div>-->
<!--        <input type="file" class="hidden" @change="handleFileUpload" accept=".csv">-->
<!--        <button @click="handleImportCSV" class="bg-red-800 hover:bg-red-500 text-white font-medium mt-2 py-2 px-4 rounded">Import CSV</button>-->
<!--      </div>-->

<!--    </caption>-->
<!--    <thead class="border-b font-medium dark:border-neutral-500">-->
<!--    <tr class="background-color">-->
<!--      <th scope="col" class="px-6 py-4">ID</th>-->
<!--      <th scope="col" class="px-6 py-4">EAN</th>-->
<!--      <th scope="col" class="px-6 py-4">Barcode</th>-->
<!--      <th scope="col" class="px-6 py-4">Brand</th>-->
<!--      <th scope="col" class="px-6 py-4">Description</th>-->
<!--    </tr>-->
<!--    </thead>-->
<!--    <tbody>-->
<!--    <tr v-for="(laptop, index) in paginatedlaptops"-->
<!--        :key="index"-->
<!--        class="border-b border-black text-center leading-10"-->
<!--        :class="{'selected': laptop === selectedLaptop}"-->
<!--        @click="onSelect(laptop)"-->
<!--    >-->
<!--      <td v-for="(value, index) in laptop"-->
<!--      :key="index">-->
<!--        <router-link :to="{name:'editLaptop', params: {EAN: laptop['id']}}" @click="onSelect(laptop)">-->
<!--        {{value}}-->
<!--        </router-link>-->
<!--      </td>-->

<!--    </tr>-->
<!--    <div class="flex justify-center mt-4">-->
<!--      <button @click="prevPage" :disabled="currentPage === 1"-->
<!--              :class="{'disabled': currentPage === 1}"-->
<!--              class="bg-primary text-white font-bold p-2 rounded-2xl">-->
<!--        Previous-->
<!--      </button>-->
<!--      <div class="mx-4">-->
<!--        Page {{ currentPage }} of {{ totalPages }}-->
<!--      </div>-->
<!--      <button @click="nextPage" :disabled="currentPage === totalPages"-->
<!--              :class="{'disabled': currentPage === totalPages}"-->
<!--              class="bg-primary text-white font-bold p-2 rounded-2xl">-->
<!--        Next</button>-->
<!--    </div>-->
<!--    </tbody>-->
<!--  </table>-->

  <router-view :key="$route.fullPath"/>
</template>

<script>


export default {
  inject: ['url'],
  name: "laptopOverview",
  data() {
    return {
      selectedLaptop: null,
      laptops: [],
      currentPage: 1,
      rowsPerPage: 10,
    }
  },
  async created() {
    // axios.get("http://localhost:8085/api/laptops/all")
    const laptopsResponse = await fetch(this.url + '/api/laptops/all', {method: 'GET'});
    this.laptops = await laptopsResponse.json();
  },
  computed: {
    // totalPages(){
    //   return Math.ceil(this.laptops.length / this.rowsPerPage)
    // },
    // paginatedlaptops() {
    //   const startIndex = (this.currentPage - 1) * this.rowsPerPage;
    //   const endIndex = startIndex + this.rowsPerPage;
    //   return this.laptops.slice(startIndex, endIndex);
    // },
  },
  methods: {
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
          const laptopsResponse = await fetch(`${this.url}/api/laptops/getAll`);
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
      console.log(laptop)
      // if (this.selectedLaptop === laptop) {
      //   this.selectedLaptop = null;
      //   this.$router.push(this.$route.matched[1].path);
      // } else {
      //   this.selectedLaptop = laptop;
      //   this.$router.push(this.$route.matched[1].path +  "/" + laptop.EAN);
      // }
    },
  }
}
</script>

<style scoped>
</style>
