<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="products"
                sort-by="product"
                item-key="name"
                show-select
                select-all
                :search="search"
                class="elevation-1">
            <template v-slot:top>
                <v-toolbar flat>

                    <v-text-field v-model="search" clearable flat solo-inverted label="Suche" class="mt-9"></v-text-field>

                    <v-divider class="mx-4" inset vertical></v-divider>
                    <v-spacer></v-spacer>
                    <v-dialog v-model="dialog" max-width="500px">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn color="red" dark class="mb-6 mt-9" v-bind="attrs" v-on="on">
                                Neues Produkt
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
                                            <v-text-field v-model="editedItem.product" label="Produkt name"></v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="4">

                                            <v-text-field v-model="editedItem.unit" label="Stückzahl"></v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="4">
                                            <v-text-field v-model="editedItem.category" label="Kategorie"></v-text-field>
                                        </v-col>
                                        <v-text-field v-model="editedItem.vendor" label="Händler"></v-text-field>

                                        <v-col cols="12" sm="6" md="4">
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
                            <v-card-title class="text-h5">Achtung! Das Produkt wird gelöscht</v-card-title>
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
            <template v-slot:item.actions="{ item }">
                <v-icon small class="mr-2" @click="editItem(item)">
                    mdi-pencil
                </v-icon>
                <v-icon small @click="deleteItem(item)">
                    mdi-delete
                </v-icon>
            </template>
            <template v-slot:no-data>
                <v-btn color="primary" @click="initialize">

                    Reset
                </v-btn>

            </template>

        </v-data-table>
        <v-btn color="red" @click="deleteItem" dark class="mx-4 mt-9">Löschen</v-btn>
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
                text: 'Produkt',
                align: 'start',
                sortable: true,
                value: 'product',
            },

            { text: 'Stückzahl', value: 'unit' },
            { text: 'Kategorie', value: 'category' },
            { text: 'Händler', value: 'vendor' },
            { text: 'Bearbeiten', value: 'actions', sortable: false }, ,
        ],
        products: [],
        editedIndex: -1,
        editedItem: {
            product: '',
            unit: 0,
            category: '',
            vendor: '',
        },
        defaultItem: {
            product: '',
            unit: 0,
            category: '',
            vendor: '',
        },
    }),

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
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
            this.products = [

                {
                    product: 'Tomaten',
                    unit: 4,
                    category: 'Gemüse',
                    vendor: 'Metro'
                },
                {
                    product: 'Rindfleisch',
                    unit: 4,
                    category: 'Fleisch',
                    vendor: 'Metro'
                },
                {
                    product: 'Vodka',
                    unit: 12,
                    category: 'Alkohol',
                    vendor: 'Metro'
                },
                {
                    product: 'Bier',
                    unit: 8,
                    category: "Alkohol",
                    vendor: 'Metro'
                },
                {
                    product: 'Lammkotlett',
                    unit: 4,
                    category: 'Fleisch',
                    vendor: 'Metro'
                },
                {
                    product: 'Karotten',
                    unit: 6,
                    category: 'Gemüse',
                    vendor: 'Metro'
                },
                {
                    product: 'Milch',
                    unit: 7,
                    category: 'Milchprodukte',
                    vendor: 'Metro'
                },
                {
                    product: 'Käse',
                    unit: 2,
                    category: 'Milchprodukte',
                    vendor: 'Metro'
                },
                {
                    product: 'Kopfsalat',
                    unit: 4,
                    category: 'Gemüse',
                    vendor: 'Metro'
                },
                {
                    product: 'Fisch',
                    unit: 2,
                    category: 'Fleisch',
                    vendor: 'Metro'
                },
            ]
        },

        editItem(item) {
            this.editedIndex = this.products.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialog = true
        },

        deleteItem(item) {
            this.editedIndex = this.products.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialogDelete = true
        },

        deleteItemConfirm() {
            this.products.splice(this.editedIndex, 1)
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
            if (this.editedIndex > -1) {
                Object.assign(this.products[this.editedIndex], this.editedItem)
            } else {
                this.products.push(this.editedItem)
            }
            this.close()
        },
    },

}
</script>