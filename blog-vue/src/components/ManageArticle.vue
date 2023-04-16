<template>
  <el-card>
    <template #header>
      <h2>文章列表</h2>
    </template>
    <el-input
      placeholder="请输入要搜索的商品名或条形码"
      v-model="searchText"
      class="input-with-select"
      @keyup.enter="request"
    >
      <template #append>
        <el-button icon="Search" @click="request">搜索</el-button>
      </template>
    </el-input>
    <div class="table">
      <el-table :data="good" height="75vh" v-loading="lod">
        <el-table-column prop="title" label="标题" width="auto" />
        <el-table-column prop="descript" label="概要" width="auto" />
        <el-table-column prop="label" label="标签" width="auto" />
        <el-table-column prop="gmtCreate" label="创建时间" width="auto" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              link
              type="primary"
              size="large"
              @click="update(scope.row.id)"
            >
              更新
            </el-button>
            <el-popconfirm
              title="确定要删除吗?"
              @confirm="deleteArticle(scope.row.id)"
            >
              <template #reference>
                <el-button link type="primary">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        :hide-on-single-page="false"
        layout="->, total, prev, pager, next, sizes"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 50, 100]"
        background
        :total="total"
        :pager-count="7"
        @current-change="taskCurrentChange"
        @size-change="taskSizeChange"
      >
      </el-pagination>
    </div>
  </el-card>
</template>

<script setup>
import { onBeforeMount, onMounted, reactive, ref } from "vue";
import api from "@/api/api";
import router from "@/router";
import utils from "@/utils/utils";
let good = ref([]);
const vditor = ref(null);
let current = ref(1);
let total = ref(0);
let pageSize = ref(10);
let searchText = ref("");
let lod = ref(false);
let form = reactive({
  abstract: "",
  title: "",
  content: "",
  label: "",
});

const deleteArticle = (param) => {
  api
    .get("article/deleteArticle", {
      params: {
        id: param,
      },
    })
    .then((res) => {
      utils.showMessage(res.data.code, res.data.msg);
      request();
    });
};
const update = (param) => {
  router.push({
    path: "/updateArticle",
    query: {
      id: param,
    },
  });
};
const request = () => {
  lod.value = true;
  api
    .post("article/getArticlePage", {
      current: current.value,
      pageSize: pageSize.value,
      title: searchText.value,
    })
    .then((res) => {
      good.value = res.data.data.records;
      current.value = res.data.data.current;
      total.value = res.data.data.total;
    })
    .finally(() => {
      lod.value = false;
    });
};
onMounted(() => {
  request();
});
const taskCurrentChange = (cnt) => {
  current.value = cnt;
  request();
};
const taskSizeChange = (ps) => {
  pageSize.value = ps;
  request();
};
</script>

<style scoped></style>
