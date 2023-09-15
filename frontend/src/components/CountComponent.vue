<template>

  <div> {{ countItem.quantity + ' ' + countItem.name + ' gezien op ' + countItem.date.toLocaleDateString() }}</div>
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
  }
}
</script>

<style scoped>

</style>