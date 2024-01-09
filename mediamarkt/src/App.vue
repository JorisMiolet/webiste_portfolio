<template>
  <router-view />

</template>

<script>
import './main.css'
export default {
  name: 'App',
  provide(){
    return {
      url: 'http://127.0.0.1:8085'
    }
  }
}


</script>

<style>
</style>
