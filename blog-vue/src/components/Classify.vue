<template>
  <el-card shadow="none" class="cardCss">
    <template #header>
      <span>文章分类</span>
    </template>
    <el-table :data="article" :show-header="false">
      <el-table-column prop="title" min-width="200px" />
      <el-table-column width="auto" fixed="right">
        <template #default="scope">
          <span>({{ scope.row.num }})</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup>
const article = [
  {
    title: "我是你爸爸",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
  {
    title: "你好",
    num: 10,
  },
];
</script>

<style scoped>
@import "../assets/css/baseCss.css";
</style>
