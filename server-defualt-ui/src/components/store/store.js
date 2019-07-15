import Vue from 'vue'
import Vuex from 'vuex'
import modal from './modules/common/modal.js'
import user  from './modules/user.js'
import assignRole from './modules/assignRole.js'
Vue.use(Vuex)
const debug = process.env.NODE_ENV !== 'production'
export default new Vuex.Store({
    modules: {
        user,
        modal,
        assignRole
    },
    strict: debug,
    plugins:[]
  })