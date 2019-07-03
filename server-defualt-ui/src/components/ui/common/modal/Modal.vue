<template>
<div  v-bind:class="[{'show':show},'modal']" tabindex="-1" role="dialog"  v-bind:style="this.show?'display:block;':''">
  <div v-bind:class="[size,'modal-dialog']" role="document">
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
        <slot name="footer"></slot>
      </div>
    </div>
  </div>
</div>
</template>
<script>
export default {
    props:["initShow","initSize"],
    data:function(){
        return {
            show:this.initShow,
            size:this.initSize==null?"modal-sm":this.initSize
        }
    },
    methods:{
        close:function(){
            this.show =false;
            this.$emit('close')
        },
        open:function(){
          this.show =true;
          this.$emit('open')
        }
    },
    watch:{
        show:function(value){
            setTimeout(() => {
                    this.show = value;
            }, 50);
        }
    }
}
</script>
