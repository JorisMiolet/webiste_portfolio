<template>
  <div>
    <header-component @scan-clicked="setClicked" />
    <div class="container flex flex-row mt-20">
      <div class="z-[999]">
        <BarcodeComponent
            :button-is-clicked="buttonClicked"
            @barcodeDecoded="handleBarcodeDecoded"
        />
      </div>
      <div class="flex-col content mr-auto">
        <ImageComponent :laptop-info="laptopInfo"/>
      </div>
    </div>
    <div v-if="buttonClicked" @click="closeBarcode" class="backdrop"></div>
  </div>
</template>

<script>
import ImageComponent from "@/components/Homepage/imageComponent.vue";
import HeaderComponent from "@/components/Homepage/heeaderComponent.vue";
import BarcodeComponent from "@/components/Homepage/barcodeComponent.vue";
export default {
  name: "welkomComponent",
  components: { BarcodeComponent, HeaderComponent, ImageComponent },
  data() {
    return {
      buttonClicked: false,
      laptopInfo: null,
    };
  },
  methods: {
    setClicked() {
      this.buttonClicked = !this.buttonClicked;
      console.log(this.buttonClicked);
    },
    handleBarcodeDecoded(laptopInfo) {
      this.laptopInfo = laptopInfo;
      console.log(this.laptopInfo);
      this.buttonClicked = false;
    },
    closeBarcode() {
      this.buttonClicked = false;
    },
  },
  created() {},
};
</script>

<style scoped>
.content {
  flex-grow: 1;
  align-content: center;
}

.backdrop {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Adjust the opacity as needed */
  z-index: 998; /* Make it higher than the barcode component */
}
</style>

