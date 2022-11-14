<template>
    <v-container>

        <h2>Produktliste</h2>
        {{cart}}
        <ul>
           <li v-for="(product, index) in products" :key="index">
               <span>{{ index }} - {{ product.product }} </span>
               <v-btn class="mx-2" fab dark color="indigo" @click="addToCart(product.product)" type="button" >
                   <v-icon dark>
                       add
                   </v-icon>
               </v-btn>
               <!--    <span>{{ cart }}</span>
              <button @click="counterAdd()" type="button">: Anzahl: {{ counter }} </button> -->
            </li>
        </ul>
    </v-container>
</template>

<script>
export default {
    name: 'ProductList',
    data: () => ({
        counter: 0
    }),
    methods: {
        addToCart(product) {
            this.$store.commit('addToCart', product);
        }
    },
    props: {
        pubId: Number
    },
    computed: {
        products() {
            return this.$store.state.products;
        },
        cart() {
            return this.$store.state.cart.filter(cartItem => cartItem.pubId === this.pubId);
        }
    }
}
</script>