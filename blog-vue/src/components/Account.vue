<template>
  <el-card style="height: 90vh; margin-top: 2%">
    <template #header>
      <div class="card-header">
        <h3>账号设置</h3>
      </div>
    </template>
    <el-collapse v-model="activeNames" accordion>
      <el-collapse-item name="1">
        <template #title>
          <el-icon size="20px" style="color: #1e9fff">
            <Tickets />
          </el-icon>
          <span style="margin-left: 5px; font-size: 20px; color: #1e9fff"
            >账号信息</span
          >
        </template>
        <el-card shadow="none">
          <div>
            <el-form label-width="120px">
              <el-form-item label="联系邮箱 :">
                <el-input v-model="userModel.email"></el-input>
              </el-form-item>
              <el-form-item label="Github :">
                <el-input v-model="userModel.github"></el-input>
              </el-form-item>
              <el-form-item label="网站备案号 :">
                <el-input v-model="userModel.record"></el-input>
              </el-form-item>
              <el-form-item label="网站主标题 :">
                <el-input v-model="userModel.zhu"></el-input>
              </el-form-item>
              <el-form-item label="网站副标题 :">
                <el-input v-model="userModel.fu"></el-input>
              </el-form-item>
              <el-button @click="changeMessage" type="primary">确定</el-button>
            </el-form>
          </div>
        </el-card>
      </el-collapse-item>
      <el-collapse-item name="2">
        <template #title>
          <el-icon size="20px" style="color: #1e9fff"><Setting /></el-icon>
          <span style="margin-left: 5px; font-size: 20px; color: #1e9fff"
            >密码修改</span
          >
        </template>
        <el-card shadow="none">
          <el-form :model="userModel" label-width="120px">
            <el-form-item label="原密码 :" prop="pwdOriginal">
              <el-input
                type="password"
                v-model="userModel.pwdOriginal"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item label="新密码 :" prop="pwdNew">
              <el-input
                type="password"
                v-model="userModel.pwdNew"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item label="确认密码 :" prop="pwdNew2">
              <el-input
                type="password"
                v-model="userModel.pwdNew2"
                show-password
              ></el-input>
            </el-form-item>
            <el-button @click="changePassword" type="primary">确定</el-button>
          </el-form>
        </el-card>
      </el-collapse-item>
      <el-collapse-item name="3">
        <template #title>
          <el-icon size="20px" style="color: #1e9fff"
            ><PictureFilled
          /></el-icon>
          <span style="margin-left: 5px; font-size: 20px; color: #1e9fff"
            >图片设置</span
          >
        </template>
        <el-card shadow="none">
          <el-form :model="userModel" label-width="120px">
            <el-form-item label="头像地址 :">
              <el-input v-model="userModel.pic"></el-input>
            </el-form-item>
            <h4>空值保存刷新页面后自动清除</h4>
            <el-scrollbar>
              <el-form-item label="背景图片 :" prop="pwdNew">
                <el-input
                  v-for="(item, index) in count"
                  :key="item"
                  v-model="userModel.backImg[index]"
                ></el-input>
                <el-icon @click="count++" size="40px"><CirclePlus /></el-icon>
              </el-form-item>
            </el-scrollbar>
            <el-button @click="changePic" type="primary">确定</el-button>
          </el-form>
        </el-card>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>

<script setup>
// eslint-disable-next-line no-undef
import { reactive, ref } from "vue";
const activeNames = ref(["1"]);
import md5 from "js-md5";
import api from "@/api/api";
import utils from "@/utils/utils";
import { useConfigStore } from "@/store/config";
import pinia from "@/store/store";
import router from "@/router";
import { useAuthStore } from "@/store/auth";
import { ElNotification } from "element-plus";
const config = useConfigStore(pinia);
const userModel = reactive({
  github: config.getGithub,
  email: config.getEmail,
  pwdOriginal: "",
  pwdNew: "",
  pwdNew2: "",
  nickname: config.getnickname,
  record: config.getRecord,
  backImg: config.getBackImg,
  pic: config.getPic,
  zhu: config.getTitle,
  fu: config.getTitle2,
});
const count = ref(config.getBackImg.length);
const checkPassword = (rule, value, callback) => {
  //验证密码的正则表达式
  const regEmail = /^(?![a-zA-Z]+$)(?!\d+$)(?![^\da-zA-Z\s]+$).{8,30}$/;
  if (regEmail.test(value)) {
    return callback();
  }
  callback(
    new Error("密码必须包含数字、字母、特殊符号中的两种，长度为8-30位！")
  );
};
const rules = reactive({
  pwdOriginal: [
    { required: true, message: "请输入密码", trigger: "blur" },
    {
      validator: checkPassword,
      min: 8,
      max: 30,
      message: "密码必须包含数字、字母、特殊符号中的两种，长度为8-30位！",
      trigger: "blur",
    },
  ],
  pwdNew: [
    { required: true, message: "请输入密码", trigger: "blur" },
    {
      validator: checkPassword,
      min: 8,
      max: 30,
      message: "密码必须包含数字、字母、特殊符号中的两种，长度为8-30位！",
      trigger: "blur",
    },
  ],
  pwdNew2: [
    { required: true, message: "请输入密码", trigger: "blur" },
    {
      validator: checkPassword,
      min: 8,
      max: 30,
      message: "密码必须包含数字、字母、特殊符号中的两种，长度为8-30位！",
      trigger: "blur",
    },
  ],
  name: [
    { required: true, message: "请输入新用户名", trigger: "blur" },
    {
      min: 1,
      max: 0,
      message: "请输入新用户名，长度为1-20位！",
      trigger: "blur",
    },
  ],
});
const changePasswordModel = ref();
const Auth = useAuthStore(pinia);
const changePic = () => {
  api
    .post("/user/changePic", {
      back: userModel.backImg,
      pic: userModel.pic,
    })
    .then((res) => {
      utils.showMessage(res.data.code, res.data.msg);
      if (res.data.code === 200) {
        config.setBackPic(userModel.backImg);
        config.setPic(userModel.pic);
      }
    });
};
const changePassword = () => {
  // 数据校验
  changePasswordModel.value.validate((valid) => {
    if (valid) {
      api
        .post("/user/changePwd", {
          pwdOriginal: md5(userModel.pwdOriginal + config.getnickname),
          pwdNew: md5(userModel.pwdNew + config.getnickname),
          pwdConfirm: md5(userModel.pwdNew2 + config.getnickname),
        })
        .then((res) => {
          if (res.data.code === 200) {
            localStorage.removeItem("token");
            Auth.setToken(null);
            router.push("/");
            ElNotification({
              title: "Success",
              message: "请重新登录",
              type: "success",
            });
          } else {
            utils.showMessage(res.data.code, res.data.msg);
          }
        });
    }
  });
};
const changeMessage = () => {
  api
    .post("/user/changeMessage", {
      email: userModel.email,
      github: userModel.github,
      record: userModel.record,
      title: userModel.zhu,
      title2: userModel.fu,
    })
    .then((res) => {
      utils.showMessage(res.data.code, res.data.msg);
      if (res.data.code === 200) {
        config.setEmail(userModel.email);
        config.setTitle(userModel.zhu);
        config.setTitle2(userModel.fu);
        config.setRecord(userModel.record);
        config.setGithub(userModel.github);
      }
    });
};
</script>
<style scoped></style>
