
const state={
        roles: {noAllocations:[],hasAllocations:[]},
        dataChangeNum:0,
};

const getters = {
    getNoAllocations:(state)=>state.roles.noAllocations,
    getHasAllocations:(state)=>state.roles.hasAllocations,
    getRoleInNoAllocationsById:(state)=>id=>state.roles.noAllocations.find(x=>x.roleId==id),
    getRoleInHasAllocationsById:(state)=>id=>state.roles.hasAllocations.find(x=>x.roleId==id),
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
        if(index!=-1){
            state.roles.hasAllocations.splice(index,1);
        }
    },
    removeFromNoAllocations:function(state,role){
        let index = state.roles.noAllocations.findIndex(x=>x.roleId==role.roleId);
        if(index!=-1){
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
    },
    changeRoleToUse(state){
        let activeRoles = state.roles.noAllocations.filter(x=>x.active);
        activeRoles.forEach(element => mutations.changeToHasAllocations(state,element));
    },
    changeRoleToNoUse(state){
        let activeRoles = state.roles.hasAllocations.filter(x=>x.active);
        activeRoles.forEach(element => mutations.changeToNoAllocations(state,element));
    },
    toggleNoAllocationsRoleActive(state,roleId){
        let role    =  getters.getRoleInNoAllocationsById(state)(roleId);
        role.active =  role.active?false:true; 
    },
    toggleHasAllocationsRoleActive(state,roleId){
        let role    =  getters.getRoleInHasAllocationsById(state)(roleId);
        role.active =  role.active?false:true; 
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
            {roleId:6,roleName:"项目1",des:"",active:false},
            {roleId:7,roleName:"项目2",des:"",active:false},
 
          ]
        })
    },
    changeToHasAllocations(context,role){
        context.commit("changeToHasAllocations",role)
    },
    changeToNoAllocations(context,role){
        context.commit("changeToNoAllocations",role)
    },
    changeRoleToUse(context){
        context.commit("changeRoleToUse");
    },
    changeRoleToNoUse(context){
        context.commit("changeRoleToNoUse");
    }
}
export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
}