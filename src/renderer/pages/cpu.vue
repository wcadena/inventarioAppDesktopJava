<template>
  <default-layout>
    <list :data="data" />
  </default-layout>
</template>

<script>
import List from "../components/List";
export default {
  components: {
    List
  },
  computed: {
    data() {
      return [
        {
          name: "Brand",
          value: this.$store.state.data.cpu.brand,
          img: `static/cpu/cpu.svg`
        },
        {
          name: "Cores",
          value: this.$store.state.data.cpu.cores,
          img: `static/cpu/core.svg`
        },
        {
          name: "Manufacturer",
          value: this.$store.state.data.cpu.manufacturer,
          img: `static/cpu/manufacturer.svg`
        },
        {
          name: "Model",
          value: this.$store.state.data.cpu.model,
          img: `static/cpu/model.svg`
        },
        {
          name: "Revision",
          value: this.$store.state.data.cpu.revision,
          img: `static/cpu/revision.svg`
        },
        {
          name: "Speed",
          value: this.$store.state.data.cpu.speed,
          img: `static/cpu/speed.svg`
        },
        {
          name: "Speed Min",
          value: this.$store.state.data.cpu.speedmin,
          img: `static/cpu/speedmin.svg`
        },
        {
          name: "Speed Max",
          value: this.$store.state.data.cpu.speedmax,
          img: `static/cpu/speedmax.svg`
        }
      ];
    }
  }
};
</script>

<style scoped>
</style>