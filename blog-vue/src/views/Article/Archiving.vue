<template>
  <BackGround></BackGround>
  <el-scrollbar height="100vh">
    <Header></Header>
    <el-row style="margin-top: 2%">
      <el-col :span="16" :offset="4">
        <div>
          <el-card shadow="none" class="cardCss2">
            <div class="h1">
              <div v-for="item in activities" :key="item.time">
                <h2 style="text-align: center">{{ item.time }}</h2>
                <el-divider />
                <el-timeline>
                  <el-timeline-item
                    v-for="(activity, index) in item.list"
                    :key="index"
                    :icon="activity.icon"
                    :type="activity.type"
                    color="#0bbd87"
                    :size="activity.size"
                    :hollow="activity.hollow"
                    placement="top"
                    :hide-timestamp="true"
                  >
                    <a
                      href=""
                      style="color: black; text-decoration: none"
                      @click="go(activity.id)"
                    >
                      <h4>{{ activity.title }}</h4>
                    </a>
                    <h5>{{ activity.gmtCreate }}</h5>
                  </el-timeline-item>
                </el-timeline>
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <Footer></Footer>
  </el-scrollbar>
</template>

<script setup>
import BackGround from "@/components/BackGround";
import Header from "@/components/Header";
import { onBeforeMount, ref } from "vue";
import api from "@/api/api";
import router from "@/router";
import { marked } from "marked";
import utils from "@/utils/utils";
import Footer from "@/components/Footer";
const activities = ref([]);
onBeforeMount(() => {
  api.get("article/getArticleTIme").then((res) => {
    if (res.data.code === 200) {
      activities.value = res.data.data;
    } else {
      utils.showMessage(res.data.msg);
    }
  });
});
const go = (id) => {
  router.push({
    path: "/article",
    query: {
      id: id,
    },
  });
};
</script>

<style scoped lang="scss">
.cardCss2 {
  border-radius: 10px;
  text-align: left;
  background-color: rgba(255, 255, 255, 0.7);
}
</style>
