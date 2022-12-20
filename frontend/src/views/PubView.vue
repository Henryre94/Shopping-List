<template>
    <div>
        <TheHeader />
        <h1>
            CAFE SAADI {{ username }} <br />
            LISTE
        </h1>

        <v-btn style="float: right" value="logout" @click="logout">
            <span>logout</span>
            <v-icon x-large color="red">mdi-logout</v-icon>
        </v-btn>

        <v-container>
            <div class="search-wrapper">
                <v-text-field v-model="searchValue" clearable label="Suche" class="mt-9"></v-text-field>
            </div>
            <div v-for="(product, index) in filteredProducts" :key="index">
                <ProductDemand :product="product" />
            </div>
        </v-container>
    </div>
</template>

<script>
import ProductDemand from "@/components/ProductDemand";
import TheHeader from "@/components/TheHeader";

export default {
    name: "PubView",
    components: { TheHeader, ProductDemand },
    data: () => ({
        searchValue: "",
    }),
    methods: {
        logout() {
            console.log("geklickt");
            this.$store.dispatch("logoutCurrentUser");
            this.$router.push("/");
        },
    },
    computed: {
        // get currentUser for Header
        username() {
            return this.$store.state.loginModule.currentUser.username;
        },
        // get all products
        products() {
            return this.$store.state.productsModule.products;
        },
        // search for product
        filteredProducts() {
            return this.products.filter((product) => {
                if (this.searchValue && this.searchValue.length > 0) {
                    return product.name.toLowerCase().includes(this.searchValue.toLowerCase());
                }
                return true;
            });
        },
    },
    mounted() {
        this.$store.dispatch("loadProducts");
    },
};
</script>

<style>
h1 {
    color: #d2bcad;
    font-size: 40px;
}
</style>
