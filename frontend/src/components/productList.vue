<template>
    <v-container>
        <v-main>
            <form @submit.prevent="addProducts">
                <h2>Produktliste</h2>
                <input type="text" id="product" v-model="product" required />
                <v-btn class="mx-2" fab dark color="indigo" type="submit">
                 <v-icon dark>
                     add
                </v-icon>
                 </v-btn>

                <ul>
                    <li v-for="(product, index) in $store.state.products" :key="index">
                        <span>{{ index }} - {{ product.product }} </span>
                    </li>
                </ul>
            </form>
        </v-main>
    </v-container>
</template>

<script>
export default {
    name: 'ProductList',
    data: () => {
        return {
            product: ''
        };
    },
    methods: {
        addProducts() {
            this.$store.commit("addProducts",  {
                data: {
                    product: this.product
                },
            });  
            this.product = '';         
        },      
    }
};
</script>

<style>
#product {
    border: 0.5px solid black;
}
</style>