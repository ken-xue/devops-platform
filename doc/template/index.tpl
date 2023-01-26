<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="queryParams.name" placeholder="请输入名称" clearable size="small"
                    @keyup.enter.native="handleQuery"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button v-permission="['/{{.Module}}/{{.AllLowName}}/add:post']" type="primary" icon="el-icon-plus" size="mini"
                     @click="handleAdd">新增
          </el-button>
          <el-button v-permission="['/{{.Module}}/{{.AllLowName}}/delete:delete']" type="danger" icon="el-icon-delete" size="mini"
                     :disabled="multiple" @click="handleDelete">删除
          </el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="序号" align="center" width="45" :show-overflow-tooltip="true">
          <template slot-scope="props">
            <p v-text="props.$index+1"/>
          </template>
        </el-table-column>
        {{range $i, $v := .Columns}}
        <el-table-column label="{{$v.Comment}}" align="center" prop="{{$v.FirstLowName}}" :show-overflow-tooltip="true"/>
        {{end}}
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button v-permission="['/{{.Module}}/{{.AllLowName}}/update:put']" size="mini" type="text" icon="el-icon-edit"
                       @click="handleUpdate(scope.row)">修改
            </el-button>
            <el-button v-permission="['/{{.Module}}/{{.AllLowName}}/delete:delete']" size="mini" type="text" style="color: red"
                       icon="el-icon-delete" @click="handleDelete(scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--页码-->
      <pagination v-show="total>0" style="padding: 0px" :total="total" :page.sync="queryParams.pageIndex"
                  :limit.sync="queryParams.pageSize" @pagination="getList"/>
      <!-- 添加或修改对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-row>
          {{range $i, $v := .Columns}}
            <el-col :span="24">
               <el-form-item label="{{$v.Comment}}" prop="{{$v.FirstLowName}}">
                   <el-input v-model="form.username" placeholder="请输入{{$v.Comment}}"/>
               </el-form-item>
            </el-col>
          {{end}}
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>

import {Add, Del, Info, Page, Update} from '@/api/{{.Module}}/{{.AllLowName}}'

export default {
  name: 'User',
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      id: 0,
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
      dataList: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        name: ''
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        {{range $i, $v := .Columns}}
          {{$v.FirstLowName}}: [{required: true, message: '{{$v.Comment}}不能为空', trigger: 'blur'}],
        {{end}}
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      this.loading = true
      Page(nestedGetQuery(this.queryParams)).then(response => {
        this.dataList = response.data.list
        this.total = response.data.total
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
        {{range $i, $v := .Columns}}
          {{$v.FirstLowName}}: undefined,
        {{end}}
      }
      this.roleIdList = []
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
      this.title = '添加'
      this.isEdit = false
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.id
      Info(id).then(response => {
        this.form = response.data
        this.form.confirmPassword = response.data.userPassword
        this.form.status = response.data.status
        this.open = true
        this.title = '修改'
        this.isEdit = true
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id !== undefined) {
            Update(this.form).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            Add(this.form).then(response => {
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
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const Ids = (row.id && [row.id]) || this.ids
      this.$confirm('是否确认删除编号为"' + Ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return Del({'ids': Ids})
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function () {
      })
    }
  }
}
</script>
