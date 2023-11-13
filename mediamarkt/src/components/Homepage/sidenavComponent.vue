<template>
  <div class="bg-red-500 fixed sidenav pt-10">
      <div class="mb-3 mt-12 flex">
        <label for="update">OUTDATED</label>
        <input type="checkbox" class="ml-1 mr-1" id="update">
    </div>

        <div class="flex items-center">
          <label for="EAN">EAN</label>
          <input type="text" id="EAN" v-model="searchquery" @input="handleSearch" class="ml-1 mr-1">
      </div>
  </div>
</template>

<script>

export default {
  name: "sidenavComponent",
  data() {
    return {
      searchquery: "",
    };
  },
  methods: {
    handleSearch() {
        this.$emit("search", this.searchquery);
      }
    },
}
</script>

<style scoped>
.sidenav {
  height: 100%;
  overflow-y: scroll;
  z-index: 1;
  top: 0;
  left: 0;
  width: 15%;
}
#EAN{
  width: 70%;
}
.checkboxes {
  margin-left: 10px;
}
</style>