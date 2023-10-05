<template>
  Menu
  <ul>
    <li @click="goTo('/blauw')"> Blauw</li>
    <li><router-link to="/rood" active-class="myclass"> Rood</router-link></li>
    <li><router-link to="/counter" active-class="myclass"> Counter</router-link></li>
  </ul>
<router-view></router-view>

</template>

<script>


export default {
  name: 'App',
  components: {
  }, methods: {

    goTo(page){
      this.$router.push(page);
    }

  }
}
</script>

<style>
.myclass{
  color: aqua;
}

</style>
