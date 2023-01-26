<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="90px">
        <el-form-item  label="应用名称">
          <el-select size="small" v-model="queryParams.applicationUuid" filterable placeholder="请选择" @change="handleQuery">
            <el-option
              v-for="item in appList"
              :key="item.uuid"
              :label="item.applicationName"
              :value="item.uuid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="流水线名称" prop="jobName">
          <el-input v-model="queryParams.pipelineName" placeholder="请输入流水线名称" clearable size="small" @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button v-permission="['sys:user:add']" type="primary" icon="el-icon-plus" size="mini" @click="addHandle">新增</el-button>
          <el-button v-permission="['sys:user:delete']" type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
          </el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="pipelineList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="序号" align="center" prop="jobId" width="50" :show-overflow-tooltip="true">
          <template slot-scope="props">
            <p v-text="props.$index+1" />
          </template>
        </el-table-column>
        <el-table-column label="流水线名称" align="center" prop="pipelineName" min-width="100" :show-overflow-tooltip="true" />
        <el-table-column label="创建人" align="center" prop="creator" width="100"  :show-overflow-tooltip="true"/>
        <el-table-column label="创建时间" align="center" prop="gmtCreate" max-width="300"  :show-overflow-tooltip="true"/>
        <el-table-column label="修改人" align="center" prop="modifier" :show-overflow-tooltip="true" />
        <el-table-column label="修改时间" align="center" prop="gmtModified" :show-overflow-tooltip="true" />
        <el-table-column label="触发方式" align="center" prop="triggerWay" :show-overflow-tooltip="true" >
          <template slot-scope="scope">
            <el-tag type="success" v-text="scope.row.triggerWay"></el-tag>
          </template>
        </el-table-column>
        <el-table-column label="最新触发时间" align="center" prop="latestTriggerTime" :show-overflow-tooltip="true" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width"  width="300" >
          <template slot-scope="scope">
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-setting" @click="handleUpdate(scope.row)">编排</el-button>
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-s-order" @click="handleRecord(scope.row)">记录</el-button>
            <el-button v-permission="['sys:log:delete']" size="mini" type="text" style="color: red" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--页码-->
      <pagination v-show="total>0" style="padding: 0px" :total="total" :page.sync="queryParams.pageIndex" :limit.sync="queryParams.pageSize" @pagination="getList" />
      <!-- 添加或修改对话框 -->
      <flowcanvas  v-if="addVisible" ref="flowcanvas" @refreshDataList="getList"></flowcanvas>
      <record v-if="recordVisible" ref="record" @refreshDataList="getList"></record>
    </el-card>
  </div>
</template>

<script>
import { del, page,add } from '@/api/app/pipeline'
import {listAppInfo} from '@/api/app/app'
import {nestedGetQuery} from "@/utils";
import flowcanvas from "@/views/pipeline/flowcanvas";
import record from "@/views/pipeline/record/record.vue";

export default {
  name: 'Pipeline',
  components: {flowcanvas,record},
  data() {
    return {
      // 遮罩层
      loading: true,
      id: 0,
      applicationUuid: '',
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      isEdit: false,
      addVisible:false,
      recordVisible:false,
      pipelineList: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        applicationUuid: undefined,
        pipelineName: undefined
      },
      // 表单参数
      form: {},
      appList: []
    }
  },
  created() {
    this.queryParams.applicationUuid = this.$route.query.uuid,
    this.getAppList(),
    this.getList()
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      this.loading = true
      page(nestedGetQuery(this.queryParams)).then(response => {
        this.pipelineList = response.data
        this.total = response.totalCount
        this.loading = false
      })
    },
    getAppList() {
      this.loading = true
      listAppInfo(this.queryParams).then(response => {
        this.appList = response.data
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        applicationUuid: undefined,
        pipelineName: undefined,
        pipelineContext: undefined
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageIndex = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加流水线'
      this.isEdit = false
      this.form.applicationUuid = this.queryParams.applicationUuid
    },
    getApplicationName(applicationUuid){
      return this.appList.find(item => {
        return item.uuid === applicationUuid
      }).applicationName
    },
    addHandle () {
      if (!this.queryParams.applicationUuid){
        this.msgError('请选择应用')
        return
      }
      this.form.applicationUuid = this.queryParams.applicationUuid
      let appUuid = this.queryParams.applicationUuid
      this.addVisible = true
      this.$nextTick(() => {
        this.$refs.flowcanvas.init(this.getApplicationName(appUuid),appUuid,null,false)
      })
    },
    handleUpdate(row) {
      this.addVisible = true
      this.$nextTick(() => {
        this.$refs.flowcanvas.init(this.getApplicationName(row.applicationUuid),row.applicationUuid,row.id,false)
      })
    },
    handleRecord(row){
      this.recordVisible = true
      this.$nextTick(() => {
        this.$refs.record.init(row.uuid)
      })
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.userIds = selection.map(item => item.userId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const Ids = (row.id && [row.id]) || this.ids
      this.$confirm('是否确认删除编号为"' + Ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return del({ 'ids': Ids })
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      console.log(this.form)
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id !== undefined) {
            update({'pipelineDTO':this.form}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            add({'pipelineDTO':this.form}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('新增成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          }
        }
      })
    }
  }
}
</script>
