<template>
  <BackGround></BackGround>
  <el-scrollbar height="100vh">
    <div>
      <el-row align="middle" style="margin-top: 40vh">
        <el-col :span="8" :offset="8">
          <div class="title">
            <div>
              <span style="font-size: 3.125rem">Diana</span>
            </div>
            <span style="font-size: 20px">When in doubt, use brute force</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="scroll-down" style="margin-top: 45vh">
      <span>
        <el-icon><ArrowDownBold /></el-icon>
      </span>
    </div>
    <div>
      <el-container>
        <el-aside width="400px">
          <el-row style="margin-top: 20px">
            <el-col :span="21" :offset="3">
              <Author></Author>
            </el-col>
            <el-col :span="21" :offset="3" style="margin-top: 20px">
              <Classify></Classify>
            </el-col>
          </el-row>
        </el-aside>
        <el-main>
          <ArticleList></ArticleList>
        </el-main>
      </el-container>
    </div>
    <div>
      <Footer></Footer>
    </div>
  </el-scrollbar>
</template>

<script setup>
import BackGround from "@/components/BackGround";
import ArticleList from "@/components/ArticleList";
import Author from "@/components/Author";
import Classify from "@/components/Classify";
import Footer from "@/components/Footer";
</script>

<style scoped>
.title {
  font-family: "Varela Round", "PingFang SC", "Microsoft YaHei", Helvetica,
    Arial, Helvetica, monospace;
  line-height: 1.2;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
  color: #ffffff;
  align-items: center; /*侧轴上居中*/
}
/*向下滑动的动画效果*/
@-webkit-keyframes drop {
  0% {
    top: 0px;
    opacity: 0;
  }
  30% {
    top: 10px;
    opacity: 1;
  }
  100% {
    top: 25px;
    opacity: 0;
  }
}
@keyframes drop {
  0% {
    top: 0px;
    opacity: 0;
  }
  30% {
    top: 10px;
    opacity: 1;
  }
  100% {
    top: 25px;
    opacity: 0;
  }
}

/*应用动画，添加按钮效果*/
.scroll-down {
  border-radius: 50%;
  margin: 0 auto;
  height: 50px;
  width: 50px;
  display: block;
  text-align: center;
  z-index: 10;
  -webkit-transition: all 0.125s ease-in-out 0s;
  -moz-transition: all 0.125s ease-in-out 0s;
  -ms-transition: all 0.125s ease-in-out 0s;
  -o-transition: all 0.125s ease-in-out 0s;
  transition: all 0.125s ease-in-out 0s;
}
.scroll-down span {
  position: relative;
  color: #bbb;
  font-size: 30px;
  -webkit-animation-name: drop;
  -webkit-animation-duration: 1.5s;
  -webkit-animation-timing-function: linear;
  -webkit-animation-delay: 0s;
  -webkit-animation-iteration-count: infinite;
  -webkit-animation-play-state: running;
  animation-name: drop;
  animation-duration: 1.5s;
  animation-timing-function: linear;
  animation-delay: 0s;
  animation-iteration-count: infinite;
  animation-play-state: running;
}
</style>
