<template>
  <default-layout>
    <v-btn @click="$router.push('')"></v-btn>
    <v-btn @click="w"></v-btn>
  </default-layout>
</template>

<script>
export default {
  methods: {
    w () {
      console.log(this.$route)
    }
  }
}
</script>

<style scoped>
</style>