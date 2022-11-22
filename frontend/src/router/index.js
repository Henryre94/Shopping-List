import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import VendorsView from '../views/VendorsView.vue'
import Pub1090View from "@/views/Pub1090View.vue";
import ProductView from "@/views/ProductView.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/product',
        name: 'product',
        component: ProductView
    },
    {
        path: '/vendor',
        name: 'vendor',
        component: VendorsView
    },
    {
        path: '/pub1090',
        name: 'pub1090',
        component: Pub1090View
    },


]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
