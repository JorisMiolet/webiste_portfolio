import { createRouter, createWebHistory } from "vue-router";
import adminOverview from "@/components/admin/adminOverview.vue";
import createImage from "@/components/admin/pages/createImage.vue";
import editUser from "@/components/admin/pages/editUser.vue";
import exportData from "@/components/admin/pages/exportData.vue";
import userOverview from "@/components/admin/pages/userOverview.vue";
import tempHomepage from "@/components/tempHomepage.vue";
import editImage from "@/components/admin/pages/editImage.vue";

const routes = [
    {
        path: "/admin",
        component: adminOverview,
        name: "adminHome",
        children: [
            {path: "create-image", component: createImage, name: "create-image"},
            {path: "image-overview", component: exportData, name: "image-overview",
                children: [
                    {path: ":uuid", component: editImage, name: "edit-image"}
                ]
            },
            {path: "user-overview", component: userOverview, name: "user-overview",
                children: [
                    {path: ":user-uuid", component: editUser, name: "edit-user"},
                ]
            }
        ]
    }, {
        path: "/",
        component: tempHomepage,
    }
];
export const router = createRouter({
    history: createWebHistory(),
    routes
})