<template>
  <div>
    <UserModal>
    </UserModal>
    <div id="app" class="container-fluid">
      <div class="row">
        <ToolBar/>
        <main class="col-md-9 ml-sm-auto col-lg-10 px-4">
          <HelloWorld/>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
import HelloWorld from "@/components/ui/UserGrid.vue";
import ToolBar from "@/components/ui/ToolBar.vue";
import UserModal from "@/components/ui/UserModal.vue";
import { mapGetters } from "vuex";
export default {
  name: "app",
  computed: {
    ...mapGetters({
      windowStatus: "modal/isOpen"
    })
  },
  components: {
    HelloWorld,
    ToolBar,
    UserModal
  }
};
</script>

<style>
</style>
