import { defineStore } from "pinia";

export const useConfigStore = defineStore("Config", {
  state: () => {
    return {
      authorImg: "https://yuu-1306213591.file.myqcloud.com/image/logo.png",
      authorGithubUrl: "https://github.com/jiuxiajingfan",
      authorEmail: "jiuxiajingfan@163.com",
      record: "",
    };
  },
  getters: {
    getPic: (state) => state.authorImg,
    getGithub: (state) => state.authorGithubUrl,
    getEmail: (state) => state.authorEmail,
    getRecord: (state) => state.record,
  },
  actions: {
    setPic(list: string) {
      this.authorImg = list;
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
