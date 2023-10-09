<template>
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
import dummyData from "@/assets/dummyData/dummyData.json";
import detailImage from "@/components/admin/pages/detail-image";

export default {
  name: "imageComponent",
  components:{
    detailImage
  },
  data() {
    return {
      laptops: Object.values(dummyData),
      selectedImageInfo: null,
    };
  },
  methods:{
    setSelectedImage(image) {
      if (image === (this.selectedImageInfo ? this.selectedImageInfo : null)) {
        this.selectedImageInfo = null;
      } else {
        this.selectedImageInfo = image;
        console.log(image)
      }
    },
  }
};
</script>

<style scoped>

</style>
