<template>
  <search-component @search="filterLaptops" @filterByDate="filterLaptopsByDate"/>
  <table class="table-auto text-left mx-auto mt-40 overflow-y-scroll hidden md:table">
    <thead class="border-b font-medium dark:border-neutral-500">
    <tr>
      <th class="whitespace-nowrap px-6 py-4" scope="col" @click="sortTable('id')"
          :class="{ 'sorted-asc': sortColumn === 'id' && sortOrder === 'asc', 'sorted-desc': sortColumn === 'id' && sortOrder === 'desc' }">
        #
      </th>
      <th class="whitespace-nowrap px-6 py-4" scope="col" @click="sortTable('Article NR')"
          :class="{ 'sorted-asc': sortColumn === 'Article NR' && sortOrder === 'asc', 'sorted-desc': sortColumn === 'Article NR' && sortOrder === 'desc' }">
        Article NR
      </th>
      <th class="whitespace-nowrap px-6 py-4" scope="col" @click="sortTable('EAN')"
          :class="{ 'sorted-asc': sortColumn === 'EAN' && sortOrder === 'asc', 'sorted-desc': sortColumn === 'EAN' && sortOrder === 'desc' }">
        EAN
      </th>
      <th class="whitespace-nowrap px-6 py-4" scope="col" @click="sortTable('Brand')"
          :class="{ 'sorted-asc': sortColumn === 'Brand' && sortOrder === 'asc', 'sorted-desc': sortColumn === 'Brand' && sortOrder === 'desc' }">
        Brand
      </th>
      <th class="whitespace-nowrap px-6 py-4" scope="col" @click="sortTable('Description / Model type')"
          :class="{ 'sorted-asc': sortColumn === 'Description / Model type' && sortOrder === 'asc', 'sorted-desc': sortColumn === 'Description / Model type' && sortOrder === 'desc' }">
        Description
      </th>
      <th class="whitespace-nowrap px-6 py-4" scope="col" @click="sortTable('PROCESSOR')"
          :class="{ 'sorted-asc': sortColumn === 'PROCESSOR' && sortOrder === 'asc', 'sorted-desc': sortColumn === 'PROCESSOR' && sortOrder === 'desc' }">
        Processor
      </th>
      <th class="whitespace-nowrap px-6 py-4" scope="col" @click="sortTable('STATUS')"
          :class="{ 'sorted-asc': sortColumn === 'STATUS' && sortOrder === 'asc', 'sorted-desc': sortColumn === 'STATUS' && sortOrder === 'desc' }">
        Status
      </th>
    </tr>
    </thead>
    <tbody>
    <tr class="border-b dark:border-neutral-500" v-for="(pcimage) in laptops" :key="pcimage.EAN"
        @click="setSelectedImage(pcimage)">
      <td class="whitespace-nowrap px-6 py-4 font-medium">{{ pcimage["id"] }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage["Article NR"] }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage["EAN"] }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage["Brand"] }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage["Description / Model type"] }}</td>
      <td class="whitespace-nowrap px-6 py-4">{{ pcimage["PROCESSOR"] }}</td>
      <td class="whitespace-nowrap px-6 py-4">
        <div :class="getStatusClasses(pcimage['STATUS'])">{{ pcimage["STATUS"] }}</div>
      </td>
    </tr>
    </tbody>
  </table>
  <detail-image v-if="selectedImageInfo !== null"
                v-bind:selectedImage="selectedImageInfo"
                v-on:resetImage="resetImage()"
                v-on:refreshTable="loadUserList"/>
</template>

<script>
import detailImage from "@/components/Homepage/detail-image.vue";
import axios from "axios";
import searchComponent from "@/components/Homepage/searchComponent.vue";

export default {
  name: "imageComponent",
  inject: ['url'],
  components: {
    detailImage,
    searchComponent,
  },
  props: ["laptopInfo", "buttonClicked"],
  data() {
    return {
      laptops: [],
      originalLaptops: [],
      selectedImageInfo: null,
      active: false,
      sortColumn: null,
      sortOrder: 'asc'
    };
  },
  mounted() {
    this.loadUserList();
  },
  watch: {
    laptopInfo: {
      immediate: true,
      handler(newVal) {
        // Handle the updated laptopInfo
        if (newVal) {
          this.laptops = [newVal];
        }
      },
    },
  },
  methods: {
    setSelectedImage(image) {
      this.selectedImageInfo = image;
    },

    resetImage() {
      this.selectedImageInfo = null;
    },
    
    //loads all laptops
    loadUserList() {
      axios.get(`${this.url}/api/images/all`)
          .then((response) => {
            this.laptops = response.data;
            this.originalLaptops = response.data;
          })
    },
    updateLaptops(laptops) {

      return this.laptops = laptops;
    },
    filterLaptopsByDate() {
      if (this.active === false) {
        const threeMonthsAgo = new Date();
        threeMonthsAgo.setMonth(threeMonthsAgo.getMonth() - 3);


        this.laptops = this.originalLaptops.filter((laptop) => {
          const laptopDate = new Date(laptop['DATE']);
          this.active = true
          return laptopDate < threeMonthsAgo;
        });

        if (this.laptops.length === 0) {
          this.laptops = [];
        }
      } else {
        this.laptops = this.originalLaptops
        this.active = false
      }
    },

    filterLaptops(searchQuery) {
      if (searchQuery.trim() === "") {
        this.loadUserList(); // Reset to the original list if the search query is empty
      } else {
        //checks if searchQuery matches a laptop
        const pattern = new RegExp(`^${searchQuery}.*$`, 'i');
        this.laptops = this.originalLaptops.filter((laptop) => {
          return pattern.test(laptop['Description / Model type']) || pattern.test(laptop['Brand']) || pattern.test(laptop['EAN']);
        });

        // If no laptops match the search query, set this.laptops to an empty array
        if (this.laptops.length === 0) {
          this.laptops = [];
        }
      }
    }, getStatusClasses(status) {
      switch (status.toLowerCase()) {
        case 'in progress':
          return 'status-pill in-progress';
        case 'completed':
          return 'status-pill completed';
          case 'outdated':
            return 'status-pill out-of-date';
        case 'created':
          return 'status-pill created';
        default:
          return 'status-pill';
      }
    },
    // Functie om de tabel te sorteren op basis van de kolomnaam
    sortTable(column) {
      if (this.sortColumn === column) {
        this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
      } else {
        this.sortOrder = 'desc';
      }

      // Bewaar de geselecteerde kolom
      this.sortColumn = column;

      // Sorteer de laptops op basis van de geselecteerde kolom
      this.laptops.sort((a, b) => {
        const compareString = function (a, b) {
          // Vergelijk de namen
          var nameComparison = a.localeCompare(b);
          // Als de namen gelijk zijn, vergelijk dan de cijfers
          if (nameComparison === 0) {
            // var numA = parseInt(a.match(/\d+/)[0]);
            // var numB = parseInt(b.match(/\d+/)[0]);
            return nameComparison;
            // return numA - numB;
          } else {
            return nameComparison;
          }
        };

        // Sorteer oplopend of aflopend op basis van de sorteervolgorde
        const order = this.sortOrder === 'asc' ? 1 : -1;
        if (column === 'Article NR' || column === 'Brand' || column === 'Description / Model type' ||
            column === 'PROCESSOR' || column === 'STATUS') {
          return compareString(a[column], b[column]) * order;
        } else if (column === 'id' || column === 'EAN') {
          return (a[column] - b[column]) * order;
        }
      });
    },
  },
}
</script>

<style scoped>
body, html {
  overflow-y: hidden;
}

.form {
  max-height: 90vh;
}

html, body {
  margin: 0;
  height: 100%;
  overflow: hidden
}

/*Sorteer functie tabel*/
th {
  cursor: pointer;
  user-select: none;
}

th::after {
  display: inline-block;
  margin-left: 4px;
}

th.sorted-asc::after {
  content: ' \25b4';
}

th.sorted-desc::after {
  content: ' \25be';
}

/*Style for status pills*/
.status-pill {
  display: inline-block;
  padding: 6px 12px;
  border-radius: 20px;
  font-size: 14px;
}

.in-progress {
  background-color: #ffcc00; /* Yellow */
  color: #333;
}

.completed {
  background-color: #00cc66; /* Green */
  color: #fff;
}

.out-of-date {
  background-color: #ff6666; /* Red */
  color: #fff;
}

.created {
  background-color: #3399ff; /* Blue */
  color: #fff;
}

</style>

