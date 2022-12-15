import axios from "axios";

export const LoginModule = {
    state: {
        currentUser: null
    },
    getters: {},
    mutations: {
        setCurrentUser(state, user) {
            state.currentUser = user;
        },
    },
    actions: {
        async loginUser(context, user) {
            const response = await axios
               .get("/api/token", {
              //  .get("/api/credentials", {
                    auth: {
                        username: user.username,
                        password: user.password,
                    },
                });
                localStorage.setItem("token", response.data.token);
                context.commit('setCurrentUser', response.data.credentialsDTO);
        },
         async currentUser(context) {
            const response = await axios.get('/api/credentials')
            context.commit('setCurrentUser', response.data.credentialsDTO)
         },

        logoutCurrentUser(context) {
            localStorage.removeItem('token');
           context.commit('setCurrentUser', null);
        }
    },
};
