<script>
import imageData from '../dummyData.json';
import {PCImage} from "@/image.js";
import axios from "axios";
import * as XLSX from "xlsx";
export default {
  name: 'exportData',
  components: {},
  inject: ['url'],
  props: {
    isAdmin: Boolean,
  },
  data() {
    return {
      images: [],
      dummyData: imageData,
      selectedImage: null,
      searchFilter: null,
      summary: [],
      currentPage: 1,
      rowsPerPage: 10,
    }
  },
  created() {
    this.loadAllImages();
    this.loadImageSummary();
    console.log(this.images)
  },
  methods: {
    createOffer(image) {
      image = PCImage.createSampleImage(
          image.ArticleNR,
          image.EAN,
          image.Brand,
          image.Description,
          image.PROCESSOR,
          image.RAM,
          image.STORAGE,
          image.GPU,
      )
      this.images.push(image);
    },
    editImage(pcimage) {
      this.selectedImage = pcimage;
      this.toggleView();
    },
    deleteImage(image) {
      const confirmDelete = confirm(`are you sure you want to delete image ${image["Article NR"]}`);
      if (confirmDelete) {
        const urlWithQuery = `${this.url}/api/images/${image["id"]}`;
        axios.delete(urlWithQuery)
            .then(this.loadAllImages);
      }
    },
    resetImage() {
      this.$router.push(this.$route.matched[0].path)
    },
    onSelect(image) {
      this.selectedImage = image;
    },
    filterImages(){
      const urlWithQuery = `${this.url}/api/images/search?Filter=${this.searchFilter}`;
      axios.get(urlWithQuery)
          .then(response => this.images = response.data)
          .then(console.log(this.images))
    },
    loadAllImages() {
      const urlWithQuery = `${this.url}/api/images/all`;
      axios.get(urlWithQuery)
          .then(response => this.images = response.data)
          .then(console.log(this.images))
    },
    loadCompletedImages(){
      const urlWithQuery = `${this.url}/api/images/completed`;
      axios.get(urlWithQuery)
          .then(response => this.images = response.data)
          .then(console.log(this.images))
    },
    loadInCompletedImages(){
      const urlWithQuery = `${this.url}/api/images/incomplete`;
      axios.get(urlWithQuery)
          .then(response => this.images = response.data)
          .then(console.log(this.images))
    },
    loadImageSummary(){
      const urlWithQuery = `${this.url}/api/images/summary`;
      axios.get(urlWithQuery)
          .then(response => this.summary = response.data)
    },
    loadOutdatedImages(){
      const urlWithQuery = `${this.url}/api/images/outdated`;
      axios.get(urlWithQuery)
          .then(response => this.images = response.data)
          .then(console.log(this.images))
    },
    exportToExcel() {
      const worksheet = XLSX.utils.json_to_sheet(this.images);
      const workbook = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');
      const excelBuffer = XLSX.write(workbook, {bookType: 'xlsx', type: 'array'});

      const blob = new Blob([excelBuffer], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
      const url = URL.createObjectURL(blob);

      const a = document.createElement('a');
      a.href = url;
      a.download = 'exported_data.xlsx';
      a.click();
      URL.revokeObjectURL(url);
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
    pickup(image){
      const urlWithQuery = `${this.url}/api/images/pickup`;
      const data = {
        "article_nr": image["Article NR"],
        "user_id": sessionStorage.getItem('user_id')
      }
      axios.post(urlWithQuery, data)
          .then(this.loadAllImages())


    },
    getUserEmail(user) {
      return user ? user.email : 'N/A'; // Modify this based on your user object structure
    },
  },
  computed: {
    totalPages(){
      return Math.ceil(this.images.length / this.rowsPerPage)
    },
    paginatedImages() {
      const startIndex = (this.currentPage - 1) * this.rowsPerPage;
      const endIndex = startIndex + this.rowsPerPage;
      return this.images.slice(startIndex, endIndex);
    },

  },
  watch: {
    searchFilter() {
      this.filterImages();
    },
  },

}

</script>

<template>
  <div class="p-6">
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6 mt-40 md:mt-0">
      <div class="bg-white rounded-md border border-gray-100 p-6 shadow-md shadow-black/5">
        <div class="flex justify-between mb-6">
          <div>
            <div v-if="this.summary.completed" class="text-2xl font-semibold mb-1">{{ this.summary.completed }}</div>
            <div v-else class="text-2xl font-semibold mb-1">0</div>
            <div class="text-sm font-medium text-gray-600">Completed</div>
          </div>
        </div>
      </div>
      <div class="bg-white rounded-md border border-gray-100 p-6 shadow-md shadow-black/5">
        <div class="flex justify-between mb-6">
          <div>
            <div v-if="this.summary.in_progress" class="text-2xl font-semibold mb-1">{{ this.summary.in_progress }}</div>
            <div v-else class="text-2xl font-semibold mb-1">0</div>
            <div class="text-sm font-medium text-gray-600">In progress</div>
          </div>
        </div>
      </div>
      <div class="bg-white rounded-md border border-gray-100 p-6 shadow-md shadow-black/5">
        <div class="flex justify-between mb-6">
          <div>
            <div v-if="this.summary.outdated" class="text-2xl font-semibold mb-1">{{ this.summary.outdated }}</div>
            <div v-else class="text-2xl font-semibold mb-1">0</div>
            <div class="text-sm font-medium text-gray-600">Outdated</div>
          </div>
        </div>
      </div>
    </div>
    <div class="mb-6">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-center sm:justify-between mb-4 sm:items-start">
          <div class="font-medium ">Manage images</div>
        </div>
        <div class="flex flex-col sm:flex-row items-center mb-4 order-tab">
          <button @click="loadAllImages" type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 rounded-tl-md rounded-bl-md hover:text-gray-600 active">All</button>
          <button @click="loadCompletedImages" type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 rounded-tl-md rounded-bl-md hover:text-gray-600 active">Completed</button>
          <button @click="loadInCompletedImages" type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 hover:text-gray-600">In progress</button>
          <button @click="loadOutdatedImages" type="button" class="bg-gray-50 text-sm font-medium text-gray-400 py-2 px-4 rounded-tr-md rounded-br-md hover:text-gray-600">Outdated</button>
          <button type="button" class="sm:ml-auto bg-red-500 text-sm font-medium text-white py-2 px-4 rounded-tr-md rounded hover:bg-red-600"><router-link :to="{name: 'createImage'}">Create image</router-link></button>
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
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tl-md rounded-bl-md">Article number</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">EAN</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Brand</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left">Status</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tr-md rounded-br-md">User</th>
              <th class="text-[12px] uppercase tracking-wide font-medium text-gray-400 py-2 px-4 bg-gray-50 text-left rounded-tr-md rounded-br-md">Actions</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(pcimage) in paginatedImages" :key="pcimage.EAN">
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="text-[13px] font-medium text-gray-800">{{ pcimage["Article NR"] }}</span>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="text-[13px] font-medium text-gray-800">{{ pcimage["EAN"] }}</span>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="text-[13px] font-medium text-gray-800">{{ pcimage["Brand"] }}</span>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="text-[13px] font-medium text-gray-800">{{ pcimage["STATUS"] }}</span>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span class="text-[13px] font-medium text-gray-800">{{ getUserEmail(pcimage["user"]) }}</span>
              </td>
              <td class="py-2 px-4 border-b border-b-gray-50">
                <span v-if="isAdmin" class="inline-block p-1 rounded bg-emerald-500/10 text-emerald-500 font-medium text-[12px] leading-none">
                  <button>
                    <router-link :to="{name:'editImage', params: {ArticleNR: pcimage['Article NR']}}" @click="onSelect(pcimage)">edit</router-link>
                  </button>
                </span>
                <span @click="deleteImage(pcimage)" v-if="isAdmin" class="inline-block ml-2 p-1 rounded bg-red-600/10 text-red-600 font-medium text-[12px] leading-none">
                  <button>
                    <span>delete</span>
                  </button>
                </span>
                <span @click="pickup(pcimage)" v-if="pcimage['STATUS'] !== 'completed' && pcimage['STATUS'] !== 'in progress'" class="inline-block ml-2 p-1 rounded bg-orange-500/10 text-orange-500 font-medium text-[12px] leading-none">
                  <button>
                    <span>pick up</span>
                  </button>
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

<style scoped>

</style>
