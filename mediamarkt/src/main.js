import { createApp } from 'vue'
import App from './App.vue'
import {router} from "@/router/index.js";
import * as Vue from "@fortawesome/fontawesome-svg-core";


Vue.config.productionTip = false

// Navigation guard to check if authentication is required for a route
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // Check if the user is logged in (you can check your token here)
        const isLoggedIn = sessionStorage.getItem("token")/* your logic to check if the user is logged in */;

        if (!isLoggedIn) {
            // If the user is not logged in, redirect to the login page
            next('/');
        } else {
            // If the user is logged in, proceed to the requested route
            next();
        }
    } else {
        // If the route doesn't require authentication, proceed to the requested route
        next();
    }
});

createApp(App).use(router).mount('#app')
