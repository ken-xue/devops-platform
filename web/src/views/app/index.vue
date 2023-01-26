<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
        <el-form-item  label="项目名称">
          <el-select v-model="queryParams.applicationInfoDTO.projectUuid" filterable placeholder="请选择" @change="handleQuery">
            <el-option
              v-for="item in projectList"
              :key="item.uuid"
              :label="item.projectName"
              :value="item.uuid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="应用名" prop="applicationName">
          <el-input v-model="queryParams.applicationInfoDTO.applicationName" placeholder="请输入应用名" clearable size="small" @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button v-permission="['sys:user:add']" type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
          <el-button v-permission="['sys:user:delete']" type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
          </el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="appList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="序号" align="center" prop="userId" width="55" :show-overflow-tooltip="true">
          <template slot-scope="props">
            <p v-text="props.$index+1" />
          </template>
        </el-table-column>
        <el-table-column label="应用名字" align="center" prop="applicationName" :show-overflow-tooltip="true" />
        <el-table-column label="应用类型" align="center" prop="applicationName" width="100" >
          <template slot-scope="scope">
            <el-tag  size="small" type="success" v-for="(item, index) in applicationTypes" :key="index" :label="item.label" :value="item.value" :disabled="item.disabled" v-if="scope.row.applicationType===item.value">{{ item.label }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="访问方式" align="center" prop="email" max-width="300" >
          <template slot-scope="scope">
            <el-tag  size="small" type="info" v-for="(item, index) in accessWays" :key="index" :label="item.label" :value="item.value" :disabled="item.disabled" v-if="scope.row.accessWay===item.value">{{ item.label }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="gmtCreate" :show-overflow-tooltip="true" />
        <el-table-column label="操作" align="center" min-width="100">
          <template slot-scope="scope">
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-s-promotion" @click="pipeline(scope.row)">流水线</el-button>
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-setting" @click="handleUpdate(scope.row)">配置</el-button>
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-user-solid" @click="handleUpdate(scope.row)">用户</el-button>
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
            <el-button v-permission="['sys:user:delete']" size="mini" type="text" style="color: red" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--页码-->
      <pagination v-show="total>0" style="padding: 0px" :total="total" :page.sync="queryParams.pageIndex" :limit.sync="queryParams.pageSize" @pagination="getList" />
      <!-- 添加或修改对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="应用名字" prop="applicationName">
                <el-input v-model="form.applicationName" placeholder="请输入应用名字" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="所属项目" prop="projectUuid">
                <el-select v-model="form.projectUuid" placeholder="请选择下拉选择" clearable :style="{width: '100%'}">
                  <el-option n v-for="item in projectList"
                             :key="item.uuid"
                             :label="item.projectName"
                             :value="item.uuid"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="应用类型" prop="applicationType">
                <el-select v-model="form.applicationType" placeholder="请选择下拉选择" clearable :style="{width: '100%'}">
                  <el-option  v-for="(item, index) in applicationTypes" :key="index" :label="item.label" :value="item.value" :disabled="item.disabled"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="代码仓库" prop="repository">
                <el-input v-model="form.repository" placeholder="请输入代码仓库" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
            <el-form-item label="访问方式" prop="accessWay">
              <el-select v-model="form.accessWay" placeholder="请选择访问方式" clearable :style="{width: '100%'}">
                <el-option v-for="(item, index) in accessWays" :key="index" :label="item.label" :value="item.value" :disabled="item.disabled"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

            <el-col :span="24">
              <el-form-item label="访问密钥" prop="accessKey">
                <el-input v-model="form.accessKey" placeholder="请输入访问密钥" />
              </el-form-item>
            </el-col>
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
import {addAppInfo, delAppInfo, getAppInfo, listAppInfo, pageAppInfo, updateAppInfo} from '@/api/app/app'
import {listRole} from "@/api/sys/role";
import {nestedGetQuery} from "@/utils";
import { list as ProjectList } from '@/api/project/project'

export default {
  name: 'AppInfo',
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
      // 类型数据字典
      typeOptions: [],
      AppInfoList: [],
      appList: [],
      roleIdList: [],
      roleList: [],
      projectList: [],
      applicationTypes: [
        { value: 'Java', label: 'Java应用' },
        { value: 'Go', label: 'Go应用' },
        { value: 'NodeJS', label: 'NodeJS应用' }
      ],
      accessWays: [
        { value: 'Https', label: 'Https' },
        { value: 'SVN', label: 'SVN' },
        { value: 'SSH', label: 'SSH' }
      ],
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        applicationInfoDTO: {
          applicationName: ''
        }
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        applicationName: [{ required: true, message: '应用名称不能为空', trigger: 'blur' }],
        applicationType: [{ required: true, message: '名称不能为空', trigger: 'blur' }],
        email: [{ required: true, message: '邮箱不能为空', trigger: 'blur' }],
        userPassword: [{ required: true, message: '密码不能为空', trigger: 'blur' }],
        confirmPassword: [{ required: true, message: '确认不能为空', trigger: 'blur' }],
        status: [{ required: true, message: '状态不能为空', trigger: 'blur' }],
        projectUuid: [{ required: true, message: '所属项目不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.queryParams.applicationInfoDTO.projectUuid = this.$route.query.uuid,
    this.getProjectList(),
    this.getList()
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      this.loading = true
      pageAppInfo(nestedGetQuery(this.queryParams)).then(response => {
        this.appList = response.data
        this.total = response.totalCount
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      listRole({}).then(response=>{
        this.form.status = 0
        this.roleList = response.data
        this.open = true
        this.title = '添加应用'
        this.isEdit = false
      })
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.userIds = selection.map(item => item.userId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.id
      listRole({}).then(response=>{
        this.roleList = response.data
      }).then(()=>{
        getAppInfo(id).then(response => {
          this.form = response.data
          this.form.confirmPassword = response.data.userPassword
          this.roleIdList = response.data.roleIdList
          this.form.status = String(this.form.status)
          this.open = true
          this.title = '修改应用'
          this.isEdit = true
        })
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      console.log(this.form)
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.form.roleIdList = this.roleIdList
          this.form.userType = parseInt(this.form.userType)
          if (this.form.id !== undefined) {
            updateAppInfo({'applicationInfoDTO':this.form}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            addAppInfo({'applicationInfoDTO':this.form}).then(response => {
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
      }).then(function() {
        return delAppInfo({ 'ids': Ids })
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    },
    // 路由跳转到流水线
    pipeline(row){
      this.$router.push(
        {
          path: '/pipeline',
          query: {
            uuid: row.uuid
          }
        }
      )
    },
    getProjectList(){
      this.loading = true
      ProjectList(this.queryParams).then(response => {
        this.projectList = response.data
        this.loading = false
      })
    }
  }
}
</script>
