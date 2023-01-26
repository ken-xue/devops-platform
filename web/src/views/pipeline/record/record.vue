<template>
  <el-drawer :title="'执行记录'" top="3vh" :visible.sync="open" size="80%" append-to-body destroy-on-close @close=closeDialog :close-on-click-modal="false">
      <div  style="height: 50px!important;">
          <el-table  :max-height="650" v-loading="loading" :data="pipelineList">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="序号" align="center" prop="jobId" width="55" :show-overflow-tooltip="true">
              <template slot-scope="props">
                <p v-text="props.$index+1" />
              </template>
            </el-table-column>
            <el-table-column label="触发时间" align="center" prop="executeStartTime" max-width="300"  :show-overflow-tooltip="true"/>
            <el-table-column label="触发方式" align="center" prop="modifier" :show-overflow-tooltip="true" >
              <template slot-scope="scope">
                <el-tag size="small" type="success">{{scope.row.targetWay}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="触发人" align="center" prop="modifier" :show-overflow-tooltip="true" />
            <el-table-column label="结束时间" align="center" prop="gmtModified" :show-overflow-tooltip="true" />
            <el-table-column label="执行结果" align="center" prop="finalStatus" :show-overflow-tooltip="true" >
              <template slot-scope="scope">
                <el-tag v-if="scope.row.finalStatus==='成功'" size="small" type="success">{{ scope.row.finalStatus }}</el-tag>
                <el-tag v-else-if="scope.row.finalStatus==='失败'" size="small" type="danger">{{ scope.row.finalStatus }}</el-tag>
                <el-tag v-else size="small" type="info">-</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >
              <template slot-scope="scope">
                <el-button v-permission="['sys:user:update']" size="mini" type="text" icon="el-icon-data-analysis" @click="handleInfo(scope.row)">详情</el-button>
                <el-button v-permission="['pipeline:logger:delete']" size="mini" type="text" style="color: red" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
               </template>
            </el-table-column>
          </el-table>
          <!--页码-->
          <pagination v-show="total>0" style="padding: 0px" :total="total" :page.sync="queryParams.pageIndex" :limit.sync="queryParams.pageSize" @pagination="getList" />
          <!-- 添加或修改对话框 -->
          <FlowInfo  v-if="addVisible" ref="FlowInfo" @refreshDataList="getList"></FlowInfo>
      </div>
  </el-drawer>
</template>
<script>
import Vue from 'vue';
import FlowChart from '../flowchart';
import PluginFlowExec from '../pluginflowexec';
import {page,del} from "@/views/pipeline/record/record";
import JavaBuild from "@/views/pipeline/config/java-build";
import {nestedGetQuery} from "@/utils";
import FlowInfo from "@/views/pipeline/flow/flowinfo.vue";

FlowChart.use(PluginFlowExec);

export default Vue.extend({
  name: 'Record',
  components: {JavaBuild,FlowInfo},
  props: {
    sidebarComponentName: String,
  },
  data() {
    return {
      isShowNode: true,
      isShowNodeConfig: false,
      open: false,
      infoVisible: false,
      nodeTreeVisible: true,
      javaBuildVisible: false,
      pipelineId: '',
      pipelineName: '',
      currentNodeId: '',
      isUndoDisable: true,
      isExecDisable: false,
      applicationUuid: '',
      applicationName: '',
      activeName: 'first',
      toolBarShow: 'component',
      dialogTableVisible: false,
      loading: true,
      id: 0,
      /////////////////////
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 弹出层标题
      isEdit: false,
      addVisible:false,
      recordVisible:false,
      pipelineList: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        pipelineUuid: undefined
      },
      // 表单参数
      form: {},
      appList: []
    };
  },
  methods: {
    init(uuid) {
      this.open = true
      this.queryParams.pipelineUuid = uuid
      this.getList()
    },
    getList() {
      this.loading = true
      page(nestedGetQuery(this.queryParams)).then(response => {
        this.pipelineList = response.data
        this.total = response.totalCount
        this.loading = false
      })
    },
    handleDelete(row) {
      const Ids = (row.id && [row.id]) || this.ids
      this.$confirm('是否确认删除编号为"' + Ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return del({ 'ids': Ids })
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    },
    handleInfo(row){
      this.addVisible = true
      this.$nextTick(() => {
        this.$refs.FlowInfo.init(row.id)
      })
    },
    closeDialog() {
      this.$emit('refreshDataList')
    }
  },
});
</script>

<style lang="scss" scoped>
.flowChartWrap {
  height: 100%;

  .left {
    border-right: 1px solid #e5e5e5;
    height: 100%;

    .el-tree {
      height: calc(100% - 40px);
      overflow-y: auto;
    }
  }

  .right {
    border-left: 1px solid #e5e5e5;
    border-top: 1px solid #e5e5e5;
    margin-bottom: 0px;
  }

  .el-main {
    padding: 0px !important;
  }

  .main {
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
      height: 41px;
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

  .el-aside {
    background: #f8f8f8;
    padding: 0px 0px;

    .search {
      height: 42px;
      background-color: #f3f3f3;
      //padding: 8px 12px;
      box-sizing: border-box;
      border-bottom: 1px solid #e5e5e5;

      .el-input {
        height: 24px;

        .el-input__inner {
          height: 42px;
        }
      }

      .el-input__prefix {
        top: 5px;
      }

      .el-input--small .el-input__icon {
        line-height: 24px;
      }
    }

    .el-tree {
      background: transparent;
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

  .el-tree-node__content,
  .el-tree-node {
    min-height: 38px !important;
  }

  .leafNode {
    .node::before {
      content: "";
      position: absolute;
      top: 2px;
      left: 3px;
      border-radius: 2px;
      padding: 13px 2px;
      background: transparent;
    }

    &:hover span.node {
      border: 1px solid #1c9bec !important;
      background: #fff;

      &::before {
        background: #1c9bec;
      }
    }
  }
}
</style>
