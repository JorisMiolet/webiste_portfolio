<template>
  <search-component/>
  <sidenav-component/>
  <image-component/>
</template>

<script>
import SidenavComponent from "@/components/Homepage/sidenavComponent.vue";
import SearchComponent from "@/components/Homepage/searchComponent.vue";
import ImageComponent from "@/components/Homepage/imageComponent.vue";

export default {
  name: "welkomComponent",
  components: {ImageComponent, SearchComponent, SidenavComponent}
}
</script>

<style scoped>

</style>