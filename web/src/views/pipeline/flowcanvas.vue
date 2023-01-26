<template>
  <el-drawer :title="!pipelineId ? '新增' : '编排'" top="3vh" :visible.sync="open" size="80%" append-to-body
             :destroy-on-close="true"
             @close=closeDialog :close-on-click-modal="false">
    <el-container class="flowChartWrap">
      <el-main>
        <el-container>
          <ComponentTree v-if="nodeTreeVisible"></ComponentTree>
          <el-container>
            <!-- 2.2.1 flow面板 -->
            <el-main class="main">
              <!-- 2.2.1.1操作按钮 -->
              <div id="mainMenu" v-if="!infoVisible" style="overflow: hidden">
                <div class="tool-left">
                  <el-button :icon="hideOrShowIcon" @click="hideOrShowTree" size="small">{{ hideOrShowText }}
                  </el-button>
                  <el-button icon="el-icon-brush" @click="resetFlow" size="small">重置</el-button>
                  <el-button icon="el-icon-takeaway-box" @click="saveOrUpdate" size="small">保存</el-button>
                  <el-button icon="el-icon-video-play" @click="execute" :disabled="isExecDisable" size="small">执行
                  </el-button>
                </div>
                <div class="tool-right">
                  <el-tooltip content="撤销">
                    <el-button icon="el-icon-refresh-left" :disabled="isUndoDisable" @click="undo" circle></el-button>
                  </el-tooltip>
                  <el-tooltip content="放大">
                    <el-button icon="el-icon-zoom-in" @click="zoomOut" circle></el-button>
                  </el-tooltip>
                  <el-tooltip content="缩小">
                    <el-button icon="el-icon-zoom-out" @click="zoomIn" circle></el-button>
                  </el-tooltip>
                  <el-tooltip content="自动布局">
                    <el-button icon="el-icon-bangzhu" circle></el-button>
                  </el-tooltip>
                  <el-tooltip content="适应画布">
                    <el-button icon="el-icon-money" circle></el-button>
                  </el-tooltip>
                  <el-tooltip content="全屏">
                    <el-button icon="el-icon-full-screen" circle></el-button>
                  </el-tooltip>
                  <el-tooltip :content="hideOrShowConfigText">
                    <el-button :icon="hideOrShowConfigIcon" circle @click="hideOrShowConfig"></el-button>
                  </el-tooltip>
                </div>
              </div>
              <!-- 画布容器 -->
              <div class="mainContainer" @drop="dropHandle" @dragover="dragoverHandle">
                <div id="mainContainer" @click="clickBgHandle"></div>
              </div>
              <!--节点执行日志-->
              <log v-if="nodeExecuteLogVisible" ref="Log"></log>
              <NodeView v-if="nodeViewVisible" ref="View"></NodeView>
            </el-main>
            <!-- 2.2.2 组件属性设置 -->
            <el-aside v-show="nodeConfigVisible" width="300px" class="right">
              <el-container id="mainNodeInfo">
                <el-main>
                  <div>
                    <div v-show="toolBarShow==='component'">
                      <div v-show="isShowNode">
                        <div class="title">流水线基本信息</div>
                        <div class="model-attr">
                          <p>
                            <span class="item">所属应用</span>
                            <span class="value" v-text="applicationName">Test</span>
                          </p>
                          <p v-if="pipelineId">
                            <span class="item">创建日期</span>
                            <span class="value" v-text="">2019-04-19 12:14:39</span>
                          </p>
                          <p>
                            <span class="item">流水线名称</span>
                            <el-input size="mini" v-model="pipelineName"></el-input>
                          </p>
                          <p>
                            <span class="item">触发条件</span>
                            <el-select size="mini" v-model="triggerWay" placeholder="请选择触发条件" clearable :style="{width: '100%'}">
                              <el-option v-for="(item, index) in triggerList" :key="index" :label="item.label"
                                         :value="item.value"
                                         :disabled="item.disabled"></el-option>
                            </el-select>
                          </p>
                          <p>
                            <span class="item">触发分支</span>
                            <el-select size="mini" v-model="branch" placeholder="请选择触发分支" clearable :style="{width: '100%'}">
                              <el-option v-for="(item, index) in branchList" :key="index" :label="item.label"
                                         :value="item.value"></el-option>
                            </el-select>
                          </p>
                          <p>
                            <span class="item">描述</span>
                            <el-input type="textarea" :rows="10" size="small" v-model="description"></el-input>
                          </p>
                        </div>
                      </div>
                      <div v-show="isShowNodeConfig">
                        <div class="title">节点配置</div>
                        <!--                        <div class="node-attr">-->
                        <!--                          <p>-->
                        <!--                            <span class="item">节点ID</span>-->
                        <!--                            <span class="value">{{ currentNodeId }}</span>-->
                        <!--                          </p>-->
                        <!--                        </div>-->
                        <div class="model-attr node-config">
                          <java-build ref="JavaBuild" v-if="javaBuildVisible"></java-build>
                          <host-deploy ref="HostDeploy" v-if="hostDeployVisible"></host-deploy>
                        </div>
                      </div>
                    </div>
                    <!--                    <div v-show="toolBarShow==='message'">-->
                    <!--                      <div class="title">执行记录</div>-->
                    <!--                      <div>-->
                    <!--                        <el-card class="messageInfo" v-for="(m,idx) in messagesList" :key="idx">-->
                    <!--                          <p>{{ m.time }}</p>-->
                    <!--                          <div>-->
                    <!--                            <i class="el-icon-circle-close" style="color:red;font-size:26px;position:relative;top:5px;"></i>-->
                    <!--                            {{ m.message }}-->
                    <!--                          </div>-->
                    <!--                        </el-card>-->
                    <!--                      </div>-->
                    <!--                    </div>-->
                  </div>
                </el-main>
                <!--                <el-aside width="32px"-->
                <!--                          class="nodeInfoToolBar">-->
                <!--                  <div :class="{'tool':true, 'component':true, 'acitve': toolBarShow==='component'}"-->
                <!--                       @click="toolBarShow='component'">-->
                <!--                    <i class="el-icon-tickets"></i>-->
                <!--                    <span> 组件参数</span>-->
                <!--                  </div>-->
                <!--                  <div :class="{'tool':true, 'message':true, 'acitve': toolBarShow==='message'}"-->
                <!--                       @click="toolBarShow='message'">-->
                <!--                    <i class="el-icon-chat-dot-round"></i>-->
                <!--                    <span> 执行记录</span>-->
                <!--                  </div>-->
                <!--                </el-aside>-->
              </el-container>
            </el-aside>
          </el-container>
        </el-container>
      </el-main>
    </el-container>
  </el-drawer>
</template>
<script>
import Vue from 'vue';
import ComponentTree from '@/views/pipeline/menu.vue';
import FlowChart from './flowchart';
import PluginFlowExec from './pluginflowexec';
import {add, info, update, execute, deploy} from "@/api/app/pipeline";
import instance from './instance';
import JavaBuild from "@/views/pipeline/config/java-build";
import HostDeploy from '@/views/pipeline/config/host-deploy'
import Log from "@/views/pipeline/log/log";
import {initPipelineTemplate} from "@/views/pipeline/const";
import editor from "@/views/pipeline/editor";
import NodeView from "@/views/pipeline/node/nodeview.vue";

FlowChart.use(PluginFlowExec);

export default Vue.extend({
  name: 'FlowCanvas',
  components: {NodeView, JavaBuild, HostDeploy, ComponentTree, Log},
  props: {
    sidebarComponentName: String,
  },
  data() {
    return {
      id: 0,
      executeLoggerUuid: 0,//当前执行记录uuid
      isShowNode: true,
      isShowNodeConfig: false,
      open: false,
      infoVisible: false,
      nodeTreeVisible: true,
      nodeConfigVisible: true,
      javaBuildVisible: false,
      hostDeployVisible: false,
      hideOrShowText: '隐藏',
      hideOrShowConfigText: '隐藏',
      hideOrShowConfigIcon: 'el-icon-caret-right',
      hideOrShowIcon: 'el-icon-d-arrow-left',
      pipelineId: '',
      pipelineName: '',
      currentNodeId: '',
      triggerWay: '',
      branch: '',
      isUndoDisable: true,
      isExecDisable: false,
      applicationUuid: '',
      applicationName: '',
      activeName: 'first',
      toolBarShow: 'component',
      modelName: '你你您',
      description: '描述',
      triggerList: [
        {value: 'code', label: '提交代码'},
        {value: 'hand', label: '手动触发'},
        {value: 'webhook', label: 'WebHook'},
        {value: 'timing', label: '定时触发'}
      ],
      branchList: [
        {value: 'master', label: 'master'}
      ],
      nodeViewVisible: false,
      dialogTableVisible: false,
      nodeExecuteLogVisible: false
    };
  },
  methods: {
    init(appName, applicationUuid, id, infoVisible) {
      this.isExecDisable = false
      this.applicationName = appName
      this.infoVisible = infoVisible || 0
      this.open = true
      this.pipelineId = id || 0
      this.id = id == null ? 0 : id
      this.applicationUuid = applicationUuid
      info(this.id).then(response => {
        if (response.code === 2000) {
          this.pipelineName = response.data.pipelineName
          this.triggerWay = response.data.triggerWay
          this.branch = response.data.branch
          instance.reset()
          FlowChart.setContainer('mainContainer');
          FlowChart.on('commandListEmpty', () => {
            this.isUndoDisable = true;
          });
          FlowChart.on('showNodeLogger', (nodeId) => {
            this.nodeExecuteLog(nodeId)
          });
          FlowChart.on('showNodeData', (nodeId) => {
            this.showNodeView(nodeId)
          });
          FlowChart.on('addCommand', () => {
            this.isUndoDisable = false;
          });
          FlowChart.on('selectNode', (data) => {
            this.currentNodeId = data;
            this.showNodeConfig(data)
          });
          FlowChart.on('restartNode', (data) => {
            let currentNodeId = data;
            this.restartNode(currentNodeId)
          });
          FlowChart.loadData(JSON.parse(response.data.pipelineContext))
        } else {
          this.msgError(response.msg)
        }
      });
    },
    showNodeConfig(data) {
      this.isShowNode = false
      this.isShowNodeConfig = true
      let node = FlowChart.getNodeDataByNodeId(data)
      const name = node.name;
      console.log('click node =' + name)
      switch (name) {
        case 'JAVA_BUILD':
          this.hostDeployVisible = false
          this.javaBuildVisible = true
          this.$nextTick(() => {
            this.$refs.JavaBuild.init(this.currentNodeId)
          })
          this.isShowNode = false
          break;
        case 'HOST_DEPLOY':
          this.javaBuildVisible = false
          this.hostDeployVisible = true
          this.$nextTick(() => {
            this.$refs.HostDeploy.init(this.currentNodeId)
          })
          this.isShowNode = false
          break;
        default:
          this.isShowNode = true
          this.isShowNodeConfig = false
      }
    },
    dragoverHandle(ev) {
      ev.preventDefault();
    },
    dropHandle(ev) {
      FlowChart.addNode({pageX: ev.pageX, pageY: ev.pageY}, ev.dataTransfer.getData('target'));
    },
    clickBgHandle() {
      FlowChart.hideMenu()
      this.isShowNode = true
      this.isShowNodeConfig = false
    },
    zoomOut() {
      FlowChart.zoomOut();
    },
    zoomIn() {
      FlowChart.zoomIn();
    },
    undo() {
      FlowChart.undo();
    },
    resetFlow() {
      FlowChart.getModelData().nodes.forEach(node => editor.removeNode(node.id))
      const data = JSON.parse(JSON.stringify(initPipelineTemplate));
      FlowChart.loadData(data)
      console.log(initPipelineTemplate)
    },
    hideOrShowTree() {
      this.nodeTreeVisible = !this.nodeTreeVisible
      this.hideOrShowText = this.nodeTreeVisible ? '隐藏' : '显示'
      this.hideOrShowIcon = this.nodeTreeVisible ? 'el-icon-d-arrow-left' : 'el-icon-d-arrow-right'
    },
    hideOrShowConfig() {
      this.nodeConfigVisible = !this.nodeConfigVisible
      this.hideOrShowConfigText = this.nodeConfigVisible ? '隐藏' : '显示'
      this.hideOrShowConfigIcon = this.nodeConfigVisible ? 'el-icon-caret-right' : 'el-icon-caret-left'
    },
    deploy() {
      deploy({'id': this.pipelineId}).then(response => {
        if (response.code === 2000) {
          this.msgSuccess('操作成功')
        } else {
          this.msgError(response.msg)
        }
      })
    },
    execute() {
      this.isExecDisable = true
      const pipeline = FlowChart.getModelData()
      pipeline.nodes.forEach(node => FlowChart.changeNodeStatus(node.id, ''))
      execute({'id': this.pipelineId}).then(response => {
        if (response.code === 2000) {
          const data = response.data
          this.executeLoggerUuid = data.uuid
          this.initSocket(data.uuid)
        } else {
          this.msgError(response.msg)
          this.isExecDisable = false
        }
      })
    },
    initSocket(uuid) {
      this.socket = new WebSocket(process.env.VUE_APP_SOCKET_API + '/ws/pipeline?' + uuid);
      this.socketOnOpen();
      this.socketOnMessage()
      this.socketOnClose();
      this.socketOnError();
    },
    socketOnMessage() {
      this.socket.onmessage = e => {
        const data = JSON.parse(e.data)
        //console.log('接收到数据 = ' + data.nodes.name + ' status = ' + data.nodes.data.nodeState)
        //console.log(data)
        this.execModel(data)
      }
    },
    execModel(data) {
      this.isExecDisable = true;
      FlowChart.execModel(data)
      const status = data.nodes.data.nodeState
      if ((data.nodes.name === 'END' && status != 'loading') || status == 'failed') {
        this.isExecDisable = false
      }
    },
    socketOnOpen() {
      this.socket.onopen = () => {
        console.log('开启socket连接')
      }
    },
    socketOnClose() {
      this.socket.onclose = () => {
        console.log('关闭 socket')
        this.isExecDisable = false;
      }
    },
    socketOnError() {
      this.socket.onerror = () => {
        console.log('socket 连接失败')
        this.isExecDisable = false;
      }
    },
    beforeDestroy() {
      this.socket.close()
      this.term.dispose()
    },
    saveOrUpdate() {
      const graph = FlowChart.getModelData();
      const data = {
        'pipelineDTO': {
          'id': this.id == 0 ? null : this.id,
          'pipelineName': this.pipelineName,
          'triggerWay': this.triggerWay,
          'description': this.description,
          'applicationUuid': this.applicationUuid,
          'branch': this.branch,
          'graph': graph
        }
      }
      if (this.id == 0) {
        add(data).then(response => {
          if (response.code === 2000) {
            console.error(response.data.id)
            this.msgSuccess('添加成功')
            this.id = response.data
          } else {
            this.msgError(response.msg)
          }
        })
      } else {
        update(data).then(response => {
          if (response.code === 2000) {
            this.msgSuccess('修改成功')
          } else {
            this.msgError(response.msg)
          }
        })
      }
    },
    closeDialog() {
      this.$emit('refreshDataList')
      FlowChart.hideMenu()
    },
    //查看当前节点的执行日志
    nodeExecuteLog(nodeId) {
      this.nodeExecuteLogVisible = true
      if (this.executeLoggerUuid == null || this.executeLoggerUuid === 0) {
        this.msgError('当前流水线未执行')
        return
      }
      this.$nextTick(() => {
        this.$refs.Log.init(nodeId, this.executeLoggerUuid)
      })
    },
    showNodeView(nodeId) {
      this.nodeViewVisible = true
      if (this.executeLoggerUuid == null || this.executeLoggerUuid === 0) {
        this.msgError('当前流水线未执行')
        return
      }
      this.$nextTick(() => {
        this.$refs.View.init(nodeId, this.executeLoggerUuid)
      })
    },
    restartNode(){
      // TODO:
      this.msgError('重启节点功能正在努力开发中')
    }
  },
})
;
</script>

<style lang="scss" scoped>
.flowChartWrap {
  height: calc(100vh - 77px);
  overflow: hidden;

  .right {
    border-left: 1px solid #e5e5e5;
    border-top: 1px solid #e5e5e5;
    margin-bottom: 0px;
  }

  .el-main {
    padding: 0px !important;
  }

  .main {
    overflow: hidden;

    #mainMenu {
      height: 41px;
      border-bottom: 1px solid #e1e1e1;
      border-top: 1px solid #e1e1e1;

      .tool-left {
        float: left;

        .el-button {
          &:first-child {
            margin-left: 10px;
          }

          border: none;
          margin-top: 4px;
        }
      }

      .tool-right {
        margin-top: 5px;
        float: right;

        .el-button {
          position: relative;
          border: none;
          margin: 3px 0 0 0;

          &:last-child {
            margin-right: 10px;
          }

          background: transparent;
        }
      }
    }

    .mainContainer {
      height: calc(100% - 42px);
      position: relative;
      overflow: hidden;
      outline: none !important;

      #mainContainer {
        outline: none !important;
        height: 100%;
        width: 100%;
        position: relative;
      }
    }
  }

  #mainNodeInfo {
    .nodeInfoToolBar {
      margin-bottom: 0px;
      border-left: 1px solid #e5e5e5;
      overflow: hidden;

      .tool {
        padding: 8px 0;
        writing-mode: vertical-rl;
        line-height: 32px;
        font-size: 12px;
        border-bottom: 1px solid #ccc;
        cursor: pointer;

        &.acitve {
          background: #eee;
        }

        span {
          margin: 5px 0;
        }
      }
    }

    .title {
      border-bottom: 1px solid #e5e5e5;
      height: 40px;
      font-size: 12px;
      // color: #999;
      line-height: 40px;
      text-align: center;
    }

    .model-attr {
      padding: 10px;

      .item {
        font-size: 12px;
      }

      .value {
        font-size: 12px;
        color: #999;
        margin-left: 10px;
      }

      .el-input {
        margin-top: 5px;
      }

      textarea {
        margin-top: 5px;
        font-family: inherit;
      }
    }

    .node-attr {
      padding: 10px;

      .item {
        font-size: 12px;
      }

      .value {
        font-size: 12px;
        color: #999;
        margin-left: 10px;
      }
    }

    .messageInfo {
      padding: 0px;
      margin: 10px;
      color: #333;
      font-size: 14px;

      .el-card__body {
        padding: 10px;
      }

      p {
        padding: 0;
        margin: 0;
        font-size: 12px;
      }
    }
  }

  .el-container {
    height: 100%;
  }

  .tabsNav {
    padding: 0;

    .el-tabs--card > .el-tabs__header .el-tabs__nav {
      border-top: 3px solid #01c1de;
      border-radius: 0;
    }

    .el-tabs__item.is-active {
      color: #333 !important;
    }

    .el-tabs__item {
      font-size: 12px;
    }

    .el-tabs__item:focus.is-active.is-focus:not(:active) {
      box-shadow: none !important;
    }
  }

  .node-config {
    height: calc(100vh - 120px);
    overflow-y: auto
  }
}
</style>
