<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
        <el-form-item label="名称" prop="roleName">
          <el-input
            v-model="queryParams.ZookeeperDTO.name"
            placeholder="请输入名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button v-permission="['middleware:zookeeper:add']" type="primary" icon="el-icon-plus" size="mini"
                     @click="handleAdd">新增
          </el-button>
          <el-button v-permission="['middleware:zookeeper:delete']" type="danger" icon="el-icon-delete" size="mini"
                     :disabled="multiple" @click="handleDelete">删除
          </el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="loading" :element-loading-text="loadingContent"
                element-loading-spinner="el-icon-loading" :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column
          label="序号"
          align="center"
          prop="id"
          width="45"
          :show-overflow-tooltip="true">
          <template slot-scope="props">
            <p v-text="props.$index+1"/>
          </template>
        </el-table-column>
        <el-table-column
          label="名称"
          align="center"
          prop="name"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="连接地址串"
          align="center"
          prop="address"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="会话超时时间"
          align="center"
          prop="sessionTimeout"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="规格"
          align="center"
          prop="specs"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="备注"
          align="center"
          prop="remark"
          :show-overflow-tooltip="true"
        />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button v-permission="['middleware:zookeeper:update']" size="mini" type="text" icon="el-icon-s-promotion"
                       @click="dataView(scope.row)">数据展示
            </el-button>
            <el-button v-permission="['middleware:zookeeper:update']" size="mini" type="text" icon="el-icon-refresh"
                       @click="connect(scope.row)">连接
            </el-button>
            <el-button v-permission="['middleware:zookeeper:update']" size="mini" type="text" icon="el-icon-edit"
                       @click="handleUpdate(scope.row)">修改
            </el-button>
            <el-button v-permission="['middleware:zookeeper:delete']" size="mini" type="text" style="color: red"
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
    </el-card>
    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="连接地址串" prop="address">
              <el-input v-model="form.address" placeholder="请输入连接地址串(如192.168.127.3:2181),集群用逗号隔开"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="会话超时时间" prop="sessionTimeout">
              <el-input v-model="form.sessionTimeout" placeholder="请输入会话超时时间"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="规格" prop="specs">
              <el-input v-model="form.specs" placeholder="请输入规格"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="testConnect">连接测试</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-drawer title="数据详情" :visible.sync="dataViewVisible" size="70%">
      <el-row :gutter="20">
        <el-col :span="16">
          <el-divider content-position="center">可视化树</el-divider>
          <el-card shadow="hover" class="custom-tree-container" style="height: 550px;overflow: auto;">
            <el-tree
              :data="tree"
              node-key="id"
              default-expand-all
              :expand-on-click-node="false"
              @node-click="lazyLeaf">
          <span class="custom-tree-node" slot-scope="{ node, data }">
             <span v-if="data.children.length > 0" style="color: indianred">
                <i class="el-icon-folder"></i>
               {{ node.label }}
            </span>
            <span v-if="data.children.length < 1" style="color: #67C23A">
                <i class="el-icon-tickets"></i>
               {{ node.label }}
            </span>
        <span>
          <el-button
            type="text"
            size="mini"
            @click="() => append(data)">
            <i class="el-icon-plus"></i>添加
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => updateNode(data)">
            <i class="el-icon-edit"></i>修改
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => deleteNode(data)">
            <i class="el-icon-minus"></i>删除
          </el-button>
        </span>
      </span>
            </el-tree>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-divider content-position="center">节点数据</el-divider>
          <el-card>
            {{ nodeDataInfo }}
          </el-card>
        </el-col>
      </el-row>
    </el-drawer>

    <!--添加子节点弹框-->
    <el-dialog
      :title="nodeTitle"
      :visible.sync="addChildNodeDialog"
      width="35%"
      center>
      <el-form ref="zkNodeForm" :model="zkNodeForm" :rules="zkNodeFormRules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="上级节点">
          {{ addZkNodeData.parentId }}
        </el-form-item>
        <el-form-item label="节点名称" prop="name">
          <el-input :disabled="nodeNameDisabled" v-model="zkNodeForm.name"></el-input>
        </el-form-item>
        <el-form-item label="节点数据" prop="data">
          <el-input type="textarea" :rows="4" v-model="zkNodeForm.data"></el-input>
        </el-form-item>
        <el-form-item label="节点类型" prop="type">
          <el-select v-model="zkNodeForm.type" placeholder="请选择" :disabled="nodeTypeDisabled">
            <el-option
              v-for="item in zkTypes"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="addChildNodeDialog = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="addZkNode">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  add,
  dataView,
  del,
  info,
  page,
  testConnect,
  update,
  lazyLeaf,
  addZkNode,
  loadNodeDataInfo, deleteZkNode
} from '@/api/base/zookeeper'
import {nestedGetQuery} from "@/utils";

export default {
  name: 'Role',
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      dataViewVisible: false,
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
      nodeTitle:'',
      // 是否显示弹出层
      open: false,
      isEdit: false,
      // 类型数据字典
      typeOptions: [],
      dataList: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        ZookeeperDTO: {
          name: ''
        }
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [{required: true, message: '名称不能为空', trigger: 'blur'}],
        address: [{required: true, message: '连接地址串不能为空', trigger: 'blur'}],
        specs: [{required: true, message: '规格不能为空', trigger: 'blur'}],
        remark: [{required: true, message: '备注不能为空', trigger: 'blur'}],
        sessionTimeout: [{required: true, message: '会话超时时间不能为空', trigger: 'blur'}],
      },
      zkNodeFormRules: {
        name: [{required: true, message: '节点名称名称不能为空', trigger: 'blur'}],
        //type: [{required: true, message: '连接地址串不能为空', trigger: 'blur'}],
      },
      zkTypes: [
        {
          value: 1,
          label: '持久化节点(默认)'
        }, {
          value: 2,
          label: '持久化顺序节点'
        }, {
          value: 3,
          label: '临时节点'
        }, {
          value: 2,
          label: '临时顺序节点'
        },
      ],
      tree: [],
      addZkNodeData: {},
      addChildNodeDialog: false,
      zkNodeForm: {
        name: null,
        data: null,
        type: 1,
        uuid: '',
      },
      nodeDataInfo: '',
      loadingContent: '',
      filterText: '',
      nodeOps: null,
      nodeNameDisabled: false,
      nodeTypeDisabled: false
    }
  },
  created() {
    this.getList()
    this.notice();
  },
  methods: {
    notice() {
      this.$notify({
        title: '版本提示',
        message: 'zookeeper3.4.x已被彻底弃用，因此请确保你的zookeeper版本大于3.4.x',
        showClose: false,
        type: 'warning'
      });
    },

    connect(row) {
      this.loading = true
      this.loadingContent = "拼命连接中"
      testConnect({"zookeeperDTO": row}).then(response => {
        if (response.code === 2000) {
          this.msgSuccess("zookeeper连接成功")
          this.loading = false
        }
      }).catch(err => {
        this.loading = false
      })
    },

    updateZkNode(){

    },

    //修改节点
    updateNode(data) {
      console.log("data  "+JSON.stringify(data))
      this.nodeTitle = '修改节点'
      this.resetAddZkForm();
      this.addZkNodeData = data
      this.zkNodeForm.name = data.id
      this.zkNodeForm.data = this.nodeDataInfo
      this.nodeTypeDisabled = true
      this.nodeNameDisabled = true
      this.addChildNodeDialog = true
    },

    addZkNode() {
      this.$refs['zkNodeForm'].validate(valid => {
        if (valid) {
          let data = this.zkNodeForm;
          data.uuid = this.form.uuid;
          let addData = this.addZkNodeData;
          if (addData.parentId === null) { //添加一级目录
            data.name = "/" + data.name;
          } else if (addData.parentId === "/") { //添加二级以上目录
            data.name = addData.id + "/" + data.name;
          } else {
            data.name = addData.parentId + "/" + data.name
          }
          addZkNode({"zookeeperAddNodeDTO": data}).then(res => {
            if (res.code === 2000) {
              this.$message.success("添加成功")
              this.addChildNodeDialog = false
              this.zkNodeForm = {}
              this.dataView(this.form)
            }
          }).catch(err => {

          })
        }
      })
    },

    resetAddZkForm(){
      this.zkNodeForm = {
        name: null,
        data: null,
        type: 1,
      }
    },

    //添加节点
    append(data) {
      this.nodeTitle = '添加节点';
      this.resetAddZkForm();
      this.addZkNodeData = data
      this.nodeTypeDisabled = false
      this.nodeNameDisabled = false
      this.addChildNodeDialog = true
    },

    deleteNode(node) {
      this.nodeOps = 3
      let name = node.parentId === "/" ? node.id : node.parentId
      deleteZkNode({"name": name, uuid: this.form.uuid}).then(response => {
        if (response.code === 2000) {
          this.msgSuccess("删除节点成功");
          this.dataView(this.form)
          this.nodeOps = null
        }
      }).catch(err => {
        this.nodeOps = null
      })

    },

    //懒加载子节点
    lazyLeaf(nodeInfo) {
      if (this.nodeOps === 3) return 1;
      if (nodeInfo.parentId === "/") { //二级目录
        this.clickPath = nodeInfo.id
      } else if (nodeInfo.parentId === null) {
        this.clickPath = "/"
      } else { //三级后的目录
        this.clickPath = nodeInfo.parentId
      }
      let node = {id: this.clickPath, uuid: this.form.uuid}
      //懒加载数据
      lazyLeaf({"zookeeperLazyLeafDTO": node}).then(res => {
        let nodeArr = res.data;
        if (nodeArr.length > 0) {
          this.nodeArray = nodeArr;
          this.iteratorTree(this.tree);
        }
      }).catch(err => {

      })
      //节点数据
      let nodeData = {name: this.clickPath, uuid: this.form.uuid}
      loadNodeDataInfo(nodeData).then(response => {
        if (response.code === 2000) {
          this.nodeDataInfo = response.data.data
        }
      }).catch(err => {

      })
      this.nodeOps = {}
    },

    //递归节点并判断
    iteratorTree(tree) {
      for (let i = 0; i < tree.length; i++) {
        if ((tree[i].parentId === "/" ? tree[i].id : tree[i].parentId) === this.clickPath) {
          tree[i].children = this.nodeArray;
          return 1;
        } else {
          if (tree[i].children.length > 0) {
            this.iteratorTree(tree[i].children)
          }
        }
      }
    },

    /**数据展示*/
    dataView(row) {
      this.form = row
      dataView({"zookeeperDTO": row}).then(response => {
        if (response.code === 2000) {
          this.tree = [response.data];
          this.dataViewVisible = true
        }
      })
    },


    /**连接测试*/
    testConnect() {
      testConnect({"zookeeperDTO": this.form}).then(response => {
        if (response.code === 2000) {
          this.msgSuccess("zookeeper连接成功")
        }
      }).catch(err => {

      })
    },

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
        name: undefined,
        address: undefined,
        specs: undefined,
        remark: undefined,
        sessionTimeout: undefined
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
      this.title = '添加'
      this.isEdit = false
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
        this.form.id = response.data.id,
          this.form.uuid = response.data.uuid,
          this.form.name = response.data.name,
          this.form.address = response.data.address,
          this.form.specs = response.data.specs,
          this.form.remark = response.data.remark,
          this.form.sessionTimeout = response.data.sessionTimeout
        this.open = true
        this.title = '修改'
        this.isEdit = true
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id !== undefined) {
            update({"zookeeperDTO": this.form}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            add({"zookeeperDTO": this.form}).then(response => {
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
      this.$confirm('是否确认删除"' + row.id + '"的数据项?', '警告', {
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
