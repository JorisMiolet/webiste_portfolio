<script>
  export default {
    name:"admin-overview",
    components: {},
    data(){
      return {

      }
    }
  }
</script>

<template>

  <div class="wrapper">
    <aside>
      <nav class="bg-red-800 text-white w-44 h-full min-h-screen">
        <ul class="space-y-3">
          <li class="p-3">
            <router-link :to="{name: 'imageOverview'}" exact>image overview</router-link>
          </li>
          <li class="p-3">
            <router-link :to="{name: 'userOverview'}" exact>user overview</router-link>
          </li>
          <li class="p-3">
          </li>
        </ul>
      </nav>
    </aside>
    <main>
      <router-view></router-view>
    </main>
  </div>

</template>

<style scoped>
    .wrapper {
      display: grid;
      grid-template-columns: 1fr 6fr;
    }
</style>