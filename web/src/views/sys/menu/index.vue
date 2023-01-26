<template>
  <div>
    <el-card class="box-card">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-position="left" label-width="68px">
        <el-form-item label="名称" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button v-permission="['/sys/menu/add:post']" type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
          <el-button v-permission="['/sys/menu/delete:delete']" type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
          </el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="dataLoading" :data="menuList" :load="getChild" row-key="uuid" lazy :tree-props="{children: 'children', hasChildren: 'hasChildren'}" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="展开" align="center" width="80" />
        <el-table-column label="序号" align="center" width="80" prop="name">
          <template slot-scope="props">
            <p v-if="props.row.type === 0 ||props.row.type === 1" v-text="props.$index+1" />
          </template>
        </el-table-column>
        <el-table-column
          label="菜单名称"
          align="center"
          prop="name"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="菜单类型"
          align="center"
          prop="type"
          width="100"
        >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.type == 0" size="small">目录</el-tag>
            <el-tag v-else-if="scope.row.type === 1" size="small" type="success">菜单</el-tag>
            <el-tag v-else-if="scope.row.type === 2" size="small" type="info">按钮</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="菜单权限"
          align="center"
          prop="api"
          max-width="300">
          <template slot-scope="scope">
            <p v-if="scope.row.type == 0">-</p>
            <p v-else >{{scope.row.api}}:{{scope.row.method}}</p>
          </template>
        </el-table-column>
        <el-table-column
          label="路径"
          align="center"
          prop="url"
          :show-overflow-tooltip="true"
        >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.type == 1" size="small">{{scope.row.url}}</el-tag>
            <el-tag v-else size="small" type="success">-</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="图标"
          align="center"
          prop="menuIcon"
          :show-overflow-tooltip="true"
        >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.type == 2 || scope.row.icon == ''" size="small">--</el-tag>
<!--            <i v-else :class="'el-icon-' + scope.row.menuIcon" />-->
            <svg-icon :icon-class="scope.row.icon" />
          </template>
        </el-table-column>
        <el-table-column
          label="排序"
          align="center"
          prop="order"
          width="70"
        />
        <el-table-column
          label="备注"
          align="center"
          prop="remark"
          :show-overflow-tooltip="true"
        />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" min-width="180">
          <template slot-scope="scope">
            <el-button v-permission="['/sys/menu/update:put']" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
            <el-button v-permission="['/sys/menu/delete:delete']" size="mini" type="text"  style="color: red"  icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
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
              <el-form-item label="类型" prop="type">
                <el-radio-group v-model="form.type">
                  <el-radio v-for="type in menuTypeList" :label="type.dictValue" :key="type.dictLabel">{{ type.dictLabel }}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col v-if="this.form.type==0" :span="12">
              <el-form-item label="目录名称" prop="name">
                <el-input v-model="form.name" placeholder="目录名称"/>
              </el-form-item>
            </el-col>
            <el-col v-if="this.form.type==1" :span="12">
              <el-form-item label="菜单名称" prop="name">
                <el-input v-model="form.name" placeholder="菜单名称"/>
              </el-form-item>
            </el-col>
            <el-col v-if="this.form.type==2" :span="12">
              <el-form-item label="按钮名称" prop="name">
                <el-input v-model="form.name" placeholder="按钮名称"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="上级菜单" prop="parentName">
                <el-popover
                  ref="menuListPopover"
                  placement="bottom-start"
                  trigger="click">
                  <el-tree
                    :data="selectMenuList"
                    :props="menuListTreeProps"
                    node-key="uuid"
                    ref="selectMenuListTree"
                    @current-change="menuListTreeCurrentChangeHandle"
                    :default-expand-all="true"
                    :highlight-current="true"
                    :expand-on-click-node="false">
                  </el-tree>
                </el-popover>
                <el-input v-model="form.parentName" v-popover:menuListPopover :readonly="true" placeholder="点击选择上级菜单"/>
              </el-form-item>
            </el-col>
            <el-col v-if="this.form.type==1"  :span="24">
              <el-form-item label="菜单路由" prop="menuUrl">
                <span slot="label">
                  菜单路由
                  <el-tooltip placement="top">
                    <div slot="content">
                      路由示例：sys/menu
                    </div>
                    <i class="el-icon-question" />
                  </el-tooltip>
                </span>
                <el-input v-model="form.url" placeholder="菜单路由"/>
              </el-form-item>
            </el-col>
            <el-col v-if="this.form.type==1||this.form.type==2" :span="24">
              <el-form-item label="授权标识" prop="menuPerms">
                <span slot="label">
                  授权标识
                  <el-tooltip placement="top">
                    <div slot="content">
                      授权标识示例：sys:menu:list 多个使用逗号分隔
                    </div>
                    <i class="el-icon-question" />
                  </el-tooltip>
                </span>
                <el-input v-model="form.menuPerms" placeholder="授权标识"/>
              </el-form-item>
            </el-col>
            <el-col  :span="12">
              <el-form-item v-if="form.type !== 2" label="菜单排序" prop="menuOrder">
                <el-input-number v-model="form.order" controls-position="right" :min="0" label="菜单排序"></el-input-number>
              </el-form-item>
            </el-col>
            <el-col v-if="this.form.type==1" :span="12">
              <el-form-item label="是否隐藏" prop="hide">
                <el-radio-group v-model="form.hide">
                  <el-radio v-for="type in hideTypeList" :label="type.dictValue" :key="type.dictLabel">{{ type.dictLabel }}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-form-item v-if="form.type !== 2" label="菜单图标" prop="menuIcon">
                <el-row>
                  <el-col :span="22">
                    <el-popover
                      ref="menuIconListPopover"
                      placement="bottom-start"
                      trigger="click"
                      popper-class="mod-menu__icon-popover">
                      <div class="mod-menu__icon-inner">
                        <div class="mod-menu__icon-list">
                          <el-button
                            v-for="(item, index) in svgIcons"
                            :key="index"
                            @click="iconActiveHandle(item)"
                            :class="{ 'is-active': item === form.icon }">
<!--                            <i :class="'el-icon-' + item" />-->
                            <svg-icon :icon-class="item" />
                          </el-button>
                        </div>
                      </div>
                    </el-popover>
                    <el-input v-model="form.icon" v-popover:menuIconListPopover :readonly="true" placeholder="菜单图标名称" class="icon-list__input"></el-input>
                  </el-col>
                </el-row>
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
  import {addMenu, delMenu, getMenu, pageMenu, updateMenu, listMenu, getMenuSelect} from '@/api/sys/menu'
  import {treeDataTranslate,nestedGetQuery} from "@/utils";
  import svgIcons from "@/views/icons/svg-icons";

export default {
  name: 'Menu',
  components: {},
  data() {
    return {
      dataLoading: true,
      id: 0,
      ids: [],
      single: true,
      multiple: true,
      total: 0,
      title: '',
      open: false,
      isEdit: false,
      typeOptions: [],
      menuList: [],
      svgIcons: [],
      selectMenuList: [],
      menuListTreeProps: {
        label: 'name',
        children: 'children'
      },
      menuTypeList: [
        { dictValue: 0, dictLabel: '目录' },
        { dictValue: 1, dictLabel: '菜单' },
        { dictValue: 2, dictLabel: '按钮' }
      ],
      hideTypeList: [
        { dictValue: false, dictLabel: '显示' },
        { dictValue: true, dictLabel: '隐藏' }
      ],
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageIndex: 1,
        pageSize: 10,
        name: ''
      },
      // 表单参数
      form: {
        parentUuid: '',
        parentName: '',
        type: 0,
        url: '',
        api: '',
        method: '',
        order: 0,
        name: '',
        icon: '',
        hide: false
      },
      // 表单校验
      rules: {
        name: [{ required: true, message: '名字不能为空', trigger: 'blur' }],
        parentName: [{ required: true, message: '上级菜单不能为空', trigger: 'change' }],
        url: [{ required: true, message: '名称不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.getList()
    this.svgIcons = svgIcons
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      this.dataLoading = true
      pageMenu(nestedGetQuery(this.queryParams)).then(response => {
        this.menuList = response.data.list
        this.total = response.data.total
        this.dataLoading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 菜单树选中
    menuListTreeCurrentChangeHandle (data, node) {
      this.form.parentUuid = data.uuid
      this.form.parentName = data.name
    },
    // 菜单树设置当前选中节点
    menuListTreeSetCurrentNode () {
      this.$refs.selectMenuListTree.setCurrentKey(this.form.parentUuid)
      this.form.parentName = (this.$refs.selectMenuListTree.getCurrentNode() || {})['name']
    },
    // 表单重置
    reset() {
      this.form = {
        uuid: '',
        parentUuid: '0',
        parentName: '',
        api: '',
        method: 'GET',
        type: 0,
        url: '',
        menuPerms: '',
        order: 0,
        name: '',
        icon: '',
        hide: false
      }
      this.resetForm('form')
    },
    // 查詢菜單子菜單
    getChild(row, treeNode, resolve) {
      listMenu(row.uuid).then(response => {
        resolve(response.data)
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageIndex = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.queryParams.name = ''
      this.handleQuery()
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.title = '添加菜单'
      this.isEdit = false
      getMenuSelect({excludeTypes:2,needRoot: true}).then(response => {
        this.selectMenuList = treeDataTranslate(response.data, 'uuid')
        this.open = true
      }).then(()=>{
        this.menuListTreeSetCurrentNode()
      })
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.jobId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 图标选中
    iconActiveHandle (iconName) {
      this.form.icon = iconName
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      getMenuSelect({excludeTypes:2,needRoot:true}).then(response => {
        this.selectMenuList = treeDataTranslate(response.data, 'uuid')
      }).then(()=>{
        this.reset()
        const id = row.id || this.ids
        getMenu({id:id}).then(response => {
          this.form = response.data
          this.form.parentUuid = response.data.parentUuid
          this.form.status = String(this.form.status)
          this.open = true
          this.$nextTick(() => {
            this.menuListTreeSetCurrentNode()
          })
          this.title = '修改菜单'
          this.isEdit = true
        })
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      console.log(this.form)
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.uuid !== undefined && this.form.uuid!=='') {
            updateMenu(this.form).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          } else {
            this.form.status = parseInt(this.form.status)
            this.form.misfirePolicy = parseInt(this.form.misfirePolicy)
            this.form.concurrent = parseInt(this.form.concurrent)
            this.form.jobType = parseInt(this.form.jobType)
            addMenu(this.form).then(response => {
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
      this.$confirm('是否确认删除"' + row.menuName + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delMenu({ 'ids': Ids })
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    }
  }
}
</script>
<style lang="scss">
  .mod-menu {
    .menu-list__input,
    .icon-list__input {
      > .el-input__inner {
        cursor: pointer;
      }
    }
    &__icon-popover {
      width: 458px;
      overflow: hidden;
    }
    &__icon-inner {
      width: 478px;
      max-height: 258px;
      overflow-x: hidden;
      overflow-y: auto;
    }
    &__icon-list {
      width: 458px;
      padding: 0;
      margin: -8px 0 0 -8px;
      > .el-button {
        padding: 8px;
        margin: 8px 0 0 8px;
        > span {
          display: inline-block;
          vertical-align: middle;
          width: 18px;
          height: 18px;
          font-size: 18px;
        }
      }
    }
    .icon-list__tips {
      font-size: 18px;
      text-align: center;
      color: #e6a23c;
      cursor: pointer;
    }
  }
  .el-tree{
     max-height: 500px;
     overflow-x: scroll;
     width: 150px;
   }
  .el-tree>.el-tree-node{
    display: inline-block !important;
    min-width: 100%;
  }
</style>
