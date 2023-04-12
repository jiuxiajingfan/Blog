<template>
  <BackGround></BackGround>
  <el-scrollbar height="100vh">
    <el-row style="margin-top: 5%">
      <el-col :span="16" :offset="4">
        <el-card shadow="none" class="cardCss2">
          <template #header>
            {{ title }}
          </template>
          <div>
            <div v-html="body" class="markdown-body"></div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </el-scrollbar>
</template>

<script setup>
import BackGround from "@/components/BackGround";
import { onBeforeMount, ref } from "vue";
import "github-markdown-css";
import api from "@/api/api";
import router from "@/router";
const body = ref("");
const title = ref("");
const desc = ref("");
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
      console.log(body.value);
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
}

.html_output {
  text-align: left;
}
</style>
