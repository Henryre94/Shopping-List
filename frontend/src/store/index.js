import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        products: [],
        suppliers: [],
    },
    
    getters: {},

    mutations: { 
        addProducts(state, payload) {
            state.products = state.products.concat(payload.data)
        },
        addSuppliers(state, payload) {
            state.suppliers = state.suppliers.concat(payload.data)
        }    
    },

    actions: {
       
    },

    modules: {}
})
