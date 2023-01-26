<template>
  <el-dialog title="创建集群" :visible.sync="open" width="700px" append-to-body @close="cancel">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-row>
        <el-col :span="24">
          <el-form-item label="集群名称" prop="name">
            <el-input v-model="form.name" placeholder="请输入集群名称"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="版本" prop="version">
            <el-select v-model="form.version" placeholder="请选择版本">
              <el-option
                v-for="item in kubernetesVersions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Runtime" prop="runtime">
            <el-select v-model="form.runtime" placeholder="请选择Runtime">
              <el-option
                v-for="item in kubernetesRuntime"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Master节点" prop="masterHostList">
            <el-select
              style="width: 100%"
              v-model="form.masterHostList"
              multiple
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
                :disabled="disableMaster(item)"
              >
                <span style="float: left">{{ item.name }}</span>
                <span style="float: right; color: #8492a6; font-size: 8px;padding-right: 20px">{{ item.ip }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Slave节点" prop="slaveHostList">
            <el-select
              style="width: 100%"
              v-model="form.slaveHostList"
              multiple
              filterable
              remote
              reserve-keyword
              placeholder="请输入关键词"
              :remote-method="getSlaveHostList"
              :loading="searchLoading">
              <el-option
                v-for="item in slaveHostList"
                :key="item.uuid"
                :label="item.name"
                :value="item.uuid"
                :disabled="disableSlave(item)"
              >
                <span style="float: left">{{ item.name }}</span>
                <span style="float: right; color: #8492a6; font-size: 8px;padding-right: 20px">{{ item.ip }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" :loading="submitLoading" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
    <logger v-if="loggerVisible" ref="logger"></logger>
  </el-dialog>
</template>

<script>
import {list as searchHostList} from "@/api/machine/machine";
import logger from "@/views/kubernetes/logger";

export default {
  name: 'Create',
  components: {logger},
  data() {
    return {
      // 遮罩层
      loading: true,
      open: false,
      isEdit: false,
      searchLoading: false,
      submitLoading: false,
      loggerVisible: false,
      // 类型数据字典
      typeOptions: [],
      dataList: [],
      hostList: [],
      slaveHostList: [],
      masterHostList: [],
      // 表单参数
      form: {},
      kubernetesVersions: [
        {value: '1.21', label: '1.21'},
        {value: '1.24', label: '1.24'},
      ],
      kubernetesRuntime: [
        {value: 'Docker', label: 'Docker'},
        {value: 'Containerd', label: 'Containerd'},
      ],
      // 表单校验
      rules: {
        name: [{required: true, message: '集群名称不能为空', trigger: 'blur'}],
        version: [{required: true, message: '版本不能为空', trigger: 'blur'}],
        runtime: [{required: true, message: 'Runtime不能为空', trigger: 'blur'}],
        masterHostList: [{required: true, message: '节点不能为空', trigger: 'blur'}],
        slaveHostList: [{required: true, message: '节点不能为空', trigger: 'blur'}]
      }
    }
  },
  created() {
    // this.getList()
  },
  methods: {
    init() {
      this.open = true
    },
    disableMaster(item) {
      for (let i = 0; i < this.form.slaveHostList.length; i++) {
        if (item.uuid === this.form.slaveHostList[i]) return true;
      }
      return false
    },
    disableSlave(item) {
      for (let i = 0; i < this.form.masterHostList.length; i++) {
        if (item.uuid === this.form.masterHostList[i]) return true;
      }
      return false
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
    getSlaveHostList(name) {
      this.searchLoading = true
      searchHostList({'name': name}).then(response => {
        if (response.code === 2000) {
          this.slaveHostList = response.data
        } else {
          this.msgError(response.msg)
        }
        this.searchLoading = false
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
        name: undefined,
        version: undefined,
        slaveHostList: [],
        masterHostList: []
      }
      this.resetForm('form')
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.submitLoading = true
          this.loggerVisible = true
          this.$nextTick(() => {
            this.$refs.logger.init(this.form)
          })
        }
      })
    }
  }
}
</script>
