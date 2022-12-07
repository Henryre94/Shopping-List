<template>
    <div>
        <TheHeader />
        <router-link to="/haendler">Händler</router-link>
        |
        <router-link to="/pub">Cafe 1090</router-link>
    </div>
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
