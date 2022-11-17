<template>
    <v-container>
        <h2>Händlerliste</h2>
        <ul>
            <li v-for="(vendor, index) in $store.state.vendors" :key="index">
                <span>{{ index }} - {{ vendor.vendor }} </span>
            </li>
        </ul>
    </v-container>
</template>

<script>
export default {
    name: 'VendorsList',
}
</script>