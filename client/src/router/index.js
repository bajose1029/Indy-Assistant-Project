import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import RegisterView from '../views/RegisterView.vue'
import CalenderView from '../views/CalenderView.vue'
import EmailView from '../views/EmailView.vue'
import ManagerView from '../views/ManagerView.vue'
import ArtistView from '../views/ArtistView.vue'


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta: {
        requiresAuth: true,
        isAdmin: true
      }
    },
    {
      path: '/manager/',
      name: 'manager',
      component: ManagerView,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/artist/',
      name: 'artist',
      component: ArtistView,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: LogoutView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/calender",
      name: "calender",
      component: CalenderView,
      // meta: {
      //   requiresAuth: true
      // }
    },
    {
      path: "/email",
      name: "email",
      component: EmailView,
      // meta: {
      //   requiresAuth: true
      // }
    }
  ];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);


  const isAdmin = to.matched.some(x => x.meta.isAdmin);
  const isArtist = to.matched.some(x => x.meta.isArtist);
  const isManager = to.matched.some(x => x.meta.isManager);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  console.log(store.state.user.type);

  if(isAdmin && store.state.user.type !== 'Admin') {
    return { name: "login" }
  }
  else if(isArtist && store.state.user.type !== 'Artist') {
    return { name: "login" }
  }
  else if(isManager && store.state.user.type !== 'Manager') {
    return { name: "login" }
  }

  //else ifs
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
