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
        }

    },
    actions: {
        async getVendors(store) {
            const response = await axios.get("/api/vendors");
            store.commit("getVendors", response.data)
        },
        async addVendor(store, vendor) {
            const response = await axios.post("/api/vendors", vendor);
            vendor.id = response.data
            store.commit("addVendor", vendor)
        },
        async deleteVendor(store, venId) {
            await axios.delete("api/vendors/{venId}" + venId)
            store.commit('deleteVendor', venId);
        },

    }
}