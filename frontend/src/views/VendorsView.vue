<template>
    <v-container>
        <router-link to="/">Home</router-link>
        <VendorsAdd />
    </v-container>
</template>

<script>
import VendorsAdd from '@/components/VendorsAdd.vue';

export default {
    name: 'VendorsView',
    components: {
        VendorsAdd,
    }
}
</script>