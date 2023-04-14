<template>
  <BackGround></BackGround>
  <div class="goto">
    <el-scrollbar height="100vh">
      <Header></Header>
      <el-row style="margin-top: 2%">
        <el-col :span="16" :offset="4">
          <div class="article" id="article">
            <el-card shadow="none" class="cardCss2">
              <template #header>
                <div style="text-align: center">
                  <h2>{{ title }}</h2>
                  <el-icon style="margin-top: 5px"> <Clock /></el-icon>
                  <span> 发布时间： {{ date2 }}</span>
                </div>
              </template>
              <div>
                <div v-highlight v-html="body" class=""></div>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </el-scrollbar>
  </div>
</template>

<script setup>
import BackGround from "@/components/BackGround";
import Header from "@/components/Header";
import "highlight.js/styles/vs2015.css";
import { onBeforeMount, ref, watch } from "vue";
import api from "@/api/api";
import router from "@/router";
const body = ref("");
const title = ref("");
const desc = ref("");
const date = ref("");
const date2 = ref("");
const show = ref(true);
onBeforeMount(() => {
  api
    .get("article/getArticle", {
      params: {
        id: router.currentRoute.value.query.id,
      },
    })
    .then((res) => {
      body.value = marked(res.data.data.body);
      title.value = res.data.data.title;
      desc.value = res.data.data.descript;
      date.value = res.data.data.gmtCreate;
      date2.value = res.data.data.gmtUpdate;
    });
});
import { marked } from "marked";
// marked 选项
marked.setOptions({
  pedantic: false,
  gfm: true,
  breaks: false,
  sanitize: false,
  smartLists: true,
  smartypants: false,
  xhtml: false,
});
</script>

<style lang="scss" scoped>
.cardCss2 {
  border-radius: 10px;
  text-align: left;
  background-color: rgba(255, 255, 255, 0.7);
}

.html_output {
  text-align: left;
}
</style>
