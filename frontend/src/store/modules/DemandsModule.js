import axios from "axios";

export const DemandsModule = {
    state: {
        demands: [],
        pubId: 4
    },
    mutations: {
        addToDemands(state, productId) {
            const product = state.demands.find(prod => prod.id === productId)
            if (product) {
                product.quantity++
            } else {
                state.demands.push({
                    id: productId,
                    quantity: 1
                })
            }
        },
        subFromDemands(state, productId) {
            const product = state.demands.find(prod => prod.id === productId)
            if (product && product.quantity > 0) {
                product.quantity--
            }
        }
    },
    actions: {
        async addToDemands(store, id) {
            console.log(id)
            await axios.put('/api/demands/' + id + '/' + store.state.pubId + '/+')
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error.response.status);
                });
            store.commit('addToDemands')
        },
        async subFromDemands(store, id) {
            await axios.put('/api/demands/' + id, {
                pubId: this.pubId,
                quantity: this.quantity
            })
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error.response.status);
                });
            store.commit('subFromDemands')
        },
        async test() {
            await axios.put('/api/demands/4/1/+')
        }
    }
}