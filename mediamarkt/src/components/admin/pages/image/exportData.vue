<script>
import imageData from '../dummyData.json';
import {PCImage} from "@/image.js";
import axios from "axios";
import * as XLSX from "xlsx";
export default {
  name: 'exportData',
  components: {},
  data() {
    return {
      images: [],
      dummyData: imageData,
      selectedImage: null,
      url: "https://ewa-back-end-r7ie.onrender.com",
      // url: process.env.VUE_APP_API_URL,
    }
  },
  created() {
    this.loadAllImages();
    console.log(this.images)
  }, methods: {
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
    deleteImage() {
      this.images = this.images.filter((image) => image === this.selectedImage);
      this.selectedImage = null;
    },
    resetImage() {
      this.$router.push(this.$route.matched[0].path)
    },
    onSelect(image) {
      this.selectedImage = image;
    },
    loadAllImages() {
      console.log(this.url + '/api/images/all')
      axios.get(this.url + '/api/images/all')
          .then(response => this.images = response.data)
          .then(response => console.log(response));
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

  }
}

</script>

<template>
  <table class="table-auto text-left ml-3">
    <caption class="text-left">
      <button class="bg-red-800 mr-2 hover:bg-red-500 text-white font-medium mt-2 py-2 px-4 rounded" @click="exportToExcel">
        Export data
      </button>
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
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["Article NR"] }}</td>
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["EAN"] }}</td>
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["Brand"] }}</td>
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["Description / Model type"] }}</td>
        <td class="whitespace-nowrap px-6 py-4">{{ pcimage["PROCESSOR"] }}</td>
        <td class="whitespace-nowrap px-6 py-4">
          <button>
            <router-link :to="{name:'editImage', params: {ArticleNR: pcimage['Article NR']}}" @click="onSelect(pcimage)">edit</router-link>
          </button>
        </td>
      </tr>
    </tbody>
  </table>
  <router-view :key="$route.fullPath"/>
</template>

<style scoped>

</style>
