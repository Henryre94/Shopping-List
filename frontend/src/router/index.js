import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import VendorsView from '../views/VendorsView.vue'
import PubView from "@/views/PubView.vue";
import ProductView from "@/views/ProductView.vue";
import VendorsProductView from "@/views/VendorsProductView";
import VendorsAddView from "@/views/VendorsAddView";
import LoginView from "@/views/LoginView";

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
        path: '/pub',
        name: 'pub',
        component: PubView
    },
    {
        path: '/vendorProducts',
        component: VendorsProductView
    },
    {
        path: '/vendorsAdd',
        component: VendorsAddView
    },

    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
