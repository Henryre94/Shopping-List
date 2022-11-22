<template>
    <div>
        {{ product.name }}

        <v-btn class="mx-2" fab dark small color="primary" type="button" @click="addToDemands">
            <v-icon dark>
                +
            </v-icon>
        </v-btn>
        <!--  button zum subtrahieren -->
        <v-btn class="mx-2" fab dark small color="primary" type="button" @click="subFromDemands">
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
            return this.$store.state.demandsModule.demands.find(demand => demand.pubId === this.product.pubId)?.quantity || 0
        }
    },
    methods: {
        addToDemands() {
            this.$store.commit('addToDemands', this.product.proId);
        },
        subFromDemands() {
            this.$store.commit('subFromDemands', this.product.proId)
        }
    }
}
</script>

<style scoped>

</style>