import Vue from 'vue'
import Vuex from 'vuex'
import modal from './modules/common/modal.js'
Vue.use(Vuex)
const debug = process.env.NODE_ENV !== 'production'
export default new Vuex.Store({
    modules: {
        modal:modal
    },
    strict: debug,
    plugins:[]
  })