<template>
  <Modal v-bind:initShow="true" v-bind:initSize="'modal-lg'" ref="modal" v-on:close="close">
      <template v-slot:title>
        <h1>1111</h1>
      </template>
      <template>
        <form style="display:none">
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
          
          <div class="card">
              <div class="card-header">
                未分配的角色
              </div>
              <ul class="list-group list-group-flush">
                <li class="list-group-item">Cras justo odio</li>
                <li class="list-group-item">Dapibus ac facilisis in</li>
                <li class="list-group-item">Vestibulum at eros</li>
              </ul>
          </div>
           <div class="card">
              <div class="card-header">
                已经分配的角色
              </div>
            <div class="card-body v-has-prower-body">
              <span class="badge badge-secondary">boss</span>
              <span class="badge badge-secondary">开发</span>
              <span class="badge badge-secondary">设计</span>
              <span class="badge badge-secondary">审计</span>
              <span class="badge badge-secondary">项目经理</span>
              <span class="badge badge-secondary">boss</span>
              <span class="badge badge-secondary">开发</span>
              <span class="badge badge-secondary">设计</span>
              <span class="badge badge-secondary">审计</span>
              <span class="badge badge-secondary">项目经理</span>
              <span class="badge badge-secondary">boss</span>
              <span class="badge badge-secondary">开发</span>
              <span class="badge badge-secondary">设计</span>
              <span class="badge badge-secondary">审计</span>
              <span class="badge badge-secondary">项目经理</span>
              <span class="badge badge-secondary">boss</span>
              <span class="badge badge-secondary">开发</span>
              <span class="badge badge-secondary">设计</span>
              <span class="badge badge-secondary">审计</span>
              <span class="badge badge-secondary">项目经理</span>
            </div>
          </div>

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
<style>
.v-has-prower-body .badge {
      margin-left: 0.5rem;
}
</style>
