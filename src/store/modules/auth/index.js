/**
 * Auth Module
 */
import Vue from 'vue'
import Nprogress from 'nprogress';
import router from '../../../router';

const state = {
    isUserSigninWithAuth0: Boolean(localStorage.getItem('isUserSigninWithAuth0'))
}

// getters
const getters = {
    isUserSigninWithAuth0: state => {
        return state.isUserSigninWithAuth0;
    }
}

// actions
const actions = {
    signInUserWithAuth0(context, payload) {
        context.commit('signInUserWithAuth0Success', payload);
    },
    signOutUserFromAuth0(context) {
        context.commit('logoutUser');
    }
}

// mutations
const mutations = {
    logoutUser(state) {
        state.user = null
        localStorage.removeItem('user');
        localStorage.removeItem('access_token')
        localStorage.removeItem('refresh_token')
        localStorage.removeItem('id_token')
        localStorage.removeItem('expires_in')
        localStorage.removeItem('equipo')
        router.push("/session/login");
    },
    signInUserWithAuth0Success(state, user) {
        state.user = user;
        localStorage.setItem('user',JSON.stringify(user));
        state.isUserSigninWithAuth0 = true;
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
