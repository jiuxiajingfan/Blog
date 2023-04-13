<template>
  <el-row>
    <el-col :span="16" :offset="4">
      <el-card class="h1">
        <el-row>
          <el-col :span="8" :offset="8">
            <el-menu mode="horizontal" @select="handleSelect" class="h2">
              <el-menu-item index="/">
                <template #title>
                  <home-two
                    style="margin-top: -5px"
                    theme="outline"
                    size="40"
                    fill="#333"
                    :strokeWidth="3"
                  />
                  <span style="margin-left: 2px; font-size: 17px">首页</span>
                </template>
              </el-menu-item>
              <el-menu-item index="archiving">
                <template #title>
                  <box
                    style="margin-top: -5px"
                    theme="outline"
                    size="40"
                    fill="#333"
                    :strokeWidth="3"
                  />
                  <span style="margin-left: 2px; font-size: 17px">归档</span>
                </template>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup>
import { HomeTwo, Box } from "@icon-park/vue-next";
import router from "@/router";
const handleSelect = (key, keyPath) => {
  router.push({
    path: key,
  });
};
</script>

<style scoped lang="scss">
.h1 {
  //border-bottom-left-radius: 8px;
  //border-bottom-right-radius: 8px;
  //-webkit-box-shadow: 0 1px 2px 0px rgba(0, 0, 0, 0.1);
  border: 1px solid transparent;
  border-bottom-right-radius: 15px;
  border-bottom-left-radius: 15px;
  background-color: rgba(255, 255, 255, 0.7);
  box-shadow: 5px 5px 0 0 rgba(0, 0, 0, 0.2);
  height: 70px;
}
.h2 {
  background-color: rgba(255, 255, 255, 0);
}
</style>
