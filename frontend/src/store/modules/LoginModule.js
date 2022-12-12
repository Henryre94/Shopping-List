import axios from "axios";

/*  Sebi 
axios.interceptors.request.use(config => {
  const jwt = localStorage.getItem('jwt');
  if (jwt) {
    // Falls der Token vom Server bereits mit "Bearer ..." kommt, dann kann man ihn hier auslassen
    // config.headers.Authorization = 'Bearer ' + jwt;
    config.headers.Authorization = jwt;
  }
  return config;
})
*/

axios.defaults.headers.common["Authorization"] = "Bearer" + localStorage.getItem("token");

export const LoginModule = {
    state: {
        user: {},
       
    },
    getters: {},
    mutations: {
        setUser(state, data) {
            state.user = data;
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
                context.commit('setCurrentUser', response.data.token);

              //  .then((response) => {
              //      console.log(response.data);
              //      if (response.data.access_token) {
                        //save token
               //         localStorage.setItem("blog_token", response.data.access_token);
                        // noch filtern wer was sehen darf
                        //  this.$router.push('/')
                       // window.location.replace("/");
              //      }
              //  });
             // context.commit('setCurrentUser', response.data);
        },
    },
};
