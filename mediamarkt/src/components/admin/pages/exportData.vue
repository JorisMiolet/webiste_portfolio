<script>
import imageData from './dummyData.json';
import {PCImage} from "@/image.js";
export default {
  name: 'exportData',
  components: {},
  data(){
    return {
      images: [],
      dummyData: imageData,
      selectedImage: null,
    }
  },
  created() {
    for (let i = 1; i <= 10; i++) {
      const laptopKey = `laptop${i}`;
      if (this.dummyData[laptopKey]) {
        const laptop = this.dummyData[laptopKey];
        this.createOffer(laptop);
      }
    }
   },methods: {
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
    editImage(pcimage){
      this.selectedImage = pcimage;
      this.toggleView();
    },
    deleteImage(){
      this.images = this.images.filter((image) => image === this.selectedImage);
      this.selectedImage = null;
    },
    resetImage(){
      this.$router.push(this.$route.matched[0].path)
    },
    onSelect(image){
      this.selectedImage = image;
    }
  },
}

</script>

<template>
  <table class="table-auto text-left ml-3">
    <caption class="text-left">
      <button class="bg-red-800 hover:bg-red-500 text-white font-medium mt-2 py-2 px-4 rounded">Export data</button>
      <button class="bg-red-800 hover:bg-red-500 text-white font-medium mt-2 py-2 px-4 rounded">
        <router-link :to="{name: 'createImage'}">Create image</router-link>
      </button>
    </caption>
    <thead class="border-b font-medium dark:border-neutral-500">
    <tr>
      <th scope="col" class="px-6 py-4">#</th>
      <th scope="col" class="px-6 py-4">Article NR</th>
      <th scope="col" class="px-6 py-4">EAN</th>
      <th scope="col" class="px-6 py-4">Brand</th>
      <th scope="col" class="px-6 py-4">Description</th>
      <th scope="col" class="px-6 py-4">Processor</th>
      <th scope="col" class="px-6 py-4">Edit image</th>
    </tr>
    </thead>
    <tbody>
    <tr class="border-b dark:border-neutral-500" v-for="(pcimage, key) in images" :key="pcimage.EAN">
      <td class="whitespace-nowrap px-6 py-4 font-medium">{{ (key+1) }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage.ArticleNR }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage.EAN }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage.PCBrand }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage.Description }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage.PROCESSOR }}</td>
      <td class="whitespace-nowrap px-6 py-4">
        <button>
          <router-link :to="{name:'editImage', params: {ArticleNR: pcimage.ArticleNR}}" @click="onSelect(pcimage)">edit</router-link>
        </button>
      </td>
    </tr>
    </tbody>
  </table>
  <router-view :key="$route.fullPath"/>
</template>

<style scoped>

</style>