<template>
    <v-main>
        <v-container fluid fill-height class="back">
            <v-layout align-center justify-center>
                <v-flex xs12 sm8 md3 pt6>
                    <v-card-text>
                        <h1 style="color: white">LOGIN</h1>
                        <br>
                        <br>
                        <br>
                        <v-form class="log">
                            <v-text-field
                                v-model="username"
                                outlined
                                class="username"
                                dark
                                color="white"
                                name="login"
                                label="Benutzername"
                                type="text"
                                :rules="[rules.required]"
                            ></v-text-field>
                            <v-text-field
                                v-model="password"
                                outlined
                                dark
                                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                :rules="[rules.required, rules.min]"
                                :type="show ? 'text' : 'password'"
                                name="input-10-2"
                                label="Passwort"
                                value=""
                                class="input-group--focused"
                                @click:append="show = !show"
                            >
                            </v-text-field>
                            <v-btn @click.prevent="login()" id="button" to="">Login</v-btn>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                    </v-card-actions>
                </v-flex>
            </v-layout>
        </v-container>
    </v-main>
</template>

<script>
export default {
    name: "LoginForm",
    props: {
        source: String,
    },
    data() {
        return {
            username: "",
            password: "",
            error: false,
            show: false,
            rules: {
                required: (value) => !!value || "Erforderlich",
                min: (v) => v.length >= 4 || "Min 4 Zeichen",
            },
        };
    },
    methods: {
        async login() {
            try {
                await this.$store.dispatch("loginUser", {
                    username: this.username,
                    password: this.password,
                });
               await this.$router.push('/')
            } catch (error) {
                console.error(error);
            }
        },
    },
};
</script>

<style scoped>
.back {
    background-color: grey;
    background-size: cover;
    background-position: center;
    background-position-y: -178px;
    background-repeat: repeat-y;
}
.log {
    margin-bottom: 40%;
}
</style>
