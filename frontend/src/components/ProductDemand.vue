<template>
    <v-container>
    <div>
        {{ product.name }}

        <v-btn id='btnAdd' color="red darken-3" dark class="mb-2 mt-5 mr-2" type="button" @click="addToDemands">
            <v-icon dark>
                +
            </v-icon>
        </v-btn>

        <v-btn id='btnSub' color="red darken-3" dark class="mb-2 mt-5 mr-2" type="button" @click="subFromDemands">
            <v-icon dark>
                min
            </v-icon>
        </v-btn>
        <span class="quantity"> Anzahl:  {{ quantity }} </span>
    </div>
    </v-container>
</template>

<script>
export default {
    name: "ProductDemand",
    props: {
        product: {
            id: String,
            name: String,
            unit: String
        }
    },
    computed: {
        quantity() {
            return this.$store.state.demandsModule.demands.find(demand => demand.id === this.product.id)?.quantity || 0
        }
    },
    methods: {
        addToDemands() {
            this.$store.dispatch('addToDemands', this.product.id);
        },
        subFromDemands() {
            this.$store.dispatch('subFromDemands', this.product.id)
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
#btnAdd {

}

</style>