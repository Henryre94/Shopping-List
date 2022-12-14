<template>
    <div>
        <TheHeader />
        <v-spacer></v-spacer>
        <v-data-table
            @click:row="handleClick"
            :headers="headers"
            :items="$store.state.vendorsModule.vendors"
            sort-by="vendor"
            item-key="name"
            :search="search"
            class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat>
                    <v-text-field
                        v-model="search"
                        clearable
                        flat
                        solo-inverted
                        label="Suche"
                        class="mt-9"
                    ></v-text-field>
                    <v-divider class="mx-4" inset vertical></v-divider>
                    <v-spacer></v-spacer>
                    <v-dialog v-model="dialog" max-width="500px">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn color="red" dark class="mb-7 mt-8" v-bind="attrs" v-on="on">Neuer Händler</v-btn>
                        </template>
                        <v-card>
                            <v-card-title>
                                <span class="text-h5">{{ formTitle }}</span>
                            </v-card-title>
                            <v-card-text>
                                <v-container>
                                    <v-row>
                                        <v-col cols="12" sm="6" md="4">
                                            <v-text-field
                                                class="newVend"
                                                v-model="editedItem.name"
                                                label="Händler name"
                                            ></v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="4">
                                            <v-text-field
                                                class="newVend"
                                                v-model="editedItem.address"
                                                label="Adresse"
                                            ></v-text-field>
                                        </v-col>
                                    </v-row>
                                </v-container>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="red" @click="close">Abbrechen</v-btn>
                                <v-btn color="green" @click="createVendor" v-if="editedItem.venId === ''"
                                    >Anlegen</v-btn
                                >
                                <v-btn color="green" @click="update" v-else>Speichern</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                    <v-dialog v-model="dialogDelete" max-width="500px">
                        <v-card>
                            <v-card-title class="text-h5"
                                >Achtung! Der Händler wird mit sämtlichen Produkten gelöscht !</v-card-title
                            >
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="red" @click="closeDelete">Abbrechen</v-btn>
                                <v-btn color="green" @click="deleteItemConfirm">OK</v-btn>
                                <v-spacer></v-spacer>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-toolbar>
            </template>
            <template #item.actions="{ item }" >
                <v-icon large color="black" class="mr-6" @click.stop="editItem(item)">
                    mdi-pencil
                </v-icon>
                <v-icon large color="red" class="mr-6" @click.stop="deleteItem(item)">
                    mdi-delete
                </v-icon>
                {{item.venId}}
                {{hasVendorDemands(item.venId)}}
                <v-icon v-if="hasVendorDemands(item.venId)" large color="blue" class="mr-6" @click="shoppingList(item)">
                    mdi-cart-plus
                </v-icon>
                <v-icon v-else large color="white" class="mr-6" @click="shoppingList(item)">
                    mdi-cart-plus
                </v-icon>

            </template>
        </v-data-table>
        <BottomBar></BottomBar>
    </div>
</template>

<script>
import TheHeader from "@/components/TheHeader";
import BottomBar from "@/components/BottomBar";

export default {
    components: { BottomBar, TheHeader },
    data: () => ({
        search: "",
        dialog: false,
        dialogDelete: false,
        selected: [],
        headers: [
            {
                text: "Händler",
                align: "start",
                sortable: true,
                value: "name",
                mobile: true,
            },
            { text: "", value: "actions", sortable: false, mobile: true },
        ],
        vendors: [],
        editedIndex: -1,
        editedItem: { name: "", address: "", venId: "" },
        defaultItem: { name: "", address: "", venId: "" },
    }),
    computed: {
        formTitle() {
            return this.editedIndex === -1 ? "Neuer Händler" : "Edit Item";
        },
    },
    watch: {
        dialog(val) {
            val || this.close();
        },
        dialogDelete(val) {
            val || this.closeDelete();
        },
    },
    created() {
        this.initialize();
    },
    methods: {
        initialize() {
            this.$store.dispatch("getVendors"); // Händler vom backend laden
        },
        //Weiterleitung auf die Händlerprodukte mit Händler ID und namen in der URL
        handleClick(value) {
            this.$router.push("/produktliste/" + value.name + "/" + value.venId);
        },
        // Händler können bearbeitet werden
        editItem(vendors) {
            this.editedIndex = this.vendors.indexOf(vendors);
            this.editedItem = Object.assign({}, vendors);
            this.dialog = true;
        },
        // Bearbeitete Händler werden gespeichert
        update() {
            this.$store.dispatch("editVendor", this.editedItem);
            this.close();
        },
        // Händler wird gelöscht
        deleteItem(venId) {
            this.editedIndex = this.vendors.indexOf(venId);
            this.editedItem = Object.assign({}, venId);
            this.dialogDelete = true;
        },
        // Bestätigung das Händler gelöscht werden soll
        deleteItemConfirm() {
            this.$store.dispatch("delVendor", this.editedItem);
            this.closeDelete();
        },
        // Weiterleitung auf die Einkaufsliste
        shoppingList(demand) {
            this.$router.push("/einkaufsliste/" + demand.name + "/" + demand.venId);
        },
        // Modal wird geschlossen
        close() {
            this.dialog = false;
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem);
                this.editedIndex = -1;
            });
        },
        // Modal wird nach dem Löschen geschlossen
        closeDelete() {
            this.dialogDelete = false;
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem);
                this.editedIndex = -1;
            });
        },
        // Händler wird erstellt
        createVendor() {
            this.$store.dispatch("addVendor", { name: this.editedItem.name, address: this.editedItem.address });
            this.close();
        },
        hasVendorDemands(venId){
            return this.$store.state.demandsModule.demands.some(vendor => vendor.venId === venId)
        }
    },
};
</script>
