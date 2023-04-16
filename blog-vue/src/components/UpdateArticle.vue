<template>
  <el-scrollbar>
    <el-card>
      <template #header>
        <div>
          <el-form>
            <el-form-item label="标题">
              <el-input v-model="form.title"></el-input>
            </el-form-item>
            <el-form-item label="概要">
              <el-input v-model="form.abstract"></el-input>
            </el-form-item>
            <el-form-item label="标签">
              <el-input v-model="form.label"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </template>
      <div id="vditor" style="text-align: left; height: 80vh" />
      <el-button style="margin-top: 10px" @click="this.$router.go(-1)"
        >返回</el-button
      >
      <el-button type="primary" style="margin-top: 10px" @click="submit"
        >保存</el-button
      >
    </el-card>
  </el-scrollbar>
</template>

<script setup>
import { onMounted, reactive, ref } from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
import utils from "@/utils/utils";
import api from "@/api/api";
import router from "@/router";
import { marked } from "marked";
const vditor = ref(null);
let form = reactive({
  abstract: "",
  title: "",
  content: "",
  label: "",
});
const submit = () => {
  api
    .post("article/updateArticle", {
      title: form.title,
      descript: form.abstract,
      label: form.label,
      body: vditor.value.getValue(),
      id: router.currentRoute.value.query.id,
    })
    .then((res) => {
      utils.showMessage(res.data.code, res.data.msg);
      if (res.data.code === 200) {
        router.push("/manageArticle");
      }
    });
};
onMounted(() => {
  api
    .get("article/getArticle", {
      params: {
        id: router.currentRoute.value.query.id,
      },
    })
    .then((res) => {
      form.title = res.data.data.title;
      form.abstract = res.data.data.descript;
      form.label = res.data.data.label;
      form.content = res.data.data.body;
      vditor.value.setValue(form.content);
    });
  vditor.value = new Vditor("vditor", {
    cache: {
      enable: false,
    },
    mode: "ir",
    typewriterMode: true,
    preview: {
      hljs: {
        lineNumber: true,
        style: "dracula",
      },
    },
    outline: {
      enable: true,
    },
    height: 730,
  });
});
</script>

<style scoped></style>
