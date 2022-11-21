import Vue from "vue";

export const DemandsModule = {
    state: {
        // TODO: cart zu demands umbenennen
        demands: [
            {
                demandId: 0,
                productName: 'Äpfel',
                pubId: 1
            },
            {
                demandId: 1,
                productName: 'Tomaten',
                pubId: 1
            },
            {
                demandId: 2,
                productName: 'Orangen',
                pubId: 0
            }
        ]
    },
    mutations: {
        addToDemands(state, product) {
            if(!(product in state.demands)) {
                Vue.set(state.demands, product, 1);
            } else {
                Vue.set(state.demands, product, state.demands[product]+1);
            }
        }
    },
    getters: {
        demands(state) {
            const demands = state.demands;
            return demands;
        }
    },
}