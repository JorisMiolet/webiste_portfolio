<template>
  <div class="flex flex-col md:flex-row items-center mx-10 mt-10 bg-gray-200 p-4 rounded-md">
    <div class="w-full md:w-[80%] flex items-center relative mb-2 md:mb-0 md:mr-2">
      <input
          v-model="searchQuery"
          type="text"
          class="py-2 pr-4 pl-10 bg-gray-50 w-full outline-none border border-gray-100 rounded-md text-sm focus:border-blue-500"
          placeholder="Zoeken..."
          @input="handleSearch"
      />
    </div>
    <div class="w-full md:w-auto flex items-center md:ml-2">
      <button @click="filterByDate(); toggleButton()"  :class="buttonClasses">
        Outdated
      </button>
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
      isActive: false
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
    toggleButton() {
      this.isActive = !this.isActive;
    }
  },
  computed: {
    buttonClasses() {
      return {
        'bg-green-500 hover:bg-green-400 text-white font-bold py-2 px-4 border-b-4 border-green-700 hover:border-green-500 rounded': this.isActive,
        'bg-gray-500 hover:bg-gray-400 text-white font-bold py-2 px-4 border-b-4 border-gray-700 hover:border-gray-500 rounded': !this.isActive
      };
    }
  }
};
</script>

<style scoped>
#myButton:active {
  transform: translateY(2px);
  border-bottom: 0;
  margin-bottom: 4px;
}


/* Voeg eventuele aanvullende stijlen toe met behulp van Tailwind CSS-klassen */
.customCheckBoxHolder {
  margin: 5px;
  display: flex;
}

.customCheckBox {
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
