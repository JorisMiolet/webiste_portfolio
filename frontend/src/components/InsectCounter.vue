<template>
  <section v-bind:style="'background: ' + color">
  <div>
    <h1>{{ getMessage }}</h1>
  </div>
  <div>
    Je hebt {{ this.counts.length }} insecten gezien
  </div>
  <div  >
    Show in
    <input v-model="color">
    </div>

  <div v-if="this.counts.length > 0">
    <ul>
      <li v-for="count in counts" :key="count.date">
        <count-component v-bind:count-item="count" v-on:delete-count="deleteCount(count.id)"></count-component>
      </li>
    </ul>
  </div>

  <div>
    <form>
      <label for="aantal">Aantal</label>
      <input v-model="newCount.quantity" id="aantal" type="number">
      <label for="name">Naam insect</label>
      <input v-model="newCount.name" id="name" type="text">
    </form>
    <button v-on:click="addInsect()">Voeg toe</button>
  </div>

  </section>
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
      newCount: new InsectCount(),
      color: 'green'
    }
  },
  methods: {
    addInsect() {
      this.newCount.id = this.counts.length;
      this.newCount.date = new Date();
      this.counts.push(this.newCount);
      this.newCount = new InsectCount();
    },

    deleteCount(id){
     this.counts.splice(id, 1);
    }
  },
  computed: {
    getMessage() {
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
 input{
  border: solid;
  padding: 5px;
  margin: 5px;
}

</style>