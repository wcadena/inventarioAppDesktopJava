import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

Vue.use(Router)

function load (component) {
  return () => import(`../pages/${component}`)
}

var router = new Router({
  routes: [
    {path: '', component: load('index')},
    {path: '/os', component: load('os')},
    {path: '/system', component: load('system')},
    {path: '/cpu', component: load('cpu')},
    {path: '/ram', component: load('ram')},
    {path: '/disk', component: load('disk')},
    {path: '/battery', component: load('battery')},
    {path: '/graphics', component: load('graphics')},
    {path: '/network', component: load('network')},
    {path: '*', component: load('notfound')}
  ]
})

router.beforeEach((to, from, next) => {
  next()
  store.dispatch('SET_TITLE', 'System Infomation')
})

export default router
