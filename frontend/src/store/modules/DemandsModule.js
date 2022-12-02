import axios from "axios";

export const DemandsModule = {
    state: {

        demands: [],
        pubId: 4
    },
        mutations: {
            addToDemands(state, productId) {
                const product = state.demands.find(prod => prod.proId === productId)
                if (product) {
                    product.quantity++
                } else {
                    state.demands.push({
                        proId: productId,
                        pubId: 4,
                        quantity: 1
                    })
                }
            },
            subFromDemands(state, productId) {
                const product = state.demands.find(prod => prod.proId === productId)
                if (product && product.quantity > 0) {
                    product.quantity--
                }
            }
        },
        actions: {
            async addToDemands(store, proId) {
                console.log(proId)
                await axios.put('/api/demands/' + proId + '/' + store.state.pubId + '/+')
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(error => {
                        console.log(error.response.status);
                    });
                store.commit('addToDemands')
            },
            async subFromDemands(store, proId) {
                await axios.put('/api/demands/' + proId + '/' + store.state.pubId + '/-')
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(error => {
                        console.log(error.response.status);
                    });
                store.commit('subFromDemands')
            },
        }
    }