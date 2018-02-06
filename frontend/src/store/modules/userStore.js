import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  user: {
    name: ''
  }
}

const mutations = {
  login(state, user) {
    state.user = user
  },

  logout(state) {
    state.user.name = ''
  }
}

const actions = {
  login: ({commit}, user) => commit('login', user),
  logout: ({commit}, user) => commit('logout', user)
}

const getters = {
}

const store = new Vuex.Store({
  state,
  getters,
  actions,
  mutations
})

export default store;
