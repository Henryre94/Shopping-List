import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        demoText: ''
    },
    getters: {},
    mutations: {
        setDemoText(state, text) {
            state.demoText = text
        }
    },
    actions: {
        async loadDemoText(store) {
            const response = await axios.get('/api/demo')
            store.commit('setDemoText', response.data)
        }
    },
    modules: {}
})
