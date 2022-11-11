import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ProductListView from '../views/ProductListView.vue'
import SupplierListView from '../views/SupplierListView.vue'

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
    component: ProductListView
  },
  {
    path: '/supplier',
    name: 'supplier',
    component: SupplierListView
  },
  //{
  //  path: '/pub',
  //  name: 'pub',
  //  component: PubView
  //},
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
