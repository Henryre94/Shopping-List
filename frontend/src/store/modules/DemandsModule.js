import axios from "axios";

export const DemandsModule = {
    state: {
        demands: [],
        pubId: 1
    },
        mutations: {
            addToDemands(state, proId) {
                const product = state.demands.find(prod => prod.proId === proId)
                if (product) {
                    product.quantity++
                } else {
                    state.demands.push({
                        proId: proId,
                        pubId: 1,
                        quantity: 1
                    })
                }
            },
            subFromDemands(state, proId) {
                const product = state.demands.find(prod => prod.proId === proId)
                if (product && product.quantity > 0) {
                    product.quantity--
                }
            }
        },
        actions: {
            async addToDemands(store, proId) {
                console.log(proId)
                await axios.post('/api/demands/' + proId + '/pubs/' + store.state.pubId)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(error => {
                        console.log(error.response.status);
                    });
                store.commit('addToDemands', proId)
            },
            async subFromDemands(store, proId) {
                await axios.put('/api/demands/' + proId + '/pubs/' + store.state.pubId)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(error => {
                        console.log(error.response.status);
                    });
                store.commit('subFromDemands', proId)
            },
        }
    }