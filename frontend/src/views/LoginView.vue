<template>
    <v-content>
        <v-container fluid fill-height class="back">
            <v-layout align-end justify-center>
                <v-flex xs12 sm8 md3 pt6>

                    <v-card-text>
                        <v-form class="log">
                            <v-text-field v-model="username" outlined class="username" dark color="white" name="login" label="Benutzername"
                                          type="text" :rules="[rules.required]"></v-text-field>
                            <v-text-field v-model="password" outlined dark :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                          :rules="[rules.required, rules.min]" :type="show ? 'text' : 'password'"
                                          name="input-10-2"
                                          label="Passwort" value="" class="input-group--focused"
                                          @click:append="show = !show">
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
    </v-content>
</template>

<script>
export default {
    name: 'LoginForm',
    props: {
        source: String,
    },
    data() {
        return {
            username: '',
            password: '',
            error: false,
            show: false,
            rules: {
                required: value => !!value || 'Erforderlich',
                min: v => v.length >= 4 || 'Min 4 Zeichen',
            },
        }
    },
    methods: {
        login() {
            this.$store.dispatch('loginUser', {
                username: this.username,
                password: this.password
            });
        }
    },
    // in arbeit
    //created() {
    //    axios.defaults.headers.common['Authorization']= 'Bearer' + localStorage.getItem('blog_token');
    //    this.$store.dispatch('currentUser/getUser');
   // }
};
</script>

<style scoped>
.back {
    background-image: url("../assets/cafelogin.jpg");
    background-size: cover;
    background-position: center;
    background-position-y: -178px;
    background-repeat: repeat-y;
}
.log {
    margin-bottom: 40%;
}
</style>