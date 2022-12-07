<template>
    <v-container>

        <TheHeader/>
        <router-link to="/product">Produkte</router-link>
        |
        <router-link to="/haendler">Händler</router-link>
        |
        <router-link to="/pub">Cafe 1090</router-link>


    </v-container>
</template>

<script>
// @ is an alias to /src
import TheHeader from '@/components/TheHeader.vue';

export default {
    name: 'HomeView',
    components: {
        TheHeader,
    }
}
</script>
