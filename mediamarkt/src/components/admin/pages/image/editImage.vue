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
        console.log(this.image);
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
  <div class="p-6 flex h-screen">
    <div class="mb-6 mx-auto">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-between mb-4 items-start">
          <div class="font-medium">Create image</div>
        </div>
        <form action="" class="flex items-center flex-col">
          <div class="relative w-full mb-2">
            <div class="flex mt-2">
              <input type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 mr-2" placeholder="Article Nr">
              <input type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500" placeholder="Ean">
            </div>
            <input type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Brand">
            <input type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Description">
            <input type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Processor">
            <div class="flex mt-2">
              <input type="text" class="flex-grow mr-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Ram">
              <input type="text" class="flex-grow py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Storage">
            </div>
            <div class="flex mt-2">
              <input type="text" class="flex-grow mr-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Gpu">
              <input type="text" class="flex-grow py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Screen size (CM)">
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


  <div>
    <template v-if="dataLoaded">
      <form class="w-full max-w-lg">
        <div class="flex flex-wrap mb-6 mt-10 ml-1">
          <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="ArticleNR">
              Article NR
            </label>
            <input v-model="image['Article NR']" disabled class="disabled:opacity-60 appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="ArticleNR" type="text" placeholder="article nummer">
          </div>
          <div class="w-full px-3 md:w-1/2">
            <label for="Brand" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
              Brand
            </label>
            <input v-model="image.EAN" type="text" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" placeholder="Brand" id="Brand">
          </div>
        </div>
        <div class="flex flex-wrap mb-6 mt-5 ml-1">
          <div class="w-full px-3">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="EAN">
              EAN
            </label>
            <input v-model="image.Brand" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="EAN" type="text" placeholder="EAN">
          </div>
        </div>
        <div class="flex flex-wrap mb-6 mt-10 ml-1">
          <div class="w-full px-3">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="Description">
              Description / Model Type
            </label>
            <input v-model="image['Description / Model type']" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="Description" type="text" placeholder="Description / Model type">
          </div>
        </div>
        <div class="flex flex-wrap mb-6 mt-10 ml-1">
          <div class="w-full px-3">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="Processor">
              Processor
            </label>
            <input v-model="image.PROCESSOR" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="Processor" type="text" placeholder="Processor">
          </div>
        </div>
        <div class="flex flex-wrap mb-6 mt-10 ml-1">
          <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="RAM">
              RAM
            </label>
            <input v-model="image.RAM" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="RAM" type="text" placeholder="RAM">
          </div>
          <div class="w-full px-3 md:w-1/2">
            <label for="Storage" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
              Storage
            </label>
            <input v-model="image.STORAGE" type="text" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" placeholder="Storage" id="Storage">
          </div>
        </div>
        <div class="flex flex-wrap mb-6 mt-10 ml-1">
          <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="GPU">
              GPU
            </label>
            <input v-model="image.GPU" class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="GPU" type="text" placeholder="GPU">
          </div>
          <div class="w-full px-3 md:w-1/2">
            <label for="ScreenSize" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
              Screen size (CM)
            </label>
            <input v-model="image['SCREENSIZE (CM)']" type="text" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" placeholder="Screen size (CM)" id="ScreenSize">
          </div>
          <div class="w-full px-3 md:w-1/2">
            <label for="ScreenSize" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
              Screen size (inch)
            </label>
            <input v-model="image['SCREENSIZE (inch)']" type="text" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" placeholder="Screen size (CM)" id="ScreenSize">
          </div>
        </div>
        <div class="md:flex md:items-center">
          <div class="md:w-1/3"></div>
          <div class="md:w-2/3 mb-2">
            <button @click="save" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
                Save
              </button>
            <button @click="deleteImage" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">delete</button>
            <button @click="resetImage" class="shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">reset</button>
          </div>
        </div>
      </form>

    </template>
    <p v-else>Loading...</p>
  </div>

</template>

<style scoped>

</style>