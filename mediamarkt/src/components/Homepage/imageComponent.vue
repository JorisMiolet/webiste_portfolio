<template>
  <sidenav-component @search="filterLaptopsbyEAN" />
  <search-component @search="filterLaptops" />
  <div class="w-3/4 p-4 grid grid-cols-2  ml-auto mr-9 gap-4">
    <div v-for="(laptop, index) in laptops"
         :key="index"
         class="border border-gray-300 p-4 rounded-lg shadow-md hover:shadow-lg h-[300px]"
         @click="setSelectedImage(laptop)"
         :class="{ 'selected': laptop === selectedImageInfo }">

      <h2 class="text-lg font-semibold">{{ laptop['Description / Model type'] }}</h2>
      <p class="text-gray-600">{{ laptop.Brand }}</p>
      <p class="text-gray-600">{{ laptop.PROCESSOR }}</p>
      <p class="text-gray-600">{{ laptop.EAN}}</p>
      <p class="text-gray-600">{{ laptop.RAM }}</p>
      <p class="text-gray-600">{{ laptop.STORAGE }}</p>
    </div>
  </div>
  <detail-image v-if="selectedImageInfo !== null"
                v-bind:selectedImage="selectedImageInfo"/>
</template>

<script>
import detailImage from "@/components/admin/pages/detail-image";
import axios from "axios";
import searchComponent from "@/components/Homepage/searchComponent.vue";
import sidenavComponent from "@/components/Homepage/sidenavComponent.vue";
export default {
  name: "imageComponent",
  components: {
    detailImage,
    searchComponent,
    sidenavComponent
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

    filterLaptops(searchQuery) {
      if (searchQuery.trim() === "") {
        this.loadUserList(); // Reset to the original list if the search query is empty
      } else {
        //checks if searchQuery matches a laptop
        const pattern = new RegExp(`^${searchQuery}.*$`, 'i');
        this.laptops = this.originalLaptops.filter((laptop) => {
          return pattern.test(laptop['Description / Model type']) || pattern.test(laptop['Brand']);
        });

        // If no laptops match the search query, set this.laptops to an empty array
        if (this.laptops.length === 0) {
          this.laptops = [];
        }
      }
    },


    filterLaptopsbyEAN(searchQuery) {
      if (searchQuery.trim() === "") {
        this.loadUserList(); // Reset to the original list if the search query is empty
      } else {
        //check if searchQuery mathes a laptop
        const pattern = new RegExp(`^${searchQuery}.*$`);
        this.laptops = this.originalLaptops.filter((laptop) => {
          return pattern.test(laptop['EAN']);
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

</style>

