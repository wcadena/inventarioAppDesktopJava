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
      let iface = this.$store.state.data.networkStats.iface;
      let net = this.$store.state.data.net.filter(
        data => data.iface === iface
      )[0];
      return [
        {
          name: "Name",
          value: net.iface,
          img: `static/network/name.svg`
        },
        {
          name: "IP v4",
          value: net.ip4,
          img: `static/network/ip4.svg`
        },
        {
          name: "IP v6",
          value: net.ip6,
          img: `static/network/ip6.svg`
        },
        {
          name: "MAC",
          value: net.mac,
          img: `static/network/mac.svg`
        }
      ];
    }
  }
};
</script>

<style scoped>
</style>