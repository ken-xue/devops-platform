<template>
  <div style="width: 100%" v-if="visible">
    <el-form ref="form" :model="form" :rules="rules" label-position="top" size="mini">
      <el-row>
        <el-col :span="24">
          <el-form-item label="JDK版本" prop="jdk">
            <el-select v-model="form.jdk" placeholder="请选择JDK版本" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in JDKVersions" :key="index" :label="item.label" :value="item.value"
                         :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Maven版本" prop="mvn">
            <el-select v-model="form.mvn" placeholder="请选择Maven版本" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in mavenVersions" :key="index" :label="item.label" :value="item.value"
                         :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="构建命令" prop="buildScript">
            <div style="width: 100%;overflow-x: scroll">
              <AceEditor
                v-model="form.buildScript"
                width="100%"
                height="150px"
                :fontSize="14"
                :showPrintMargin="true"
                :showGutter="true"
                :highlightActiveLine="true"
                mode="sh"
                theme="monokai"
                :onChange="onChange"
                name="editor"
                :editorProps="{$blockScrolling: true}"
              />
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="打包路径" prop="packagePath">
                <span slot="label">
                  打包路径
                  <el-tooltip placement="top">
                    <div slot="content">
                      打包路径示例：target/devops-platform 多个路径使用;分号分隔
                    </div>
                    <i class="el-icon-question"/>
                  </el-tooltip>
                </span>
              <el-input v-model="form.packagePath" placeholder="请输入打包路径"/>
          </el-form-item>
        </el-col>
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

import {nodeInfo, addNodeInfo, updateInfo} from '@/api/app/pipeline'

export default {
  name: "java-build",
  components: {
    AceEditor,
    SplitEditor,
  },
  data() {
    return {
      id:0,
      visible: false,
      nodeUuid: '',
      initBuildScript: '# maven build default command\nmvn -B clean package -Dmaven.test.skip=true -Dautoconfig.skip\n# gradle build default command\n# ./gradlew build\n\n# ant build default command\n# ant \n',
      form: {
        buildScript: this.initBuildScript
      },
      radio: 1,
      JDKVersions: [
        {value: 'JDK 1.7', label: 'JDK 1.7'},
        {value: 'JDK 1.8', label: 'JDK 1.8'},
        {value: 'JDK 1.9', label: 'JDK 1.9'},
        {value: 'OPENJDK 10', label: 'OPENJDK 10'},
        {value: 'OPENJDK 11', label: 'OPENJDK 11'},
        {value: 'JDK 17', label: 'JDK 17'},
      ],
      mavenVersions: [
        {value: 'MAVEN 3.6', label: 'MAVEN 3.6'},
        {value: 'MAVEN 3.7', label: 'MAVEN 3.7'},
      ],
      noticeWays: [
        {value: 'DING_DING', label: '钉钉'},
        {value: 'EMAIL', label: '邮件'},
      ],
      rules: {
        jdk: [{required: true, message: 'jdk版本不能为空', trigger: 'blur'}],
        mvn: [{required: true, message: 'maven版本不能为空', trigger: 'blur'}],
        buildScript: [{required: true, message: '构建命令不能为空', trigger: 'blur'}],
        packagePath: [{required: true, message: '打包路径不能为空', trigger: 'blur'}]
      }
    }
  },
  methods: {
    init(nodeUuid) {
      this.form = {
        buildScript: this.initBuildScript
      }
      this.nodeUuid = nodeUuid
      nodeInfo(nodeUuid).then(response => {
        const data = response.data
        if (data) {
          this.form = JSON.parse(data.info)
          this.id = data.id
        }
        this.visible = true
      })
    },
    //获取当前节点的配置文件（改之前的每个配置节点提交为统一流水线一起提交到后台）
    getNodeConfig(){
        return this.form
    },
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 构建数据json
          let info = {
            'id':this.id,
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
    onChange(data){
      this.form.buildScript = data
    }
  }
}
</script>

<style scoped>
</style>
