import axios from 'axios';

export const LoginModule = {
    state: {
        currentUser: null
    },
    getters: {},
    mutations: {
        setCurrentUser(state, user) {
            state.currentUser = user
        }
    },
    actions:{
        async loginUser(context, user) {
            const response = await axios.post('/api/user/login', user); // backend address nachschauen
            context.commit('setCurrentUser', response.data.user);

        }
    }
}