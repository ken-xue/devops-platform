<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="98px">
        <el-form-item label="所属分组" prop="group" label-width="2">
          <el-select v-model="queryParams.machineInfoDTO.groupUuid" size="medium" clearable
                     @change="getList" placeholder="请选择">
            <el-option
              v-for="item in groupList"
              :key="item.uuid"
              :label="item.name"
              :value="item.uuid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="IP/服务器名" prop="name" label-width="2">
          <el-input v-model="queryParams.machineInfoDTO.name" placeholder="请输入IP或者服务器名称" clearable size="small"
                    @keyup.enter.native="handleQuery"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button v-permission="['/sys/user/add:post']" type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">
            新增
          </el-button>
          <el-button v-permission="['sys:user:add']" type="success" icon="el-icon-upload2" size="mini" @click="handleImport">
            导入
          </el-button>
          <el-button v-permission="['sys:user:delete']" type="danger" icon="el-icon-delete" size="mini"
                     :disabled="multiple" @click="handleDelete">删除
          </el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="machineList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="序号" align="center" prop="userId" width="55" :show-overflow-tooltip="true">
          <template slot-scope="props">
            <p v-text="props.$index+1"/>
          </template>
        </el-table-column>
        <el-table-column label="服务器名" align="center" prop="name" :show-overflow-tooltip="true"/>
        <el-table-column label="IP" align="center" prop="ip" :show-overflow-tooltip="true"/>
        <el-table-column label="访问用户名" align="center" prop="accessUsername" width="100"/>
        <el-table-column label="访问方式" align="center" prop="email" max-width="100">
          <template slot-scope="scope">
            <el-tag size="small" :type="item.value === 'PASSWORD' ? 'success' : 'info'"
                    v-for="(item, index) in accessWays" :key="index" :label="item.label" :value="item.value"
                    :disabled="item.disabled" v-if="scope.row.accessWay===item.value">{{ item.label }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="gmtCreate" :show-overflow-tooltip="true"/>
        <el-table-column label="更新时间" align="center" prop="gmtModified" :show-overflow-tooltip="true"/>
        <el-table-column label="操作" align="center" min-width="150">
          <template slot-scope="scope">
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-s-platform"
                       @click="terminal(scope.row)">终端
            </el-button>
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-edit"
                       @click="handleUpdate(scope.row)">修改
            </el-button>
            <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-circle-plus-outline"
                       @click="openAddSecret(scope.row)">秘钥
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

      <!--终端-->
      <el-dialog :title="title" top="10vh" :visible.sync="terminalVisible" :before-close="closeTerminal" width="80%"
                 append-to-body>
        <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
          <el-tab-pane
            :key="item.name"
            v-for="(item, index) in editableTabs"
            :label="item.title"
            :name="item.name">

            <div style="overflow: hidden" :id="item.name" class="xterm"/>
            <!--            <xterm ref="Xterm"></xterm>-->
          </el-tab-pane>
          <!--          <xterm ref="Xterm"></xterm>-->
        </el-tabs>
      </el-dialog>

      <!-- 添加或修改对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="服务器名" prop="name">
                <el-input v-model="form.name" placeholder="请输入服务器名称"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="服务器IP" prop="ip">
                <el-input v-model="form.ip" placeholder="请输入服务器IP"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="访问端口" prop="port">
                <el-input v-model="form.port" placeholder="请输入访问端口"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="访问方式" prop="accessWay">
                <el-radio-group v-model="form.accessWay">
                  <el-radio v-for="type in accessWays" :label="type.value" :key="type.label">{{ type.label }}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="所属分组" prop="host">
                <el-select v-model="form.groupList" size="100" multiple placeholder="请选择">
                  <el-option
                    v-for="item in groupList"
                    :key="item.uuid"
                    :label="item.name"
                    :value="item.uuid">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="访问用户名" prop="accessUsername">
                <el-input v-model="form.accessUsername" placeholder="请输入用户名"/>
              </el-form-item>
            </el-col>


            <el-col v-if="form.accessWay === 'PASSWORD'" :span="24">
              <el-form-item label="访问密码" prop="accessPassword">
                <el-input type="password" v-model="form.accessPassword" placeholder="请输入访问密码"/>
              </el-form-item>
            </el-col>
            <el-col v-if="form.accessWay === 'ACCESS_KEY'" :span="24">
              <el-form-item label="访问密钥" prop="accessKey">
                <el-input type="password" v-model="form.accessKey" placeholder="请输入访问密钥"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="success" @click="testConnect">测试连接</el-button>
          <el-button type="primary" @click="submitForm" :disabled="submitDisable">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
      <!--      添加秘钥-->
      <el-dialog :title="title" :visible.sync="secretVisible" width="700px" append-to-body>
        <el-form ref="secretForm" :model="form" :rules="secretRules" label-width="120px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="服务器名" prop="name">
                <el-input v-model="form.name" :disabled="true" placeholder="请输入服务器名称"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="服务器IP" prop="ip">
                <el-input :disabled="true" v-model="form.ip" placeholder="请输入服务器IP"/>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="访问密钥" prop="accessKey">
                <el-input type="textarea" :rows="10" v-model="form.accessKey" placeholder="请输入访问密钥"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addSecret">添 加</el-button>
          <el-button @click="secretVisible=!secretVisible">取 消</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
import {add, del, testConn, get, page, update, addSecret} from '@/api/machine/machine'
import {list as getGroupList} from "@/api/machine/group"
import {nestedGetQuery} from "@/utils";
import Xterm from "@/views/machine/xterm";

import 'xterm/css/xterm.css'
import {Terminal} from 'xterm'
import {FitAddon} from 'xterm-addon-fit'
import {AttachAddon} from 'xterm-addon-attach'

export default {
  name: 'MachineInfo',
  components: {Xterm},
  data() {
    return {
      // 遮罩层
      loading: true,
      id: 0,
      terminalUuid: '',
      groupUUID: '',
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      submitDisable: true,
      // 总条数
      total: 0,
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      isEdit: false,
      terminalVisible: false,
      secretVisible: false,
      // 类型数据字典
      typeOptions: [],
      AppInfoList: [],
      roleIdList: [],
      groupList: [],
      machineList: [],
      accessWays: [
        {value: 'PASSWORD', label: '密码'},
        {value: 'ACCESS_KEY', label: '秘钥'}
      ],
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        machineInfoDTO: {
          name: '',
          groupUuid: ''
        }
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [{required: true, message: '名词不能为空', trigger: 'blur'}],
        ip: [{required: true, message: 'IP不能为空', trigger: 'blur'}],
        port: [{required: true, message: '端口不能为空', trigger: 'blur'}],
        email: [{required: true, message: '邮箱不能为空', trigger: 'blur'}],
        userPassword: [{required: true, message: '密码不能为空', trigger: 'blur'}],
        confirmPassword: [{required: true, message: '确认不能为空', trigger: 'blur'}],
        status: [{required: true, message: '状态不能为空', trigger: 'blur'}]
      },
      secretRules: {
        accessKey: [{required: true, message: '秘钥不能为空', trigger: 'blur'}],
      },
      editableTabsValue: '1',
      editableTabs: [{
        title: '终端1',
        name: '1',
        content: 'Tab 1 content'
      }],
      tabIndex: 1
    }
  },
  created() {
    this.getMachineGroupList()
    this.getList()
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      this.loading = true
      page(nestedGetQuery(this.queryParams)).then(response => {
        this.machineList = response.data
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
        status: 0,
        accessWay: 'PASSWORD'
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
      this.title = '添加服务器'
      this.form.accessWay = 'PASSWORD'
      this.form.port = 22
      this.isEdit = false
    },
    handleImport(){
      this.msgError('该功能未实现')
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
      get(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改服务器信息'
        this.isEdit = true
      })
    },
    openAddSecret(row) {
      this.reset()
      const id = row.id || this.id
      get(id).then(response => {
        this.form = response.data
        this.secretVisible = true
        this.accessKey = ''
        this.title = '添加访问秘钥'
        this.isEdit = true
      })
    },
    getMachineGroupList(){
      getGroupList({}).then(response => {
        this.groupList = response.data
      })
    },
    // 终端
    terminal(row) {
      this.terminalVisible = true
      this.title = '终端'
      this.editableTabs = [{
        title: '终端1',
        name: '1',
        content: 'Tab 1 content'
      }]
      this.terminalUuid = row.uuid;
      this.initSocket(row.uuid, 1)
    },
    closeTerminal() {
      this.terminalVisible = false
      this.editableTabs = []
      this.socket.close()
      this.term.dispose()
    },
    // 测试连接服务器
    testConnect() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          testConn({'machineInfoDTO': this.form}).then(response => {
            if (response.code === 2000) {
              this.msgSuccess('连接成功')
              this.submitDisable = false;
            } else {
              this.msgError(response.msg)
            }
          })
        }
      })
    },
    addSecret() {
      this.$refs['secretForm'].validate(valid => {
        if (valid) {
          addSecret({'machineInfoDTO': this.form}).then(response => {
            if (response.code === 2000) {
              this.msgSuccess('添加成功')
              this.open = false
              this.getList()
            } else {
              this.msgError(response.msg)
            }
          })
        }
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
            update({'machineInfoDTO': this.form}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            add({'machineInfoDTO': this.form}).then(response => {
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
        return del({'ids': Ids})
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function () {
      })
    },
    // 路由跳转到流水线
    pipeline(row) {
      this.$router.push(
        {
          path: '/pipeline',
          query: {
            uuid: row.uuid
          }
        }
      )
    },
    handleTabsEdit(targetName, action) {
      if (action === 'add') {
        if (this.editableTabs.length >= 12) {
          this.msgError('最大只能开12个终端')
          return
        }
        let newTabName = ++this.tabIndex + '';
        let title = '终端' + (this.tabIndex);
        this.editableTabs.push({
          title: title,
          name: newTabName,
          content: title
        });
        this.editableTabsValue = newTabName;
        this.initSocket(this.terminalUuid, newTabName)
      }
      if (action === 'remove') {
        let tabs = this.editableTabs;
        let activeName = this.editableTabsValue;
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
              }
            }
          });
        }
        this.editableTabsValue = activeName;
        this.editableTabs = tabs.filter(tab => tab.name !== targetName);
      }
    },
    initTerm(id) {
      const term = new Terminal({
        fontSize: 14,
        rows: 33,
        cursorBlink: true,
        windowsMode: true,
        disableStdin: false
      });
      const attachAddon = new AttachAddon(this.socket);
      const fitAddon = new FitAddon();
      term.loadAddon(attachAddon);
      term.loadAddon(fitAddon);
      term.open(document.getElementById(id));
      fitAddon.fit();
      term.focus();
      this.term = term
    },
    initSocket(uuid, id) {
      // this.socket = new WebSocket(this.socketURI);
      this.socket = new WebSocket('ws://127.0.0.1:8088/ws/terminal?' + uuid);
      this.socketOnClose();
      this.socketOnOpen(id);
      this.socketOnError();
    },
    socketOnOpen(id) {
      this.socket.onopen = () => {
        // 链接成功后
        this.initTerm(id)
      }
    },
    socketOnClose() {
      this.socket.onclose = () => {
        // console.log('close socket')
      }
    },
    socketOnError() {
      this.socket.onerror = () => {
        // console.log('socket 链接失败')
      }
    },
    beforeDestroy() {
      this.socket.close()
      this.term.dispose()
    }
  }
}
</script>
