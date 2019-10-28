<template>
  <default-layout>
    <v-expansion-panel expand>
      <v-expansion-panel-content
        v-for="(position, index) in $store.state.data.diskLayout.length"
        :key="index"
      >
        <div slot="header">{{$store.state.data.diskLayout[index].name}}</div>
        <v-card>
          <list :data="getDiskData(index)" />
        </v-card>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </default-layout>
</template>

<script>
import List from '../components/List'

export default {
  components: {
    List
  },
  methods: {
    getDiskData (num) {
      try {
        return [
          {
            name: 'Name',
            value: this.$store.state.data.diskLayout[num].name,
            img: `static/disk/name.svg`
          },
          {
            name: 'Type',
            value: this.$store.state.data.diskLayout[num].type,
            img: `static/disk/type.svg`
          },
          {
            name: 'Firmware Revision',
            value: this.$store.state.data.diskLayout[num].firmwareRevision,
            img: `static/disk/firmwarerevision.svg`
          },
          {
            name: 'Serial Num',
            value: this.$store.state.data.diskLayout[num].serialNum,
            img: `static/disk/serialnum.svg`
          },
          {
            name: 'Size',
            value: Math.round(
              this.$store.state.data.diskLayout[num].size / 1000000000
            ),
            img: `static/disk/size.svg`,
            unit: 'GB'
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