import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import VendorsView from '../views/VendorsView.vue'
import PubView from "@/views/PubView.vue"
import VendorsProductView from "@/views/VendorsProductView"
import VendorsAddView from "@/views/VendorsAddView"
import LoginView from "@/views/LoginView"
import AdminDemandView from "@/views/AdminDemandView"
import store from '@/store'


Vue.use(VueRouter)

const adminGuard = (to, from, next) => {
    if (store.state.loginModule.currentUser?.admin) {
        next();
    } else {
       next('/')
    }
};

const routes = [
    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/product',
        name: 'product',
        component: VendorsProductView,
        beforeEnter: adminGuard
    },
    {
        path: '/vendor',
        name: 'vendor',
        component: VendorsView,
        beforeEnter: adminGuard
    },
    {
        path: '/pub',
        name: 'pub',
        component: PubView
    },
    {
        path: '/produktliste/:vendor/:vendorId',
        component: VendorsProductView,
        beforeEnter: adminGuard
    },
    {
        path: '/haendler',
        component: VendorsAddView,
        beforeEnter: adminGuard
    },
    {
        path: '/einkaufsliste/:vendor/:vendorId',
        name: 'shopping',
        component: AdminDemandView,
        beforeEnter: adminGuard
    },
]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router
