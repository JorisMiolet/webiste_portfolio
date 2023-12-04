<template>
  <search-component @search="filterLaptops" @filterByDate="filterLaptopsByDate" />
  <div class="form w-3/4 p-4 mt-24 pb-28 grid grid-cols-2 ml-auto mr-9 gap-4 overflow-y-scroll">
    <div  v-for="(laptop, index) in laptops"
         :key="index"
         class="border border-gray-300 p-4 rounded-lg shadow-md hover:shadow-lg h-50 w-auto"
         @click="setSelectedImage(laptop)"
         :class="{ 'selected': laptop === selectedImageInfo }">

      <h2 class="text-lg font-semibold">{{ laptop['Description / Model type'] }}</h2>
      <p class="text-gray-600">{{ laptop.Brand }}</p>
      <p class="text-gray-600">{{ laptop.PROCESSOR }}</p>
      <p class="text-gray-600">{{ laptop.EAN}}</p>
      <p class="text-gray-600">{{ laptop.RAM }}</p>
      <p class="text-gray-600">{{ laptop.STORAGE }}</p>
      <p class="text-gray-600">{{ laptop.DATE }}</p>
    </div>
  </div>
  <detail-image v-if="selectedImageInfo !== null"
                v-bind:selectedImage="selectedImageInfo"/>
</template>

<script>
import detailImage from "@/components/admin/pages/detail-image";
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
    };
  },
  mounted() {
    this.loadUserList();
  },
  methods: {

    setSelectedImage(image) {
      if (image === (this.selectedImageInfo ? this.selectedImageInfo : null)) {
        this.selectedImageInfo = null;
      } else {
        this.selectedImageInfo = image;
        console.log(image)
      }
    },

    //loads all laptops
    loadUserList() {
      axios.get('http://localhost:8085/api/images/all').then((response) => {
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
        return laptopDate <= threeMonthsAgo;
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
</style>

