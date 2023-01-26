<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
        <el-form-item label="表名" prop="tableName">
          <el-input v-model="queryParams.name" placeholder="请输入表名" clearable size="small"
                    @keyup.enter.native="handleQuery"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button type="primary" icon="el-icon-download" size="mini" :disabled="multiple"
                     @click="handleCreate('download')">生成并下载
          </el-button>
          <el-button type="danger" icon="el-icon-folder-add" size="mini" :disabled="multiple"
                     @click="handleCreate('inner')">生成到项目
          </el-button>
          <el-button type="normal" icon="el-icon-folder-delete" size="mini" @click="handleDeleteRecent">删除最近一次生成的代码
          </el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="序号" align="center" prop="userId" width="55" :show-overflow-tooltip="true">
          <template slot-scope="props">
            <p v-text="props.$index+1"/>
          </template>
        </el-table-column>
        <el-table-column label="表名" align="center" prop="TableName" :show-overflow-tooltip="true"/>
        <el-table-column label="创建时间" align="center" prop="CreateTime"/>
        <el-table-column label="描述" align="center" prop="TableComment"/>
      </el-table>
      <!--页码-->
      <pagination v-show="total>0" style="padding: 0px" :total="total" :page.sync="queryParams.pageIndex"
                  :limit.sync="queryParams.pageSize" @pagination="getList"/>
      <!-- 添加或修改对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户账号" prop="userId">
                <el-input v-model="form.userId" placeholder="请输入用户ID"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="用户名称" prop="userName">
                <el-input v-model="form.userName" placeholder="请输入用户名称"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="用户邮箱" prop="email">
                <el-input v-model="form.email" placeholder="请输入用户邮箱"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="登录密码" prop="userPassword">
                <el-input type="password" v-model="form.userPassword" placeholder="请输入密码"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="确认密码" prop="confirmPassword">
                <el-input type="password" v-model="form.confirmPassword" placeholder="请输入确认密码"/>
              </el-form-item>
            </el-col>
            <el-col :space="12">
              <el-form-item label="用户角色" size="mini" prop="roleIdList">
                <el-checkbox-group v-model="roleIdList">
                  <el-checkbox v-for="role in roleList" :key="role.uuid" :label="role.uuid">{{
                      role.roleName
                    }}
                  </el-checkbox>
                </el-checkbox-group>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="账号状态" prop="status">
                <el-radio-group v-model="form.status" size="small">
                  <el-radio-button label="0">正常</el-radio-button>
                  <el-radio-button label="2">冻结</el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>

      <el-dialog title="生成代码" :visible.sync="createVisible" width="700px" append-to-body>
        <el-form ref="createForm" :model="createForm" :rules="rules" label-width="80px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="所属模块" prop="createModuleName">
                <el-input v-model="createForm.createModuleName" placeholder="请输入所属模块"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="生成的表">
                <el-input type="textarea" :rows="5" disabled v-model="tableNames"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitCreateForm" :loading="createCodeLoading">确 定</el-button>
          <el-button @click="createVisible=false">取 消</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
import {create, download, pageTable,DeleteRecent} from '@/api/dev/code'

export default {
  name: 'User',
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      id: 0,
      // 选中数组
      tableNames: [],
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
      createVisible: false,
      createCodeLoading: false,
      isEdit: false,
      createType: '',
      // 类型数据字典
      typeOptions: [],
      userList: [],
      roleIdList: [],
      roleList: [],
      userGroupOptions: [
        {dictValue: '0', dictLabel: '目录'},
        {dictValue: '1', dictLabel: '菜单'},
        {dictValue: '2', dictLabel: '按钮'}
      ],
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        name: ''
      },
      // 表单参数
      form: {},
      createForm: {
        createModuleName: ''
      },
      // 表单校验
      rules: {
        userId: [{required: true, message: '编码不能为空', trigger: 'blur'}],
        userName: [{required: true, message: '名称不能为空', trigger: 'blur'}],
        email: [{required: true, message: '邮箱不能为空', trigger: 'blur'}],
        userPassword: [{required: true, message: '密码不能为空', trigger: 'blur'}],
        confirmPassword: [{required: true, message: '确认不能为空', trigger: 'blur'}],
        status: [{required: true, message: '状态不能为空', trigger: 'blur'}],
        createModuleName: [{required: true, message: '模块不能为空', trigger: 'blur'}]
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
      pageTable(this.queryParams).then(response => {
        this.userList = response.data.list
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
        userId: undefined,
        userName: undefined,
        userGroup: undefined,
        cronExpression: undefined,
        invokeTarget: undefined,
        args: undefined,
        misfirePolicy: 1,
        concurrent: 1,
        userType: 1,
        status: 0
      }
      this.roleIdList = []
      this.resetForm('form')
    },
    userGroupFormat(row) {
      return this.selectDictLabel(this.userGroupOptions, row.userGroup)
    },
    statusFormat(row) {
      return this.selectDictLabel(this.statusOptions, row.status)
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
    handleCreate(type) {
      this.createVisible = true
      this.createType = type
    },
    submitCreateForm() {
      this.$refs['createForm'].validate(valid => {
        if (valid) {
          this.createCodeLoading = true
          const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
          if (this.createType == 'download') {
            this.handlerDownload(loading)
          }
          if (this.createType == 'inner') {
            this.handlerCreate(loading)
          }
          this.createVisible = false
          this.createCodeLoading = false
        }
      })
    },
    handlerDownload(loading) {
      const tableNames = this.tableNames
      download({
        'tableNames': tableNames,
        'module': this.createForm.createModuleName
      }).then(function (response) {
        debugger
        const data = response.data
        const blob = new Blob([data])
        const fileName = 'code.zip'
        if ('download' in document.createElement('a')) {
          // 不是IE浏览器
          const url = window.URL.createObjectURL(blob)
          const link = document.createElement('a')
          link.style.display = 'none'
          link.href = url
          link.setAttribute('download', fileName)
          document.body.appendChild(link)
          link.click()
          document.body.removeChild(link) // 下载完成移除元素
          window.URL.revokeObjectURL(url) // 释放掉blob对象
        } else {
          // IE 10+
          window.navigator.msSaveBlob(blob, fileName)
        }
        loading.close()
      })
    },
    /** 删除按钮操作 */
    handlerCreate(loading) {
      const tableNames = this.tableNames
      create({'tableNames': tableNames, 'module': this.createForm.createModuleName}).then(response => {
        loading.close()
        this.getList()
        this.msgSuccess('生成成功')
        this.createCodeLoading = false
        loading.close()
      }).catch(function () {
        this.createCodeLoading = false
        loading.close()
      })
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.tableNames = selection.map(item => item.TableName)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.id
      listRole({}).then(response => {
        this.roleList = response.data
      }).then(() => {
        getUser(id).then(response => {
          this.form = response.data
          this.form.confirmPassword = response.data.userPassword
          this.roleIdList = response.data.roleIdList
          this.form.status = String(this.form.status)
          this.open = true
          this.title = '修改用户'
          this.isEdit = true
        })
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      console.log(this.form)
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.form.roleIdList = this.roleIdList
          this.form.userType = parseInt(this.form.userType)
          if (this.form.id !== undefined) {
            updateUser({'userDTO': this.form}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            addUser({'userDTO': this.form}).then(response => {
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
    handleDeleteRecent() {
      this.$confirm('是否确认删除最新一次生成的代码?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return DeleteRecent()
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    }
  }
}
</script>
