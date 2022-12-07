<template>
    <div>
        <TheHeader />
        <h1>1090</h1>
        <v-container>
            <v-text-field v-model="searchValue" clearable label="Suche" class="mt-9"></v-text-field>

            <div v-for="(product, index) in filteredProducts" :key="index">
                <ProductDemand :product="product"/>
            </div>
        </v-container>
        <br/>
        <router-link to="/">Home</router-link>
    </div>
</template>

<script>
import ProductDemand from "@/components/ProductDemand";
import TheHeader from "@/components/TheHeader";

export default {
    name: "PubView",
    components: {TheHeader, ProductDemand},
    data: () => ({
        searchValue: "",
    }),
    computed: {
        // get all products
        products() {
            return this.$store.state.productsModule.products;
        },
        // search for product
        filteredProducts() {
            return this.products.filter(product => {
                if (this.searchValue && this.searchValue.length > 0) {
                    return product.name.toLowerCase().includes(this.searchValue.toLowerCase())
                }
                return true;
            });
        },
    },
    mounted() {
        this.$store.dispatch('loadProducts')
    }
};
</script>

<style>
#searchValue {
    border: 0.5px solid black;
}
</style>