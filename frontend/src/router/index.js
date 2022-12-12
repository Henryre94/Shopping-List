import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import VendorsView from '../views/VendorsView.vue'
import PubView from "@/views/PubView.vue";
import VendorsProductView from "@/views/VendorsProductView";
import VendorsAddView from "@/views/VendorsAddView";
import LoginView from "@/views/LoginView";
import AdminDemandView from "@/views/AdminDemandView"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'login',
        component: LoginView
    },
    {
        path: '/home',
        name: 'home',
        component: HomeView
    },
    {
        path: '/product',
        name: 'product',
        component: VendorsProductView
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
        path: '/produktliste/:vendor/:vendorId',
        component: VendorsProductView
    },
    {
        path: '/haendler',
        component: VendorsAddView
    },
    {
        path: '/einkaufsliste/:vendor/:vendorId',
        name: 'shopping',
        component: AdminDemandView
    },
]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
