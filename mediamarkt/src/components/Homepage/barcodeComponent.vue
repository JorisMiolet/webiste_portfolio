<template>
  <div>
    <StreamBarcodeReader @decode="onDecode" @loaded="onLoaded"></StreamBarcodeReader>
  </div>
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
      laptopInfo: null,
    };
  },
  methods: {
    onDecode(result) {
      console.log(result);

      // Assuming the barcode contains a unique identifier for your laptops
      const barcode = result.text;

      // Make an API call to fetch laptop information based on the scanned barcode
      axios.get(`http://localhost:8085/images/${barcode}`)
          .then(response => {
            // Update the component's data with the fetched laptop information
            this.laptopInfo = response.data;
          })
          .catch(error => {
            console.error('Error fetching laptop information:', error);
          });
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
