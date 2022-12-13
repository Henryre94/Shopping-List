import Vue from 'vue'
import Vuex from 'vuex'
import {ProductsModule} from "@/store/modules/ProductsModule";
import {VendorsModule} from "@/store/modules/VendorsModule";
import {DemandsModule} from "@/store/modules/DemandsModule";
import {LoginModule} from "@/store/modules/LoginModule"
import axios from "axios";

Vue.use(Vuex)

axios.defaults.headers.common["Authorization"] = "Bearer " + localStorage.getItem("token");

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
