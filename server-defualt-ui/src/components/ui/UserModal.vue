<template>
<div  v-bind:class="[{show:'show'},'modal']" tabindex="-1" role="dialog"  v-bind:style="this.show?'display:block;':''">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <slot name="title"></slot>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" v-on:click="close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <slot></slot>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary"  v-on:click="close" >关闭</button>
        <button type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>
</template>
<script>
export default {
    props:["initShow"],
    data:function(){
        return {
            show:this.initShow
        }
    },
    methods:{
        close:function(){
            this.show =false;
        }
    },
    watch:{
        show:function(value){
            setTimeout(() => {
                    this.nextShow = value;
            }, 50);
        }
    }
}
</script>
