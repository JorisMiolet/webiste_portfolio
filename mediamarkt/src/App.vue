<template>
  <router-view />

</template>

<script>
import './main.css'
export default {
  name: 'App',
  provide(){
    return {
      url: 'https://ewa-back-end-r7ie.onrender.com'
    }
  }
}


</script>

<style>
</style>
