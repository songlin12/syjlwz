import { createRouter, createWebHistory } from 'vue-router'
import Admin from '../views/admin/Admin.vue'
import Welcome from '../views/admin/Welcome.vue'
const routes = [
  {
    path: '/main',
    name: 'Main',
    component: () => import('../views/Main'),
    redirect: "/welcome",
    children: [
      {
        path: '/admin',
        name: 'Admin',
        component: Admin,
        meta:{requireAuth:true}
      },
      {
        path: '/welcome',
        name: 'Welcome',
        component: Welcome,
        meta:{requireAuth:true}
      },
      {
        path: '/updatePwd',
        name: 'UpdatePwd',
        component: () => import('../views/admin/UpdatePwd'),
        meta:{requireAuth:true}
      },
      {
        path: '/zpqy',
        name: 'Zpqy',
        component: () => import('../views/admin/Zpqy'),
        meta:{requireAuth:true}
      },
      {
        path: '/zptype',
        name: 'Zptype',
        component: () => import('../views/admin/Zptype'),
        meta:{requireAuth:true}
      },
      {
        path: '/zpxs',
        name: 'Zpxs',
        component: () => import('../views/admin/Zpxs'),
        meta:{requireAuth:true}
      },
      {
        path: '/uinfo',
        name: 'Uinfo',
        component: () => import('../views/admin/Uinfo'),
        meta:{requireAuth:true}
      },
      {
        path: '/zpinfo',
        name: 'Zpinfo',
        component: () => import('../views/admin/Zpinfo'),
        meta:{requireAuth:true}
      },
      {
        path: '/plinfo',
        name: 'Plinfo',
        component: () => import('../views/admin/Plinfo'),
        meta:{requireAuth:true}
      },
      {
        path: '/updateUinfo',
        name: 'UpdateUinfo',
        component: () => import('../views/admin/UpdateUinfo'),
        meta:{requireAuth:true}
      },
      {
        path: '/uinfoZpinfo',
        name: 'UinfoZpinfo',
        component: () => import('../views/admin/UinfoZpinfo'),
        meta:{requireAuth:true}
      },
      {
        path: '/uinfoPlinfo',
        name: 'UinfoPlinfo',
        component: () => import('../views/admin/UinfoPlinfo'),
        meta:{requireAuth:true}
      },
    ]

  },
 
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
 

 
 

  {
    path: '/index',
    name: 'Index',
    component: () => import('../views/Index'),
    redirect: "/home",
    children: [
      {
        path: '/home',
        name: 'Home',
        component: () => import('../views/web/Home'),
      },
      {
        path: '/zpinfoView',
        name: 'ZpinfoView',
        component: () => import('../views/web/ZpinfoView'),
      },
      {
        path: '/register',
        name: 'Register',
        component: () => import('../views/web/Register'),
      },
      {
        path: '/forget',
        name: 'Forget',
        component: () => import('../views/web/ForgetPage'),
      },
      {
        path: '/uinfoLogin',
        name: 'UinfoLogin',
        component: () => import('../views/web/UinfoLogin'),
      },

      {
        path: '/zpinfoAll',
        name: 'ZpinfoAll',
        component: () => import('../views/web/ZpinfoAll'),
      },
      {
        path: '/zpinfoSearch',
        name: 'ZpinfoSearch',
        component: () => import('../views/web/ZpinfoSearch'),
      },
    ]

  }

]



const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})



router.beforeEach((to, from, next) => {
  if (to.path == '/login') {
    sessionStorage.removeItem('currentUser');
    sessionStorage.removeItem('userinfo');
  }
  let currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
  let userinfo = JSON.parse(sessionStorage.getItem('userinfo'));
 
  if (to.meta.requireAuth) {
    if(to.path != '/login'){
       if(!currentUser && !userinfo ){

        next({ path: '/login' });
       }else{
        next();
       }
    }else{
      next();
    }
    
  } else {

    next();
  }
})

export default router
