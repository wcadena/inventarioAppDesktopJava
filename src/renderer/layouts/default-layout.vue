<template>
  <v-app>
    <v-navigation-drawer fixed :mini-variant="miniVariant" :clipped="clipped" v-model="drawer" app>
      <v-list>
        <v-list-tile
          value="true"
          v-for="(item, i) in menus"
          :key="i"
          exact
          @click="$router.push(`/${item.href}`)"
        >
          <v-list-tile-action>
            <v-icon v-html="item.icon"></v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title v-text="item.title"></v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar fixed app :clipped-left="clipped" dark color="primary">
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-btn icon @click.stop="miniVariant = !miniVariant">
        <v-icon v-html="miniVariant ? 'chevron_right' : 'chevron_left'"></v-icon>
      </v-btn>
      <v-btn icon @click.stop="clipped = !clipped">
        <v-icon>web</v-icon>
      </v-btn>
      <v-btn icon @click.stop="fixed = !fixed">
        <v-icon>remove</v-icon>
      </v-btn>
      <v-toolbar-title v-text="title"></v-toolbar-title>
    </v-toolbar>
    <v-content>
      <v-container fluid>
        <v-slide-y-transition mode="out-in">
          <slot></slot>
        </v-slide-y-transition>
      </v-container>
    </v-content>
    <v-footer :fixed="fixed" app>
      <span>&copy; 2019</span>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      clipped: false,
      drawer: true,
      fixed: false,
      menus: [
        {
          icon: "bubble_chart",
          title: "Summary",
          href: ""
        },
        {
          icon: "bubble_chart",
          title: "Operating System",
          href: "os"
        },
        {
          icon: "bubble_chart",
          title: "System",
          href: "system"
        },
        {
          icon: "bubble_chart",
          title: "CPU",
          href: "cpu"
        },
        {
          icon: "bubble_chart",
          title: "RAM",
          href: "ram"
        },
        {
          icon: "bubble_chart",
          title: "Disk",
          href: "disk"
        },
        {
          icon: "bubble_chart",
          title: "Battery",
          href: "battery"
        },
        {
          icon: "bubble_chart",
          title: "Graphics",
          href: "graphics"
        },
        {
          icon: "bubble_chart",
          title: "Network",
          href: "network"
        }
      ],
      miniVariant: false
    };
  },
  computed: {
    title() {
      return this.$store.state.title;
    }
  }
};
</script>