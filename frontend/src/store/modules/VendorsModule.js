import axios from "axios";

export const VendorsModule = {
    state: {
        vendors: []
    },
    mutations: {
        addVendors(state, payload) {
            // TODO: payload sollten nur die vendors sein und kein "data" beinhalten
            state.vendors = state.vendors.concat(payload.data)
        },
    },
    actions:{
        async getVendors(store){
            const response = await axios.get("/api/vendors");
            store.commit("setVendors", response.data)
        }
    }

}