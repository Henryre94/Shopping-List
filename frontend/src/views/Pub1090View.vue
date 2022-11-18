<template>
    <v-container>
        <h1>Cafe Saadi 1090</h1>
        <router-link to="/">Home</router-link>

        <div>
            <input type="text" id="searchValue" placeholder="Search" v-model="searchValue"/>
        </div>

        <div>
            <div v-for="(product, index) in filteredProducts" :key="index">
                {{ product.product }}

                <v-btn class="mx-2" fab dark small color="primary" type="button"  @click="addToCart(product.product)">
                    <v-icon dark>
                        +
                    </v-icon>
                </v-btn>

                {{ cart }}
            </div>
        </div>
    </v-container>
</template>

<script>
export default {
    name: "Pub1090View",

    data: () => ({
        searchValue: "",
    }),
    methods: {
        addToCart(product) {
            this.$store.commit('addToCart', product);
        },
    },
    computed: {
        products() {
            return this.$store.state.products;
        },
        filteredProducts() {
            return this.products.filter((product) => product.product.toLowerCase().includes(this.searchValue.toLowerCase()));
        },
        cart() {
            return this.$store.state.cart;
        },
        // cart() {
        //     return this.$store.state.cart.filter(cartItem => cartItem.pubId === this.pubId);
        //  }
    },
};
</script>

<style>
#searchValue {
    border: 0.5px solid black;
}
</style>