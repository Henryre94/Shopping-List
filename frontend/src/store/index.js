import Vue from 'vue'
import Vuex from 'vuex'
import {ProductsModule} from "@/store/modules/ProductsModule";
import {VendorsModule} from "@/store/modules/VendorsModule";
import {DemandsModule} from "@/store/modules/DemandsModule";
import {LoginModule} from "@/store/modules/LoginModule"
import axios from "axios";

Vue.use(Vuex)

axios.interceptors.request.use(request => {

    if(!request.headers.Authorization && localStorage.getItem('token')) {

        request.headers.Authorization = 'Bearer ' + localStorage.getItem('token');
    }
return request
});

export default new Vuex.Store({
    state: {},
    mutations: {},
    actions: {},
    modules: {
        productsModule: ProductsModule,
        vendorsModule: VendorsModule,
        demandsModule: DemandsModule,
        loginModule: LoginModule
    }
})
