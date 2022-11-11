<template>
    <v-container>
        <v-main>
            <form @submit.prevent="addSuppliers">
                <h2>Händlerliste</h2>
                <input type="text" id="supplier" v-model="supplier" required />
                <v-btn class="mx-2" fab dark color="indigo" type="submit">
                 <v-icon dark>
                     add
                </v-icon>
                 </v-btn>
                 <ul>
                    <li v-for="(supplier, index) in $store.state.suppliers" :key="index">
                        <span>{{ index }} - {{ supplier.supplier }} </span>
                    </li>
                </ul>
            </form>   
        </v-main>
    </v-container>
</template>

<script>
export default {
    name: 'SupplierList',
    data: () => {
        return {
            supplier: ''
        };
    },
    methods: {
        addSuppliers() {
            this.$store.commit("addSuppliers",  {
                data: {
                    supplier: this.supplier
                },
            }); 
            this.supplier = '';             
        }
    }
};
</script>

<style>
#supplier {
    border: 0.5px solid black;
}
</style>