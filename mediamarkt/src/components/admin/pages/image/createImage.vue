<script>
import axios from "axios";

export default {
  name: 'createImage',
  inject: ['url'],
  data() {
    return {
      articleNumber: '',
      brand: '',
      ean: '',
      description: '',
      processor: '',
      ram: '',
      storage: '',
      gpu: '',
      screenSizeCm: '',
      status: 'in progress', // Set a default value for status
      barcode: '',
      errorMessage: null,
    };
  },
  methods: {
    cancel(){
      const confirmReset = confirm(`are you sure you want to cancel`);
      if(confirmReset){
        this.$router.go(-1)
      }
    },
    save() {
      // Check if all fields are filled
      if (!this.articleNumber || !this.brand || !this.ean || !this.description || !this.processor || !this.ram || !this.storage || !this.gpu || !this.screenSizeCm || !this.barcode) {
        this.errorMessage = "Please fill in all fields.";
        return;
      }

      // Clear any previous error message
      this.errorMessage = null;

      const newImage = {
        "Article NR": this.articleNumber,
        "EAN": this.ean,
        "Brand": this.brand,
        "Description / Model type": this.description,
        "PROCESSOR": this.processor,
        "RAM": this.ram,
        "STORAGE": this.storage,
        "GPU": this.gpu,
        "SCREENSIZE (inch)": 'this.screenSizeInch', // Check this property, it seems incorrect
        "SCREENSIZE (cm)": this.screenSizeCm,
        "BARCODE": this.barcode,
        "DATE": new Date(),
        "STATUS": this.status,
      };

      console.log(newImage)

      axios.post(this.url + '/api/images/create-image', newImage)
          .then((data) => {
            console.log(data)
            alert('Image created successfully');
            this.$router.go(-1);
          })
          .catch(error => {
            console.error('Error creating image:', error);
            // Handle the error appropriately, e.g., show an error message to the user
          });
    },

    clear() {
      this.articleNumber = '';
      this.brand = '';
      this.ean = '';
      this.description = '';
      this.processor = '';
      this.ram = '';
      this.storage = '';
      this.gpu = '';
      this.screenSizeCm = '';
      this.status = 'in progress';
      this.barcode = '';
    },
  },
};
</script>

<template>
  <div class="p-6 flex h-screen">
    <div class="mb-6 mx-auto">
      <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md">
        <div class="flex justify-between mb-4 items-start">
          <div class="font-medium">Create image</div>
        </div>
        <div class="flex items-center flex-col">
          <div class="relative w-full mb-2">
            <div class="flex mt-2">
              <input v-model="articleNumber" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500 mr-2" placeholder="Article Nr">
              <input v-model="ean" type="text" class="flex-grow text-sm py-2 pl-4 pr-10 bg-gray-50 border border-gray-100 rounded-md focus:border-blue-500" placeholder="Ean">
            </div>
            <input v-model="brand" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Brand">
            <input v-model="description" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Description">
            <input v-model="processor" type="text" class="mt-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Processor">
            <div class="flex mt-2">
              <input v-model="ram" type="text" class="flex-grow mr-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Ram">
              <input v-model="storage" type="text" class="flex-grow py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Storage">
            </div>
            <div class="flex mt-2">
              <input v-model="gpu" type="text" class="flex-grow mr-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Gpu">
              <input v-model="screenSizeCm" type="text" class="flex-grow py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Screen size (CM)">
            </div>
            <div class="flex mt-2">
              <select v-model="status"  class="block appearance-none w-full bg-gray-50 border border-gray-100 text-sm py-2 pl-4 pr-10 rounded-md focus:border-blue-500">
                <option value="completed">completed</option>
                <option selected value="in progress">in progress</option>
                <option selected value="started">started</option>
              </select>
              <input v-model="barcode" type="text" class="flex-grow mr-2 py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500" placeholder="Barcode">
            </div>
          </div>
          <div class="flex justify-between">
            <button @click="save" class="mr-3 shadow bg-red-800 hover:bg-red-500 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Create image
            </button>
            <button @click="clear" class="mr-3 shadow bg-gray-500 hover:bg-gray-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              Clear
            </button>
            <button @click="cancel" class="mr-3 shadow bg-gray-500 hover:bg-gray-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded" type="button">
              cancel
            </button>
            <div v-if="errorMessage" class="text-red-500 mt-2">{{ errorMessage }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>