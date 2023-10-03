<template>
  <div class="w-3/4 p-4 grid grid-cols-2  ml-auto mr-9 gap-4 ">
    <div v-for="(laptop, index) in laptops" :key="index" class="border border-gray-300 p-4 rounded-lg shadow-md hover:shadow-lg h-[300px]">
      <h2 class="text-lg font-semibold">{{ laptop['Description / Model type'] }}</h2>
      <p class="text-gray-600">{{ laptop.Brand }}</p>
      <p class="text-gray-600">{{ laptop.PROCESSOR }}</p>
      <p class="text-gray-600">{{ laptop.EAN}}</p>
      <p class="text-gray-600">{{ laptop.RAM }}</p>
      <p class="text-gray-600">{{ laptop.STORAGE }}</p>
    </div>
  </div>
</template>

<script>
import dummyData from "@/assets/dummyData/dummyData.json";

export default {
  name: "imageComponent",
  data() {
    return {
      laptops: Object.values(dummyData),
    };
  },
};
</script>

<style scoped>

</style>
