<template>
    <div>
        <v-container>
            <h1>Händler</h1>

        </v-container>
        <v-data-table
                @click:row="handleClick"
                :headers="headers"
                :items="$store.state.vendorsModule.vendors"
                sort-by="vendor"
                item-key="name"
                :search="search"
                class="elevation-1">
            <template v-slot:top>
                <v-toolbar flat>

                    <v-text-field v-model="search" clearable flat solo-inverted label="Suche" class="mt-9"></v-text-field>


                    <v-spacer> </v-spacer>

                    <v-dialog v-model="dialog" max-width="500px">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn color="red" dark class="mb-6 mt-9" v-bind="attrs" v-on="on" >
                                +
                            </v-btn>
                        </template>
                        <v-card>
                            <v-card-title>
                                <span class="text-h5">{{ formTitle }}</span>
                            </v-card-title>

                            <v-card-text>
                                <v-container>
                                    <v-row>
                                        <v-col cols="12" sm="6" md="4">
                                            <v-text-field class="newVend" v-model="editedItem.vendor" label="Händler name"></v-text-field>
                                        </v-col>

                                    </v-row>
                                </v-container>
                            </v-card-text>

                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="blue darken-1" text @click="close">
                                    Abbrechen
                                </v-btn>
                                <v-btn color="blue darken-1" text @click="save">
                                    Speichern
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                    <v-dialog v-model="dialogDelete" max-width="500px">
                        <v-card>
                            <v-card-title class="text-h5">Achtung! Der Händler wird gelöscht</v-card-title>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="blue darken-1" text @click="closeDelete">Abbrechen</v-btn>
                                <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
                                <v-spacer></v-spacer>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-toolbar>
            </template>
            <template #item.actions="{ item }">
                <v-icon  class="mr-2" @click="editItem(item)">
                    mdi-pencil
                </v-icon>
                <v-icon  @click="deleteItem(item)">
                    mdi-delete
                </v-icon>
                <v-icon  @click="shoppingList(item)">
                    mdi-storefront
                </v-icon>
            </template>


        </v-data-table>
    </div>
</template>


<script>

export default {
    data: () => ({

        search: "",
        dialog: false,
        dialogDelete: false,
        selected: [],
        headers: [
            {
                text: 'Händler',
                align: 'start',
                sortable: true,
                value: 'name',
            },


            { text: '', value: 'actions', sortable: false },
        ],
        vendors: [],
        editedIndex: -1,
        editedItem: {
            vendor: '',

        },
        defaultItem: {
            vendor: '',

        },
    }),

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'Neuer Händler' : 'Edit Item'
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
    mounted() {
        this.$store.dispatch("getVendors")
        this.$store.dispatch("addVendor")
        this.$store.dispatch("deleteVendor")


    },

    methods: {

        initialize() {
            this.vendors = [

            ]
        },
        handleClick(value){

            console.log("row clicked", value.name )
            this.$router.push("/vendorProducts", value.venId)
        },

        editItem(item) {
            this.editedIndex = this.vendors.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialog = true
        },

        deleteItem(item) {
            this.editedIndex = this.vendors.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialogDelete = true
        },
        shoppingList(){

        },

        deleteItemConfirm() {
            this.vendors.splice(this.editedIndex, 1)
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

        save() {
            this.$store.commit('addVendor', {name: this.editedItem});
             {
              //   this.vendors.push(this.editedItem)
            }
            this.close()
        },

    },

}
</script>
