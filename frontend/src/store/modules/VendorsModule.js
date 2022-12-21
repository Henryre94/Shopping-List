import axios from "axios";



export const VendorsModule = {
    state: {
        vendors: []
    },
    getters: {
        vendors(state) {
            const vendors = state.vendors;
            return vendors;
        }
    },
    mutations: {
        getVendors(state, VendorArray) {
            state.vendors = VendorArray
        },
        deleteVendor(state, venId) {
            const vendorIndex = state.vendors.findIndex(vendor => vendor.venId === venId);

            state.vendors.splice(vendorIndex, 1)
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
        async delVendor(store, editedItem) {
            await axios.delete("api/vendors/" + editedItem.venId)
            store.commit('deleteVendor', editedItem.venId);
        },
        // ahmed
        // async delVendor(store, vendor) {
        //     await axios.delete("api/vendors/" + vendor.venId)
        //     store.commit('deleteVendor', vendor.venId);
        // },
        async editVendor(store, item) {
            await axios.put("/api/vendors/" + item.venId, item)
            await store.dispatch('getVendors');
        },
    }
}