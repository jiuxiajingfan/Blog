<template>
  <div class="test">
    <img :src="imgUrl" @error="change" />
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useConfigStore } from "@/store/config";
import pinia from "@/store/store";
const config = useConfigStore(pinia);
let imgUrl = ref(
  config.getBackImg[Math.floor(Math.random() * config.getBackImg.length)]
);
const change = () => {
  imgUrl.value =
    config.getBackImg[Math.floor(Math.random() * config.getBackImg.length)];
};
let timer = null;
timer = setInterval(() => {
  imgUrl.value =
    config.getBackImg[Math.floor(Math.random() * config.getBackImg.length)];
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
