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
        addVendorsProduct(state, product) {
            state.products.push(product)
        },
        getVendorsProduct(state, productsList) {
            state.products = productsList
        },
        deleteVendorsProduct(state, proId) {
            const vendorIndex = state.products.findIndex(product => product.id === proId)
            state.products.splice(vendorIndex, 1)
        },
        updateVendorsProduct(state, {id, payload}) {
            const vendorUp = state.products.find(vendorUp => vendorUp.id === id)
            if (vendorUp) {
                vendorUp.products = payload.products
            }
        },
        addProducts(state, products) {
            state.products = state.products.concat(products.data)
        },
        loadProducts(state, productsList) {
            state.products = productsList
        },
    },
    actions: {
        async loadProducts(store) {
            const response = await axios.get('api/products');
            store.commit('loadProducts', response.data)
        }
    },

    async getVendorsProduct(store) {
        const response = await axios.get("/api/products");
        console.log(response)
        store.commit("getVendorsProduct", response.data)
    },
    async addVendorsProduct(store, payload) {
        console.log(payload.venId)
        await axios.post("/api/products/" + payload.venId, payload.product);
        await store.dispatch('getVendorsProduct');
    },
    async delVendorsProduct(store, product) {
        await axios.delete("/api/products/" + product.proId)
        store.commit('deleteVendorsProduct', product.proId);
    },
    async editVendorsProduct(store, item) {
        console.log(this.products)
        await axios.put("/api/products", item)
        await store.dispatch('getVendorsProduct');
    }
}
