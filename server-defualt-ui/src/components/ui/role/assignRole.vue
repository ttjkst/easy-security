<template>
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
<script>
import { mapGetters,mapMutations } from "vuex";
export default {
    computed:{
       ...mapGetters({
            "noAllocations":"assignRole/getNoAllocations",
            "hasAllocations":"assignRole/getHasAllocations"
          }),
    },
    methods:{
         ...mapMutations({
          "toggleRoleActive":"assignRole/toggleRoleActive"
        })
    },
    created() {
      this.$store.dispatch("assignRole/init");
    }
}
</script>

