<template>
  <div class="relative w-2/5 mx-auto mt-4 p-4 rounded-full bg-red-500 border border-gray-300 flex items-center">
    <input
        v-model="searchQuery"
        type="text"
        class="w-full rounded-full px-4 py-2 focus:outline-none"
        placeholder="Zoeken..."
    />
    <div class="ml-2 flex items-center space-x-2">
      <button  @click="resetSearch" class="focus:outline-none rounded-full bg-white p-2">
        <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-6 w-6"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
        >
          <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M15 11a4 4 0 11-8 0 4 4 0 018 0z"
          />
          <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M17 21v-2a2 2 0 00-2-2H4a2 2 0 00-2 2v2"
          />
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
          <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M12 6v6m0 0v6m0-6h6m-6 0H6"
          />
        </svg>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "searchComponent",
  data() {
    return {
      searchQuery: "",
      filteredLaptops: [],
    };
  },
  methods:{
    resetSearch() {
      this.searchQuery = "";
      this.filteredLaptops = [];
    }

  },
};
</script>

<style scoped>

</style>
