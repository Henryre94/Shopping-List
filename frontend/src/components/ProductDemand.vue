<template>
    <div>
        <v-simple-table>
            <template v-slot:default>
                <thead>
                <tr>
                    <th class="text-left">
                        {{ product.name }}
                    </th>
                    <th class="text-left">
                        <v-btn color="red darken-3" dark class="mb-2 mt-5 mr-2 ml-2" type="button" @click="increaseDemands">
                            <v-icon dark>
                                +1
                            </v-icon>
                        </v-btn>
                    </th>
                    <th >
                        <v-btn color="red darken-3" dark class="mb-2 mt-5 mr-2 ml-2" type="button" @click="decreaseDemands">
                            <v-icon dark>
                                min
                            </v-icon>
                        </v-btn>
                    </th>
                    <th class="text-left">
                        <span class="quantity"> Anzahl:  {{ quantity }} </span>
                    </th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </template>
        </v-simple-table>
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
        increaseDemands() {
            this.$store.dispatch('increaseDemands', this.product.proId);
        },
        decreaseDemands() {
            this.$store.dispatch('decreaseDemands', this.product.proId)
        }
    },
}
</script>

<style scoped>

</style>