import { defineStore } from "pinia";

export const useKeyWordStore = defineStore("KeyWord", {
  state: () => {
    return {
      current: 1,
      pageSize: 5,
      title: "",
      label: "",
    };
  },
  getters: {
    getCurrent: (state) => state.current,
    getPageSize: (state) => state.pageSize,
    getTitle: (state) => state.title,
    getLabel: (state) => state.label,
  },
  actions: {
    setCurrent(list: number) {
      this.current = list;
    },
    setPageSize(list: number) {
      this.pageSize = list;
    },
    setTitle(list: string) {
      this.title = list;
    },
    setLabel(list: string) {
      this.label = list;
    },
    addCurrent() {
      this.current++;
    },
    subCurrent() {
      this.current--;
      if (this.current < 0) this.current = 0;
    },
  },
});
