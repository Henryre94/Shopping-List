<template>
    <div>
        {{ product.name }}

        <v-btn color="red darken-3" dark class="mb-2 mt-5 mr-2 ml-2" type="button" @click="addToDemands">
            <v-icon dark>
                +
            </v-icon>
        </v-btn>

        <v-btn color="red darken-3" dark class="mb-2 mt-5 mr-2 ml-2" type="button" @click="subFromDemands">
            <v-icon dark>
                min
            </v-icon>
        </v-btn>
        <span class="quantity"> Anzahl:  {{ quantity }} </span>
    </div>
</template>

<script>
export default {
    name: "ProductDemand",
    props: {
        product: {
            proId: String,
            name: String,
            unit: String
        }
    },
    computed: {
        quantity() {
            return this.$store.state.demandsModule.demands.find(demand => demand.proId === this.product.proId)?.quantity || 0
        }
    },
    methods: {
        addToDemands() {
            this.$store.dispatch('addToDemands', this.product.proId);
        },
        subFromDemands() {
            this.$store.dispatch('subFromDemands', this.product.proId)
        }
    },

    // Sebi Test
    mounted() {
        console.log(this.product)
        this.$store.dispatch('test')
    },
}
</script>

<style scoped>

</style>