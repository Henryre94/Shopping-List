<template>
    <v-container>
        <TheHeader />
        <h1>Cafe Saadi 1090</h1>

        <div>
            <input type="text" id="searchValue" placeholder="Search" v-model="searchValue"/>
        </div>
        <div>
            <div v-for="(product, index) in filteredProducts" :key="index">
                <ProductDemand :product="product"/>
            </div>
        </div>
        <br/>
        <p>
            {{ demands }}
        </p>
        <router-link to="/">Home</router-link>
    </v-container>
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
        products() {
            return this.$store.state.productsModule.products;
        },
        filteredProducts() {
            return this.products.filter((product) => product.name.toLowerCase().includes(this.searchValue.toLowerCase()));
        },
        demands() {
            return this.$store.state.demandsModule.demands;
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