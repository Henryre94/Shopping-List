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

    }

}