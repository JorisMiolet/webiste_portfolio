<script>
  export default {
  }
</script>

<template>
  <table class="table-auto text-left">
    <caption>
      User Overview
      <button>
        <router-link :to="{name: 'createUser'}">create user</router-link>
      </button>
    </caption>
    <thead class="border-b font-medium dark:border-neutral-500">
      <tr>
        <th scope="col" class="px-6 py-4">#</th>
        <th scope="col" class="px-6 py-4">name</th>
        <th scope="col" class="px-6 py-4">email</th>
        <th scope="col" class="px-6 py-4">edit</th>
      </tr>
    </thead>
    <tbody>
      <tr class="border-b dark:border-neutral-500">
        <td class="whitespace-nowrap px-6 py-4 font-medium">1</td>
        <td class="whitespace-nowrap px-6 py-4">Max van Gorp</td>
        <td class="whitespace-nowrap px-6 py-4">maxvangorp1001@gmail.com</td>
        <td class="whitespace-nowrap px-6 py-4">
          <button class="bg-red-500 hover:bg-red-800 py-2 px-4 rounded text-white">
            <router-link :to="{name: 'editUser', params: {id: '1'}}" exact>edit</router-link>
          </button>
          <button>delete</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>

</style>