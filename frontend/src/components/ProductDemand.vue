<template>
    <div>
        {{ product.name }}


        <v-btn color="red darken-3" dark class="mb-2 mt-5" type="button" @click="addToDemands">
            <v-icon dark>
                +
            </v-icon>
        </v-btn>

        <v-btn color="red darken-3" dark class="mb-2 mt-5" type="button" @click="subFromDemands">
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
        product: Object
    },
    computed: {
        quantity() {
            return this.$store.state.demandsModule.demands.find(demand => demand.proId === this.product.proId)?.quantity
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
    mounted() {
        this.$store.dispatch('addToDemands', this.product.proId)
        this.$store.dispatch('subFromDemands', this.product.proId)
        console.log(this.product)
    },
}
</script>

<style scoped>

</style>