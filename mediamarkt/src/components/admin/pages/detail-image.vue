<template>
    <!-- Knop om de popup te openen -->

    <!-- Overlay / Achtergrond donker maken -->
    <div v-if="popupVisible" class="overlay"></div>

    <!-- De popup -->
    <div v-if="popupVisible" class="popup" @click="closePopup">

      <!-- Inhoud van de popup -->
      <div class="popup-content" @click.stop>

        <div class="image-div">
          <div
              id="carouselExampleIndicators"
              class="relative w-3/5  "
              data-te-carousel-init
              data-te-ride="carousel">
            <!--Carousel indicators-->
            <div
                class="absolute bottom-0 left-0 right-0 z-[2] mx-[15%] mb-4 flex list-none justify-center p-0"
                data-te-carousel-indicators>
              <button
                  type="button"
                  data-te-target="#carouselExampleIndicators"
                  data-te-slide-to="0"
                  data-te-carousel-active
                  class="mx-[3px] box-content h-[3px] w-[30px] flex-initial cursor-pointer border-0 border-y-[10px] border-solid border-transparent bg-white bg-clip-padding p-0 -indent-[999px] opacity-50 transition-opacity duration-[600ms] ease-[cubic-bezier(0.25,0.1,0.25,1.0)] motion-reduce:transition-none"
                  aria-current="true"
                  aria-label="Slide 1"></button>
              <button
                  type="button"
                  data-te-target="#carouselExampleIndicators"
                  data-te-slide-to="1"
                  class="mx-[3px] box-content h-[3px] w-[30px] flex-initial cursor-pointer border-0 border-y-[10px] border-solid border-transparent bg-white bg-clip-padding p-0 -indent-[999px] opacity-50 transition-opacity duration-[600ms] ease-[cubic-bezier(0.25,0.1,0.25,1.0)] motion-reduce:transition-none"
                  aria-label="Slide 2"></button>
              <button
                  type="button"
                  data-te-target="#carouselExampleIndicators"
                  data-te-slide-to="2"
                  class="mx-[3px] box-content h-[3px] w-[30px] flex-initial cursor-pointer border-0 border-y-[10px] border-solid border-transparent bg-white bg-clip-padding p-0 -indent-[999px] opacity-50 transition-opacity duration-[600ms] ease-[cubic-bezier(0.25,0.1,0.25,1.0)] motion-reduce:transition-none"
                  aria-label="Slide 3"></button>
            </div>

            <!--Carousel items-->
            <div
                class="relative w-full overflow-hidden after:clear-both after:block after:content-[''] ">
              <!--First item-->
              <div
                  class="relative float-left -mr-[100%] w-full transition-transform duration-[600ms] ease-in-out motion-reduce:transition-none"
                  data-te-carousel-item
                  data-te-carousel-active>
                <img
                    src="https://mdbcdn.b-cdn.net/img/new/slides/041.webp"
                    class="block w-full"
                    alt="Wild Landscape"/>
              </div>
              <!--Second item-->
              <div
                  class="relative float-left -mr-[100%] hidden w-full transition-transform duration-[600ms] ease-in-out motion-reduce:transition-none"
                  data-te-carousel-item>
                <img
                    src="https://mdbcdn.b-cdn.net/img/new/slides/042.webp"
                    class="block w-full"
                    alt="Camera"/>
              </div>
              <!--Third item-->
              <div
                  class="relative float-left -mr-[100%] hidden w-full transition-transform duration-[600ms] ease-in-out motion-reduce:transition-none"
                  data-te-carousel-item>
                <img
                    src="https://mdbcdn.b-cdn.net/img/new/slides/043.webp"
                    class="block w-full"
                    alt="Exotic Fruits"/>
              </div>
            </div>

            <!--Carousel controls - prev item-->
            <button
                class="absolute bottom-0 left-0 top-0 z-[1] flex w-[15%] items-center justify-center border-0 bg-none p-0 text-center text-white opacity-50 transition-opacity duration-150 ease-[cubic-bezier(0.25,0.1,0.25,1.0)] hover:text-white hover:no-underline hover:opacity-90 hover:outline-none focus:text-white focus:no-underline focus:opacity-90 focus:outline-none motion-reduce:transition-none"
                type="button"
                data-te-target="#carouselExampleIndicators"
                data-te-slide="prev">
                    <span class="inline-block h-8 w-8">
                      <svg
                          xmlns="http://www.w3.org/2000/svg"
                          fill="none"
                          viewBox="0 0 24 24"
                          stroke-width="1.5"
                          stroke="currentColor"
                          class="h-6 w-6">
                        <path
                            stroke-linecap="round"
                            stroke-linejoin="round"
                            d="M15.75 19.5L8.25 12l7.5-7.5"/>
                      </svg>
                    </span>
                    <span class="!absolute !-m-px !h-px !w-px !overflow-hidden !whitespace-nowrap !border-0 !p-0 ![clip:rect(0,0,0,0)]">Previous</span>
            </button>

            <!--Carousel controls - next item-->
            <button
                class="absolute bottom-0 right-0 top-0 z-[1] flex w-[15%] items-center justify-center border-0 bg-none p-0 text-center text-white opacity-50 transition-opacity duration-150 ease-[cubic-bezier(0.25,0.1,0.25,1.0)] hover:text-white hover:no-underline hover:opacity-90 hover:outline-none focus:text-white focus:no-underline focus:opacity-90 focus:outline-none motion-reduce:transition-none"
                type="button"
                data-te-target="#carouselExampleIndicators"
                data-te-slide="next">
                    <span class="inline-block h-8 w-8">
                      <svg
                          xmlns="http://www.w3.org/2000/svg"
                          fill="none"
                          viewBox="0 0 24 24"
                          stroke-width="1.5"
                          stroke="currentColor"
                          class="h-6 w-6">
                        <path
                            stroke-linecap="round"
                            stroke-linejoin="round"
                            d="M8.25 4.5l7.5 7.5-7.5 7.5"/>
                      </svg>
                    </span>
                    <span class="!absolute !-m-px !h-px !w-px !overflow-hidden !whitespace-nowrap !border-0 !p-0 ![clip:rect(0,0,0,0)]">Next</span>
            </button>

          </div>
        </div>

        <!-- Weergave van de 10 waarden uit de JSON-gegevens -->
        <div class="table-div">
          <table class="table-images">
            <tbody>
            <tr v-for="(item, index) in getLaptopData()" :key="index">
              <td>{{ item.key }}</td>
              <td>
                <input v-model="item.value" class="transparent-input"/>
              </td>
            </tr>
            </tbody>
          </table>
        </div>

      </div>
    </div>

</template>

<!--<script>-->
<!--import jsonData from '@/assets/dummyData/dummyData.json';-->
<!--// import {onMounted} from "vue";-->
<!--// import {Carousel, initTE} from "tw-elements";-->
<!--//-->
<!--//-->
<!--// onMounted(() => {-->
<!--//   console.log("asd");-->
<!--//   initTE({Carousel});-->
<!--// });-->

<!--export default {-->
<!--  name: 'detail-image',-->
<!--  props: ['selectedImage'],-->
<!--  data() {-->
<!--    return {-->
<!--      popupVisible: true,-->
<!--      selectedImageInfo: null,-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    getLaptopData() {-->
<!--      const laptopData = jsonData['laptop1'];-->
<!--      const formattedData = [];-->

<!--      for (const key in laptopData) {-->
<!--        formattedData.push({key, value: laptopData[key]});-->
<!--      }-->

<!--      return formattedData;-->
<!--    },-->
<!--    openPopup() {-->
<!--      this.popupVisible = true;-->
<!--    },-->
<!--    closePopup() {-->
<!--      this.popupVisible = false;-->
<!--      this.selectedImageInfo = null;-->
<!--    },-->
<!--  },-->
<!--  computed:{-->
<!--    loadSelectedImage(){-->
<!--      return this.selectedImage;-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->


<script>
import jsonData from "./dummyData.json";
import { onMounted } from "vue";
import { Carousel, initTE } from "tw-elements";

export default {
  name: 'detail-image',
  props: ['selectedImage'],
  data() {
    return {
      popupVisible: true,
      selectedImageInfo: null,
    };
  },
  methods: {
    getLaptopData() {
      const laptopData = jsonData['laptop1'];
      const formattedData = [];

      for (const key in laptopData) {
        formattedData.push({ key, value: laptopData[key] });
      }

      return formattedData;
    },
    openPopup() {
      this.popupVisible = true;
    },
    closePopup() {
      this.popupVisible = false;
      this.selectedImageInfo = null;
    },
  },
  computed: {
    loadSelectedImage() {
      return this.selectedImage;
    }
  },
  setup() {
// Initialize the Carousel when the component is mounted
    onMounted(() => {
      initTE({ Carousel });
    });
  }
}
</script>


<!--<script>-->
<!--import jsonData from '@/assets/dummyData/dummyData.json';-->
<!--import { onMounted, ref } from "vue";-->
<!--import { Carousel, initTE } from "tw-elements";-->

<!--export default {-->
<!--  name: 'detail-image',-->
<!--  setup() {-->
<!--    const popupVisible = ref(false);-->
<!--    const selectedImageInfo = ref(null);-->

<!--    const getLaptopData = () => {-->
<!--      const laptopData = jsonData['laptop1'];-->
<!--      const formattedData = [];-->

<!--      for (const key in laptopData) {-->
<!--        formattedData.push({ key, value: laptopData[key] });-->
<!--      }-->

<!--      return formattedData;-->
<!--    };-->

<!--    const openPopup = () => {-->
<!--      popupVisible.value = true;-->
<!--    };-->

<!--    const closePopup = () => {-->
<!--      popupVisible.value = false;-->
<!--      selectedImageInfo.value = null;-->
<!--    };-->

<!--    onMounted(() => {-->
<!--      initTE({ Carousel });-->
<!--    });-->

<!--    return {-->
<!--      popupVisible,-->
<!--      selectedImageInfo,-->
<!--      getLaptopData,-->
<!--      openPopup,-->
<!--      closePopup,-->
<!--    };-->
<!--  },-->
<!--};-->
<!--</script>-->


<style scoped>

.image-div {
  display: flex;
  justify-content: center;
  align-items: center;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 998;
  pointer-events: none;
}

#carouselExampleIndicators {
  z-index: 999;
}

.popup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;

}

.popup-content {
  background-color: #fff;
  padding: 2vw;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  width: 60vw;
  height: 80vh;
  overflow: auto;
  justify-content: center;
  border: 5px solid #FE4A49;
}


.table-div {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  margin-top: 2vw;
  width: 100%;

}

.table-images {
  flex-grow: 1;
  width: 100%;

}

.table-images input {
  width: 100%;
  box-sizing: border-box;
  border: none;
  outline: none;
  text-align: center;
  vertical-align: middle;
  background-color: transparent;
}


table {
  border-collapse: collapse;
  /*border: 2px solid #FE4A49;*/
  border-radius: 100px;
}

table th,
table td {
  padding: 8px;
  border: 1px solid #ddd;
  text-align: center;
}

/* Kleur kop items tabel */
table th {
  background-color: #f2f2f2;
}

/* Kleur overige rijen tabel */
table tbody tr:nth-child(odd) {
  background-color: #f2f2f2;
}

table tbody tr td:first-child {
  font-weight: bold;
  font-size: 0.8rem;
}

</style>
