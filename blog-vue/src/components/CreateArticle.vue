<template>
  <el-scrollbar>
    <div>
      <el-form>
        <el-form-item label="标题">
          <el-input></el-input>
        </el-form-item>
        <el-form-item label="概要">
          <el-input></el-input>
        </el-form-item>
        <el-form-item label="标签">
          <el-input></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div id="vditor" style="text-align: left; height: 80vh" />
    <el-button type="primary" style="margin-top: 10px">保存</el-button>
  </el-scrollbar>
</template>

<script setup>
import { onMounted, reactive, ref } from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
const vditor = ref(null);
let form = reactive({
  name: "",
  content: "",
});
onMounted(() => {
  vditor.value = new Vditor("vditor", {
    cache: {
      enable: false,
    },
    mode: "ir",
    typewriterMode: true,
    preview: {
      hljs: {
        lineNumber: true,
      },
      preview: {
        delay: 5,
      },
    },
    value: form.content,
    height: 730,
  });
});
</script>

<style scoped></style>
