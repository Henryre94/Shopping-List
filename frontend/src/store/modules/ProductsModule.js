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
        getVendorsProduct(state, productsList) {
            state.products = productsList
        },
        deleteVendorsProduct(state, proId) {
            const vendorIndex = state.products.findIndex(product => product.proId === proId);
            state.products.splice(vendorIndex, 1)
        },
        addProducts(state, products) {
           // state.products = state.products.concat(products.data)
            state.products = products
        },
      //  loadProducts(state, productsList) {
      //      state.products = productsList
     //   },
    },
    actions: {
        async loadProducts(store) {
            const response = await axios.get('api/products');
            store.commit('addProducts', response.data)
        },
        async getVendorsProduct(store, venId) {
            const response = await axios.get("/api/vendors/" + venId + "/products");
            console.log(response)
            store.commit("getVendorsProduct", response.data)
        },
        async addVendorsProduct(store, payload) {
            console.log(payload)
            await axios.post("/api/products/" + payload.venId, payload.product);
            await store.dispatch('getVendorsProduct', payload.venId);
        },

        // async delVendorsProduct(store, product) {
        //     await axios.delete("/api/products/" + product.proId)
        //     store.commit('deleteVendorsProduct', product.proId);
       // },
        async delVendorsProduct(store, editedItem) {
            console.log('editedItem', editedItem)
            await axios.delete("/api/products/" + editedItem.proId)
            store.commit('deleteVendorsProduct', editedItem.proId);
        },
        async editVendorsProduct(store, payload) {
            console.log(this.products)
            await axios.put("/api/products/" + payload.product.proId, payload.product)
            console.log(payload);
            await store.dispatch('getVendorsProduct', payload.venId);
        }
    }
}
