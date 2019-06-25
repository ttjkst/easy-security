<template>
  <Modal v-bind:initShow="false" ref="modal" v-on:close="close">
      <template v-slot:title>
        <h1>1111</h1>
      </template>
      <template>
        <form>
            <div class="form-group">
              <label for="uid">uid</label>
              <input type="uid" class="form-control" id="uid" aria-describedby="uid" v-model="uid" disabled>
            </div>
            <div class="form-group">
              <label for="username">用户名</label>
              <input type="text" class="form-control" id="username" placeholder="用户名" v-model="username">
            </div>
            <div class="form-group">
              <label for="password">密码</label>
              <input type="password" class="form-control" id="password" v-model="password">
            </div>
          </form>
      </template>
      <template v-slot:footer>
        <button type="button" class="btn btn-secondary"  v-on:click="close" >关闭</button>
        <button type="button" class="btn btn-primary">保存</button>
      </template>
    </Modal>
</template>
<script>
import Modal from './common/modal/Modal.vue'
import { mapGetters, mapMutations } from 'vuex';

const root  = {root:true}
export default {
    computed:{
          uid:{
            get(){
              return this.$store.getters['user/getShowItem'].uid
            },
            set(value){
               this.$store.commit('user/updateUid',value,root)
            }
          },
          username:{
            get(){
              return this.$store.getters['user/getShowItem'].username
            },
            set(value){
               this.$store.commit('user/updateUsername',value,root)
            }
          },
          password:{
            get(){
              return this.$store.getters['user/getShowItem'].password
            },
            set(value){
               this.$store.commit('user/updatePassword',value,root)
            }
          },
          ...mapGetters({
            "show":"modal/isOpen",
            "user":"user/getShowItem"
          }),
    },
    methods:{
        ...mapMutations({
          "close":"modal/close"
        })
    },
    watch:{
        show:function(value){
           if(value==true){
                this.$refs.modal.open()
           }else{
                this.$refs.modal.close()
           }
        }
    },
    components: {
    Modal
  }
}
</script>
