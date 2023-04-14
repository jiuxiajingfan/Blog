<template>
  <el-card shadow="none" class="cardCss2">
    <template #header>
      <h3 style="text-align: center">文章分类</h3>
    </template>
    <div style="text-align: center">
      <input
        class="input"
        v-model="search"
        @keydown.enter="searchA"
        placeholder="Search"
      />
      <button class="bnt" @click="searchA">
        <el-icon size="20" style="margin-top: 5px"><Search /></el-icon>
      </button>
    </div>
    <el-table
      @row-click="onRowClick"
      style="margin-top: 20px"
      highlight-current-row
      :data="article"
      :show-header="false"
      class="cardCss2"
      height="auto"
    >
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
import router from "@/router";
import { useKeyWordStore } from "@/store/article";
import pinia from "@/store/store";
const keyWordStore = useKeyWordStore(pinia);
const article = ref([]);
const search = ref("");
onBeforeMount(() => {
  api.get("article/getLabel").then((res) => {
    article.value = res.data.data;
  });
});
const searchA = () => {
  keyWordStore.setTitle(search.value);
};
const onRowClick = (row, column, event) => {
  if (row.label === "全部") keyWordStore.setLabel("");
  else keyWordStore.setLabel(row.label);
};
</script>

<style scoped lang="scss">
.cardCss2 {
  border-radius: 10px;
  text-align: left;
  background-color: rgba(255, 255, 255, 0.7);
}
.input {
  font-size: 14px;
  display: inline-block;
  height: 35px;
  line-height: inherit;
  border: 0 none;
  outline: 0;
  background: #ffffff;
  color: #222226;
  vertical-align: top;
  text-indent: 32px;
  border: 1px solid #e8e8ed;
  border-right: none;
  width: 260px;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  border-radius: 16px 0 0 16px;
}
.bnt {
  display: inline-block;
  width: 40px;
  height: 35px;
  outline: 0;
  border: 0 none;
  border-radius: 0 16px 16px 0;
  font-size: 14px;
  line-height: 32px;
  cursor: pointer;
  -webkit-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
  background-color: #ffffff;
  text-align: left;
}
</style>
