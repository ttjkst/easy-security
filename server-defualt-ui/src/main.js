import Vue from 'vue'
import App from './App.vue'
import Vuex from 'vuex'
import store from '@/components/store/store.js'
import 'bootstrap/dist/css/bootstrap.min.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
Vue.config.productionTip = false
Vue.use(Vuex)
new Vue({
  el:"#app",
  store,
  render: h => h(App),
});
