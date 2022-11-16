<template>
    <v-container>
        <h1>Verwaltungstabelle</h1>
        <AdminTable/>
    </v-container>
</template>

<script>


import AdminTable from "@/components/AdminTable";

export  default {
    name: 'AdminView.vue',
    components :{
        AdminTable,
    }
}
</script>