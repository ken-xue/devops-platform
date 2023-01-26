<template>
    <div>
        <el-card class="box-card">
            <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
                <el-form-item label="名称" prop="roleName">
                    <el-input
                            v-model="queryParams.MinioDTO.name"
                            placeholder="请输入名称"
                            clearable
                            size="small"
                            @keyup.enter.native="handleQuery"
                    />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                    <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                    <el-button v-permission="['middleware:minio:add']" type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">录入</el-button>
                    <el-button v-permission="['middleware:minio:delete']" type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
                    <el-button v-permission="['middleware:minio:add']" type="success" icon="el-icon-circle-plus-outline" size="mini" @click="handleCreate">创建</el-button>
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
                            label="uri"
                            align="center"
                            prop="uri"
                            :show-overflow-tooltip="true"
                    />
                    <el-table-column
                            label="访问用户名"
                            align="center"
                            prop="username"
                            :show-overflow-tooltip="true"
                    />
                    <el-table-column
                            label="访问密码或秘钥"
                            align="center"
                            prop="password"
                            :show-overflow-tooltip="true"
                    />
                    <el-table-column
                            label="别名"
                            align="center"
                            prop="name"
                            :show-overflow-tooltip="true"
                    />
                    <el-table-column
                            label="备注"
                            align="center"
                            prop="remark"
                            :show-overflow-tooltip="true"
                    />
                    <el-table-column
                            label="访问端口"
                            align="center"
                            prop="port"
                            :show-overflow-tooltip="true"
                    />
                <el-table-column label="操作" align="center" min-width="100" class="">
                    <template slot-scope="scope">
                      <a  target="_blank" :href="scope.row.uri" >
                        <el-button v-permission="['sys:role:update']" size="mini" type="text" icon="el-icon-s-promotion">访问</el-button></a>
                        <el-button v-permission="['middleware:minio:update']" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
                        <el-button v-permission="['middleware:minio:delete']" size="mini" type="text" style="color: red" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
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
                                <el-form-item label="uri" prop="uri">
                                    <el-input v-model="form.uri" placeholder="请输入uri"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="访问用户名" prop="username">
                                    <el-input v-model="form.username" placeholder="请输入访问用户名"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="访问密码或秘钥" prop="password">
                                    <el-input v-model="form.password" placeholder="请输入访问密码或秘钥"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="别名" prop="name">
                                    <el-input :disabled="form.name=='SYSTEM_DEFAULT'" v-model="form.name" placeholder="请输入别名"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="备注" prop="remark">
                                    <el-input v-model="form.remark" placeholder="请输入备注"/>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="访问端口" prop="port">
                                    <el-input v-model="form.port" placeholder="请输入访问端口"/>
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
<!--      <minio-create ref="MinioCreate" v-if="createMinioVisible" @refreshDataList="getList"></minio-create>-->
      <MinioCreate ref="MinioCreate" v-if="createMinioVisible" @refreshDataList="getList"></MinioCreate>
    </div>
</template>

<script>
import { add, del, info, page, update } from '@/api/middleware/minio'
import { nestedGetQuery } from "@/utils";
import MinioCreate from '@/views/base/minio/minio-create'

export default {
    name: 'minio',
    components: {MinioCreate},
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
            createMinioVisible: false,
            // 类型数据字典
            typeOptions: [],
            dataList: [],
            // 查询参数
            queryParams: {
                pageIndex: 1,
                pageSize: 10,
                MinioDTO: {
                    name: ''
                }
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
            uri: [{ required: true, message: 'uri不能为空', trigger: 'blur' }],
            username: [{ required: true, message: '访问用户名不能为空', trigger: 'blur' }],
            password: [{ required: true, message: '访问密码或秘钥不能为空', trigger: 'blur' }],
            name: [{ required: true, message: '别名不能为空', trigger: 'blur' }],
            remark: [{ required: true, message: '备注不能为空', trigger: 'blur' }],
            port: [{ required: true, message: '访问端口不能为空', trigger: 'blur' }],
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
                uri: undefined,
                username: undefined,
                password: undefined,
                name: undefined,
                remark: undefined,
                port: undefined,
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
      handleCreate(row) {
        this.createMinioVisible = true
        this.$nextTick(() => {
          this.$refs.MinioCreate.init(row)
        })
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
                this.form.uri = response.data.uri,
                this.form.username = response.data.username,
                this.form.password = response.data.password,
                this.form.name = response.data.name,
                this.form.remark = response.data.remark,
                this.form.port = response.data.port,
                this.open = true
                this.title = '修改'
                this.isEdit = true
            })
        },
        /** 提交按钮 */
        submitForm: function() {
       this.$refs['form'].validate(valid=>{
        if (valid) {
          if (this.form.id !== undefined) {
            update({"minioDTO":this.form}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            add({"minioDTO":this.form}).then(response => {
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
