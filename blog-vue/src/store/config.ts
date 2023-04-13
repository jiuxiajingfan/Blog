import { defineStore } from "pinia";

export const useConfigStore = defineStore("Config", {
  state: () => {
    return {
      authorImg: "https://yuu-1306213591.file.myqcloud.com/image/logo.png",
      authorGithubUrl: "https://github.com/jiuxiajingfan",
      authorEmail: "jiuxiajingfan@163.com",
      record: "",
      nickname: "nine",
      title: "",
      title2: "",
    };
  },
  getters: {
    getPic: (state) => state.authorImg,
    getGithub: (state) => state.authorGithubUrl,
    getEmail: (state) => state.authorEmail,
    getRecord: (state) => state.record,
    getnickname: (state) => state.nickname,
    getTitle: (state) => state.title,
    getTitle2: (state) => state.title2,
  },
  actions: {
    setPic(list: string) {
      this.authorImg = list;
    },
    setTitle(list: string) {
      this.title = list;
    },
    setTitle2(list: string) {
      this.title2 = list;
    },
    setGithub(list: string) {
      this.authorGithubUrl = list;
    },
    setEmail(list: string) {
      this.authorEmail = list;
    },
    setRecord(list: string) {
      this.record = list;
    },
  },
  persist: true,
});
