<template>
  <el-card shadow="none" class="cardCss2">
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
      <a style="margin-left: 20px" @click="centerDialogVisible = true">
        <setting theme="outline" size="28" fill="#333" />
      </a>
    </div>
  </el-card>
  <el-dialog
    v-model="centerDialogVisible"
    title="后台登录"
    width="20%"
    align-center
  >
    <el-form ref="ruleFormRef" :rules="loginRules" :model="registerFormData">
      <el-form-item prop="name">
        <el-input
          size="large"
          prefix-icon="User"
          placeholder="请输入账号"
          v-model="registerFormData.name"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          size="large"
          prefix-icon="Lock"
          type="password"
          placeholder="请输入密码"
          @keydown.enter="login"
          v-model="registerFormData.password"
        ></el-input>
      </el-form-item>
      <el-button @click="centerDialogVisible = false">取消</el-button>
      <el-button type="primary" @click="login">登录</el-button>
    </el-form>
  </el-dialog>
</template>

<script setup>
import { Github, SendEmail, Setting } from "@icon-park/vue-next";
import { useConfigStore } from "@/store/config";
import pinia from "@/store/store";
import md5 from "js-md5";
import { storeToRefs } from "pinia/dist/pinia";
import { onBeforeMount, reactive, ref } from "vue";
import api from "@/api/api";
import utils from "@/utils/utils";
import { useAuthStore } from "@/store/auth";
import router from "@/router";
const config = useConfigStore(pinia);
const { authorImg } = storeToRefs(config);
const email = config.getEmail;
const send = "mailto:" + email;
const githubUrl = config.getGithub;
let centerDialogVisible = ref(false);
onBeforeMount(() => {
  if (null === utils.getData("Config")) {
    api.get("user/getMessage").then((res) => {
      config.setEmail(res.data.data.email);
      config.setPic(res.data.data.imgurl);
      config.setGithub(res.data.data.github);
      config.setRecord(res.data.data.record);
      config.setTitle(res.data.data.title);
      config.setTitle2(res.data.data.title2);
      config.setNickname(res.data.data.name);
      config.setBackPic(res.data.data.backList);
    });
  }
});
let registerFormData = reactive({
  name: "",
  password: "",
});
const ruleFormRef = ref();
const REGEXP_PWD =
  /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)]|[()])+$)(?!^.*[\u4E00-\u9FA5].*$)([^(0-9a-zA-Z)]|[()]|[a-z]|[A-Z]|[0-9]){8,18}$/;
const loginRules = reactive({
  password: [
    {
      required: true,
      message: "请输入密码",
      trigger: "blur",
    },
  ],
  name: [
    { required: true, message: "请输入账号", trigger: "blur" },
    {
      min: 5,
      max: 20,
      message: "登录账号长度必须在5-20位之间！",
      trigger: "blur",
    },
  ],
});
const Auth = useAuthStore(pinia);
const login = () => {
  ruleFormRef.value.validate((valid) => {
    if (valid) {
      console.log(1);
      api
        .post("user/login", {
          name: registerFormData.name,
          password: md5(registerFormData.password + registerFormData.name),
        })
        .then((res) => {
          if (res.data.code === 200) {
            Auth.setToken(res.data.msg);
            utils.showMessage(200, "登录成功，欢迎回来！");
            router.push("/admin");
          } else {
            utils.showErrMessage(res.data.msg);
          }
        });
    }
  });
};
</script>

<style scoped>
.image {
  height: 300px;
  width: 300px;
}
.cardCss2 {
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.7);
}
</style>
