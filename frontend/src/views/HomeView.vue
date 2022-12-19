<template>
    <div>

        <TheHeader/>

        <router-link to="/login">Login</router-link>
        |
        <router-link to="/haendler">Händler</router-link>
        |
        <router-link to="/pub">Cafe 1090</router-link>
        |

        |
        <LogoutButton />



    </div>
</template>

<script>
// @ is an alias to /src
import TheHeader from '@/components/TheHeader.vue';
import LogoutButton from '@/components/LogoutButton.vue';

export default {
    name: 'HomeView',
    components: {
        TheHeader,
        LogoutButton,


    }
}
</script>
