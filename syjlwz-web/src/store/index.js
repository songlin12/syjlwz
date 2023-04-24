import { createStore } from 'vuex'

export default createStore({
  state: {
    currentMenu:0
  },
  mutations: {
    setState (state, n) {
      state.currentMenu = n
    }
  },
  actions: {
  },
  modules: {
  }
})
