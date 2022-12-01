<template>
    <div>
    <v-container>
        <h1>Händler Produkte</h1>

    </v-container>
    <v-data-table
            :headers="headers"
            :items="$store.state.vendorsModule.products"
            sort-by="product"
            item-key="name"
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
                                        <v-text-field v-model="editedItem.name" label="Produkt name"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field v-model="editedItem.unit" label="Einheit(kg,l,..)"></v-text-field>
                                    </v-col>

                                </v-row>
                            </v-container>
                        </v-card-text>

                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="close">Abbrechen</v-btn>
                            <v-btn color="blue darken-1" text @click="create" v-if="editedItem.name == ''">Anlegen</v-btn>
                            <v-btn color="blue darken-1" text @click="update" v-else>Speichern</v-btn>
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
        <template #item.actions="{ item }">
            <v-icon class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
            <v-icon  @click="deleteItem(item)">mdi-delete</v-icon>
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
                text: 'Produkt',
                align: 'start',
                sortable: true,
                value: 'name',
            },

            {text: '', value:'unit',sortable: true},
            { text: '', value: 'actions', sortable: false },

        ],
        products: [],
        editedIndex: -1,
        editedItem: {
            name:'',
            unit:'',
            id: '',

        },
        defaultItem: {
            name:'',
            unit:'',
            id: '',


        },
    }),

    computed: {
        formTitle() {
            return this.editedIndex == -1 ? 'Neues Produkt' : 'Edit Item'
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
            console.log(this.$route.params.id);
            this.$store.dispatch("getVendorsProduct")

        },
        editItem(products) {
            this.editedIndex = this.products.indexOf(products)
            this.editedItem = Object.assign({}, products)
            this.dialog = true
            console.log(this.editedItem);
        },
        update() {
            this.$store.dispatch("editVendorsProduct", this.editedItem )
            console.log(this.products)
            this.close()
        },

        deleteItem(proId) {
            this.editedIndex = this.products.indexOf(proId)
            this.editedItem = Object.assign({}, proId)
            this.dialogDelete = true
        },

        deleteItemConfirm() {
           this.$store.dispatch('delVendorsProduct', this.editedItem)
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

        create() {
            this.$store.dispatch("addVendorsProduct",{product: this.editedItem, venId: this.$route.params.id})
            console.log(this.products)
            this.close()
        },
    },

}
</script>