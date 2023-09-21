import { createApp } from 'vue'
import App from './App.vue'
import {createRouter, createWebHistory} from "vue-router";
import RoodComponent from "@/components/RoodComponent";
import BlauwComponent from "@/components/BlauwComponent";
import HelloWorld from "@/components/HelloWorld";
import NotFoundComponent from "@/components/NotFoundComponent";
import InsectCounter from "@/components/InsectCounter";

const  router = createRouter({
    history : createWebHistory(),
    routes: [
        {path: '/' ,component: HelloWorld},

        {path: '/rood' ,component: RoodComponent},
        {path: '/blauw' ,component: BlauwComponent},
        {path: '/counter' ,component: InsectCounter},
        {path: '/:pathMatch(.+)+', component:NotFoundComponent},
    ]
});

createApp(App).use(router).mount('#app')
