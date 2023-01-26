<template>
  <div style="width: 100%" v-if="visible">
    <el-form ref="form" :model="form" :rules="rules" label-position="top" size="mini">
      <el-row>
        <el-col :span="24">
          <el-form-item label="主机" prop="host">
            <el-select
              style="width: 100%"
              v-model="form.host"
              multiple
              filterable
              remote
              reserve-keyword
              placeholder="请输入关键词"
              :remote-method="getHostList"
              :loading="loading">
              <el-option
                v-for="item in hostList"
                :key="item.uuid"
                :label="item.name"
                :value="item.uuid">
                <span style="float: left">{{ item.name }}</span>
                <span style="float: right; color: #8492a6; font-size: 8px">{{ item.ip }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="下载路径" prop="packagePath">
                <span slot="label">
                  下载路径
                  <el-tooltip placement="top">
                    <div slot="content">
                      下载路径示例：/home/admin/devops_platform
                    </div>
                    <i class="el-icon-question"/>
                  </el-tooltip>
                </span>
            <el-input v-model="form.packagePath" placeholder="请输入下载路径"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="部署脚本" prop="deployScript">
            <div style="width: 100%;overflow-x: scroll">
              <AceEditor
                width="100%"
                height="150px"
                :fontSize="14"
                :showPrintMargin="true"
                :showGutter="true"
                :highlightActiveLine="true"
                mode="sh"
                theme="monokai"
                :onChange="onChange"
                v-model="form.deployScript"
                name="editor"
                :editorProps="{$blockScrolling: true}"
              />
            </div>
          </el-form-item>
        </el-col>
<!--        <el-col :span="24">-->
<!--          <el-form-item label="通知" prop="notice">-->
<!--            <el-select v-model="form.notice" placeholder="请选择通知方式" clearable :style="{width: '100%'}">-->
<!--              <el-option v-for="(item, index) in noticeWays" :key="index" :label="item.label" :value="item.value" :disabled="item.disabled"></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button size="mini" style="width: 100%" type="primary" @click="submitForm">保 存</el-button>
    </div>
  </div>
</template>

<script>
// see https://www.npmjs.com/package/vue2-brace-editor
import {Ace as AceEditor, Split as SplitEditor} from 'vue2-brace-editor';

import 'brace/mode/sh';
import 'brace/theme/monokai';
import {list as searchHostList} from '@/api/machine/machine'

import {nodeInfo, addNodeInfo, updateInfo} from '@/api/app/pipeline'

export default {
  name: "host-deploy",
  components: {
    AceEditor,
    SplitEditor,
  },
  data() {
    return {
      id:0,
      visible: false,
      loading: false,
      nodeUuid: '',
      hostList: [],
      initDeployScript: '# 解压缩到指定目录中，再执行启动脚本（通常在代码中维护，如示deploy.sh \n\n # tar zxvf /home/admin/app/package.tgz -C /home/admin/app/\n # sh /home/admin/app/deploy.sh restart\n ',
      form: {
        host:'',
        deployScript: this.initDeployScript
      },
      radio: 1,
      noticeWays: [
        {value: 'DING_DING', label: '钉钉'},
        {value: 'EMAIL', label: '邮件'},
      ],
      rules: {
        host: [{required: true, message: '主机不能为空', trigger: 'blur'}],
        jdk: [{required: true, message: 'jdk版本不能为空', trigger: 'blur'}],
        mvn: [{required: true, message: 'maven版本不能为空', trigger: 'blur'}],
        deployScript: [{required: true, message: '构建命令不能为空', trigger: 'blur'}],
        packagePath: [{required: true, message: '打包路径不能为空', trigger: 'blur'}]
      }
    }
  },
  methods: {
    init(nodeUuid) {
      this.form = {
        deployScript: this.initDeployScript
      }
      this.nodeUuid = nodeUuid
      nodeInfo(nodeUuid).then(response => {
        if (response.code === 2000) {
          const data = response.data
          if (data) {
            this.form = JSON.parse(data.info)
            this.id = data.id
          }
        } else {
          this.msgError(response.msg)
        }
        this.getHostList();
        this.visible = true
      })
    },
    // 获取主机列表
    getHostList(name) {
      console.log(name)
      this.loading = true
      searchHostList({'name': name}).then(response => {
        if (response.code === 2000) {
          this.hostList = response.data
        } else {
          this.msgError(response.msg)
        }
        this.loading = false
      })
    },
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 构建数据json
          let info = {
            'id': this.id,
            'nodeUuid': this.nodeUuid,
            'info': JSON.stringify(this.form)
          }
          if (this.id !== 0) {
            updateInfo({'pipelineNodeInfoDTO': info}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
              addNodeInfo({'pipelineNodeInfoDTO': info}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('新增成功')
              } else {
                this.msgError(response.msg)
              }
            })
          }
        }
      })
    },
    onChange() {

    }
  }
}
</script>

<style scoped>

</style>
