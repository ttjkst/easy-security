<template>
  <div class="row container-fluid no-gutters">
    <div class="card col-lg-5">
      <div class="card-header">未分配的角色</div>
      <div class="list-group list-group-flush text-center v-role-body-with-over">
        <button
          v-for="role in noAllocations"
          v-bind:class="[{'list-group-item-action active':role.active},'list-group-item']"
          v-bind:key="role.roleId"
          v-on:click.stop="toggleNoAllocationsRoleActive(role.roleId)"
        >{{role.roleName}}</button>
      </div>
    </div>
    <div class="col-lg-2 d-flex justify-content-center flex-column">
      <div class="align-self-center" v-on:click.stop="changeRoleToUse()">
        <i class="fas fa-angle-double-right fa-4x"></i>
      </div>
      <div class="align-self-center" v-on:click.stop="changeRoleToNoUse()">
        <i class="fas fa-angle-double-left fa-4x"></i>
      </div>
    </div>
    
    <div class="card col-lg-5">
      <div class="card-header">已经分配的角色</div>
       <div class="list-group list-group-flush text-center v-role-body-with-over">
        <button
          v-for="role in hasAllocations"
          v-bind:class="[{'list-group-item-action active':role.active},'list-group-item']"
          v-bind:key="role.roleId"
          v-on:click.stop="toggleHasAllocationsRoleActive(role.roleId)"
        >{{role.roleName}}</button>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  computed: {
    ...mapGetters({
      noAllocations: "assignRole/getNoAllocations",
      hasAllocations: "assignRole/getHasAllocations"
    })
  },
  methods: {
    ...mapActions({
      changeRoleToUse: "assignRole/changeRoleToUse",
      changeRoleToNoUse:"assignRole/changeRoleToNoUse",
    }),
    ...mapMutations({
      toggleNoAllocationsRoleActive: "assignRole/toggleNoAllocationsRoleActive",
      toggleHasAllocationsRoleActive:"assignRole/toggleHasAllocationsRoleActive"
    })
  },
  created() {
    this.$store.dispatch("assignRole/init");
  }
};
</script>

<style>
.v-has-prower-body .badge {
      margin-left: 0.5rem;
}
.v-role-body-with-over {
    height: 300px;
    overflow: auto;
}
</style>