export const VendorsModule = {
    state: {
        vendors: []
    },
    mutations: {
        addVendors(state, payload) {
            // TODO: payload sollten nur die vendors sein und kein "data" beinhalten
            state.vendors = state.vendors.concat(payload.data)
        },
    }
}