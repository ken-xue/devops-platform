<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
        <el-form-item label="名称" prop="roleName">
          <el-input
            v-model="queryParams.ClusterDTO.name"
            placeholder="请输入名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button v-permission="['kubernetes:cluster:add']" type="primary" icon="el-icon-plus" size="mini"
                     @click="handleAdd">导入
          </el-button>
          <el-button v-permission="['kubernetes:cluster:delete']" type="danger" icon="el-icon-delete" size="mini"
                     :disabled="multiple" @click="handleDelete">删除
          </el-button>
          <el-button v-permission="['kubernetes:cluster:add']" type="success" icon="el-icon-orange" size="mini"
                     @click="handleCreate">创建集群
          </el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column
          label="序号"
          align="center"
          prop="id"
          width="55"
          :show-overflow-tooltip="true">
          <template slot-scope="props">
            <p v-text="props.$index+1"/>
          </template>
        </el-table-column>
        <el-table-column
          label="集群名称"
          align="center"
          prop="name"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="版本"
          align="center"
          width="50"
          prop="version"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="访问方式"
          align="center"
          prop="accessWay"
          :show-overflow-tooltip="true"
        >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.accessWay=='TOKEN'">TOKEN</el-tag>
            <el-tag v-if="scope.row.accessWay=='CONFIG_FILE'" type="success">配置文件</el-tag>
            <el-tag v-if="scope.row.accessWay=='UPWD'" type="info">用户名密码</el-tag>
            <!--            <el-tag type="warning">标签四</el-tag>-->
            <!--            <el-tag type="danger">标签五</el-tag>-->
          </template>
        </el-table-column>
        <!--        <el-table-column-->
        <!--          label="集群配置文件"-->
        <!--          align="center"-->
        <!--          prop="config"-->
        <!--          :show-overflow-tooltip="true"-->
        <!--        />-->
        <el-table-column label="操作" min-width="100" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button v-permission="['kubernetes:cluster:delete']" size="mini" type="text" style="color: #0cd20c"
                       icon="el-icon-set-up" @click="controlPanel(scope.row)">资源
            </el-button>

            <a target="_blank" :href="scope.row.dashboard">
              <el-button v-permission="['kubernetes:cluster:dashboard']" size="mini" type="text"
                         icon="el-icon-s-promotion">面板
              </el-button>
            </a>

            <el-button v-if="scope.row.machineUuid != '' && scope.row.machineUuid != null"
                       v-permission="['kubernetes:cluster:update']" size="mini" type="text" icon="el-icon-s-platform" style="color: #000500"
                       @click="terminalHandler(scope.row)">终端
            </el-button>

              <el-popover v-show="scope.row.machineUuid == '' || scope.row.machineUuid == null" placement="top"
                width="200"
                :trigger="click">
<!--                <el-tooltip content='如果机器不存在则需要先将机器添加进列表'>-->
<!--                  <i class="el-icon-question">这是一段内容这是一段内容确定删除吗</i>-->
<!--                </el-tooltip>-->
                <p>需要先将机器添加进列表</p>
                  <el-select
                    v-model="machineUuid"
                    filterable
                    remote
                    reserve-keyword
                    placeholder="请输入关键词"
                    :remote-method="getHostList"
                    :loading="searchLoading">
                    <el-option
                      v-for="item in hostList"
                      :key="item.uuid"
                      :label="item.name"
                      :value="item.uuid"
                      :disabled="machineUuid==item.uuid"
                    >
                      <span style="float: left">{{ item.name }}</span>
                      <span style="float: left; color: #8492a6; font-size: 8px;margin-left: 25px">{{ item.ip }}</span>
                    </el-option>
                  </el-select>
                <div style="text-align: right; margin-top: 10px">
                  <el-button size="mini" type="text" @click="visible = false">取消</el-button>
                  <el-button type="primary" size="mini" @click="configTerminal(scope.row)">确定</el-button>
                </div>
                <el-button slot="reference" size="mini" style="color: #000500" type="text" icon="el-icon-s-platform">终端</el-button>
              </el-popover>

            <el-button v-permission="['kubernetes:cluster:update']" size="mini" type="text" icon="el-icon-edit"
                       @click="handleUpdate(scope.row)">修改
            </el-button>
            <el-button v-permission="['kubernetes:cluster:delete']" size="mini" type="text" style="color: red"
                       icon="el-icon-delete" @click="handleDelete(scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total>0"
        style="padding: 0px"
        :total="total"
        :page.sync="queryParams.pageIndex"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
      <!-- 添加或修改对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="集群名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入集群名称"/>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="版本" prop="version">
                <el-select v-model="form.version" size="100" placeholder="请选择">
                  <el-option
                    v-for="item in kubeVersions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="访问方式" prop="accessWay">
                <el-select v-model="form.accessWay" size="100" placeholder="请选择">
                  <el-option
                    v-for="item in accessWays"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="form.accessWay == 'TOKEN'">
              <el-form-item label="秘钥" prop="secretKey">
                <el-input type="textarea"
                          :rows="5" v-model="form.secretKey" placeholder="请输入秘钥"/>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="form.accessWay == 'TOKEN'||form.accessWay == 'UPWD'">
              <el-form-item label="访问地址" prop="accessUrl">
                <el-input v-model="form.accessUrl" placeholder="请输入访问链接"/>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="form.accessWay == 'CONFIG_FILE'">
              <el-form-item label="文件" prop="file">
                <el-upload
                  ref="fileRefs"
                  class="upload-demo"
                  action="importUrl"
                  multiple
                  :on-change="fileChange"
                  :limit="1"
                  :file-list="configFileList"
                  :on-remove="fileRemove"
                  :auto-upload="false">
                  <el-button size="small" type="primary">点击上传</el-button>
                </el-upload>
              </el-form-item>
            </el-col>
            <div v-if="form.accessWay == 'UPWD'">
              <el-col :span="24">
                <el-form-item label="用户名" prop="username">
                  <el-input v-model="form.username" placeholder="请输入集群访问用户名"/>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="密码" prop="password">
                  <el-input v-model="form.password" placeholder="请输入集群访问密码"/>
                </el-form-item>
              </el-col>
            </div>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </el-card>
    <Create ref="Create" v-if="createClusterVisible" @refreshDataList="getList"></Create>
    <ControlPanel ref="ControlPanel" v-if="controlPanelVisible"></ControlPanel>
    <terminal ref="Terminal" v-if="terminalVisible"></terminal>
  </div>
</template>

<script>
import {add, del, importing, info, page, update} from '@/api/kubernetes/cluster'
import {nestedGetQuery} from "@/utils";
import Create from "@/views/kubernetes/create";
import ControlPanel from "@/views/kubernetes/resource";
import Terminal from "@/views/kubernetes/terminal";
import {list as searchHostList} from "@/api/machine/machine";

export default {
  name: 'Kubernetes',
  components: {Terminal, ControlPanel, Create},
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
      visible: false,
      open: false,
      isEdit: false,
      createClusterVisible: false,
      controlPanelVisible: false,
      terminalVisible: false,
      searchLoading: false,
      // 类型数据字典
      typeOptions: [],
      hostList: [],
      dataList: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        ClusterDTO: {
          name: ''
        }
      },
      accessWays: [
        {value: 'TOKEN', label: '秘钥'},
        {value: 'CONFIG_FILE', label: '配置文件'},
        {value: 'UPWD', label: '用户名密码'}
      ],
      kubeVersions: [
        {value: '1.21', label: '1.21'},
        {value: '1.23', label: '1.23'},
        {value: '1.24', label: '1.24'},
        {value: '1.25', label: '1.25'}
      ],
      configFileList: [],
      importUrl: '',
      machineUuid: '',
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [{required: true, message: '集群名称不能为空', trigger: 'blur'}],
        version: [{required: true, message: '版本不能为空', trigger: 'blur'}],
        secretKey: [{required: true, message: '访问秘钥不能为空', trigger: 'blur'}],
        accessWay: [{required: true, message: '访问方式不能为空', trigger: 'blur'}],
        username: [{required: true, message: '访问用户名不能为空', trigger: 'blur'}],
        password: [{required: true, message: '访问密码不能为空', trigger: 'blur'}],
        accessUrl: [{required: true, message: '访问地址不能为空', trigger: 'blur'}],
        file: [{required: true, message: '集群配置文件不能为空', trigger: 'change'}]
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
        this.dataList = response.data
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
        file: null,
        name: undefined,
        version: undefined,
        accessWay: undefined,
        accessUrl: undefined,
        secretKey: undefined,
        username: undefined,
        password: undefined,
        config: undefined
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
      this.resetForm('queryForm')
      this.queryParams.roleDTO.roleName = ''
      this.handleQuery()
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '导入'
      this.isEdit = false
    },
    fileChange(uploadFile, files) {
      this.form.file = uploadFile
      if (files.length !== 0) {
        this.$refs.form.validateField('file')
      }
    },
    fileRemove(uploadFile, files) {
      if (files.length === 0) {
        this.form.file = null
        this.$refs.form.validateField('file')
      }
    },
    handleCreate(row) {
      this.createClusterVisible = true
      this.$nextTick(() => {
        this.$refs.Create.init(row)
      })
    },
    controlPanel(row) {
      this.controlPanelVisible = true
      this.$nextTick(() => {
        this.$refs.ControlPanel.init(row)
      })
    },
    terminalHandler(row) {
      this.terminalVisible = true
      if (row.machineUuid != null && row.machineUuid != '') {
        this.$nextTick(() => {
          this.$refs.Terminal.init(row.machineUuid)
        })
      } else {
        //需要配置要访问的终端
        this.$refs.node-popover-`row.id`.click
        this.msgError('Master节点为空')
      }
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.jobId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      info(row.id).then(response => {
        this.form.id = response.data.id
        this.form.name = response.data.name,
          this.form.version = response.data.version,
          this.form.secretKey = response.data.secretKey,
          this.form.config = response.data.config,
          this.open = true
        this.title = '修改'
        this.isEdit = true
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          const file = this.form.accessWay == 'CONFIG_FILE' ? this.$refs.fileRefs.uploadFiles[0].raw : null
          const fd = new FormData()
          fd.append('info', JSON.stringify(this.form))
          fd.append('file', file)
          if (this.form.id !== undefined) {
            update(fd).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
                this.$nextTick(() => {
                  this.$refs.Terminal.init(row.machineUuid)
                })
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            importing(fd).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('导入成功')
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
    configTerminal(row){
      row.machineUuid = this.machineUuid
      update({'clusterDTO':row}).then(response => {
        if (response.code === 2000) {
          this.hostList = response.data
          this.getList()
        } else {
          this.msgError(response.msg)
        }
        this.searchLoading = false
      })
    },
    getHostList(name) {
      this.searchLoading = true
      searchHostList({'name': name}).then(response => {
        if (response.code === 2000) {
          this.hostList = response.data
        } else {
          this.msgError(response.msg)
        }
        this.searchLoading = false
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const Ids = (row.id && [row.id]) || this.ids
      this.$confirm('是否确认释放"' + row.name + '"的集群?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return del({'ids': Ids})
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function () {
      })
    }
  }
}
</script>
