import Vue from "vue";
import Router from "vue-router";
import Login from "@/components/Login";
import SignUp from "@/components/SignUp";
import Converter from "@/components/Converter";

Vue.use(Router)

export default new Router({
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
    }, {
      path: '/converter',
      name: 'converter',
      component: Converter
    }
  ]
})
