<template>
  <button @click="setClicked" class="bg-primary py-3 px-10 rounded-2xl text-white m-10">
    Camera
  </button>
  <template v-if="buttonClicked">
         <StreamBarcodeReader @decode="onDecode" @loaded="onLoaded"></StreamBarcodeReader>
  </template>
</template>

<script>
import { StreamBarcodeReader } from "vue-barcode-reader";
import axios from "axios";

export default {
  name: "barcodeComponent",
  components: {
    StreamBarcodeReader,
  },
  data() {
    return {
      laptopInfo: [],
      buttonClicked: false
    };
  },
  methods: {
    setClicked(){
      this.buttonClicked ? this.buttonClicked = false : this.buttonClicked = true;
    },
    onDecode(result) {
      console.log(result);

      // Assuming the barcode contains a unique identifier for your laptops
      const barcode = result;

      // Make an API call to fetch laptop information based on the scanned barcode
      axios.get(`http://localhost:8085/api/images/barcode/${barcode}`)
          .then(response => {
            // Update the component's data with the fetched laptop information
            this.laptopInfo = response.data;

            // Emit the laptop information to the parent component (imageComponent)
            this.$emit("updateLaptops", [this.laptopInfo]); // Send the laptop information as an array


          })
          .catch(error => {
            console.error('Error fetching laptop information:', error);
          });
      this.buttonClicked = false;
    },
    onLoaded() {
      console.log('Barcode reader loaded and camera ready');
    },
    onError(error) {
      console.error('Error scanning barcode:', error);
    },
  },
};
</script>

<style scoped>
/* Add your custom styles here */
</style>
