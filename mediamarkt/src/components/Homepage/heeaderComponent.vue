<template>
  <header class="bg-red-500 p-4 fixed top-0 left-0 right-0 z-20">
    <div class="container mx-auto flex justify-between items-center">
      <h1 class="text-white text-2xl font-semibold">Mediamarkt</h1>
      <nav class="space-x-4">
        <router-link to="/" class="text-white hover:underline">Home</router-link>
        <router-link to="/admin/image-overview" class="text-white hover:underline">Admin</router-link>
        <router-link to="/Login" class="text-white hover:underline">Log in</router-link>
      </nav>
    </div>
  </header>
</template>

<script>
export default {
  name: "headerComponent",
};
</script>

<style scoped>

</style>
