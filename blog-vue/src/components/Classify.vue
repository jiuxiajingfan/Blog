<template>
  <el-card shadow="none" class="cardCss">
    <template #header>
      <span>文章分类</span>
    </template>
    <el-table :data="article" :show-header="false">
      <el-table-column prop="label" min-width="200px" />
      <el-table-column width="auto" fixed="right">
        <template #default="scope">
          <span>({{ scope.row.num }})</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
import { onBeforeMount, ref } from "vue";
import api from "@/api/api";

const article = ref([]);
onBeforeMount(() => {
  api.get("article/getLabel").then((res) => {
    article.value = res.data.data;
  });
});
</script>

<style scoped>
@import "../assets/css/baseCss.css";
</style>
