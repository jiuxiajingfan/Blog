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
                    v-for="(activity, index) in item.data"
                    :key="index"
                    :icon="activity.icon"
                    :type="activity.type"
                    :color="activity.color"
                    :size="activity.size"
                    :hollow="activity.hollow"
                    placement="top"
                    :hide-timestamp="true"
                  >
                    <a href="ww" style="color: black; text-decoration: none">
                      <h4>Update Github template</h4>
                    </a>
                    <h5>{{ activity.timestamp }}</h5>
                  </el-timeline-item>
                </el-timeline>
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </el-scrollbar>
</template>

<script setup>
import BackGround from "@/components/BackGround";
import Header from "@/components/Header";
const activities = [
  {
    time: 2021,
    data: [
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
    ],
  },
  {
    time: 2020,
    data: [
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
    ],
  },
  {
    time: 2019,
    data: [
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
      {
        content: "Custom color",
        timestamp: "2018-04-03 20:46",
        color: "#0bbd87",
      },
    ],
  },
];
</script>

<style scoped lang="scss">
.cardCss2 {
  border-radius: 10px;
  text-align: left;
  background-color: rgba(255, 255, 255, 0.7);
}
</style>
