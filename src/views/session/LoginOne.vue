<template>
	<div class="session-wrapper">
		<div class="session-left">
			<session-slider-widget></session-slider-widget>
		</div>
		<div class="session-right text-center">
			<div class="session-table-cell">
				<div class="session-content">
					<img 
						:src="appLogo"
						class="img-responsive mb-4" 
						width="78" 
						height="78" 
					/>
					<h2 class="mb-4">{{$t('message.loginToAdmin')}}</h2>
					<p class="fs-14">{{$t('message.enterUsernameAndPasswordToAccessControlPanelOf')}} {{brand}}.</p>
					<v-form v-model="valid" class="mb-5">
						<v-text-field 
							label="Equipo"
							v-model="equipo"
							required
						></v-text-field>
						<v-text-field 
							label="Usuario"
							v-model="usuario"
							type="text"
							required
						></v-text-field>
						<router-link class="mb-2" to="/session/forgot-password">{{$t('message.forgotPassword')}}?</router-link>
						<div>
							<v-btn large @click="submit" block color="primary" class="mb-2">{{$t('message.loginNow')}}</v-btn>
						</div>
						<p>{{$t('message.bySigningUpYouAgreeTo')}} {{brand}}</p>
						<router-link to="">{{$t('message.termsOfService')}}</router-link>
					</v-form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { mapGetters } from "vuex";
import SessionSliderWidget from "Components/Widgets/SessionSlider";
import AppConfig from "Constants/AppConfig";

import AuthService from "../../auth/AuthService";

const auth = new AuthService();
const { login, logout } = auth;

export default {
  components: {
    SessionSliderWidget
  },
  data() {
    return {
      checkbox: false,
      valid: false,
      equipo: "EQ1p0",
      usuario: "wcadenaUser",
      appLogo: AppConfig.appLogo2,
      brand: AppConfig.brand
    };
  },
  methods: {
    submit() {
      const equipo = {
		  no_serie: this.equipo,
		  usuario_dom: this.usuario
			};
		login(equipo);
    }
  }
};
</script>
