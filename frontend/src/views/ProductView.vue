<template>
    <v-container>
        <router-link to="/">Home</router-link>
        <ProductAdd/>
    </v-container>
</template>

<script>
import ProductAdd from '@/components/ProductAdd.vue';

export default {
    name: 'ProductView',
    components: {
        ProductAdd,
    }
}
</script>