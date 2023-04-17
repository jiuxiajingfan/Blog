<template>
  <el-row style="margin-top: 10px" class="font">
    <el-col>本站总访问量为{{ guest }}人次</el-col>
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
import { onBeforeMount, ref } from "vue";
import utils from "@/utils/utils";
import api from "@/api/api";
const config = useConfigStore(pinia);
const { record } = storeToRefs(config);
const guest = ref(0);
onBeforeMount(() => {
  api.get("record/getGuest").then((res) => {
    guest.value = res.data.msg;
  });
});
</script>

<style scoped>
.font {
  color: #ffffff;
}
</style>
