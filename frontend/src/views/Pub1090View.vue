<template>
    <v-container>
        <h1>Cafe Saadi 1090</h1>
        <!-- brauch ich das ? -->
        <router-link to="/">Home</router-link>
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
    </v-container>
</template>

<script>
import ProductDemand from "@/components/ProductDemand";

export default {
    name: "Pub1090View",
    components: {ProductDemand},
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

        // demands() {
        // return this.$store.state.demands.filter(product => product.quantity > 0 );
        //     }

        //  demands() {
        //    return this.$store.state.demands.filter(demandItem => demandItem.pubId === this.pubId);
        //  }
    },
};
</script>

<style>
#searchValue {
    border: 0.5px solid black;
}
</style>