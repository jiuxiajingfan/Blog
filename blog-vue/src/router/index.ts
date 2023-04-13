import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import err404 from "../views/Error/404.vue";
import home from "../views/Home/Home.vue";
import read from "../views/Article/Read.vue";
import archiving from "../views/Article/Archiving.vue";
import { ElMessage } from "element-plus";
import pinia from "@/store/store";
import { useAuthStore } from "@/store/auth";
const store = useAuthStore(pinia);
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home",
    component: home,
    meta: {
      auth: false,
    },
  },
  {
    path: "/article",
    name: "article",
    component: read,
    meta: {
      auth: false,
    },
  },
  {
    path: "/archiving",
    name: "archiving",
    component: archiving,
    meta: {
      auth: false,
    },
  },
  {
    path: "/:pathMatch(.*)*",
    name: "404",
    component: err404,
    meta: {
      auth: false,
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  if (!!to.meta && to.meta.auth === false) {
    next();
    return;
  } else {
    const token = store.getToken;
    if (to.path === "/login") {
      if (token && token != "null") {
        next("/userCenter");
      } else {
        next();
      }
    } else {
      if (token === null || token === "" || token === "null") {
        next("/login");
        ElMessage({
          showClose: true,
          message: "请先登录！",
          type: "error",
        });
      } else {
        next();
      }
    }
  }
});

export default router;
