<template>
  <div> rood component

  </div>

  <router-view></router-view>
</template>

<script>
export default {
  name: "RoodComponent",
  data(){
    return {
      title :"ik eb een title!"
    }
  }

}
</script>

<style scoped>
div {
  border: solid red; padding: 10px; margin: 10px;
}

</style>