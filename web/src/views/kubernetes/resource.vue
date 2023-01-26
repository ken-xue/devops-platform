<template>
  <div>
    <el-drawer :with-header="false" title="Pod" size="80%" :visible.sync="open" :destroy-on-close="true" append-to-body
               @closed="cancel">
      <el-card class="box-card">
        <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left">
          <el-form-item label="资源类型" prop="resource">
            <el-select v-model="resource" placeholder="请选择资源类型" @change="getResourceList">
              <el-option
                v-for="item in resources"
                :key="item.name"
                :label="item.name"
                :value="item.value">
                <!--              <span style="float: left">{{ item.name }}</span>-->
                <!--              <span style="float: left;margin-left:20px;overflow: hidden; color: #6c7582; font-size: 8px">{{ item.creationTime }}</span>-->
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="命名空间" prop="roleName">
            <el-select v-model="namespace" clearable filterable placeholder="请选择命名空间" @change="refreshPage">
              <el-option
                v-for="item in namespaces"
                :key="item.namespaceName"
                :label="item.namespaceName"
                :value="item.namespaceName">
                <span style="float: left">{{ item.namespaceName }}</span>
                <span style="float: left;margin-left:20px;overflow: hidden; color: #6c7582; font-size: 8px">{{
                    item.creationTime
                  }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="名称" prop="podName">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="refreshPage">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            <el-button v-permission="['kubernetes:cluster:add']" type="primary" icon="el-icon-plus" size="mini"
                       @click="handleAdd">导入
            </el-button>
          </el-form-item>
          <el-table max-height="650" type="mini" v-loading="loading" :data="pageList"
                    @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="45" align="center"/>
            <el-table-column
              fixed
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
              fixed
              label="Pod名称"
              prop="name"
              :show-overflow-tooltip="true"
            />
            <el-table-column
              label="hostIP"
              prop="hostIP"
              :show-overflow-tooltip="true"
            />
            <el-table-column
              label="节点名称"
              prop="nodeName"
              :show-overflow-tooltip="true"
            />
            <el-table-column
              label="phase"
              prop="phase"
              :show-overflow-tooltip="true"
            >
              <template slot-scope="scope">
                <el-tag v-if="scope.row.phase=='Pending'">Pending</el-tag>
                <el-tag v-if="scope.row.phase=='Running'" type="success">Running</el-tag>
                <el-tag v-if="scope.row.phase=='Succeeded'" type="info">Succeeded</el-tag>
                <el-tag v-if="scope.row.phase=='Unknown'" type="warning">Unknown</el-tag>
                <el-tag v-if="scope.row.phase=='Failed'" type="danger">Failed</el-tag>
              </template>
            </el-table-column>
            <el-table-column
              label="podIPs"
              prop="podIPs"
              :show-overflow-tooltip="true"
            />
            <el-table-column
              label="创建时间"
              prop="createTime"
              :show-overflow-tooltip="true"
            />
            <el-table-column fixed="right" label="操作" min-width="200" align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button v-permission="['kubernetes:cluster:list']" size="mini" type="text" style="color: #0dcb0d"
                           icon="el-icon-s-order" @click="desc(scope.row)">Desc
                </el-button>
                <el-button v-permission="['kubernetes:cluster:list']" size="mini" type="text" style="color: #2d2f2d"
                           icon="el-icon-receiving" @click="attach(scope.row)">Attach
                </el-button>
                <el-button v-permission="['kubernetes:cluster:list']" size="mini" type="text" style="color: #e01515"
                           icon="el-icon-receiving" @click="exec(scope.row)">Exec
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
            @pagination="refreshPage"
          />
        </el-form>
      </el-card>
    </el-drawer>
    <attach ref="Attach" v-if="attachVisible"></attach>
    <describe ref="Describe" v-if="descVisible"></describe>
  </div>
</template>

<script>
import {namespaceList, podList, resources} from "@/api/kubernetes/cluster";
import Attach from "@/views/kubernetes/attach"
import Describe from "@/views/kubernetes/describe"

export default {
  name: 'resource',
  components: {Attach, Describe},
  data() {
    return {
      // 遮罩层
      loading: true,
      open: false,
      isEdit: false,
      searchLoading: false,
      submitLoading: false,
      loggerVisible: false,
      attachVisible: false,
      descVisible: false,
      namespace: '',
      clusterId: 0,
      total: 0,
      // 类型数据字典
      typeOptions: [],
      dataList: [],
      pageList: [],
      namespaces: [],
      resource: 'Pod',
      resources: [{
        name: 'Service', value: 'Service'
      }, {
        name: 'Pod', value: 'Pod'
      }, {
        name: 'Deployment', value: 'Deployment'
      }, {
        name: 'ReplicaSet', value: 'ReplicaSet'
      }],
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
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        name: ''
      },
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
    // this.getResourceList()
  },
  methods: {
    init(row) {
      this.dataList = []
      this.pageList = []
      this.queryParams.pageIndex = 1
      this.queryParams.pageSize = 10
      this.clusterId = row.id
      this.getNamespaceList()
      this.getResourceList()
    },
    getNamespaceList() {//获取命名空间
      namespaceList({'id': this.clusterId}).then(response => {
        if (response.code === 2000) {
          this.namespaces = response.data
          this.namespaces.push()
          this.open = true
        } else {
          this.msgError(response.msg)
        }
        this.searchLoading = false
      })
    },
    getResourceList() {//获取命名空间
      this.loading = true
      const data = {'id': this.clusterId, 'namespace': this.namespace,'resource':this.resource}
      resources(data).then(response => {
        if (response.code === 2000) {
          this.dataList = response.data
          this.refreshPage()
        } else {
          this.msgError(response.msg)
        }
        this.loading = false
      })
    },
    handleQuery() {
      this.queryParams.pageIndex = 1
      this.getPodList(this.clusterId)
    },
    resetQuery() {
      this.namespace = ''
      this.name = ''
      this.refreshPage()
    },
    handleSelectionChange() {
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.dataList = []
      this.pageList = []
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
    attach(row) {
      this.attachVisible = true
      row.clusterId = this.clusterId
      row.namespace = this.namespace
      this.$nextTick(() => {
        this.$refs.Attach.init(row)
      })
    }
    , desc(row) {
      this.descVisible = true
      row.clusterId = this.clusterId
      this.$nextTick(() => {
        this.$refs.Describe.init(row)
      })
    }
    , exec(row) {
      this.attachVisible = true
      row.clusterId = this.clusterId
      row.namespace = this.namespace
      this.$nextTick(() => {
        this.$refs.Attach.init(row)
      })
      this.msgError('未实现')
    },
    handleAdd() {

    },
    refreshPage(){
      //条件查询 1.命名空间 2.名称
      let name = this.queryParams.name
      let data = this.dataList.filter(item=>{
        let match = true
        if (name!='' && name!=null){
          console.log(item.name)
          console.log(item.name.indexOf(name))
          debugger
          match = (item.name.indexOf(name)>-1)
        }
        if (this.namespace!=''&&this.namespace!=null){
          match = item.namespace == this.namespace
        }
        return match
      })
      this.total = data.length
      let pageIndex = this.queryParams.pageIndex
      let pageSize = this.queryParams.pageSize
      this.pageList = data.slice((pageIndex - 1) * pageSize, pageIndex * pageSize)
    }
  }
}
</script>
