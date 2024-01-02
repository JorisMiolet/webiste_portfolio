import { createRouter, createWebHistory } from "vue-router";
import adminOverview from "@/components/admin/adminOverview.vue";
import exportData from "@/components/admin/pages/image/exportData.vue";
import welcomeComponent from "@/components/Homepage/welcomeComponent.vue";
import Login from "@/components/login/login";
import editImage from "@/components/admin/pages/image/editImage.vue";
import userOverview from "@/components/admin/pages/user/userOverview.vue";
import editUser from "@/components/admin/pages/user/editUser.vue";
import createUser from "@/components/admin/pages/user/createUser.vue";
import createImage from "@/components/admin/pages/image/createImage.vue";
import laptopOverview from "@/components/admin/pages/laptop/laptopOverview.vue";
import editLaptop from "@/components/admin/pages/laptop/editLaptop.vue";
import createLaptop from "@/components/admin/pages/laptop/createLaptop.vue";

const routes = [
    {path: "/", redirect: "/home", name: 'home'},
    {path: "/home", name: "welcome", component: welcomeComponent},
    {path: "/Login", name: "login", component: Login},
    {
        path: "/admin",
        component: adminOverview,
        name: "admin",
        meta: { requiresAuth: true },
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
            },
            {
                path: "laptop-overview",
                name:"laptopOverview",
                component: laptopOverview
            },
            {
                path: "edit-laptop/:EAN",
                name:"editLaptop",
                component: editLaptop
            },
            {
                path: "create-laptop",
                name:"createLaptop",
                component: createLaptop
            }
        ]
    },
];
export const router = createRouter({
    history: createWebHistory(),
    routes
})
