<template>
  <div class="absolute top-[60%] left-1/2 transform -translate-x-1/2 -translate-y-1/2 flex justify-center flex-col">
    <template v-if="buttonClicked">
      <StreamBarcodeReader class="w-[500px] border-[50px]  border-primary rounded-2xl"
                           @decode="onDecode"
                           @loaded="onLoaded"></StreamBarcodeReader>
    </template>


  </div>
</template>

<script>
import { StreamBarcodeReader } from "vue-barcode-reader";
import axios from "axios";

export default {
  name: "barcodeComponent",
  props: ['buttonIsClicked'],
  inject: ['url'],
  components: {
    StreamBarcodeReader,
  },
  data() {
    return {
      laptopInfo: [],
      buttonClicked: this.buttonIsClicked,
    };
  },
  computed: {
    updateButtonClicked: {
      get() {
        return this.buttonIsClicked;
      },
      set(value) {
        if (value !== this.buttonIsClicked) {
          this.$emit("update:buttonIsClicked", value);
        }
      },
    },
  },
  watch: {
    buttonIsClicked: {
      immediate: true,
      handler(newVal) {
        this.buttonClicked = newVal;
      },
    },
    buttonClicked: {
      immediate: true,
      handler(newVal) {
        if (newVal) {
          // Perform any actions needed when buttonClicked becomes true
        }
      },
    },
  },
  methods: {
    onDecode(result) {


      // Assuming the barcode contains a unique identifier for your laptops
      const barcode = result;

      // Make an API call to fetch laptop information based on the scanned barcode
      axios.get(this.url + `/api/images/barcode/${barcode}`)
          .then(response => {
            // Update the component's data with the fetched laptop information
            this.laptopInfo = response.data;

            // Emit the laptop information to the parent component (imageComponent)
            this.$emit("barcodeDecoded", this.laptopInfo); // Send the laptop information as an



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

<style>
.slide{
  border: 3px solid #ce3938;
}
.slide:hover{
  box-shadow: 2px 2px 5px #000000;
}
.slide:active{
  box-shadow: none;
}

</style>
