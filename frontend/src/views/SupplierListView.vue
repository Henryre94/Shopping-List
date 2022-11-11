<template>
    <v-container>
        <router-link to="/">Home</router-link>
        <SupplierList />
    </v-container>

</template>
<script>
import SupplierList from '@/components/SupplierList.vue';

export default {
    name: 'SupplierListView',
    components: {
        SupplierList,
    }
}
</script>