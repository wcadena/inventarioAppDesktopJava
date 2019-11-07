<template>
  <v-app
	id="inspire"
	:class="[{ 
		'box-layout': boxLayout, 
		'collapse-sidebar': collapseSidebar, 
		'rtl-layout': rtlLayout
	}]"
  > 
    	<router-view :auth="auth" :authenticated="authenticated"></router-view>
      <notifications 
        group="loggedIn" 
        position="top right"
        animation-type="velocity"
      />
  </v-app>
</template>

<script>
import { mapGetters } from "vuex";

import AuthService from "./auth/AuthService";

const auth = new AuthService();

const { login, logout, authenticated, authNotifier } = auth;

export default {
  data() {
    authNotifier.on("authChange", authState => {
      this.authenticated = authState.authenticated;
    });
    return {
      auth,
      authenticated,
      animation: {
        enter: {
          opacity: [1, 0],
          translateX: [0, -300],
          scale: [1, 0.2]
        },
        leave: {
          opacity: 0,
          height: 0
        }
     }
    };
  },
  mounted() {
    if (
      this.selectedLocale.locale === "sp"
    ) {
      this.$store.dispatch("rtlLayout");
    }
  },
  computed: {
    ...mapGetters([
      "collapseSidebar",
      "boxLayout",
      "rtlLayout",
      "selectedLocale"
    ]),
    // isDark(){ return (this.$vuetify.theme.dark) ? 'dark' : 'light' }
  }
};
</script>
