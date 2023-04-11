<template>
  <div v-for="item in article" :key="item">
    <el-row>
      <el-col :span="20" :offset="1">
        <el-card class="cardCss" shadow="hover">
          <template #header>
            <div class="card-header">
              <h2>{{ item.title }}</h2>
              <div style="text-align: left">
                {{ item.desc }}
              </div>
            </div>
          </template>
          <div style="text-align: left">
            {{ item.time }}
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
const article = [
  {
    title: "我是你爸爸",
    desc: "我是你爸爸我是你爸爸我是你爸爸我是你爸爸我是你爸爸我是你爸爸",
    time: "2000-11-22",
  },
  {
    title: "你好",
    desc: "你好你好你好你好你好你好你好你好你好",
    time: "2000-11-22",
  },
  {
    title: "你好",
    desc: "你好你好你好你好你好你好你好你好你好",
    time: "2000-11-22",
  },
  {
    title: "你好",
    desc: "你好你好你好你好你好你好你好你好你好",
    time: "2000-11-22",
  },
  {
    title: "你好",
    desc: "你好你好你好你好你好你好你好你好你好",
    time: "2000-11-22",
  },
];
</script>

<style scoped>
@import "../assets/css/baseCss.css";
.el-row {
  margin-bottom: 20px;
}
.card-header {
  cursor: pointer;
  text-decoration: none;
  text-align: left;
}
</style>
