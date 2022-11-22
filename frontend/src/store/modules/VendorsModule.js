import axios from "axios";

export const VendorsModule = {
    state: {
        vendors: []
    },
    mutations: {
        addVendor(state, vendor) {
            state.vendors.push(vendor)
        },
        setVendors(state, VendorArray){
        // VendorArray.forEach(VendorName => state.vendors.push(VendorName))
            state.vendors = VendorArray
        },

    },
    actions:{
        async getVendors(store){
            const response = await axios.get("/api/vendors");
            store.commit("setVendors", response.data)
        }
    }

}