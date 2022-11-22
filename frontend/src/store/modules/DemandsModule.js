export const DemandsModule = {
    state: {
        // TODO: cart zu demands umbenennen
        demands: [
            /*
            {
                demandId: 0,
                productName: 'Äpfel',
                pubId: 1,
                quantity: ''
            },
            {
                demandId: 1,
                productName: 'Tomaten',
                pubId: 1,
                quantity: ''
            },
            {
                demandId: 2,
                productName: 'Orangen',
                pubId: 0,
                quantity: ''
            }
            */
        ]
    },
    mutations: {
        addToDemands(state, productId) {
            const product = state.demands.find(prod => prod.proId === productId)
            if (product) {
                product.quantity++
            } else {
                state.demands.push({
                    proId: productId,
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

        /*if(!(product in state.demands)) {
            Vue.set(state.demands, product);
            product.quantity = 1;
            console.log(product);
      } else {
           Vue.set(state.demands, product,state.demands[product.quantity]+1);
       }*/
    },
    getters: {
        demands(state) {
            const demands = state.demands;
            return demands;
        }
    },
}