<template>
  Menu

  <ul>
    <li><router-link to="/blauw" active-class="myclass"> Blauw</router-link></li>
    <li><router-link to="/rood" active-class="myclass"> Rood</router-link></li>
    <li><router-link to="/counter" active-class="myclass"> Counter</router-link></li>

  </ul>
<router-view></router-view>

</template>

<script>


export default {
  name: 'App',
  components: {

  }
}
</script>

<style>
.myclass{
  color: aqua;
}

</style>
