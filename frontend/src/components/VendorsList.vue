<template>
    <v-container>
        <h2>Händlerliste</h2>
        <ul>
            <li v-for="(vendor, index) in vendors" :key="index">
                <span>{{ index }} - {{ vendor.vendor }} </span>
            </li>
        </ul>
    </v-container>
</template>

<script>
export default {
    name: 'VendorsList',
    data: () => ({

    }),
    computed: {
        vendors() {
            return this.$store.state.vendorsModule.vendors;
        }

    }
}
</script>