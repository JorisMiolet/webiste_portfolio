<template>
<div class="h-[100vh] w-full flex items-center justify-center">
  <img :src="backgroundImage" alt="background_img" class="h-[100vh] z-[-1] absolute top-0 left-0">
  <div class="login-small w-[500px] h-[500px] shadow-2xl bg-secondary flex flex-col ml-[200px]">
    <div class="text-center pt-10">
      <h1 class="text-6xl">Login</h1>
    </div>
    <div class="w-80 mx-auto my-10 flex flex-col justify-between flex-1 relative">
      <input type="text"
             class="h-16 w-full border-[#D0D0D0] border-2 rounded-xl text-2xl pl-5 bg-input"
             placeholder="Naam"
      />
      <input :type="[showPassword ? 'text' : 'password']"
             class="h-16 w-full border-[#D0D0D0] border-2 rounded-xl text-2xl pl-5 pr-12 sm:pr-16 bg-input"
             placeholder="Wachtwoord"
      />
      <img :src="[showPassword ? showEye : hiddenEye]"
           @click="handleClickEye"
           alt="eye"
           class="w-[40px] absolute right-10 bottom-3 sm:right-5 cursor-pointer"
      />
    </div>
    <div class="pb-10 w-full flex items-center justify-center">
        <button class="text-3xl text-white rounded bg-primary px-[23%] py-5 hover:drop-shadow-lg hover:bg-[#F36261FF]">Log In</button>
    </div>
  </div>
</div>
</template>

<script>
import bgImage from "../../assets/images/loginbg.png";
import show from "../../assets/images/view.png";
import hide from "../../assets/images/hide.png";
export default {
  name: "log-in",
  data() {
    return {
      backgroundImage: bgImage,
      showEye: show,
      hiddenEye: hide,
      showPassword: false,
    }
  },
  methods: {
    handleClickEye() {
      this.showPassword ? this.showPassword = false : this.showPassword = true;
    }
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

@media (max-width: 768px){
  .login-small{
    width: 300px;
    margin: 0
  }
}


</style>
