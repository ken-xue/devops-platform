<template>
    <div>
        <el-card class="box-card">
            <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
                <el-form-item label="名称" prop="roleName">
                    <el-input
                            v-model="queryParams.jenkinsDTO.name"
                            placeholder="请输入名称"
                            clearable
                            size="small"
                            @keyup.enter.native="handleQuery"
                    />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                    <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                    <el-button v-permission="['kubernetes:cluster:add']" type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
                    <el-button v-permission="['kubernetes:cluster:delete']" type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
                    </el-button>
                </el-form-item>
            </el-form>

            <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center" />
                <el-table-column
                        label="序号"
                        align="center"
                        prop="id"
                        width="45"
                        :show-overflow-tooltip="true">
                    <template slot-scope="props">
                        <p v-text="props.$index+1" />
                    </template>
                </el-table-column>
                    <el-table-column
                            label="集群名称"
                            align="center"
                            prop="name"
                            :show-overflow-tooltip="true"
                    />
                    <el-table-column
                            label="版本"
                            align="center"
                            prop="version"
                            :show-overflow-tooltip="true"
                    />
                    <el-table-column
                            label="秘钥"
                            align="center"
                            prop="secretKey"
                            :show-overflow-tooltip="true"
                    />
                    <el-table-column
                            label="集群配置文件"
                            align="center"
                            prop="config"
                            :show-overflow-tooltip="true"
                    />
                    <el-table-column
                            label="流水线快照"
                            align="center"
                            prop="graphContent"
                            :show-overflow-tooltip="true"
                    />
                <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                        <el-button v-permission="['kubernetes:cluster:update']" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
                        <el-button v-permission="['kubernetes:cluster:delete']" size="mini" type="text" style="color: red" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
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
            <!-- 添加或修改对话框 -->
            <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
                <el-form ref="form" :model="form" :rules="rules" label-width="120px">
                    <el-row>
                            <el-col :span="24">
                                <el-form-item label="集群名称" prop="name">
                                    <el-input v-model="name" placeholder="请输入集群名称"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="版本" prop="version">
                                    <el-input v-model="version" placeholder="请输入版本"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="秘钥" prop="secretKey">
                                    <el-input v-model="secretKey" placeholder="请输入秘钥"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="集群配置文件" prop="config">
                                    <el-input v-model="config" placeholder="请输入集群配置文件"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="流水线快照" prop="graphContent">
                                    <el-input v-model="graphContent" placeholder="请输入流水线快照"/>
                                </el-form-item>
                            </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submitForm">确 定</el-button>
                    <el-button @click="cancel">取 消</el-button>
                </div>
            </el-dialog>
        </el-card>
    </div>
</template>

<script>
import { add, del, info, page, update } from '@/api/kubernetes/cluster'
import { nestedGetQuery } from "@/utils";

export default {
    name: 'Role',
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
            dataList: [],
            // 查询参数
            queryParams: {
                pageIndex: 1,
                pageSize: 10,
                ClusterDTO: {
                    name: ''
                }
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
            name: [{ required: true, message: '集群名称不能为空', trigger: 'blur' }],
            version: [{ required: true, message: '版本不能为空', trigger: 'blur' }],
            secretKey: [{ required: true, message: '秘钥不能为空', trigger: 'blur' }],
            config: [{ required: true, message: '集群配置文件不能为空', trigger: 'blur' }],
            graphContent: [{ required: true, message: '流水线快照不能为空', trigger: 'blur' }],
            }
        }
    },
    created() {
        this.getList()
    },
    methods: {
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
                version: undefined,
                secretKey: undefined,
                config: undefined,
                graphContent: undefined,
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
                this.form.id = response.data.id
                this.form.name = response.data.name,
                this.form.version = response.data.version,
                this.form.secretKey = response.data.secretKey,
                this.form.config = response.data.config,
                this.form.graphContent = response.data.graphContent,
                this.open = true
                this.title = '修改'
                this.isEdit = true
            })
        },
        /** 提交按钮 */
        submitForm: function() {
       this.refs['form'].validate(valid=>{
        if (valid) {
          if (this.form.id !== undefined) {
            update({"clusterDTO":this.form}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            add({"clusterDTO":this.form}).then(response => {
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
            }).then(function() {
                return del({ 'ids': Ids })
            }).then(() => {
                this.getList()
                this.msgSuccess('删除成功')
            }).catch(function() {
            })
        }
    }
}
</script>
