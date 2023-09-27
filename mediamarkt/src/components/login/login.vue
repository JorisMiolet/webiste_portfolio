<template>
<div class="h-[100vh] w-full flex items-center justify-center">
  <img :src="backgroundImage" alt="background_img" class="h-[100vh] z-[-1] absolute top-0 left-0">
  <div class="w-[500px] h-[500px] shadow-2xl bg-secondary flex flex-col ml-[200px]">
    <div class="text-center pt-10">
      <h1 class="text-6xl">Login</h1>
    </div>
    <div class="w-80 mx-auto my-10 flex flex-col justify-between flex-1">
      <input type="text"
             class="h-16 w-full border-[#D0D0D0] border-2 rounded-xl text-2xl pl-5 bg-input"
             placeholder="Naam"
      />
      <input type="password"
             class="h-16 w-full border-[#D0D0D0] border-2 rounded-xl text-2xl pl-5 bg-input"
             placeholder="Wachtwoord"
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
export default {
  name: "log-in",
  data() {
    return {
      backgroundImage: bgImage,
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

</style>
