<template>
  <el-dialog center top="10vh" title="创建集群" :visible.sync="open" width="700px" append-to-body @close="cancel">
    <el-form ref="form" :model="form" :rules="rules" label-width="70px">
      <el-tabs type="card" v-model="form.type">
        <el-tab-pane name="Linux" label="Linux" style="margin-top: 20px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="别名" prop="name">
                <el-input v-model="form.name" placeholder="请输入别名"/>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username" placeholder="请输入用户名"/>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="密码" prop="password">
                <el-input v-model="form.password" placeholder="请输入密码"/>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="版本" prop="version">
                <el-select style="width: 100%" v-model="form.version" placeholder="请选择版本">
                  <el-option
                    v-for="item in versions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="备注" prop="remark">
                <el-input
                  type="textarea"
                  :rows="5"
                  placeholder="请输入内容"
                  v-model="form.remark">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-for="(node, index) in form.nodeList" :key="node.key">
              <el-form-item
                :label="'节点' + (index+1)"
                :prop="'nodeList.' + index + '.value'"
                :rules="{required: true, message: '请选择机器节点', trigger: 'blur'}"
              >
                <el-select
                  style="width: 100%"
                  v-model="node.value"
                  filterable
                  remote
                  reserve-keyword
                  placeholder="请输入关键词搜索"
                  :remote-method="getHostList"
                  :loading="searchLoading">
                  <el-option
                    v-for="item in hostList"
                    :key="item.uuid"
                    :label="item.name"
                    :value="item.uuid"
                  >
                    <span style="float: left">{{ item.name }}</span>
                    <span style="float: right; color: #8492a6; font-size: 8px;padding-right: 20px">{{ item.ip }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item
                :label="'路径' + (index+1)"
                :prop="'nodeList.' + index + '.path'"
                :rules="{required: true, message: '路径不能为空', trigger: 'blur'}"
              >
                <el-input style="width: 60%" v-model="node.path" placeholder="请选择输入路径"></el-input>
                <el-button type="danger" style="margin-left: 10px" @click.prevent="removeNode(node)">删除</el-button>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item>
                <el-button @click="addNode">新增节点</el-button>
                <el-button @click="resetForm('dynamicValidateForm')">重置</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane name="Kubernetes" label="Kubernetes">Kubernetes</el-tab-pane>
        <el-tab-pane name="Docker" label="Docker">Docker</el-tab-pane>
      </el-tabs>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" :loading="submitLoading" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
    <minio-create-logger v-if="loggerVisible" ref="MinioCreateLogger"></minio-create-logger>
  </el-dialog>
</template>

<script>
import {list as searchHostList} from "@/api/machine/machine";
import MinioCreateLogger from "@/views/base/minio/minio-create-logger";

export default {
  name: 'MinioCreate',
  components: {MinioCreateLogger},
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
      // 表单参数
      form: {
        type: 'Linux',
        nodeList: [{
          value: '',
          path: '',
        }],
      },
      versions: [
        {value: 'latest', label: 'latest'}
      ],
      // 表单校验
      rules: {
        name: [{required: true, message: '名称不能为空', trigger: 'blur'}],
        username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}],
        version: [{required: true, message: '版本不能为空', trigger: 'blur'}]
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
        hostList: []
      }
      this.resetForm('form')
    },
    removeNode(item) {
      let index = this.form.nodeList.indexOf(item)
      if (this.form.nodeList.length == 1) {
        this.msgError('至少添加一个节点')
        return
      }
      if (index !== -1) {
        this.form.nodeList.splice(index, 1)
      }
    },
    addNode() {
      this.form.nodeList.push({
        value: '',
        path: '',
        key: Date.now()
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.submitLoading = true
          this.loggerVisible = true
          this.$nextTick(() => {
            this.$refs.MinioCreateLogger.init(this.form)
          })
        }
      })
    }
  }
}
</script>
