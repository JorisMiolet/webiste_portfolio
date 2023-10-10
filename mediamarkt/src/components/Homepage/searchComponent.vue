<template>

  <div class="relative w-2/5 mx-auto mt-4 p-4 rounded-full bg-red-500 border border-gray-300 flex items-center ml-auto">
    <input
        v-model="searchQuery"
        type="text"
        class="w-full rounded-full px-4 py-2 focus:outline-none"
        placeholder="Zoeken..."
        @input="handleSearch"
    />
    <div class="ml-2 flex items-center space-x-2">
      <button @click="resetSearch" class="focus:outline-none rounded-full bg-white p-2">
        <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-6 w-6"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
        >
          <!-- SVG-pictogram voor de eerste knop -->
        </svg>
      </button>
      <button class="focus:outline-none rounded-full bg-white p-2">
        <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-6 w-6"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
        >
          <!-- SVG-pictogram voor de tweede knop -->
        </svg>
      </button>
    </div>
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
  },
};
</script>

<style scoped>
/* Voeg eventuele aanvullende stijlen toe met behulp van Tailwind CSS-klassen */
</style>
