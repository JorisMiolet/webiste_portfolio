<template>
    <!-- Overlay / Achtergrond donker maken -->
    <div v-if="popupVisible" class="overlay"></div>

    <!-- De popup -->
    <div v-if="popupVisible" class="popup" @click="closePopup">

      <!-- Inhoud van de popup -->
      <div class="popup-content" @click.stop>

        <!-- Weergave van de 10 waarden uit de JSON-gegevens -->
        <div class="table-div">
          <table class="table-images">
            <tbody>
            <tr v-for="(value, key) in proxyImage" :key="key">
              <td>{{ key }}</td>
              <td>
                <span>{{ value }}</span>
              </td>
            </tr>
            </tbody>
          </table>
        </div>

      </div>
    </div>

</template>



<script>
export default {
  name: 'detail-image',
  props: ['selectedImage'],
  data() {
    return {
      popupVisible: true,
      proxyImage: null,
    };
  },
  methods: {
    loadImage() {
      if (this.selectedImage.laptop === null) {
        // If the laptop property is present, create a new object without it
        // eslint-disable-next-line no-unused-vars
        const { laptop, ...selectedImageWithoutLaptop } = this.selectedImage;
        this.proxyImage = selectedImageWithoutLaptop;
      } else {
        // If laptop is null, use the entire selectedImage
        this.proxyImage = this.selectedImage;
      }

      // console.log("Proxy- detailview", JSON.stringify(this.proxyImage, null, 2));
    },
    closePopup() {
      this.popupVisible = false;
      this.proxyImage = null;

    },
  },
  // computed: {
  //   loadSelectedImage() {
  //     return this.selectedImage;
  //   }
  // },
  mounted() {
      this.loadImage();
  },
}
</script>

<style scoped>

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
