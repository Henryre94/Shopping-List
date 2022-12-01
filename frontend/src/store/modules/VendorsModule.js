import axios from "axios";

export const VendorsModule = {
    state: {
        vendors: []
    },
    mutations: {
        addVendor(state, vendor) {
            state.vendors.push(vendor)
        },
        getVendors(state, VendorArray) {
            state.vendors = VendorArray
        },
        deleteVendor(state, venId) {
            const vendorIndex = state.vendors.findIndex(vendor => vendor.id === venId)
            state.vendors.splice(vendorIndex, 1)
        },
        updateVendor(state, {venId, payload}) {
            const vendorUp = state.vendors.find(vendorUp => vendorUp.id === venId)
            if (vendorUp) {
                vendorUp.vendors = payload.vendors
            }
        },
        //HÄNDLER PRODUKTE
        addVendorsProduct(state, product) {
            state.products.push(product)
        },
        getVendorsProduct(state, ProductList) {
            state.products = ProductList

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
        async getVendors(store) {
            const response = await axios.get("/api/vendors");
            store.commit("getVendors", response.data)
        },
        async addVendor(store, vendor) {
            await axios.post("/api/vendors", vendor);
            await store.dispatch('getVendors');
        },
        async delVendor(store, vendor) {
            await axios.delete("api/vendors/" + vendor.venId)
            store.commit('deleteVendor', vendor.venId);
        },
        async editVendor(store, item) {
            await axios.put("/api/vendors/", item)
            await store.dispatch('getVendors');
        },
        //HÄNDLER PRODUKTE
        async getVendorsProduct(store) {
            const response = await axios.get("/api/products");
            console.log(response)
            store.commit("getVendorsProduct", response.data)
        },
        async addVendorsProduct(store, payload) {
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

}