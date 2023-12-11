<template>

  <div class="flex w-2/5 left-[50%] translate-x-[-50%] mx-auto mt-4 p-4 rounded-full bg-red-500 border border-gray-300 fixed items-center ml-auto">
    <input
        v-model="searchQuery"
        type="text"
        class="w-full rounded-full px-4 py-2 focus:outline-none"
        placeholder="Zoeken..."
        @input="handleSearch"
    />
    <button @click="filterByDate" class="ml-4 px-4 py-2 rounded-full bg-blue-500 text-white">OUT DATED</button>
  </div>
</template>

<script>
export default {
  name: "SearchComponent",
  data() {
    return {
      searchQuery: "",
    };
  },
  methods: {
    resetSearch() {
      this.searchQuery = "";
    },
    handleSearch() {
      this.$emit("search", this.searchQuery);
    },
    filterByDate() {
      this.$emit("filterByDate");
    },
  },
};
</script>

<style scoped>
/* Voeg eventuele aanvullende stijlen toe met behulp van Tailwind CSS-klassen */
</style>
