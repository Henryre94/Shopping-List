
import axios from "axios"

export const DemandsModule = {
    state: {

    },
    mutations: {

        getVendorsDemand(state,demandList){
            state.demands = demandList
        },
        deleteVendorsDemand(state, proId) {
            const demandIndex = state.demands.findIndex(demand => demand.id === proId)
            state.demands.splice(demandIndex, 1)
        },
        updateVendorsProduct(state, {id, payload}) {
            const demandUp = state.demands.find(demandUp => demandUp.id === id)
            if (demandUp) {
                demandUp.demands = payload.demands
            }
        },
    },

    actions: {
        async getVendorsDemand(store,venId) {
            const response = await axios.get("/api/demands/vendor/" + venId);
            console.log(response)
            store.commit("getVendorsDemand", response.data)
        },
        async editVendorsDemand(store, payload) {
            console.log(this.demands)
            await axios.put("/api/demands/" + payload.proId + "/pubs" + payload.pubId)
            console.log(payload);
            await store.dispatch('getVendorsDemand', payload.venId);

        },
        async delVendorsDemand(store, product) {
            await axios.delete("/api/demands/" + product.proId)
            store.commit('deleteVendorsDemand', product.proId);
        },
    }
}