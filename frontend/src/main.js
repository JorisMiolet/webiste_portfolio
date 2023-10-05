import { createApp } from 'vue'
import App from './App.vue'
import {createRouter, createWebHashHistory} from "vue-router";
import HelloWorld from "@/components/HelloWorld";
import RoodComponent from "@/components/RoodComponent";
import BlauwComponent from "@/components/BlauwComponent";
import InsectCounter from "@/components/InsectCounter";
import NotFoundComponent from "@/components/NotFoundComponent";

const router = createRouter({
history: createWebHashHistory(),
    routes: [

        {path: '/', component : HelloWorld
        },
        {path: '/rood', component: RoodComponent,
            children: [{path: 'blauw', component: BlauwComponent},  {path: 'rood', component: RoodComponent}]
        },
        {path: '/blauw', component: BlauwComponent},
        {path: '/counter', component: InsectCounter},
        {path: '/:pathMatch(.+)+', component: NotFoundComponent}

    ]
});

createApp(App).use(router).mount('#app')
