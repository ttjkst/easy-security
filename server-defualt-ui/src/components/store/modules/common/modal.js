import Vue from 'vue'
const state = {
    windowState:"close",//must be open or close
}

const mutations ={
    open(state){
        windowState ="open";
    },
    close(state){
        windowState ="close";
    }
}

export default{
    state,
    mutations,
}