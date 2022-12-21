<template>
    <v-bottom-navigation v-model="value" fixed>
        <router-link style="text-decoration: none; color: inherit" to="/">
            <v-btn value="home">
                <span>Startseite</span>
                <v-icon x-large color="black">mdi-home-outline</v-icon>
            </v-btn>
        </router-link>

        <router-link style="text-decoration: none; color: inherit" to="/haendler">
            <v-btn value="vendor">
                <span>Händler</span>
                <v-icon x-large color="blue">mdi-store</v-icon>
            </v-btn>
        </router-link>

        <v-btn value="logout" @click="logout">
            <span>logout</span>
            <v-icon x-large color="red">mdi-logout</v-icon>
        </v-btn>
    </v-bottom-navigation>
</template>

<script>
export default {
    data: () => ({ value: "recent" }),
    methods: {
        logout() {
            this.$store.dispatch("logoutCurrentUser");
            this.$router.push("/");
        },
    },
};
</script>
