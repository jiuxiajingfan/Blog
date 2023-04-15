<template>
  <el-container>
    <el-aside width="auto" style="height: 100vh">
      <el-scrollbar>
        <el-menu
          class="el-menu-vertical-demo"
          :collapse="isCollapse"
          :default-openeds="openeds"
          router
        >
          <div class="receive">
            <el-button @click="contraction" type="text" style="width: 100%">
              <el-icon v-if="isCollapse === true">
                <ArrowRightBold />
              </el-icon>
              <el-icon v-if="isCollapse === false">
                <ArrowLeftBold />
              </el-icon>
            </el-button>
          </div>
          <el-menu-item index="/account">
            <template #title>
              <el-icon><location /></el-icon>
              <span>账号设置</span>
            </template>
          </el-menu-item>
          <el-menu-item index="/createArticle">
            <template #title>
              <el-icon><location /></el-icon>
              <span>新建文章</span>
            </template>
          </el-menu-item>
        </el-menu>
      </el-scrollbar>
    </el-aside>
    <el-main class="h1">
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script setup>
import { defineAsyncComponent, onBeforeMount, ref } from "vue";
import pinia from "@/store/store";
import api from "@/api/api";
import utils from "@/utils/utils";
import { useConfigStore } from "@/store/config";
const cnt = ref(0);
const menuData = ref([]);
const map = new Map();
const menuData2 = [];
const openeds = [0];
const isCollapse = ref(false);
const buttonWidth = ref("200px");
const contraction = () => {
  isCollapse.value = !isCollapse.value;
  if (isCollapse.value) {
    buttonWidth.value = "62px";
  } else {
    buttonWidth.value = "200px";
  }
};
const editableTabs = ref([
  {
    title: "系统信息",
    name: "SystemStatus",
    content: "SystemStatus",
  },
]);
const focus = ref("SystemStatus");
const mapTab = new Map();
mapTab.set("SystemStatus", "SystemStatus");
const addTab = (targetName, component, title) => {
  if (mapTab.get(component) !== component) {
    cnt.value = cnt.value + 1;
    editableTabs.value.push({
      title: title,
      name: component,
      content: component,
    });
    mapTab.set(component, component);
    focus.value = component;
  } else {
    focus.value = component;
  }
};
const removeTab = (targetName) => {
  const tabs = editableTabs.value;
  let activeName = focus.value;
  if (activeName === targetName) {
    tabs.forEach((tab, index) => {
      if (tab.name === targetName) {
        const nextTab = tabs[index + 1] || tabs[index - 1];
        if (nextTab) {
          activeName = nextTab.name;
        }
      }
    });
  }
  mapTab.delete(targetName);
  focus.value = activeName;
  editableTabs.value = tabs.filter((tab) => tab.name !== targetName);
};
const config = useConfigStore(pinia);
onBeforeMount(() => {
  api.get("user/getMessage").then((res) => {
    config.setEmail(res.data.data.email);
    config.setPic(res.data.data.imgurl);
    config.setGithub(res.data.data.github);
    config.setRecord(res.data.data.record);
    config.setTitle(res.data.data.title);
    config.setTitle2(res.data.data.title2);
    config.setNickname(res.data.data.name);
    config.setBackPic(res.data.data.backList);
  });
});
</script>

<style scoped lang="scss">
.layout-container-demo .el-header {
  position: relative;
  background-color: #24292f;
  height: 65px !important;
  //color: var(--el-text-color-primary);
  //display: flex;
  //justify-content: space-between;
}

.layout-container-demo .el-main {
  padding: 0;
  height: calc(100vh - 65px);
}

.layout-container-demo .el-scrollbar ::-webkit-scrollbar {
  /* 设置竖向滚动条的宽度 */
  width: 5px;
  /* 设置横向滚动条的高度 */
  height: 5px;
}

.layout-container-demo .el-scrollbar ::-webkit-scrollbar-thumb {
  /*滚动条的背景色*/
  background-color: rgba(144, 147, 153, 0.3);
  border-radius: 35px;
  position: relative;
}

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}

.layout-container-demo .el-menu {
  border-right: none;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.receive {
  .el-button {
    height: 56px;
    min-width: 62px;
  }

  .el-button--text {
    color: #606266;
  }
}

.cop {
  position: relative;
}
.el-aside {
  height: calc(100vh - 65px);
}
.h1 {
  background-color: #eef2f4;
}
</style>
