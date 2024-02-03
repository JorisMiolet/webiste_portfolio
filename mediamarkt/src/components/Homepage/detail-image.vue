<template>
  <!-- Overlay / Achtergrond donker maken -->
  <div v-if="popupVisible" class="overlay"></div>

  <!-- De popup -->
  <div v-if="popupVisible" class="popup" @click="closePopup">

    <!-- Inhoud van de popup -->
    <div class="popup-content mx-5 h-[90%] sm:h-full flex flex-col items-center" @click.stop>

      <!-- Weergave van de 10 waarden uit de JSON-gegevens -->
        <table class="table-image-details">
          <tbody>
          <tr v-for="(value, key) in filteredProxyImage" :key="key">
            <td>{{ key }}</td>
            <td>
              <span v-if="key === 'user'">
              {{ value && value.username !== null ? value.username : 'Niet toegewezen' }}
              </span>
              <span v-else>{{ value }}</span>
            </td>
          </tr>
          </tbody>
        </table>

      <span @click="markDone(filteredProxyImage)" v-if="checkIfImageBelongsToUser(filteredProxyImage)"
            class="inline-block bg-green-500 hover:bg-green-400 text-white px-4 py-2 rounded transition-colors duration-300 cursor-pointer"
            :style="{ marginTop: '1vw' }">
                  <button>Mark as completed</button>
      </span>

      <span @click="pickup(filteredProxyImage)" v-if="checkIfLoggedIn && filteredProxyImage['STATUS'] !== 'completed' && filteredProxyImage['STATUS'] !== 'in progress' && filteredProxyImage.username !== null"
            class="inline-block bg-orange-500 hover:bg-orange-400 text-white px-4 py-2 rounded transition-colors duration-300 cursor-pointer"
            :style="{ marginTop: '1vw' }">
                  <button>Pickup image</button>
      </span>


    </div>
  </div>
</template>


<script>
import axios from "axios";

export default {
  name: 'detail-image',
  props: ['selectedImage'],
  emits: ['resetImage','refreshTable'],
  inject: ['url'],
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
        const {laptop, ...selectedImageWithoutLaptop} = this.selectedImage;
        this.proxyImage = selectedImageWithoutLaptop;
      } else {
        // If laptop is null, use the entire selectedImage
        this.proxyImage = this.selectedImage;
      }
    },
    closePopup() {
      this.popupVisible = false;
      this.proxyImage = null;
      this.$emit('resetImage');

    },
    markDone(image){
      const urlWithQuery = `${this.url}/api/images/done`;
      const data = {
        "article_nr": image["Article NR"]
      }
      axios.post(urlWithQuery, data)
          .then(() => {
            this.$emit('refreshTable');
            this.closePopup();
          })
          .catch((error) => {
            console.error('Error picking up image:', error);
          });

    },
    pickup(image) {
      const urlWithQuery = `${this.url}/api/images/pickup`;
      const data = {
        "article_nr": image["Article NR"],
        "user_id": sessionStorage.getItem('user_id')
      }
      axios.post(urlWithQuery, data)
          .then(() => {
            this.$emit('refreshTable');
            this.closePopup();
          })
          .catch((error) => {
            console.error('Error picking up image:', error);
          });
    },


  },
  computed: {
    filteredProxyImage() {
      // Check if this.proxyImage exists before filtering
      if (!this.proxyImage) {
        return {};
      }

      // Filter the proxyImage object to exclude the 'laptop' key
      return Object.keys(this.proxyImage)
          .filter(key => key !== 'laptop')
          .reduce((acc, key) => {
            acc[key] = this.proxyImage[key];
            return acc;
          }, {});
    },
    checkIfLoggedIn(){
      const loggedInUserId = sessionStorage.getItem('user_id');

      return loggedInUserId !== null;
    },
    checkIfImageBelongsToUser(){
      return (image) => {
        const loggedInUserId = sessionStorage.getItem('user_id');
        let imageId = 0;
        if(image.user != null){
          imageId = image.user.id
        }
        // return false
        return imageId == loggedInUserId && (image.STATUS !== 'completed' || image.STATUS !== 'in progress');
      };
    },
  },
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
  overflow: auto;
  justify-content: center;
  border: 5px solid #FE4A49;
  min-width: 60vw;
  min-height: 40vh;
}

.table-image-details {
  flex-grow: 1;
  width: 100%;
}

.table-image-details input {
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
}

table td {
  padding: 8px;
  text-align: center;

}
table td:first-child{
  border-bottom-left-radius: 10px;
  border-top-left-radius: 10px;
}
table td:last-child{
  border-bottom-right-radius: 10px;
  border-top-right-radius: 10px;
}

table tbody tr:nth-child(odd) {
  background-color: #f2f2f2;
}

table tbody tr td:first-child {
  font-weight: bold;
  font-size: 0.8rem;
}

</style>
