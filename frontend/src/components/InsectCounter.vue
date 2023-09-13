<template>
  <div>
    <h1>{{getMessage()}}</h1>
    <p v-bind:style=" counter > 1 ?'visibility: hidden' : ''">klik op de button als je een insect ziet </p>
  </div>

  <div>
    Je hebt {{ counter }} insecten gezien
  </div>

  <div v-if="counter % 2 ==0"> Je hebt een even aantal insecten gezien</div>
  <div v-else> Je hebt een oneven anatal insecten gezien</div>

  <div v-if="this.counts.length > 0">
    <ol>
      <li v-for=" count in counts" :key="count.date"> {{ count.date }}</li>
    </ol>
  </div>

  <div>
    <button v-on:click="addInsect()">Click</button>
  </div>
</template>

<script>
import {InsectCount} from "@/models/InsectCount";

export default {
  name: "InsectCounter",
  data() {
    return {
      counter: 1,
      counts: []
    }
  },
  methods: {
    addInsect() {
      this.counter++;
      const count = new InsectCount();
      count.date = new Date();
      this.counts.push(count);
    },
    getMessage(){
      console.log("Zet welkom message")
      return "Welkom bij de insect counter";
    }
  }
}
</script>

<style scoped>
div {
  border: solid black;
  padding: 10px;
  margin: 10px;
}

</style>