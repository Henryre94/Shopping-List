import axios from "axios";

export const ProductsModule = {
    state: {
        products: []
    },
    getters: {
        products(state) {
            const products = state.products;
            return products;
        }
    },
    mutations: {
        addProducts(state, products) {
            state.products = state.products.concat(products.data)
        },
        loadProducts(state, productsList) {
            state.products = productsList
            console.log(productsList)
        },
    },
    actions: {
        async loadProducts(store) {
            const response = await axios.get('api/products');
            store.commit('loadProducts', response.data)
        }
    },
}