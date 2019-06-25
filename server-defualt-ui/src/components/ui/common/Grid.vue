<template>
    <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
  <th v-for="col in realCols" scope="col" v-bind:key="col.field">
    {{col.title}}
  </th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
</template>
<script>
export default {
    data:function(){
        return {
            data:[

            ],
            styleConfig:{

            }
        }
    },
    props:[
      /**
             * {
             *      title:'',
             *      field:''
             * }
             */
      'cols',
    ],
    computed:{
        realCols:function(){
           return this.cols; 
        }
    }
}
</script>

