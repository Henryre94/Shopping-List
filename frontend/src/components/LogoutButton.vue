<template>
    <div>
        <v-btn @click="logout">Logout</v-btn>
    </div>
</template>

<script>
export default {
    name: "LogoutButton",
    methods: {
        logout() {
            console.log('geklickt')
            this.$store.dispatch('logoutCurrentUser');
         //   this.$router.push('/login')
        },
    },
};
</script>
