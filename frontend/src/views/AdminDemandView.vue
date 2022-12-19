<template>
    <div>
        <TheHeader></TheHeader>
        <v-spacer></v-spacer>
        <v-container>
            <router-link to="/haendler"><v-icon large rot class="mr-6" >mdi-arrow-left-bold</v-icon></router-link>
            <router-link to="/"><v-icon large >mdi-home-outline</v-icon></router-link>
        </v-container>
        <v-data-table
                :headers="headers"
                :items="$store.state.demandsModule.demands"
                multi-sort
                item-key="name"
                :search="search"
                class="elevation-1">
            <template v-slot:top>
                <v-toolbar flat>
                    <v-text-field v-model="search" clearable flat solo-inverted label="Suche" class="mt-9"></v-text-field>
                    <v-spacer></v-spacer>
                    <v-dialog v-model="dialog" max-width="500px">
                        <v-card>
                            <v-card-title>
                                <span class="text-h5">{{ formTitle }}</span>
                            </v-card-title>

                            <v-card-text>
                                <v-container>
                                    <v-row>
                                        <v-col cols="12" sm="6" md="4">
                                            <v-text-field v-model="editedItem.quantity" label="Stückzahl"></v-text-field>
                                        </v-col>
                                    </v-row>
                                </v-container>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="red" @click="close">Abbrechen</v-btn>
                                <v-btn color="green" @click="update" >Speichern</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                    <v-dialog v-model="dialogDelete" max-width="500px">
                        <v-card>
                            <v-card-title class="text-h5">Achtung! Das Produkt wird aus der Einkaufsliste gelöscht</v-card-title>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="red" @click="closeDelete">Abbrechen</v-btn>
                                <v-btn color="green" @click="deleteItemConfirm()">OK</v-btn>
                                <v-spacer></v-spacer>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-toolbar>
            </template>
            <template #item.actions="{ item }">
                <v-icon large color="blue" class="mr-6" @click="decreaseProductDemand(item)">
                    mdi-minus
                </v-icon>
                <v-icon large color="red" class="mr-6" @click="deleteItem(item)">
                    mdi-delete
                </v-icon>
            </template>
        </v-data-table>
    </div>
</template>

<script>
import TheHeader from "@/components/TheHeader";
export default {
    components: {TheHeader},
    data: () => ({

        search: "",
        dialog: false,
        dialogDelete: false,
        selected: [],
        headers: [
            {
                text: 'Produkt',
                align: 'start',
                sortable: true,
                value: 'name',
                mobile: true,
            },
            {text: 'Pub', value: 'pubName'},
            {text: 'Stückzahl', value: 'quantity'},
            {text: 'Bearbeiten', value: 'actions', sortable: false},
        ],
        demands: [],
        editedIndex: -1,
        editedItem: {name: '',pubName: '', quantity: '', proId: '',},
        defaultItem: {name: '', pubName: '', quantity: '', proId: '',},
    }),
    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'Neues Produkt' : 'Edit Item'
        },
    },
    watch: {
        dialog(val) {
            val || this.close()
        },
        dialogDelete(val) {
            val || this.closeDelete()
        },
    },
    created() {
        this.initialize()
    },
    methods: {

        initialize() {
            console.log(this.$route.params.vendorId);
            this.$store.commit("getVendorsDemand")
            this.$store.dispatch("getVendorsDemand", this.$route.params.vendorId)
        },
        decreaseProductDemand(item){
            console.log(item)
            this.$store.dispatch('decreaseDemands', item.proId)

        },
        update() {
            this.$store.dispatch("editVendorsDemand", {demands: this.editedItem,} )
            console.log(this.demands)
            this.close()
        },
        deleteItem(item) {
            this.editedIndex = this.demands.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialogDelete = true
        },
        deleteItemConfirm() {
            this.$store.dispatch('delVendorsDemand', this.editedItem)
            this.closeDelete()
        },
        close() {
            this.dialog = false
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
            })
        },
        closeDelete() {
            this.dialogDelete = false
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
            })
        },
    },
}
</script>