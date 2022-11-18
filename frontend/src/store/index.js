import Vue from 'vue'
import Vuex from 'vuex'
import {ProductsModule} from "@/store/modules/ProductsModule";
import {VendorsModule} from "@/store/modules/VendorsModule";
import {DemandsModule} from "@/store/modules/DemandsModule";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
    },
    mutations: {
    },
    actions: {
    },
    modules: {
        productsModule: ProductsModule,
        vendorsModule: VendorsModule,
        demandsModule: DemandsModule
    }
})
