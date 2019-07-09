
const state={
        roles: {noAllocations:[],hasAllocations:[]},
        dataChangeNum:0,
};

const getters = {
    getNoAllocations:(state)=>state.noAllocations,
    getHasAllocations:(state)=>state.hasAllocations,
    getRoleInNoAllocationsById:(state)=>id=>state.noAllocations.find(x=>x.roleId==id),
    getDataChangeNum:(state)=>state.dataChangeNum,
    isDataLoadedFromServer:(state)=>state.dataChangeNum==0
};

const mutations ={
    increaseDataChange(state){
        state.dataChangeNum = state.dataChangeNum+1
    },
    decreaseDataChange(state){
        state.dataChangeNum = state.dataChangeNum-1
    },
    loadDate:function(state,data){
        state.roles = data;
    },
    addToHasAllocations:function(state,role){
        state.roles.hasAllocations.push(role);
    },
    addToNoAllocations:function(state,role){
        state.roles.noAllocations.push(role);
    },
    removeFromHasAllocations:function(state,role){
        let index = state.roles.hasAllocations.findIndex(x=>x.roleId==role.roleId);
        if(index){
            state.roles.hasAllocations.splice(index,1);
        }
    },
    removeFromNoAllocations:function(state,role){
        let index = state.roles.noAllocations.findIndex(x=>x.roleId==role.roleId);
        if(index){
            state.roles.noAllocations.splice(index,1);
        }
    },
    changeToNoAllocations(state,role){
        mutations.addToNoAllocations(state,role);
        mutations.removeFromHasAllocations(state,role);
    },
    changeToHasAllocations(state,role){
        mutations.addToHasAllocations(state,role);
        mutations.removeFromNoAllocations(state,role);
    }
};

const actions = {
    init(context){
        context.commit("loadDate",{
            noAllocations:[
            {roleId:1,roleName:"项目1",des:"",active:false},
            {roleId:2,roleName:"项目2",des:"",active:false},
            {roleId:3,roleName:"项目3",des:"",active:false},
            {roleId:4,roleName:"项目4",des:"",active:false},
            {roleId:5,roleName:"项目5",des:"",active:false}
          ],
          hasAllocations:[
            {roleId:6,roleName:"项目1",des:""},
            {roleId:7,roleName:"项目2",des:""},
            {roleId:8,roleName:"项目3",des:""},
            {roleId:9,roleName:"项目4",des:""},
            {roleId:10,roleName:"项目5",des:""},
            {roleId:11,roleName:"项目1",des:""},
            {roleId:12,roleName:"项目2",des:""},
            {roleId:13,roleName:"项目3",des:""},
            {roleId:14,roleName:"项目4",des:""},
            {roleId:15,roleName:"项目5",des:""},
            {roleId:16,roleName:"项目1",des:""},
            {roleId:17,roleName:"项目2",des:""},
            {roleId:18,roleName:"项目3",des:""},
            {roleId:19,roleName:"项目4",des:""},
            {roleId:20,roleName:"项目5",des:""},
            {roleId:21,roleName:"项目1",des:""},
            {roleId:22,roleName:"项目2",des:""},
            {roleId:23,roleName:"项目3",des:""},
            {roleId:24,roleName:"项目4",des:""},
            {roleId:25,roleName:"项目5",des:""}
          ]
        })
    },
    changeToHasAllocations(context,role){
        context.commit("changeToHasAllocations",role)
    },
    changeToNoAllocations(context,role){
        context.commit("changeToNoAllocations",role)
    }
}
export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
}