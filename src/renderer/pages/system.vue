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
      return [
        {
          name: 'Baseboard Manufacturer',
          value: this.$store.state.data.baseboard.manufacturer,
          img: 'static/system/baseboard/manufacturer.svg'
        },
        {
          name: 'Baseboard Model',
          value: this.$store.state.data.baseboard.model,
          img: 'static/system/baseboard/model.svg'
        },
        {
          name: 'Baseboard Serial',
          value: this.$store.state.data.baseboard.serial,
          img: 'static/system/baseboard/serial.svg'
        },
        {
          name: 'Baseboard Version',
          value: this.$store.state.data.baseboard.version,
          img: 'static/system/baseboard/version.svg'
        },
        {
          name: 'Bios',
          value: this.$store.state.data.bios.vendor,
          img: 'static/system/bios/vendor.svg'
        },
        {
          name: 'System Manufacturer',
          value: this.$store.state.data.system.manufacturer,
          img: 'static/system/system/manufacturer.svg'
        },
        {
          name: 'System Serial',
          value: this.$store.state.data.system.serial,
          img: 'static/system/system/serial.svg'
        },
        {
          name: 'System Model',
          value: this.$store.state.data.system.model,
          img: 'static/system/system/model.svg'
        },
        {
          name: 'System UUID',
          value: this.$store.state.data.system.uuid,
          img: 'static/system/system/uuid.svg'
        }
      ]
    }
  }
}
</script>

<style scoped>
</style>