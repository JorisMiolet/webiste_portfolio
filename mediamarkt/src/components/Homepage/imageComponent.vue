<template>
  <div class="w-2/4  h-64 p-4  flex flex-col items-end ml-auto mr-9">
    <div v-for="(laptop, index) in laptops" :key="index" class="mb-8 border border-gray-300 p-4 rounded-lg shadow-md hover:shadow-lg w-4/5 ">
      <h2 class="text-lg font-semibold">{{ laptop['Description / Model type'] }}</h2>
      <p class="text-gray-600">{{ laptop.Brand }}</p>
      <p class="text-gray-600">{{ laptop.PROCESSOR }}</p>

    </div>
  </div>
</template>

<script>
import dummyData from "@/assets/dummyData/dummyData.json";

export default {
  name: "imageComponent",
  data() {
    return {
      laptops: Object.values(dummyData), // Converteer de objecten uit de JSON naar een array
    };
  },
};
</script>

<style scoped>
/* Voeg eventuele aanvullende stijlen toe met behulp van Tailwind CSS-klassen */
</style>
