import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        products: [],
        vendors: [],
        cart: [
            {
                demandId: 0,
                productName: 'Äpfel',
                pubId: 1
            },
            {
                demandId: 1,
                productName: 'Tomaten',
                pubId: 1
            },
            {
                demandId: 2,
                productName: 'Orangen',
                pubId: 0
            }
        ]
    },
    
    getters: {
        getAllProducts(state) {
            const allProducts = state.products;
            return allProducts;
        }
    },

    mutations: { 
        addProducts(state, payload) {
            state.products = state.products.concat(payload.data)
        },
        addVendors(state, payload) {
            state.vendors = state.vendors.concat(payload.data)
        },
        addToCart(state, product) {
            if(!(product in state.cart)) {
                Vue.set(state.cart, product, 1);
            } else {
                Vue.set(state.cart, product, state.cart[product]+1);
            }
        }
    },

    actions: {
       
    },

    modules: {}
})
