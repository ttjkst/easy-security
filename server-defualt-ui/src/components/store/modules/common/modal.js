const state = {
    windowState:"close",//must be open or close
}

const mutations ={
    open(state){
        state.windowState ="open";
    },
    close(state){
        state.windowState ="close";
    }
}
const getters ={
    isClose:(state)=>state.windowState=="close",
    isOpen :(state,getters)=>!getters.isClose
}

export default{
    namespaced: true,
    state,
    mutations,
    getters,
}