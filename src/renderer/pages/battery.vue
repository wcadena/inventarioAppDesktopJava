<template>
  <default-layout>
    <list :data="data" />
  </default-layout>
</template>

<script>
import List from '../components/List'

export default {
  components: {
    List
  },
  computed: {
    data () {
      try {
        return [
          {
            name: 'Is Charging',
            value: this.$store.state.data.battery.ischarging,
            img: `static/battery/ischarging.svg`
          },
          {
            name: 'Max Capacity',
            value: this.$store.state.data.battery.maxcapacity,
            img: `static/battery/maxcapacity.svg`
          },
          {
            name: 'Current Capacity',
            value: this.$store.state.data.battery.currentcapacity,
            img: `static/battery/currentcapacity.svg`
          },
          {
            name: 'Percent',
            value: this.$store.state.data.battery.percent,
            img: `static/battery/percent.svg`
          }
        ]
      } catch (e) {
        return []
      }
    }
  }
}
</script>

<style scoped>
</style>