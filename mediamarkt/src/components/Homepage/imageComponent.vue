<template>
  <search-component @search="filterLaptops" @filterByDate="filterLaptopsByDate" />
    <table class="table-auto text-left mx-auto mt-20 h-[300px] overflow-y-scroll">
      <thead class="border-b font-medium dark:border-neutral-500">
      <tr>
        <th scope="col" class="px-6 py-4">#</th>
        <th scope="col" class="px-6 py-4">Article NR</th>
        <th scope="col" class="px-6 py-4">EAN</th>
        <th scope="col" class="px-6 py-4">Brand</th>
        <th scope="col" class="px-6 py-4">Description</th>
        <th scope="col" class="px-6 py-4">Processor</th>
      </tr>
      </thead>
      <tbody>
      <tr class="border-b dark:border-neutral-500" v-for="(pcimage, key) in laptops" :key="pcimage.EAN"
      @click="setSelectedImage(pcimage)">
        <td class="whitespace-nowrap px-6 py-4 font-medium">{{ (key+1) }}</td>
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["Article NR"] }}</td>
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["EAN"] }}</td>
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["Brand"] }}</td>
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["Description / Model type"] }}</td>
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["PROCESSOR"] }}</td>
      </tr>
      </tbody>
    </table>
  <detail-image v-if="selectedImageInfo !== null"
                v-bind:selectedImage="selectedImageInfo"
                v-on:resetImage="resetImage()"/>
</template>

<script>
import detailImage from "@/components/admin/pages/image/detail-image.vue";
import axios from "axios";
import searchComponent from "@/components/Homepage/searchComponent.vue";
export default {
  name: "imageComponent",
  components: {
    detailImage,
    searchComponent,
  },
  data() {
    return {
      laptops: [],
      originalLaptops: [], // Voeg een array toe om de oorspronkelijke lijst met laptops op te slaan
      selectedImageInfo: null,
      // url: process.env.VUE_APP_API_URL,
      url: 'https://ewa-back-end-r7ie.onrender.com',
    };
  },
  mounted() {
    this.loadUserList();
  },

  methods: {
    setSelectedImage(image) {
      this.selectedImageInfo = image;
    },

    resetImage(){
      this.selectedImageInfo = null;
    },

    //loads all laptops
    loadUserList() {
      axios.get(`${this.url}/api/images/all`).then((response) => {
        this.laptops = response.data;
        this.originalLaptops = response.data; // Bewaar de oorspronkelijke lijst
      });
    },
    updateLaptops(laptops) {
      console.log('Updating laptops:', laptops);
      return this.laptops = laptops;
    },
    filterLaptopsByDate() {
      const threeMonthsAgo = new Date();
      threeMonthsAgo.setMonth(threeMonthsAgo.getMonth() - 3);

      this.laptops = this.originalLaptops.filter((laptop) => {
        const laptopDate = new Date(laptop['DATE']);
        return laptopDate < threeMonthsAgo;
      });

      if (this.laptops.length === 0) {
        this.laptops = [];
      }
    },

    filterLaptops(searchQuery) {
      if (searchQuery.trim() === "") {
        this.loadUserList(); // Reset to the original list if the search query is empty
      } else {
        //checks if searchQuery matches a laptop
        const pattern = new RegExp(`^${searchQuery}.*$`, 'i');
        this.laptops = this.originalLaptops.filter((laptop) => {
          return pattern.test(laptop['Description / Model type']) || pattern.test(laptop['Brand']) || pattern.test(laptop['EAN']);
        });

        // If no laptops match the search query, set this.laptops to an empty array
        if (this.laptops.length === 0) {
          this.laptops = [];
        }
      }
    }
  }
}
</script>

<style scoped>
body, html{
  overflow-y: hidden;
}
.form{
  max-height: 90vh;
}
html, body {margin: 0; height: 100%; overflow: hidden}
</style>

