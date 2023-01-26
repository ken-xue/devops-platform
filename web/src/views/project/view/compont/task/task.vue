<template>
  <div class="task-row">
    <el-card v-for="(group,index) in dataList" :key="index" class="box-card" shadow="never">
      <div slot="header" class="clearfix">
        <el-tooltip effect="dark" :content="group.groupName" placement="bottom">
          <span class="task-title-span" v-text="group.groupName"></span>
        </el-tooltip>
        <el-popover
          placement="bottom"
          width="20"
          trigger="click">
          <div >
            <el-button @click="updateGroup(group)" class="tip-button" type="text" icon="el-icon-edit">修改组名</el-button>
            <el-button @click="handleDelete(group)" class="tip-button" style="color: red;margin-left: 0px!important;" type="text" icon="el-icon-delete">删除该组</el-button>
          </div>
          <el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-more-outline" slot="reference"></el-button>
        </el-popover>
      </div>
      <div class="text item">
        <el-card shadow="never" class="task-card">
          <div>
            <div>
              <div style="display: inline;line-height: 30px">
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
              <div style="display: inline" @click="detail()">
                <span class="task-title">开发项目管理模块</span>
              </div>
              <div style="display: inline;float:right;margin-top: 0px;">
                <el-avatar size="small" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
            </div>
            <div class="task-line">
              开始时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              截止时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              <div style="display: inline">
                <span>完成量：4/8</span>
              </div>
              <div style="float:right;display: inline">
                <el-icon name="chat-dot-square"></el-icon><span style="margin-left: 8px;margin-right: 3px">33</span>
              </div>
            </div>
            <div class="task-line">
            </div>
            <div class="task-line">标签
              <el-tag size="mini" style="margin-left: 3px">标签一</el-tag>
              <el-tag size="mini" type="success" style="margin-left: 3px">标签二</el-tag>
              <el-tag size="mini" type="info" style="margin-left: 3px">标签三</el-tag>
            </div>
          </div>
          <el-progress style="margin-bottom: 0px" :percentage="50" ></el-progress>
        </el-card>
        <el-button @click="detail" icon="el-icon-circle-plus-outline" style="width: 100%;">添加新任务</el-button>
      </div>
    </el-card>

    <el-card class="box-card" shadow="never">
      <div slot="header" class="clearfix">
        <el-tooltip effect="dark" content="任务组1任务组1任务组1任务组1任务组1任务组1" placement="bottom">
            <span class="task-title-span">任务组任务组任务组任务组任务组任务组任务组任务组</span>
        </el-tooltip>
        <el-popover
          placement="bottom"
          width="20"
          trigger="click">
          <div >
            <el-button class="tip-button" type="text" icon="el-icon-edit">修改组名</el-button>
            <el-popconfirm title="确定删除该任务组吗？" @confirm="handleDelete">
            <el-button slot="reference" class="tip-button" style="color: red;margin-left: 0px!important;" type="text" icon="el-icon-delete">删除该组</el-button>
            </el-popconfirm>
          </div>
          <el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-more-outline" slot="reference"></el-button>
        </el-popover>
      </div>
      <div class="text item">
        <el-card shadow="never" class="task-card">
          <div>
            <div>
              <div style="display: inline;line-height: 30px">
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
              <div style="display: inline">
                <span class="task-title">开发项目管理模块</span>
              </div>
              <div style="display: inline;float:right;margin-top: 0px;">
                <el-avatar size="small" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
            </div>
            <div class="task-line">
              开始时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              截止时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              <div style="display: inline">
                <span>完成量：4/8</span>
              </div>
              <div style="float:right;display: inline">
                <el-icon name="chat-dot-square"></el-icon><span style="margin-left: 8px;margin-right: 3px">33</span>
              </div>
            </div>
            <div class="task-line">
            </div>
            <div class="task-line">标签
              <el-tag size="mini" style="margin-left: 3px">标签一</el-tag>
              <el-tag size="mini" type="success" style="margin-left: 3px">标签二</el-tag>
              <el-tag size="mini" type="info" style="margin-left: 3px">标签三</el-tag>
            </div>
          </div>
          <el-progress style="margin-bottom: 0px" :percentage="50" format="0"></el-progress>
        </el-card>
          <el-button @click="detail" icon="el-icon-circle-plus-outline" style="width: 100%;">添加新任务</el-button>
      </div>
    </el-card>


<!--    模拟-->
    <el-card class="box-card" shadow="never">
      <div slot="header" class="clearfix">
        <span>任务组1</span>
        <el-popover
          placement="bottom"
          title="操作"
          width="200"
          trigger="manual"
          content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。"
          v-model="visible">
          <el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-more-outline" slot="reference"
                     @click="visible = !visible"></el-button>
        </el-popover>
      </div>
      <div class="text item">
        <el-card shadow="never" class="task-card">
          <div>
            <div>
              <div style="display: inline;line-height: 30px">
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
              <div style="display: inline">
                <span class="task-title">开发流水线模块</span>
              </div>
              <div style="display: inline;float:right;margin-top: 0px;">
                <el-avatar size="small" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
            </div>
            <div class="task-line">
              开始时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              截止时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              <div style="display: inline">
                <span>完成量：4/8</span>
              </div>
              <div style="float:right;display: inline">
                <el-icon name="chat-dot-square"></el-icon><span style="margin-left: 8px;margin-right: 3px">33</span>
              </div>
            </div>
            <div class="task-line">
            </div>
            <div class="task-line">标签
              <el-tag size="mini" style="margin-left: 3px">标签一</el-tag>
              <el-tag size="mini" type="success" style="margin-left: 3px">标签二</el-tag>
              <el-tag size="mini" type="info" style="margin-left: 3px">标签三</el-tag>
            </div>
          </div>
          <el-progress style="margin-bottom: 0px" :percentage="50" format="0"></el-progress>
        </el-card>
        <el-card class="task-card" shadow="never">
          <div>
            <div>
              <div style="display: inline;line-height: 30px">
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
              <div style="display: inline">
                <span class="task-title">开发项目管理模块</span>
              </div>
              <div style="display: inline;float:right;margin-top: 0px;">
                <el-avatar size="small" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
            </div>
            <div class="task-line">
              开始时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              截止时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              <div style="display: inline">
                <span>完成量：4/8</span>
              </div>
              <div style="float:right;display: inline">
                <el-icon name="chat-dot-square"></el-icon><span style="margin-left: 8px;margin-right: 3px">33</span>
              </div>
            </div>
            <div class="task-line">
            </div>
            <div class="task-line">标签
              <el-tag size="mini" style="margin-left: 3px">标签一</el-tag>
              <el-tag size="mini" type="success" style="margin-left: 3px">标签二</el-tag>
              <el-tag size="mini" type="info" style="margin-left: 3px">标签三</el-tag>
            </div>
          </div>
          <el-progress style="margin-bottom: 0px" :percentage="50" format="0"></el-progress>
        </el-card>
        <el-card class="task-card" shadow="never">
          <div>
            <div>
              <div style="display: inline;line-height: 30px">
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
              <div style="display: inline">
                <span class="task-title">开发项目管理模块</span>
              </div>
              <div style="display: inline;float:right;margin-top: 0px;">
                <el-avatar size="small" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
            </div>
            <div class="task-line">
              开始时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              截止时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              <div style="display: inline">
                <span>完成量：4/8</span>
              </div>
              <div style="float:right;display: inline">
                <el-icon name="chat-dot-square"></el-icon><span style="margin-left: 8px;margin-right: 3px">33</span>
              </div>
            </div>
            <div class="task-line">
            </div>
            <div class="task-line">标签
              <el-tag size="mini" style="margin-left: 3px">标签一</el-tag>
              <el-tag size="mini" type="success" style="margin-left: 3px">标签二</el-tag>
              <el-tag size="mini" type="info" style="margin-left: 3px">标签三</el-tag>
            </div>
          </div>
          <el-progress style="margin-bottom: 0px" :percentage="50" format="0"></el-progress>
        </el-card>
          <el-button @click="detail" icon="el-icon-circle-plus-outline" style="width: 100%;">添加新任务</el-button>
      </div>
    </el-card>
    <el-card class="box-card" shadow="never">
      <div slot="header" class="clearfix">
        <span>任务组1</span>
        <el-popover
          placement="bottom"
          title="操作"
          width="200"
          trigger="manual"
          content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。"
          v-model="visible">
          <el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-more-outline" slot="reference"
                     @click="visible = !visible"></el-button>
        </el-popover>
      </div>
      <div class="text item">
        <el-card shadow="never" class="task-card">
          <div>
            <div>
              <div style="display: inline;line-height: 30px">
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
              <div style="display: inline">
                <span class="task-title">开发项目管理模块</span>
              </div>
              <div style="display: inline;float:right;margin-top: 0px;">
                <el-avatar size="small" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
            </div>
            <div class="task-line">
              开始时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              截止时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              <div style="display: inline">
                <span>完成量：4/8</span>
              </div>
              <div style="float:right;display: inline">
                <el-icon name="chat-dot-square"></el-icon><span style="margin-left: 8px;margin-right: 3px">33</span>
              </div>
            </div>
            <div class="task-line">
            </div>
            <div class="task-line">标签
              <el-tag size="mini" style="margin-left: 3px">标签一</el-tag>
              <el-tag size="mini" type="success" style="margin-left: 3px">标签二</el-tag>
              <el-tag size="mini" type="info" style="margin-left: 3px">标签三</el-tag>
            </div>
          </div>
          <el-progress style="margin-bottom: 0px" :percentage="50" format="0"></el-progress>
        </el-card>
        <el-card class="task-card" shadow="never">
          <div>
            <div>
              <div style="display: inline;line-height: 30px">
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
              <div style="display: inline">
                <span class="task-title">开发项目管理模块</span>
              </div>
              <div style="display: inline;float:right;margin-top: 0px;">
                <el-avatar size="small" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
            </div>
            <div class="task-line">
              开始时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              截止时间:2022-06-02 14:46:35
            </div>
            <div class="task-line">
              <div style="display: inline">
                <span>完成量：4/8</span>
              </div>
              <div style="float:right;display: inline">
                <el-icon name="chat-dot-square"></el-icon><span style="margin-left: 8px;margin-right: 3px">33</span>
              </div>
            </div>
            <div class="task-line">
            </div>
            <div class="task-line">标签
              <el-tag size="mini" style="margin-left: 3px">标签一</el-tag>
              <el-tag size="mini" type="success" style="margin-left: 3px">标签二</el-tag>
              <el-tag size="mini" type="info" style="margin-left: 3px">标签三</el-tag>
            </div>
          </div>
          <el-progress style="margin-bottom: 0px" :percentage="50" format="0"></el-progress>
        </el-card>
        <el-button @click="detail" icon="el-icon-circle-plus-outline" style="width: 100%;">添加新任务</el-button>
      </div>
    </el-card>

    <!--    新增列表-->
    <el-card shadow="never" class="create-box-card">
      <div slot="header" class="clearfix">
        <span></span>
        <el-button style="float: right; padding: 3px 0" type="text"></el-button>
      </div>
      <el-button v-if="!createTaskGroupVisible" icon="el-icon-circle-plus-outline" style="width: 100%" @click="createTaskGroupVisible=!createTaskGroupVisible">新建任务列表</el-button>
      <el-form v-if="createTaskGroupVisible" :model="taskGroupForm" ref="taskGroupForm" >
        <el-form-item
          prop="groupName"
          :rules="[{ required: true, message: '请输入任务组名', trigger: 'blur' }]">
          <el-input v-model="taskGroupForm.groupName" placeholder="请输入任务组名"></el-input>
        </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="createTaskGroup">提交</el-button>
        <el-button @click="createTaskGroupVisible=!createTaskGroupVisible">取消</el-button>
      </el-form-item>
      </el-form>
    </el-card>

    <el-dialog title="修改" :visible.sync="renameVisible" width="700px" append-to-body>
      <el-form ref="renameForm" :model="renameForm" :rules="groupRules">
        <el-form-item prop="groupName">
          <el-input v-model="renameForm.groupName" placeholder="请输入任务组名"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitUpdateTaskGroup">提交</el-button>
        <el-button @click="renameVisible=!renameVisible">取消</el-button>
      </div>
    </el-dialog>
    <task-info v-if="taskInfoVisible" ref="TaskInfo"></task-info>
  </div>
</template>

<script>
import TaskInfo from "@/views/project/view/compont/task/task-info";
import {add,list,del,update} from "@/api/project/task/TaskGroup"
export default {
  name: "task",
  components: {TaskInfo},
  created() {
    this.getList()
  },
  data() {
    return {
      checked: false,
      visible: false,
      renameVisible: false,
      taskInfoVisible: false,
      createTaskGroupVisible: false,
      renameForm: {
        groupName: ''
      },
      taskGroupForm: {},
      dataList:[],
      groupRules: {
        groupName: [{required: true, message: '组名不能为空', trigger: 'blur'}]
      }
    }
  },
  methods:{
    getList(){
      this.loading = true
      list({'projectUuid':this.$route.query.uuid}).then(response => {
        if (response.code === 2000) {
          const data = response.data;
          this.dataList = data
        } else {
          this.msgError(response.msg)
        }
        this.loading = false
      })
    },
    detail(id) {
      this.taskInfoVisible = true
      this.$nextTick(() => {
        this.$refs.TaskInfo.init(id)
      })
    },
    updateGroup(group){
      this.renameVisible = true
      this.renameForm.uuid = group.uuid
      this.renameForm.groupName = group.groupName
      this.renameForm.id = group.id
    },
    handleDelete(group) {
      this.$confirm('是否确认删除列表为"' + group.groupName + '"的任务组嘛 ?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return del({'ids':[group.id]})
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    },
    createTaskGroup(){
      this.$refs['taskGroupForm'].validate(valid => {
        if (valid) {
            this.taskGroupForm.projectUuid = this.$route.query.uuid
            add({'taskGroupDTO':this.taskGroupForm}).then(response => {
              if (response.code === 2000) {
                this.msgSuccess('添加成功')
                this.taskGroupForm.groupName = ''
                this.getList()
              } else {
                this.msgError(response.msg)
              }
            })
          }
      })
    },
    submitUpdateTaskGroup(){
      this.$refs['renameForm'].validate(valid => {
        if (valid) {
          update({'taskGroupDTO':this.renameForm}).then(response => {
            if (response.code === 2000) {
              this.msgSuccess('修改成功')
              this.renameVisible = false
              this.getList()
            } else {
              this.msgError(response.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  display: inline-grid;
  width: 280px;
  margin-left: 20px;
}

.create-box-card {
  display: inline-grid;
  width: 280px;
  margin-left: 20px;
}

.task-row {
  white-space: nowrap;
  overflow-x: scroll;
  height: calc(100vh - 95px);
}

.task-title {
  font-weight: 800;
  margin-left: 8px;
}

.task-line {
  margin: 8px 0;
}

.task-card {
  margin-bottom: 30px;
}
.tip-button{
  display: block;
  width: 100%;
}
.task-title-span{
  float: left;
  max-width: 210px;
  display: inline;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>
