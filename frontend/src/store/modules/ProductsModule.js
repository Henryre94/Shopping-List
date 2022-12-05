import axios from "axios";

export const ProductsModule = {
    state: {
        products: [
            // {proId: 1, name: 'Äpfel'},
            // {proId: 2, name: 'Tomaten'},
            // {proId: 3, name: 'Orangen'},
            // {proId: 4, name: 'Bananen'},
        ]
    },
    getters: {
        // TODO: das braucht man nicht
        // Getter verwendet man wie Variablen. Der "get"-Prefix ist somit irreführend
        // getAllProducts(state) {
        //     const allProducts = state.products;
        //     return allProducts;
        // }
    },
    mutations: {
        addProducts(state, payload) {
            // TODO: payload sollten nur die vendors sein und kein "data" beinhalten
            state.products = state.products.concat(payload.data)
        },
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
    },
    actions: {
        async getVendorsProduct(store,venId) {
            const response = await axios.get("/api/vendors/" + venId + "/products");
            console.log(response)
            store.commit("getVendorsProduct", response.data)
        },
        async addVendorsProduct(store, payload) {
            console.log(payload)
            await axios.post("/api/products/" + payload.venId, payload.product);
            await store.dispatch('getVendorsProduct', payload.venId);
        },
        async delVendorsProduct(store, product) {
            await axios.delete("/api/products/" + product.proId)
            store.commit('deleteVendorsProduct', product.proId);
        },
        async editVendorsProduct(store, payload) {
            console.log(this.products)
            await axios.put("/api/products/" + payload.product.proId , payload.product)
            console.log(payload);
            await store.dispatch('getVendorsProduct', payload.venId);

        }
    }
}