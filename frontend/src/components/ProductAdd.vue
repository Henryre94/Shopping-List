<template>
    <v-container>
        <v-main>
            <form @submit.prevent="addProducts">
                <h2>Produkte hinzufügen</h2>
                <input type="text" id="product" v-model="product" required/>

                <v-btn class="mx-2" fab dark small color="primary" type="submit"
                >
                    <v-icon dark>
                        +
                    </v-icon>
                </v-btn>

            </form>
            <ProductList/>
        </v-main>
    </v-container>
</template>

<script>
import ProductList from "@/components/ProductList.vue";

export default {
    name: 'ProductAdd',
    components: {
        ProductList
    },
    data: () => {
        return {
            product: ''
        };
    },
    methods: {
        addProducts() {
            this.$store.commit("addProducts", {
                data: {
                    product: this.product
                },
            });
            console.log(this.product);
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