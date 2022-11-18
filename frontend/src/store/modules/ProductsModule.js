export const ProductsModule = {
    state: {
        products: []
    },
    getters: {
        // TODO: das braucht man nicht
        // Getter verwendet man wie Variablen. Der "get"-Prefix ist somit irreführend
        getAllProducts(state) {
            const allProducts = state.products;
            return allProducts;
        }
    },
    mutations: {
        addProducts(state, payload) {
            // TODO: payload sollten nur die vendors sein und kein "data" beinhalten
            state.products = state.products.concat(payload.data)
        }
    },
    actions: {}
}