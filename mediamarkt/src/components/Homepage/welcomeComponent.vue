<template>
  <header-component @scan-clicked="setClicked"/>
  <div class="container flex flex-row mt-20">
    <div class="z-[999]">
      <BarcodeComponent :button-is-clicked="buttonClicked" @barcodeDecoded="handleBarcodeDecoded"/>
    </div>
    <div class="flex-col content mr-auto">
<!--      <search-component/>-->
      <ImageComponent :laptop-info="laptopInfo"/>
    </div>
  </div>
</template>

<script>
import ImageComponent from "@/components/Homepage/imageComponent.vue";
import HeaderComponent from "@/components/Homepage/heeaderComponent.vue";
import BarcodeComponent from "@/components/Homepage/barcodeComponent.vue";
export default {
  name: "welkomComponent",
  components: {BarcodeComponent, HeaderComponent, ImageComponent},
  data() {
    return{
      buttonClicked: false,
      laptopInfo: null,
    }
  },
  methods: {
    setClicked(){
      this.buttonClicked = !this.buttonClicked;
      console.log(this.buttonClicked)
    },
    handleBarcodeDecoded(laptopInfo) {
      // Update the laptopInfo directly
      this.laptopInfo = laptopInfo;
      console.log(this.laptopInfo)
    },
  },
  created() {
  }
}
</script>

<style scoped>
.content{
  flex-grow: 1;
  align-content: center;
}
</style>
