<template>
  <default-layout>
    <div>
      <v-list two-line>
        <template v-for="(item, i) in data">
          <v-divider :key="i"></v-divider>
          <v-list-tile :key="item.name" avatar>
            <v-list-tile-avatar v-if="item.img">
              <img :src="item.img" />
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>{{item.name}}</v-list-tile-title>
              <v-list-tile-sub-title>{{item.value}} {{(item.unit) ? item.unit : ''}}</v-list-tile-sub-title>
            </v-list-tile-content>
          </v-list-tile>
        </template>
        <template>
          <v-expansion-panel expand>
            <v-expansion-panel-content
              v-for="(position, index) in $store.state.data.memLayout.length"
              :key="index"
            >
              <div slot="header">Memory {{position}}</div>
              <v-card>
                <list :data="getMemory(index)" />
              </v-card>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </template>
      </v-list>
    </div>
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
          name: "Total",
          value: Math.round(this.$store.state.data.mem.total / 1000000),
          img: `static/ram/total.svg`,
          unit: "MB"
        },
        {
          name: "Active",
          value: Math.round(this.$store.state.data.mem.active / 1000000),
          img: `static/ram/active.svg`,
          unit: "MB"
        },
        {
          name: "Free",
          value: Math.round(this.$store.state.data.mem.free / 1000000),
          img: `static/ram/free.svg`,
          unit: "MB"
        },
        {
          name: "Available",
          value: Math.round(this.$store.state.data.mem.available / 1000000),
          img: `static/ram/available.svg`,
          unit: "MB"
        },
        {
          name: "Swap Total",
          value: Math.round(this.$store.state.data.mem.swaptotal / 1000000),
          img: `static/ram/swaptotal.svg`,
          unit: "MB"
        },
        {
          name: "Swap Used",
          value: Math.round(this.$store.state.data.mem.swapused / 1000000),
          img: `static/ram/swapused.svg`,
          unit: "MB"
        },
        {
          name: "Swap Free",
          value: Math.round(this.$store.state.data.mem.swapfree / 1000000),
          img: `static/ram/swapfree.svg`,
          unit: "MB"
        }
      ];
    }
  },
  methods: {
    getMemory(pos) {
      try {
        return [
          {
            name: "Size",
            value: Math.round(
              this.$store.state.data.memLayout[pos].size / 1000000
            ),
            img: `static/ram/size.svg`,
            unit: "MB"
          },
          {
            name: "Type",
            value: this.$store.state.data.memLayout[pos].type,
            img: `static/ram/type.svg`
          },
          {
            name: "Clock Speed",
            value: this.$store.state.data.memLayout[pos].clockSpeed,
            img: `static/ram/clockspeed.svg`
          },
          {
            name: "Manufacturer",
            value: this.$store.state.data.memLayout[pos].manufacturer,
            img: `static/ram/manufacturer.svg`
          },
          {
            name: "Part Num",
            value: this.$store.state.data.memLayout[pos].partNum,
            img: `static/ram/partnum.svg`
          },
          {
            name: "Serial Num",
            value: this.$store.state.data.memLayout[pos].serialNum,
            img: `static/ram/serialnum.svg`
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