<template>
    <v-container>
        <h1> Cafe Saadi 1160</h1>
        <router-link to="/">Home</router-link>
        <ProductList :pub-id="0" />
    </v-container>
</template>

<script>
import ProductList from "@/components/ProductList.vue";

export  default {
    name: 'Pub1160View',
    components: {
        ProductList,
    }
}
</script>