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
              <div id="V1"></div>
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
import { onBeforeMount, ref, watch } from "vue";
import api from "@/api/api";
import router from "@/router";
import VditorPreview from "vditor/dist/method.min";
import "vditor/dist/index.css";
const body = ref("");
const title = ref("");
const desc = ref("");
const date = ref("");
const date2 = ref("");
onBeforeMount(() => {
  api
    .get("article/getArticle", {
      params: {
        id: router.currentRoute.value.query.id,
      },
    })
    .then((res) => {
      body.value = res.data.data.body;
      title.value = res.data.data.title;
      desc.value = res.data.data.descript;
      date.value = res.data.data.gmtCreate;
      date2.value = res.data.data.gmtUpdate;
      VditorPreview.preview(document.getElementById("V1"), body.value, {
        mode: "dark",
        speech: {
          enable: true,
        },
        anchor: 1,
        hljs: {
          lineNumber: true,
          style: "dracula",
        },
      });
    });
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
