import Vue from 'vue'
import Vuex from 'vuex'
import si from 'systeminformation'
const { getCurrentWindow } = require('electron').remote;

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    data: null,
    title: 'System Information'
  },
  mutations: {
    setData(state, data) {
      state.data = data
    },
    setTitle(state, title) {
      state.title = title
    }
  },
  actions: {
    async GET_DATA({ commit }) {
      let data
      try {
        data = await si.getAllData()
      } catch (e) {
        getCurrentWindow().reload()
      }
      commit('setData', data)
    },
    SET_TITLE({ commit }, title) {
      commit('setTitle', title)
    }
  },
  strict:
    process.env.NODE_ENV !== 'production'
})
