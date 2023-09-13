<template>
  {{ countItem.quantity + ' ' + countItem.name + ' gezien op ' + countItem.date.toLocaleDateString()}}
</template>

<script>
import {InsectCount} from "@/models/InsectCount";

export default {
  name: "CountComponent",
  props:{
    countItem: InsectCount
  }
}
</script>

<style scoped>

</style>