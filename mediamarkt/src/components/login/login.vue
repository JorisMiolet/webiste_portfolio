<template>
<!--  adds header component to the file-->
  <HeaderComponent/>
  <div class="h-[100vh] w-full flex items-center justify-center">
    <img :src="backgroundImage" alt="background_img" class="h-[100vh] z-[-1] absolute top-0 left-0">
    <div class="  w-[500px] h-[500px] shadow-2xl bg-secondary flex flex-col md:ml-[200px]">
      <div class="text-center pt-10">
        <h1 class="text-6xl">Login</h1>
      </div>
      <div class="w-80 mx-auto my-10 flex flex-col justify-between flex-1 relative">
        <input :class="{ 'border-red-500': errorMessage && !emailInput }"
               type="email"
               class="h-16 w-full border-[#D0D0D0] border-2 rounded-xl text-2xl pl-5 bg-input"
               placeholder="Email"
               v-model="emailInput"
        />
        <input :class="{ 'border-red-500': errorMessage && !passwordInput }"
               :type="[showPassword ? 'text' : 'password']"
               class="h-16 w-full border-[#D0D0D0] border-2 rounded-xl text-2xl pl-5 pr-12 sm:pr-16 bg-input"
               placeholder="Wachtwoord"
               v-model="passwordInput"
        />
        <img :src="[showPassword ? showEye : hiddenEye]"
             @click="handleClickEye"
             alt="eye"
             class="w-[40px] absolute right-10 bottom-3 sm:right-5 cursor-pointer"
        />
      </div>
      <div class="pb-10 w-full flex items-center justify-center">
        <button class="text-3xl text-white rounded bg-primary px-[23%] py-5 hover:drop-shadow-lg hover:bg-[#F36261FF]"
                @click="handleButton"
        >
          Log In
        </button>
      </div>
      <div v-if="errorMessage" class="text-red-500 text-center mb-4">{{ errorMessage }}</div>
    </div>
  </div>
</template>

<script>
import bgImage from "../../assets/images/loginbg.png";
import show from "../../assets/images/view.png";
import hide from "../../assets/images/hide.png";
import axios from "axios"
import HeaderComponent from "@/components/Homepage/heeaderComponent";

export default {
  name: "log-in",
  components: {HeaderComponent},
  inject: ['url'],
  data() {
    return {
      backgroundImage: bgImage,
      showEye: show,
      hiddenEye: hide,
      showPassword: false,
      userList: [],
      emailInput: "",
      passwordInput: "",
      currentUser: null,
      errorMessage: null,
    }
  },
  mounted() {
    this.loadUserList();
  },
  methods: {
    handleClickEye() {
      this.showPassword ? this.showPassword = false : this.showPassword = true;
    },
    loadUserList() {
      axios.get(`${this.url}/api/users/all`).then(response => this.userList = response.data)
    },
    setErrorMessage(message) {
      this.errorMessage = message;

      setTimeout(() => {
        this.errorMessage = null;
      }, 3000);
    },
    async handleButton() {
      try {
        if (!this.emailInput || !this.passwordInput) {
          this.setErrorMessage("Vul alle velden in");
          return;
        }

        let response = await fetch(`${this.url}/authentication/login`, {
          method: "POST",
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({email: this.emailInput.toLowerCase(), password: this.passwordInput})
        });

        if (response.ok) {
          let token = response.headers.get("Authorization");
          if (token == null) {
            throw new Error('Token niet gevonden');
          }

          let responsedata = await response.json();
          token = token.replace('Bearer ', '');
          sessionStorage.setItem('token', token);
          sessionStorage.setItem('user_id', responsedata.uuid);
          sessionStorage.setItem('isAdmin', responsedata.admin);
          this.$router.push("/");
        } else {
          // Foutafhandeling voor niet-OK-responsen
          let errorText = await response.text();
          if (response.status === 404) {
            this.setErrorMessage("Email niet gevonden");
          } else if (response.status === 401) {
            this.setErrorMessage("Onjuist wachtwoord");
          } else {
            this.setErrorMessage("Er is een fout opgetreden");
            console.error("Serverfout:", response.status, errorText);
          }
        }
      } catch (error) {
        // Foutafhandeling voor andere soorten fouten
       this.setErrorMessage("Er is een fout opgetreden");
        console.error("An error occurred:", error);
      }
    },


  }
}
</script>

<style scoped>

.bg-input {
  background-color: rgba(208, 208, 208, 0.2);
}

.bg-input:focus {
  background-color: transparent;
}



</style>
