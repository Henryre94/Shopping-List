<template>
    <div>
        <v-simple-table>
            <template v-slot:default>
                <tbody>
                <tr>
                    <td data-label>
                        {{ product.name }}
                    </td>
                    <td data-label>
                        <v-btn color="#d2bcad" dark class="mb-2 mt-2 " type="button" @click="increaseDemands">
                            <v-icon dark>
                                mdi-plus
                            </v-icon>
                        </v-btn>
                    </td>
                    <td data-label>
                        <v-btn color="#d2bcad" dark class="mb-2 mt-2" type="button" @click="decreaseDemands">
                            <v-icon dark>
                                mdi-minus
                            </v-icon>
                        </v-btn>
                    </td>
                    <td data-label>
                        <span class="quantity"> Gesamt:  {{ quantity }} </span>
                    </td>
                </tr>
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
td {
    width: 25%;
    border-bottom: 1px solid black;
}

tr {
    width: 100%;
    overflow: scroll;
}

v-simple-table {
    width: 100%;
}

v-simple-table, tr, td {
    border-collapse: collapse;
}

@media screen and (max-width: 700px) {
    tr {
        float: left;
        width: 100%;
    }

    td {
        float: left;

        width: 100%;
    }

    td::before {
        content: attr(data-label);
        word-wrap: break-word;
        width: 20%;
        float: left;
    }
}
</style>