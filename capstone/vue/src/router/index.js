import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import LogMeal from '../views/LogMeal.vue'
import Profile from '../views/Profile.vue'
import LogWorkout from '../views/LogWorkout.vue'
import RecentFoods from '../views/RecentFoods.vue'
import FoodForm from '../views/AddFoodForm.vue'
import QuickAddMeals from '../views/QuickAddMeals.vue'
import ProfilePicture from '../views/ProfilePicture.vue'
import ChangePassword from '../views/ChangePassword.vue'
import Progress from '../components/BarChart.vue'
import AddWorkout from '../views/AddWorkout.vue'



Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path:"/myMeals",
      name: 'myMeals',
      component: LogMeal,
      meta: {
        requiresAuth: true
      }

    },
    {
      path:"/profile",
      name: 'profile',
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:"/workout",
      name: 'workout',
      component: LogWorkout,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/recentFoods',
      name: 'recentFoods',
      component: RecentFoods,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/addFoodForm',
      name: 'addFoodForm',
      component: FoodForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/quickAddMeals',
      name: 'quickAddMeals',
      component: QuickAddMeals,
      meta: {
        requiresAuth: true
      }

    },
    {
      path: '/profile/avatar',
      name: 'profilePicture',
      component: ProfilePicture,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/profile/password',
      name: 'changePassword',
      component: ChangePassword,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/progress',
      name: 'progress',
      component: Progress,
      meta: {
        requiresAuth: true
      }

    },
    {   
      path: '/addWorkout',
      name: 'addWorkout',
      component: AddWorkout,
      meta: {
        requiresAuth: true
      }
    } 

  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
