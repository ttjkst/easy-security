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
          
          <div class="row container-fluid no-gutters">
          <div class="card col-lg-5">
              <div class="card-header">
                未分配的角色
              </div>
              <div class="list-group list-group-flush text-center">
                <button v-for="role in noAllocations" 
                 v-bind:class="[{'list-group-item-action active':role.active},'list-group-item']" 
                 v-bind:key="role.roleId"
                 v-on:click.stop="toggleRoleActive(role.roleId)"
                >{{role.roleName}}</button>
              </div>
          </div>
          <div class="col-lg-2 d-flex justify-content-center">
            <div class="align-self-center">
              <i class="fas fa-angle-double-right fa-4x"></i>
            </div>
          </div>
           <div class="card col-lg-5">
              <div class="card-header">
                已经分配的角色
              </div>
            <div class="card-body v-has-prower-body">
              <span class="badge badge-secondary" v-for="role in hasAllocations" v-bind:key="role.roleId" >{{role.roleName}}</span>
            </div>
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
    data:function(){
        return {
          noAllocations:[
            {roleId:1,roleName:"项目1",des:"",active:false},
            {roleId:2,roleName:"项目2",des:"",active:false},
            {roleId:3,roleName:"项目3",des:"",active:false},
            {roleId:4,roleName:"项目4",des:"",active:false},
            {roleId:5,roleName:"项目5",des:"",active:false}
          ],
          hasAllocations:[
            {roleId:6,roleName:"项目1",des:""},
            {roleId:7,roleName:"项目2",des:""},
            {roleId:8,roleName:"项目3",des:""},
            {roleId:9,roleName:"项目4",des:""},
            {roleId:10,roleName:"项目5",des:""},
            {roleId:11,roleName:"项目1",des:""},
            {roleId:12,roleName:"项目2",des:""},
            {roleId:13,roleName:"项目3",des:""},
            {roleId:14,roleName:"项目4",des:""},
            {roleId:15,roleName:"项目5",des:""},
            {roleId:16,roleName:"项目1",des:""},
            {roleId:17,roleName:"项目2",des:""},
            {roleId:18,roleName:"项目3",des:""},
            {roleId:19,roleName:"项目4",des:""},
            {roleId:20,roleName:"项目5",des:""},
            {roleId:21,roleName:"项目1",des:""},
            {roleId:22,roleName:"项目2",des:""},
            {roleId:23,roleName:"项目3",des:""},
            {roleId:24,roleName:"项目4",des:""},
            {roleId:25,roleName:"项目5",des:""}
          ]
        }
    },
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
      getRoleById:function(roleId){
        return  this.noAllocations.find(x=>x.roleId==roleId)
      },
      toggleRoleActive:function(roleId){
         let role    =  this.getRoleById(roleId);
         role.active =  role.active?false:true; 
      },
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
