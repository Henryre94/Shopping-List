<template>
    <v-container>
        <v-main>
            <form @submit.prevent="addVendors">
                <h2>Händler hinzufügen</h2>
                <input type="text" id="vendor" v-model="vendor" required />
                <v-btn class="mx-2" fab dark color="indigo" type="submit">
                 <v-icon dark>
                     add
                </v-icon>
                 </v-btn>
            </form>
            <VendorsList />
        </v-main>
    </v-container>
</template>

<script>
import VendorsList from "@/components/VendorsList.vue";
export default {
    name: 'VendorsAdd',
    components: {
        VendorsList
    },
    data: () => {
        return {
            vendor: ''
        };
    },
    methods: {
        addVendors() {
            this.$store.commit("addVendors",  {
                data: {
                    vendor: this.vendor
                },
            }); 
            this.vendor = '';
        }
    }
};
</script>

<style>
#vendor {
    border: 1px solid black;
}
</style>