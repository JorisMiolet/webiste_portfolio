import { createRouter, createWebHistory } from "vue-router";
import adminOverview from "@/components/admin/adminOverview.vue";
import exportData from "@/components/admin/pages/exportData.vue";
import welcomeComponent from "@/components/Homepage/welcomeComponent.vue";
import Login from "@/components/login/login";
import editImage from "@/components/admin/pages/editImage.vue";
import userOverview from "@/components/admin/pages/userOverview.vue";
import editUser from "@/components/admin/pages/editUser.vue";
import createUser from "@/components/admin/pages/createUser.vue";
import createImage from "@/components/admin/pages/createImage.vue";

const routes = [
    {path: "/", redirect: "/home",},
    {path: "/home", name: "welcome", component: welcomeComponent},
    {path: "/Login", name: "login", component: Login},
    {
        path: "/admin",
        component: adminOverview,
        name: "admin",
        children: [
            {
                path:"image-overview",
                name:"imageOverview",
                component: exportData,
            }, {
                path:"edit-image/:ArticleNR",
                name:"editImage",
                component:editImage
            }, {
                path:"user-overview",
                name:"userOverview",
                component: userOverview,
            }, {
                path:"edit-user/:id",
                name:"editUser",
                component: editUser,
            }, {
                path:"create-user",
                name:"createUser",
                component: createUser,
            }, {
                path:"create-image",
                name:"createImage",
                component: createImage
            }
        ]
    },
];
export const router = createRouter({
    history: createWebHistory(),
    routes
})
