<template>
  <el-row style="margin-top: 10px" class="font">
    <el-col>本站总访问量为 次</el-col>
    <el-col>
      <a
        href="https://github.com/jiuxiajingfan/blog"
        target="_blank"
        style="color: white; text-decoration: none"
        >Copyright © Nine. All Rights Reserved</a
      ></el-col
    >
    <el-col>
      <a
        href="https://beian.miit.gov.cn/"
        target="_blank"
        style="color: white; text-decoration: none"
        >{{ record }}</a
      >
    </el-col>
  </el-row>
</template>

<script setup>
import { useConfigStore } from "@/store/config";
import pinia from "@/store/store";
import { storeToRefs } from "pinia/dist/pinia";
const config = useConfigStore(pinia);
const { record } = storeToRefs(config);
</script>

<style scoped>
.font {
  color: #ffffff;
}
</style>
