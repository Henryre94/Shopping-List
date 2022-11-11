<template>
    <v-container>
        <router-link to="/">Home</router-link>
        <ProductList />
    </v-container>

</template>
<script>
import ProductList from '@/components/ProductList.vue';

export default {
    name: 'ProductListView',
    components: {
        ProductList,
    }
}
</script>