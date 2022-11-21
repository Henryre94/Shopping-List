<template>

    <v-app id="inspire">
        <v-content>
            <v-container fluid fill-height class="back">
                <v-layout align-end justify-center>
                    <v-flex xs12 sm8 md3 pt6>


                        <v-card-text>
                            <v-form class="log">
                                <v-text-field outlined class="benutzer" dark color="white" name="login" label="Benutzername"
                                              type="text" :rules="[rules.required]"></v-text-field>
                                <v-text-field outlined dark :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                              :rules="[rules.required, rules.min]" :type="show ? 'text' : 'password'" name="input-10-2"
                                              label="Passwort" value="" class="input-group--focused" @click:append="show = !show">
                                </v-text-field>
                                <v-btn id="button"  to="">Login</v-btn>
                            </v-form>

                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>

                        </v-card-actions>

                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
export default {
    name: 'LoginForm',
    props: {
        source: String,
    },
    data() {
        return {
            show: false,
            password: 'Password',
            rules: {
                required: value => !!value || 'Erforderlich',
                min: v => v.length >= 8 || 'Min 8 Zeichen',
            },
        }
    },
};
</script>

<style scoped>
.back {
    background-image: url("../assets/cafelogin.jpg");
    background-size: cover;
    background-position: center;
    background-position-y: -178px;



}

.log {
    margin-bottom: 60%;
}
</style>