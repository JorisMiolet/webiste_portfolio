<template>
  <div>
    <h1>{{ getMessage }}</h1>
    <p v-bind:style=" this.counts.length > 1 ?'visibility: hidden' : ''">klik op de button als je een insect ziet </p>
  </div>

  <div>
    Je hebt {{ this.counts.length }} insecten gezien
  </div>

  <div v-if="counter % 2 ==0"> Je hebt een even aantal insecten gezien</div>
  <div v-else> Je hebt een oneven anatal insecten gezien</div>

  <div v-if="this.counts.length > 0">
    <ul>
      <li v-for=" count in counts" :key="count.date">
        <count-component v-bind:count-item="count"></count-component>
      </li>
    </ul>
  </div>

  <div>
    <form>
      <label for="name">Naam insect</label>
      <input v-model="newCount.name" id="name" type="text">
      <label for="aantal">Aantal</label>
      <input v-model="newCount.quantity" id="aantal" type="number">
    </form>
    <button v-on:click="addInsect()">Add</button>
  </div>
</template>

<script>
import {InsectCount} from "@/models/InsectCount";
import CountComponent from "@/components/CountComponent";

export default {
  name: "InsectCounter",
  components: {CountComponent},
  data() {
    return {
      counts: [],
      newCount: new InsectCount()
    }
  },
  methods: {
    addInsect() {
      this.counter++;
      this.newCount.date = new Date();
      this.counts.push(this.newCount);
      this.newCount = new InsectCount();
    }
  },
  computed: {
    getMessage() {
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