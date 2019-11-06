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
          name: "Distro",
          value: this.$store.state.data.os.distro,
          img: `static/os/platform/${this.$store.state.data.os.platform.toLowerCase()}.svg`
        },
        {
          name: "Arch",
          value: this.$store.state.data.os.arch,
          img: `static/os/arch/${this.$store.state.data.os.arch}.svg`
        },
        {
          name: "Release",
          value: this.$store.state.data.os.release,
          img: "static/os/release.svg"
        },
        {
          name: "Host Name",
          value: this.$store.state.data.os.hostname,
          img: "static/os/hostname.svg"
        },
        {
          name: "Users",
          value: this.$store.state.data.os.users,
          img: "static/os/users.svg"
        }
      ];
    }
  }
};
</script>

<style scoped>
</style>