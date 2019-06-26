<template>
  <div>
    <div>
      <input
        type="text"
        class="form-control"
        placeholder="用户的uid，名字"
        aria-label="用户的uid，名字"
        aria-describedby="button-addon2"
      >
      <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="button" id="button-addon2">Button</button>
      </div>
    </div>
    <div class="card">
      <div class="card-header">
        用户信息
        <div style="float:right">
          <button type="button" class="btn btn-primary" v-on:click="onAdd">新增</button>
          <button type="button" class="btn btn-light border border-secondary">分配</button>
          <button type="button" class="btn btn-danger">删除</button>
        </div>
      </div>
      <div class="card-body">
        <table class="table table-hover table-bordered">
          <thead>
            <tr>
              <th scope="col" class="s-table-check-header"><input type="checkbox" ></th>
              <th scope="col">UID</th>
              <th scope="col">用户名</th>
              <th scope="col">locked</th>
              <th scope="col">详情</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="row in userRows" v-bind:key="row.num">
              <th scope="row" class="s-table-check-header" > <input type="checkbox" v-bind:row-num='row.num'></th>
              <td>{{row.uid}}</td>
              <td>{{row.username}}</td>
              <td>{{row.locked}}</td>
              <td>
                <a
                  href="javascript:void(0);"
                  v-bind:uid="row.uid"
                  v-on:click.stop="openWindow(row.uid)"
                >open</a>
              </td>
            </tr>
          </tbody>
        </table>
        <nav aria-label="Page navigation for userGrid">
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
  </div>
</template>
<script>
import { mapState, mapGetters } from "vuex";
const emptyUseData = { uid: "", password: "", username: "" };
export default {
  data: function() {
    return {
      pageNum: 1
    };
  },
  props: ["pageSize"],
  computed: {
    ...mapState({
      userRows: state => state.user.items
    })
  },
  methods: {
    openWindow: function(uid) {
      let userData = this.getItemById()(uid);
      this.$store.dispatch("user/openModal", userData);
    },
    onAdd: function() {
      this.$store.dispatch("user/openModal", emptyUseData);
    },
    ...mapGetters({
      getItemById: "user/getItemById"
    })
  },
  created() {
    this.$store.dispatch("user/init");
  }
};
</script>
<style>
.s-table-check-header {
      width: 2rem;
}
</style>

