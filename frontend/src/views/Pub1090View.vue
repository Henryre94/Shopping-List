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

              <v-btn class="mx-2" fab dark small color="primary" type="button" @click="addToDemands(product.product)">
                    <v-icon dark>
                        +
                    </v-icon>
                </v-btn>

            </div>
        </div>
        <p>
            {{ demands }}
        </p>

    </v-container>
</template>

<script>
export default {
    name: "Pub1090View",

    data: () => ({
        searchValue: "",
    }),

    methods: {
        addToDemands(product) {
            this.$store.state.demandsModule.commit('addToDemands', product);
       },
    },

    computed: {
        products() {
            return this.$store.state.productsModule.products;
        },
        filteredProducts() {
            return this.products.filter((product) => product.product.toLowerCase().includes(this.searchValue.toLowerCase()));
        },
        demands() {
           return this.$store.state.demandsModule.demands;
        },

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