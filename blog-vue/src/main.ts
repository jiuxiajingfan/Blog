import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import pinia from "@/store/store";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import "@icon-park/vue-next/styles/index.css";
import hljs from "highlight.js";
import "highlight.js/styles/atom-one-dark.css"; //样式

const app = createApp(App);
app.use(ElementPlus);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}
//创建v-highlight全局指令
app.directive("highlight", function (el) {
  const blocks = el.querySelectorAll("pre code");
  blocks.forEach((block: any) => {
    hljs.highlightBlock(block);
  });
});
app.use(router);
app.use(pinia);
app.mount("#app");
