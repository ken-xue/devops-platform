<template>
<div>
  <el-card class="box-card">
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
      <el-form-item label="命名空间" prop="userName">
        <el-input v-model="queryParams.namespaceName" placeholder="请输入命名空间" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button v-permission="['sys:user:add']" type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="namespaceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="userId" width="45" :show-overflow-tooltip="true">
        <template slot-scope="props">
          <p v-text="props.$index+1" />
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="namespaceName" :show-overflow-tooltip="true" />
      <el-table-column label="创建时间" align="center" prop="creationTimestamp" :show-overflow-tooltip="true" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</div>
</template>

<script>
import {listNamespace} from "@/api/kube/namespace";

export default {
  name: "index",
  data(){
    return{
      namespaceList:[],
      queryParams:{
        namespaceName:"",
      },

    }
  },

  created() {
    this.listNamespace();
  },

  methods:{
    listNamespace(){
      listNamespace(null).then(res => {
        this.namespaceList = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>
