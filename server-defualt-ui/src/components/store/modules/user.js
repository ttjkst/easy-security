const state={
    items : [],
    showItem:{uid:"",password:"",username:""},
};
const getters = {
    normailItems:(state)=>state.items,
    getItemById:(state)=>(id)=> {
      return   state.items.find(item=>item.uid==id)
    },
    getShowItem:state=>state.showItem
};
const mutations ={
    loadItems(state,payload){
        state.items = payload?payload:[];
    },
    loadShowItem:(state,payload)=>state.showItem=payload?payload:{},
    updateUid(state,payload){
        state.showItem.uid = payload
    },
    updatePassword(state,payload){
        state.showItem.password = payload
    },
    updateUsername(state,payload){
        state.showItem.username = payload 
    }

};
const actions = {
    init(context){
        context.commit("loadItems",[
            {uid:1,username:"test",local:true},
            {uid:2,username:"test",local:true},
            {uid:3,username:"test",local:true},
            {uid:4,username:"test",local:true}
        ])
    },
    openModal(context,payload){
        context.commit("user/loadShowItem",payload,{root:true})
        context.commit("modal/open",null,{root:true})
    }
}
export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
}