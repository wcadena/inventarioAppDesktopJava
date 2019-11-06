<template>
  <v-list two-line>
    <template v-for="item in data">
      <v-divider></v-divider>
      <v-list-tile avatar>
        <v-list-tile-avatar v-if="item.img">
          <img :src="item.img" />
        </v-list-tile-avatar>
        <v-list-tile-content>
          <v-list-tile-title>{{item.name}}</v-list-tile-title>
          <v-list-tile-sub-title>{{item.value}} {{(item.unit) ? item.unit : ''}}</v-list-tile-sub-title>
        </v-list-tile-content>
      </v-list-tile>
    </template>
  </v-list>
</template>

<script>
export default {
  props: ["data"]
};
</script>

<style scoped>
</style>