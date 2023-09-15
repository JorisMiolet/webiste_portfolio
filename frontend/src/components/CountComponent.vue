<template>

  <div> {{ countItem.quantity + ' ' + countItem.name + ' gezien op ' + countItem.date.toLocaleDateString() }}
  <button @click="$emit('delete-count', countItem.id)">Delete</button>
  </div>
</template>

<script>
import {InsectCount} from "@/models/InsectCount";

export default {
  name: "CountComponent",
  props: {
    countItem: {
      type: InsectCount,
      required: true
    }, name: String
  },
  emits: ["delete-count"]

}
</script>

<style scoped>

</style>