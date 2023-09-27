import { createRouter, createWebHashHistory } from "vue-router";
import adminOverview from "@/components/admin/adminOverview.vue";
import createImage from "@/components/admin/pages/createImage.vue";
import editUser from "@/components/admin/pages/editUser.vue";
import exportData from "@/components/admin/pages/exportData.vue";
import userOverview from "@/components/admin/pages/userOverview.vue";

const routes = [
    {
        path: "/admin",
        component: adminOverview,
        children: [
            {path: "/create-image", component: createImage},
            {path: "/export-images", component: exportData},
            {path: "/user-overview", component: userOverview,
                children: [
                    {path: "/:user-uuid", component: editUser},
                ]
            }
        ]
    }
];
export const router = createRouter({
    history: createWebHashHistory(),
    routes
})