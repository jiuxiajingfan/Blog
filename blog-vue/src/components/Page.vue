<template>
  <el-row>
    <el-col :span="2" :offset="2">
      <el-button class="rightbutton">Previous</el-button>
    </el-col>
    <el-col :span="2" :offset="6">
      <div style="color: #ffffff">
        <h2>1/10</h2>
      </div>
    </el-col>
    <el-col :span="2" :offset="6">
      <el-button class="leftbutton">Next</el-button>
    </el-col>
  </el-row>
</template>

<script setup></script>

<style scoped>
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
</style>
