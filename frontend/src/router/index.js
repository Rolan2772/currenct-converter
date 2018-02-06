import Vue from "vue";
import Router from "vue-router";
import Login from "@/components/Login";
import SignUp from "@/components/SignUp";
import Converter from "@/components/Converter";
import store from "../store/modules/userStore";

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'converter',
      component: Converter
    }, {
      path: '/login',
      name: 'login',
      component: Login
    }, {
      path: '/signup',
      name: 'signup',
      component: SignUp
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/signup') {
    next()
  } else if (store.state.user.name) {
    next()
  } else {
    next({path: '/login'})
  }
})

export default router;
