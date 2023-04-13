<template>
  <div class="test">
    <img :src="imgUrl" />
  </div>
</template>

<script setup>
import { ref } from "vue";
let imgUrl = ref(
  "http://124.220.189.127:9099/pic/bg/bg%20(" +
    Math.floor(Math.random() * 30 + 1) +
    ").png"
);
let timer = null;
timer = setInterval(() => {
  imgUrl.value =
    "http://124.220.189.127:9099/pic/bg/bg%20(" +
    Math.floor(Math.random() * 30 + 1) +
    ").png";
}, 10000);
</script>
<style scoped lang="scss">
body {
  margin: 0;
}
.test {
  width: 100%;
  height: 100%;
  overflow: hidden;
  position: absolute;
  top: 0;
  z-index: 0;
}
.test img {
  width: 100%;
  height: 100%;
  opacity: 1;
  transition: opacity 1s;
}
</style>
