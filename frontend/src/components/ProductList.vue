<template>
    <v-container>

        <h2>Produktliste</h2>
        <ul>
            <li v-for="(product, index) in products" :key="index">
                <span>{{ index }} - {{ product.product }} </span>

            </li>
        </ul>
    </v-container>
</template>

<script>
export default {
    name: 'ProductList',
    data: () => ({

    }),
    //  methods: {
    //     addToCart(product) {
    //         this.$store.commit('addToCart', product);
    //     },
    // },
    props: {
        pubId: Number
    },
    computed: {
        products() {
            return this.$store.state.products;
        },
       // cart() {
       //     return this.$store.state.cart.filter(cartItem => cartItem.pubId === this.pubId);
       //   }
    }
}
</script>