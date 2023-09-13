<template>
  <div>
  <h1>Welkom bij de insect counter </h1>
  <p v-bind:style=" counter > 1 ?'visibility: hidden' : ''">klik op de button als je een insect ziet </p>
  </div>

  <div>
    Je hebt {{counter}} insecten gezien
  </div>

  <div v-if="counter % 2 ==0"> Je hebt een evenaantal insecten gezien</div>
  <div v-else> Je hebt een oneven anatal insecten gezien</div>

  <div>
    <button v-on:click="addInsect()">Click</button>
  </div>
</template>

<script>
export default {
  name: "InsectCounter",
  data(){
    return {
      counter : 1
    }
  },
  methods:{
      addInsect(){
        this.counter++;
      }
  }
}
</script>

<style scoped>
div {
  border: solid black; padding: 10px; margin: 10px;
}


</style>