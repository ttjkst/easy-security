<template>
  <div id="app" class="container-fluid">
      <div class="row">
    <ToolBar/>
    <main class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <HelloWorld v-bind:tableDatas="datas"  />
    </main>
    </div>
  </div>
</template>

<script>
import HelloWorld from '@/components/ui/UserGrid.vue'
import ToolBar from '@/components/ui/ToolBar.vue'

export default {
  name: 'app',
  data:function(){
    return {

        datas:[
          {uid:1,username:"test",local:true},
          {uid:1,username:"test",local:true},
          {uid:1,username:"test",local:true},
          {uid:1,username:"test",local:true}
        ]
    }
  },
  components: {
    HelloWorld,ToolBar
  }
}
</script>

<style>
</style>
