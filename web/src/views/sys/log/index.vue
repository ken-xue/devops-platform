<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
        <el-form-item label="名称" prop="jobName">
          <el-input v-model="queryParams.jobName" placeholder="请输入名称" clearable size="small" @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button v-permission="['sys:user:delete']" type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
          </el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="menuList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="序号" align="center" prop="jobId" width="45" :show-overflow-tooltip="true">
          <template slot-scope="props">
            <p v-text="props.$index+1" />
          </template>
        </el-table-column>
        <el-table-column label="用户名" align="center" prop="username" :show-overflow-tooltip="true" />
        <el-table-column label="动作" align="center" prop="operation" width="100"  :show-overflow-tooltip="true"/>
        <el-table-column label="请求方法" align="center" prop="method" max-width="300"  :show-overflow-tooltip="true"/>
        <el-table-column label="参数" align="center" prop="params" :show-overflow-tooltip="true" />
        <el-table-column label="发生时间" align="center" prop="occurTime" :show-overflow-tooltip="true" />
        <el-table-column label="执行耗时(毫秒)" align="center" prop="executeTime" :show-overflow-tooltip="true"/>
        <el-table-column label="IP地址" align="center" prop="ip" :show-overflow-tooltip="true"/>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button v-permission="['sys:log:delete']" size="mini" type="text" style="color: red" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--页码-->
      <pagination v-show="total>0" style="padding: 0px" :total="total" :page.sync="queryParams.pageIndex" :limit.sync="queryParams.pageSize" @pagination="getList" />
      <!-- 添加或修改对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
        <el-form ref="form" :model="form" label-width="120px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="名称" prop="jobName">
                <el-input v-model="form.jobName" placeholder="名称" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="任务分组" prop="jobGroup">
                <el-select v-model="form.jobGroup" placeholder="请选择">
                  <el-option v-for="dict in jobGroupOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="调用目标" prop="invokeTarget">
                <span slot="label">
                  调用目标
                  <el-tooltip placement="top">
                    <div slot="content">
                      调用示例：func (t *EXEC) ExamplesNoParam(){..} 填写 ExamplesNoParam 即可；
                      <br>参数说明：目前不支持带参调用
                    </div>
                    <i class="el-icon-question" />
                  </el-tooltip>
                </span>
                <el-input
                  v-model="form.invokeTarget"
                  placeholder="调用目标"
                />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="目标参数" prop="args">
                <span slot="label">
                  目标参数
                  <el-tooltip placement="top">
                    <div slot="content">
                      参数示例：有参：请以string格式填写；无参：为空；
                      <br>参数说明：目前仅支持函数调用
                    </div>
                    <i class="el-icon-question" />
                  </el-tooltip>
                </span>
                <el-input
                  v-model="form.args"
                  placeholder="目标参数"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="cron表达式" prop="cronExpression">
                <el-input
                  v-model="form.cronExpression"
                  placeholder="cron表达式"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="是否并发" prop="concurrent">
                <el-radio-group v-model="form.concurrent" size="small">
                  <el-radio-button label="0">允许</el-radio-button>
                  <el-radio-button label="1">禁止</el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="调用类型" prop="jobType">
                <el-radio-group v-model="form.jobType" size="small">
                  <el-radio-button label="1">接口</el-radio-button>
                  <el-radio-button label="2">函数</el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col>

            <el-col :span="24">
              <el-form-item label="执行策略" prop="misfirePolicy">
                <el-radio-group v-model="form.misfirePolicy" size="small">
                  <el-radio-button label="1">立即执行</el-radio-button>
                  <el-radio-button label="2">执行一次</el-radio-button>
                  <el-radio-button label="3">放弃执行</el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="状态" prop="status">
                <el-select
                  v-model="form.status"
                  placeholder="请选择"
                >
                  <el-option
                    v-for="dict in statusOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="this.visible=false">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
import { delLog, getLog, pageLog } from '@/api/sys/log'

export default {
  name: 'Log',
  components: {},
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
      open: false,
      isEdit: false,
      // 类型数据字典
      typeOptions: [],
      menuList: [],
      jobGroupOptions: [
        { dictValue: '0', dictLabel: '目录' },
        { dictValue: '1', dictLabel: '菜单' },
        { dictValue: '2', dictLabel: '按钮' }
      ],
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        jobName: undefined,
        jobGroup: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      this.loading = true
      pageLog(this.queryParams).then(response => {
        console.log('数据：', JSON.stringify(response.data))
        this.menuList = response.data
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
        jobId: undefined,
        jobName: undefined,
        jobGroup: undefined,
        cronExpression: undefined,
        invokeTarget: undefined,
        args: undefined,
        misfirePolicy: 1,
        concurrent: 1,
        jobType: 1,
        status: undefined
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
      this.dateRange = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加定时任务'
      this.isEdit = false
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.userIds = selection.map(item => item.userId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const Ids = (row.id && [row.id]) || this.ids
      this.$confirm('是否确认删除编号为"' + Ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delUser({ 'ids': Ids })
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    }
  }
}
</script>
