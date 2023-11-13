<template>
  <header-component/>
  <div class="container flex flex-row mt-16">
    <div>
      <sidenav-component/>
    </div>
    <div class="flex-col content mr-auto">
<!--      <search-component/>-->
      <image-component/>
    </div>
  </div>
</template>

<script>
import ImageComponent from "@/components/Homepage/imageComponent.vue";
import HeaderComponent from "@/components/Homepage/heeaderComponent.vue";
import barcodeComponent from "@/components/Homepage/barcodeComponent.vue";
export default {
  name: "welkomComponent",
  components: {HeaderComponent, ImageComponent}
}
</script>

<style scoped>
.content{
  flex-grow: 1;
  align-content: center;
}
</style>
