import Vue from "vue";

export const DemandsModule = {
    state: {
        // TODO: cart zu demands umbenennen
        cart: [
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
        addToCart(state, product) {
            if(!(product in state.cart)) {
                Vue.set(state.cart, product, 1);
            } else {
                Vue.set(state.cart, product, state.cart[product]+1);
            }
        }
    }
}