<template>
    <v-container>
        <h1>Cafe Saadi 1090</h1>
        <!-- Brauchen die Cafes überhaupt Home Route ?? -->
        <router-link to="/">Home</router-link>
        <ProductList :pub-id="1" />
    </v-container>
</template>

<script>
import ProductList from "@/components/ProductList.vue";

export  default {
    name: 'Pub1090View',
    components: {
        ProductList,
    }
}
</script>