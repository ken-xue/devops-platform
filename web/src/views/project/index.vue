<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
        <el-form-item label="项目名称" prop="applicationName">
          <el-input v-model="queryParams.projectInfoDTO.projectName" placeholder="请输入项目名称" clearable size="small"
                    @keyup.enter.native="handleQuery"/>
        </el-form-item>
        <el-form-item>
          <el-radio v-model="queryParams.projectType" @change="handleQuery" label="MY">我负责的</el-radio>
          <el-radio v-model="queryParams.projectType" @change="handleQuery" label="JOIN">我参与的</el-radio>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button v-permission="['sys:user:add']" type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">
            新增
          </el-button>
          <el-button v-permission="['sys:user:delete']" type="danger" icon="el-icon-delete" size="mini"
                     :disabled="multiple" @click="handleDelete">删除
          </el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="序号" align="center" prop="userId" width="55" :show-overflow-tooltip="true">
          <template slot-scope="props">
            <p v-text="props.$index+1"/>
          </template>
        </el-table-column>
        <el-table-column label="项目名称" align="center" prop="projectName" :show-overflow-tooltip="true"/>
        <el-table-column label="负责人" align="center" prop="username" width="100"/>
        <el-table-column label="描述" align="center" prop="projectDesc" width="300" :show-overflow-tooltip="true"/>
        <el-table-column label="创建时间" align="center" prop="gmtCreate" :show-overflow-tooltip="true"/>
        <el-table-column label="操作" align="center" min-width="100">
          <template slot-scope="scope">
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-menu"
                       @click="application(scope.row)">应用
            </el-button>
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-s-data"
                       @click="projectView(scope.row)">数据
            </el-button>
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-edit"
                       @click="handleUpdate(scope.row)">修改
            </el-button>
            <el-button v-permission="['sys:user:delete']" size="mini" type="text" style="color: red"
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
            <el-col :span="12">
              <el-form-item label="项目名称" prop="projectName">
                <el-input v-model="form.projectName" placeholder="请输入项目名称"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="负责人" prop="userUuid">
                <el-select
                  v-model="form.userUuid"
                  filterable
                  remote
                  reserve-keyword
                  placeholder="请输入关键词搜索"
                  :remote-method="remoteMethod"
                  :loading="searchLoading">
                  <el-option
                    v-for="item in userList"
                    :key="item.uuid"
                    :label="item.userName"
                    :value="item.uuid">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="项目成员" prop="userMembers">
                <el-select
                  style="width: 100%"
                  v-model="form.userMembers"
                  filterable
                  remote
                  size="medium"
                  multiple
                  reserve-keyword
                  placeholder="请输入关键词"
                  :remote-method="queryMembers"
                  :loading="memberSearchLoading">
                  <el-option
                    v-for="item in membersList"
                    :key="item.uuid"
                    :label="item.userName"
                    :value="item.uuid">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="项目描述" prop="projectDesc">
                <el-input type="textarea" :rows="10" v-model="form.projectDesc" placeholder="请输入项目描述"/>
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
import {add, del, info, page, update} from '@/api/project/project'
import {list as listUser} from "@/api/sys/user";
import {nestedGetQuery} from "@/utils";

export default {
  name: 'project',
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      searchLoading: false,
      memberSearchLoading: false,
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
      projectList: [],
      userList: [],
      membersList: [],
      AppInfoList: [],
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        projectInfoDTO: {
          projectName: ''
        },
        projectType: 'MY'
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userUuid: [{required: true, message: '负责人不能为空', trigger: 'blur'}],
        projectName: [{required: true, message: '名称不能为空', trigger: 'blur'}],
        projectDesc: [{required: true, message: '描述不能为空', trigger: 'blur'}]
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
      page(nestedGetQuery(this.queryParams)).then(response => {
        this.projectList = response.data
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
        userUuid: undefined,
        userName: undefined,
        userGroup: undefined,
        cronExpression: undefined,
        invokeTarget: undefined,
        args: undefined,
        misfirePolicy: 1,
        concurrent: 1,
        userType: 1,
        status: 0,
        userMembers: []
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
      this.form.status = 0
      this.open = true
      this.userList = []
      this.title = '新增项目'
      this.isEdit = false
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
      listUser({}).then(response => {
        if (response.code === 2000) {
          this.userList = response.data
          this.membersList = response.data
          info(id).then(response => {
            this.form = response.data
            this.open = true
            this.title = '修改应用'
            this.isEdit = true
          })
        } else {
          this.msgError(response.msg)
          this.userList = []
        }
        this.searchLoading = false;
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.form.roleIdList = this.roleIdList
          this.form.userType = parseInt(this.form.userType)
          if (this.form.id !== undefined) {
            update({'projectInfoDTO': this.form}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            add({'projectInfoDTO': this.form}).then(response => {
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
        return delAppInfo({'ids': Ids})
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function () {
      })
    },
    // 路由跳转到流水线
    application(row) {
      this.$router.push(
        {
          path: '/application',
          query: {
            uuid: row.uuid
          }
        }
      )
    },
    projectView(row) {
      this.$router.push(
        {
          path: '/project/view',
          query: {
            uuid: row.uuid
          }
        }
      )
    },
    remoteMethod(query) {
      if (query !== '') {
        this.searchLoading = true;
        listUser(query).then(response => {
          if (response.code === 2000) {
            this.userList = response.data
          } else {
            this.msgError(response.msg)
            this.userList = []
          }
          this.searchLoading = false;
        })
      }
    },
    queryMembers(query) {
      if (query !== '') {
        this.memberSearchLoading = true;
        listUser(query).then(response => {
          if (response.code === 2000) {
            this.membersList = response.data
          } else {
            this.msgError(response.msg)
            this.membersList = []
          }
          this.memberSearchLoading = false;
        })
      }
    }
  }
}
</script>
