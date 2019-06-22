<template>
  <div class="card">
    <div class="card-header">用户信息
        <div  style="float:right">
        <button type="button" class="btn btn-primary">新增</button>
        <button type="button" class="btn btn-light border border-secondary ">分配</button>
        <button type="button" class="btn btn-danger">删除</button>
      </div>
    </div>
    <div class="card-body">
      <table class="table table-hover table-bordered">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">UID</th>
            <th scope="col">用户名</th>
            <th scope="col">locked</th>
            <th scope="col">详情</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="row in userRows" v-bind:key="row.num">
            <th scope="row">{{row.num}}</th>
            <td>{{row.uid}}</td>
            <td>{{row.username}}</td>
            <td>{{row.locked}}</td>
            <td>
              <a href="javascript:void(0);" v-bind:uid="row.uid"></a>
            </td>
          </tr>
        </tbody>
      </table>
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
              <span class="sr-only">Previous</span>
            </a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">1</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">2</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">3</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
              <span class="sr-only">Next</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>
<script>
import {mapState,mapActions} from 'vuex'
export default {
  data: function() {
    return {
      pageNum: 1
    };
  },
  props: ["pageSize", "tableDatas"],
  computed: mapActions({
    userRows:state=>state.user.items
  }),
  methods:{
    openUserWindow:function(){
       this.$state.modal.commit("open")
    }
  },
  created(){
    this.$state.dispatch("user/init")
  }
};
</script>
