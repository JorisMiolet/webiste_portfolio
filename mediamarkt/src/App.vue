<template>
  <router-view />

</template>

<script>
import './main.css'
export default {
  name: 'App',
  provide(){
    return {
      url: 'http://localhost:8085'
    }
  }
}


</script>

<style>
</style>
