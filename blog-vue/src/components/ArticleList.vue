<template>
  <div v-for="item in article" :key="item">
    <el-row>
      <el-col :span="20" :offset="1">
        <el-card class="cardCss2" @click="go(item.id)">
          <template #header>
            <div class="card-header">
              <h2 style="text-align: center">{{ item.title }}</h2>
              <div style="text-align: left">
                {{ item.descript }}
              </div>
            </div>
          </template>
          <div style="text-align: left">
            {{ item.gmtCreate }}
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
  <div>
    <el-row>
      <el-col :span="2" :offset="2">
        <el-button class="rightbutton" @click="pre" v-show="currentPage !== 1"
          >Previous</el-button
        >
      </el-col>
      <el-col :span="2" :offset="6">
        <div style="color: #ffffff">
          <h2>{{ currentPage }}/{{ totalPage }}</h2>
        </div>
      </el-col>
      <el-col :span="2" :offset="6">
        <el-button
          class="leftbutton"
          @click="next"
          v-show="currentPage !== totalPage"
          >Next</el-button
        >
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from "vue";
import api from "@/api/api";
import router from "@/router";

const article = ref([]);
let currentPage = ref(1);
let pageSize = ref(10);
let total = ref(0);
let totalPage = ref(0);
onBeforeMount(() => {
  api
    .post("article/getArticlePage", {
      current: currentPage.value,
      pageSize: pageSize.value,
    })
    .then((res) => {
      total.value = res.data.data.total;
      article.value = res.data.data.records;
      currentPage.value = res.data.data.current;
      totalPage.value = Math.ceil(total.value / pageSize.value);
    });
});
const next = () => {
  currentPage.value = currentPage.value + 1;
  api
    .post("article/getArticlePage", {
      current: currentPage.value,
      pageSize: pageSize.value,
    })
    .then((res) => {
      article.value = res.data.data.records;
      currentPage.value = res.data.data.current;
      totalPage.value = Math.ceil(total.value / pageSize.value);
    });
};
const pre = () => {
  currentPage.value = currentPage.value - 1;
  api
    .post("article/getArticlePage", {
      current: currentPage.value,
      pageSize: pageSize.value,
    })
    .then((res) => {
      article.value = res.data.data.records;
      currentPage.value = res.data.data.current;
      totalPage.value = Math.ceil(total.value / pageSize.value);
    });
};
const go = (id) => {
  router.push({
    path: "/article",
    query: {
      id: id,
    },
  });
};
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
.card-header {
  cursor: pointer;
  text-decoration: none;
  text-align: left;
}
.leftbutton {
  border-radius: 8px;
  text-align: left;
  border-top-left-radius: 32px;
  border-bottom-left-radius: 32px;
  padding: 30px;
  font-family: UbuntuMono, "PingFang SC", "Microsoft YaHei", Helvetica, Arial,
    Menlo, Monaco, monospace, sans-serif;
}
.rightbutton {
  border-radius: 8px;
  text-align: left;
  border-top-right-radius: 32px;
  border-bottom-right-radius: 32px;
  padding: 30px;
  font-family: UbuntuMono, "PingFang SC", "Microsoft YaHei", Helvetica, Arial,
    Menlo, Monaco, monospace, sans-serif;
}
.cardCss2 {
  border-radius: 10px;
  text-align: left;
  background-color: rgba(255, 255, 255, 0.7);
  box-shadow: 5px 5px 0 0 rgba(0, 0, 0, 0.2);
}
</style>
