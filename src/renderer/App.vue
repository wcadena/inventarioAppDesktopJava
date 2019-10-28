<template>
  <div id="app">
    <div v-if="isLoading">
      <router-view></router-view>
    </div>
    <div v-else>
      <v-app>
        <v-progress-linear v-bind:indeterminate="true"></v-progress-linear>
      </v-app>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      isLoading: false
    }
  },
  async created () {
    await this.$store.dispatch('GET_DATA')
    this.isLoading = true
  }
}
</script>

<style>
/* CSS */
</style>
