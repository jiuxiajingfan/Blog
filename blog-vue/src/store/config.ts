import { defineStore } from "pinia";

export const useConfigStore = defineStore("Config", {
  state: () => {
    return {
      authorImg: "",
      authorGithubUrl: "",
      authorEmail: "",
      record: "",
      nickname: "",
      title: "",
      title2: "",
      backImage: [],
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
    getBackImg: (state) => state.backImage,
  },
  actions: {
    setPic(list: string) {
      this.authorImg = list;
    },
    setBackPic(list: any) {
      this.backImage = list;
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
    setNickname(list: string) {
      this.nickname = list;
    },
  },
  persist: true,
});
