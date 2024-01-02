<script>
import axios from "axios";

  export default {
    name: 'editImage',
    inject: ['url'],
    data() {
      return {
        image: null,
        dataLoaded: false,
      }
    },
    created() {
      this.loadImage();
    },
    methods: {
      deleteImage(){
        const confirmDelete = confirm(`are you sure you want to delete image ${this.$route.params.ArticleNR}`);
        if(confirmDelete){
          axios.delete(this.url + `/api/images/${this.$route.params.ArticleNR}`)
              .then(this.$router.go(-1));
        }
      },
      resetImage(){
        const confirmReset = confirm(`are you sure you want to reset the image`);
        if(confirmReset){
          this.loadImage();
        }
      },
      save(){
        axios.put(this.url + `/api/images/edit/${this.$route.params.ArticleNR}`, this.image)
            .then(() => {
              alert("Image saved");
              this.$router.go(-1);
            })
            .catch(error => {
              console.error("Error saving image:", error);
              // Handle the error appropriately, e.g., show an error message to the user
            });
      },
      loadImage(){
        axios.get(this.url + `/api/images/${this.$route.params.ArticleNR}`)
            .then(response => {
              this.image = response.data;
              this.dataLoaded = true;
            });
      }
    }
  }

</script>

<template>
  <div v-if="dataLoaded" class="p-6 flex h-screen">
    <div class="mb-6 mx-auto">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-between mb-4 items-start">
          <div class="font-medium">Edit image</div>
        </div>
        <form action="" class="flex items-center flex-col">
          <div class="relative w-full mb-2">
            <div class="flex mt-2">
              <input v-model="image['Article NR']" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 mr-2" placeholder="Article Nr">
              <input v-model="image.EAN" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500" placeholder="Ean">
            </div>
            <input v-model="image.Brand" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Brand">
            <input v-model="image['Description / Model type']" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Description">
            <input v-model="image.PROCESSOR" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Processor">
            <div class="flex mt-2">
              <input v-model="image.RAM" type="text" class="flex-grow mr-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Ram">
              <input v-model="image.STORAGE" type="text" class="flex-grow py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Storage">
            </div>
            <div class="flex mt-2">
              <input v-model="image.GPU" type="text" class="flex-grow mr-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Gpu">
              <input v-model="image['SCREENSIZE (cm)']" type="text" class="flex-grow py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Screen size (CM)">
            </div>
          </div>
          <div class="flex justify-between">
            <button @click="save" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Save
            </button>
            <button @click="deleteImage" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">delete</button>
            <button @click="resetImage" class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">reset</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>