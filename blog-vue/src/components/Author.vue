<template>
  <el-card shadow="none" class="cardCss">
    <template #header>
      <img :src="authorImg" class="image" />
    </template>
    <div>
      <a :href="githubUrl" target="_blank">
        <github theme="outline" size="28" fill="#333" :strokeWidth="1" />
      </a>
      <a :href="send">
        <send-email
          style="margin-left: 20px"
          theme="outline"
          size="28"
          fill="#333"
          :strokeWidth="4"
        />
      </a>
      <router-link :to="{ path: '/admin' }" style="margin-left: 20px">
        <setting theme="outline" size="28" fill="#333" />
      </router-link>
    </div>
  </el-card>
</template>

<script setup>
import { Github, SendEmail, Setting } from "@icon-park/vue-next";
import { useConfigStore } from "@/store/config";
import pinia from "@/store/store";
import { storeToRefs } from "pinia/dist/pinia";
import { onBeforeMount } from "vue";
import api from "@/api/api";
import utils from "@/utils/utils";
const config = useConfigStore(pinia);
const { authorImg } = storeToRefs(config);
const email = config.getEmail;
const send = "mailto:" + email;
const githubUrl = config.getGithub;
onBeforeMount(() => {
  if (null === utils.getData("Config")) {
    api.get("user/getMessage").then((res) => {
      config.setEmail(res.data.data.email);
      config.setPic(res.data.data.imgurl);
      config.setGithub(res.data.data.github);
      config.setRecord(res.data.data.record);
    });
  }
});
</script>

<style scoped>
@import "../assets/css/baseCss.css";
.image {
  height: 240px;
  width: 240px;
}
</style>
