<template>
    <div>
    <TheHeader></TheHeader>
    <v-card

            height="100%"
            width="100%"
    >
        <v-navigation-drawer
                class="blue-grey darken-4"
                dark
                permanent
                width="100%"
                height="100%"
        >
            <v-list>
                <v-list-item
                        v-for="item in items"
                        :key="item.title"
                        link
                >
                    <v-list-item-icon>
                        <router-link style="text-decoration: none; color: white;" to="/haendler"><v-icon x-large color="white">{{ item.iconStore }}</v-icon></router-link>
                        <router-link style="text-decoration: none; color: white;" to="/pub"> <v-icon x-large color="white">{{ item.icon1090 }}</v-icon></router-link>
                        <router-link style="text-decoration: none; color: white;" to="/pub"><v-icon x-large color="white">{{ item.icon1160 }}</v-icon></router-link>
                        <router-link style="text-decoration: none; color: white;" to="/login"><v-icon x-large color="white">{{item.iconLogin}}</v-icon></router-link>
                    </v-list-item-icon>

                    <v-list-item-content>
                       <router-link style="text-decoration: none; color: white;font-size: large;" to='/haendler'><v-list-item-title >{{ item.titleStore }}</v-list-item-title></router-link>
                        <router-link style="text-decoration: none; color: white;font-size: large;" to='/pub'><v-list-item-title style="font-size: large">{{ item.title1090 }}</v-list-item-title></router-link>
                        <router-link style="text-decoration: none; color: white;font-size: large;" to='/pub'><v-list-item-title style="font-size: large">{{ item.title1160 }}</v-list-item-title></router-link>
                       <router-link style="text-decoration: none; color: white;font-size: large;" to="/login"> <v-list-item-title >{{item.titleLogin}}</v-list-item-title></router-link>
                    </v-list-item-content>
                </v-list-item>
            </v-list>

            <template v-slot:append>
                <div class="pa-2">
                    <v-btn block color="red" @click="logout">
                        Logout
                    </v-btn>
                </div>
            </template>
        </v-navigation-drawer>
    </v-card>
    </div>
</template>

<script>
// @ is an alias to /src
import TheHeader from '@/components/TheHeader.vue';

export default {
    name: 'HomeView',
    components: {
        TheHeader,

    },
    data () {
        return {
            items: [
                { titleStore: 'Händler', iconStore: 'mdi-store' },
                { title1090: 'Cafe 1090', icon1090: 'mdi-silverware-variant' },
                { title1160: 'Cafe 1160', icon1160: 'mdi-silverware-variant' },
                { titleLogin: 'Login', iconLogin: ' mdi-lock-open' },
            ],
        }
    },
    methods: {
        logout() {
            console.log('geklickt')
            this.$store.dispatch('logoutCurrentUser');
            //   this.$router.push('/login')
        },
    },
}
</script>
