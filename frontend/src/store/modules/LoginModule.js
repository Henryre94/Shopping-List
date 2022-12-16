import axios from "axios";

export const LoginModule = {
    state: {
        currentUser: null
    },
    mutations: {
        setCurrentUser(state, user) {
            state.currentUser = user;
        },
    },
    actions: {
        async loginUser(context, user) {
            const response = await axios
               .get("/api/token", {
                    auth: {
                        username: user.username,
                        password: user.password,
                    },
                });
                localStorage.setItem("token", response.data.token);
                context.commit('setCurrentUser', response.data.credentialsDTO);
        },
         async currentUser(context) {
           if (localStorage.getItem("token")) {
               const response = await axios.get('/api/credentials')
               context.commit('setCurrentUser', response.data)
           }
         },

        logoutCurrentUser(context) {
            localStorage.removeItem('token');
           context.commit('setCurrentUser', null);
        }
    },
};
