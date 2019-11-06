import Vue from 'vue'
import Vuetify from 'vuetify'
// store
import { store } from '../store/store';

Vue.use(Vuetify);

export default new Vuetify({	
	theme:{
		themes: {
			light: store.getters.selectedTheme.theme,
			dark: store.getters.selectedTheme.theme
		},
	},
})