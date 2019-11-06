<template>
  <default-layout>
    <v-expansion-panel expand>
      <v-expansion-panel-content v-for="(type, index, i) in controllers" :key="i">
        <div slot="header">{{type.model}}</div>
        <v-card>
          <list :data="getController(index)" />
        </v-card>
      </v-expansion-panel-content>
      <v-expansion-panel-content v-for="(type, index) in displays" :key="index">
        <div slot="header">{{type.model}}</div>
        <v-card>
          <list :data="getDisplay(index)" />
        </v-card>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </default-layout>
</template>

<script>
import List from "../components/List";
export default {
  components: {
    List
  },
  computed: {
    controllers() {
      try {
        return this.$store.state.data.graphics.controllers;
      } catch (e) {
        return [];
      }
    },
    displays() {
      try {
        return this.$store.state.data.graphics.displays;
      } catch (e) {
        return [];
      }
    }
  },
  methods: {
    getController(pos) {
      try {
        return [
          {
            name: "Model",
            value: this.$store.state.data.graphics.controllers[pos].model,
            img: `static/graphics/controllers/model.svg`
          },
          {
            name: "Vendor",
            value: this.$store.state.data.graphics.controllers[pos].vendor,
            img: `static/graphics/controllers/vendor.svg`
          },
          {
            name: "VRAM",
            value: this.$store.state.data.graphics.controllers[pos].vram,
            img: `static/graphics/controllers/vram.svg`,
            unit: "MB"
          },
          {
            name: "VRAM Dynamic",
            value: this.$store.state.data.graphics.controllers[pos].vramDynamic,
            img: `static/graphics/controllers/vramdynamic.svg`
          },
          {
            name: "Bus",
            value: this.$store.state.data.graphics.controllers[pos].bus,
            img: `static/graphics/controllers/bus.svg`
          }
        ];
      } catch (e) {
        console.log(e);
        return [];
      }
    },
    getDisplay(pos) {
      try {
        return [
          {
            name: "Model",
            value: this.$store.state.data.graphics.displays[pos].model,
            img: `static/graphics/displays/model.svg`
          },
          {
            name: "Main",
            value: this.$store.state.data.graphics.displays[pos].main,
            img: `static/graphics/displays/main.svg`
          },
          {
            name: "Resolution X",
            value: this.$store.state.data.graphics.displays[pos].resolutionx,
            img: `static/graphics/displays/resolutionx.svg`
          },
          {
            name: "Resolution Y",
            value: this.$store.state.data.graphics.displays[pos].resolutiony,
            img: `static/graphics/displays/resolutiony.svg`
          },
          {
            name: "Pixel Depth",
            value: this.$store.state.data.graphics.displays[pos].pixeldepth,
            img: `static/graphics/displays/pixeldepth.svg`
          }
        ];
      } catch (e) {
        console.log(e);
        return [];
      }
    }
  }
};
</script>

<style scoped>
</style>