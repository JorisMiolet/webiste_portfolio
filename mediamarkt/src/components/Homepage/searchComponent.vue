<template>

  <div class="flex lg:w-2/5 left-[50%] translate-x-[-50%] w-[80%] lg:mx-auto mt-4 p-4 sm:rounded-full bg-red-500 border border-gray-300 sm:flex-row flex-col fixed items-center ml-auto">
    <input
        v-model="searchQuery"
        type="text"
        class="w-full rounded-full px-4 py-2 focus:outline-none"
        placeholder="Zoeken..."
        @input="handleSearch"
    />
    <div class="customCheckBoxHolder w-[200px] flex justify-center">

      <input type="checkbox" @change="filterByDate" id="cCB1" class="customCheckBoxInput">
      <label for="cCB1" class="customCheckBoxWrapper">
        <div class="customCheckBox">
          <div class="inner">Out Dated</div>
        </div>
      </label>

    </div>
  </div>
</template>

<script>
export default {
  name: "SearchComponent",
  inject: ['url'],
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
.customCheckBoxHolder {
  margin: 5px;
  display: flex;
}

.customCheckBox {
  width: fit-content;
  position: relative;
  overflow: hidden;
  cursor: pointer;
  user-select: none;
  padding: 2px 8px;
  background-color: rgba(0, 0, 0, 0.16);
  border-radius: 0px;
  color: rgba(255, 255, 255, 0.7);
  transition-timing-function: cubic-bezier(0.25, 0.8, 0.25, 1);
  transition-duration: 300ms;
  transition-property: color, background-color, box-shadow;
  display: flex;
  height: 32px;
  align-items: center;
  box-shadow: rgba(0, 0, 0, 0.15) 0px 2px 1px 0px inset, rgba(255, 255, 255, 0.17) 0px 1px 1px 0px;
  outline: none;
  justify-content: center;
  min-width: 55px;
}

.customCheckBox:hover {
  background-color: #2c2c2c;
  color: white;
  box-shadow: rgba(0, 0, 0, 0.23) 0px -4px 1px 0px inset, rgba(255, 255, 255, 0.17) 0px -1px 1px 0px, rgba(0, 0, 0, 0.17) 0px 2px 4px 1px;
}

.customCheckBox .inner {
  font-size: 18px;
  font-weight: 900;
  pointer-events: none;
  transition-timing-function: cubic-bezier(0.25, 0.8, 0.25, 1);
  transition-duration: 300ms;
  transition-property: transform;
  transform: translateY(0px);
}

.customCheckBox:hover .inner {
  transform: translateY(-2px);
}

/* Multiple Checkboxes can be chained together for a radio input */
.customCheckBoxWrapper:first-of-type .customCheckBox {
  border-bottom-left-radius: 5px;
  border-top-left-radius: 5px;
  border-right: 0px;
}

.customCheckBoxWrapper:last-of-type .customCheckBox {
  border-bottom-right-radius: 5px;
  border-top-right-radius: 5px;
  border-left: 0px;
}

.customCheckBoxInput {
  display: none;
}

.customCheckBoxInput:checked + .customCheckBoxWrapper .customCheckBox {
  background-color: #2d6737;
  color: white;
  box-shadow: rgba(0, 0, 0, 0.23) 0px -4px 1px 0px inset, rgba(255, 255, 255, 0.17) 0px -1px 1px 0px, rgba(0, 0, 0, 0.17) 0px 2px 4px 1px;
}

.customCheckBoxInput:checked + .customCheckBoxWrapper .customCheckBox .inner {
  transform: translateY(-2px);
}

.customCheckBoxInput:checked + .customCheckBoxWrapper .customCheckBox:hover {
  background-color: #34723f;
  box-shadow: rgba(0, 0, 0, 0.26) 0px -4px 1px 0px inset, rgba(255, 255, 255, 0.17) 0px -1px 1px 0px, rgba(0, 0, 0, 0.15) 0px 3px 6px 2px;
}

.customCheckBoxWrapper .customCheckBox:hover .inner {
  transform: translateY(-2px);
}
</style>
