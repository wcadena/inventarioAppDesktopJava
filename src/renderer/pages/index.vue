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
  // created() {
  //     this.$store.dispatch('SET_TITLE', 'YOUR TITLE')
  // },
  computed: {
    data() {
      try {
        return [
          {
            name: "OS",
            value: this.$store.state.data.os.distro,
            img: `static/os/platform/${this.$store.state.data.os.platform.toLowerCase()}.svg`
          },
          {
            name: "Baseboard",
            value: this.$store.state.data.baseboard.manufacturer,
            img: `static/system/baseboard/manufacturer.svg`
          },
          {
            name: "CPU",
            value: this.$store.state.data.cpu.brand,
            img: `static/cpu/cpu.svg`
          },
          {
            name: "RAM",
            value: Math.round(this.$store.state.data.mem.total / 1000000),
            img: `static/ram/total.svg`,
            unit: "MB"
          },
          {
            name: "Battery",
            value: this.$store.state.data.battery.hasBattery
              ? "Has battery"
              : "Hasnt battery",
            img: `static/battery/ischarging.svg`
          }
        ];
      } catch (e) {
        return [];
      }
    }
  }
};
</script>

<style scoped>
</style>