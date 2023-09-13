<template>

hello it is me
  <rood-component/>
  <blauw-component/>

</template>

<script>
import RoodComponent from './components/RoodComponent.vue'
import BlauwComponent  from "@/components/BlauwComponent.vue";

export default {
  name: 'App',
  components: {
    RoodComponent, BlauwComponent
  }
}
</script>

<style>

</style>
