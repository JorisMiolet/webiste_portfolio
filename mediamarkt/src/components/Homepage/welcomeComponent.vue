<template>
  <header-component/>
  <search-component/>
  <sidenav-component/>
  <image-component/>
</template>

<script>
import SidenavComponent from "@/components/Homepage/sidenavComponent.vue";
import SearchComponent from "@/components/Homepage/searchComponent.vue";
import ImageComponent from "@/components/Homepage/imageComponent.vue";
import HeaderComponent from "@/components/Homepage/heeaderComponent.vue";

export default {
  name: "welkomComponent",
  components: {HeaderComponent, ImageComponent, SearchComponent, SidenavComponent}
}
</script>

<style scoped>

</style>