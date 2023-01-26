<template>
  <el-dialog title="任务" top="3vh" :visible.sync="visible" width="70%" append-to-body :destroy-on-close="true"
             @close=closeDialog :close-on-click-modal="false">
    <div>
      <el-container>
        <el-main>
          <el-card class="box-card" shadow="never">
            <el-container direction="vertical">
              <el-form ref="form" :model="form" label-width="99px" label-position="left">
                <el-form-item label="任务名称">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-tickets"/>
                      <span class="item-span">任务名称</span>
                    </span>
                  </span>
                  <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="任务状态">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-monitor"/>
                      <span class="item-span">任务状态</span>
                    </span>
                  </span>
                  <el-tag :disable-transitions="false">
                    进行中
                  </el-tag>
                </el-form-item>
                <el-form-item label="任务时间">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-date"/>
                      <span class="item-span">任务时间</span>
                    </span>
                  </span>
                  <div class="block">
                    <el-date-picker
                      v-model="form.taskTime"
                      type="datetimerange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      align="right">
                    </el-date-picker>
                  </div>
                </el-form-item>

                <el-form-item label="执行人">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-user"/>
                      <span class="item-span">执行人</span>
                    </span>
                  </span>
                  <div style="display: inline;float:left;margin-top: 0px;">
                    <el-avatar size="small"
                               src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                  </div>
                  <span style="margin-left: 5px">mikey</span>
                </el-form-item>

                <el-form-item label="任务描述">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-document"/>
                      <span class="item-span">任务描述</span>
                    </span>
                  </span>
                  <tinymce :height="300" v-model="form.desc" id='tinymce'></tinymce>
                </el-form-item>

                <el-form-item label="优先级">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-s-opportunity"/>
                      <span class="item-span">优先级</span>
                    </span>
                  </span>
                  <el-tag type="danger" :disable-transitions="false">紧急</el-tag>
                </el-form-item>

                <el-form-item label="标签">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-price-tag"/>
                      <span class="item-span">标签</span>
                    </span>
                  </span>
                  <el-tag style="margin-right: 8px;">标签一</el-tag>
                  <el-tag style="margin-right: 8px;" type="success">标签二</el-tag>
                  <el-tag style="margin-right: 8px;" type="info">标签三</el-tag>
                  <el-tag style="margin-right: 8px;" type="warning">标签四</el-tag>
                  <el-tag style="margin-right: 8px;" type="danger">标签五</el-tag>

                  <el-button icon="el-icon-plus" circle size="mini"></el-button>
                </el-form-item>

                <el-form-item label="子任务">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-s-unfold"/>
                      <span class="item-span">子任务</span>
                    </span>
                  </span>
                </el-form-item>

                <el-form-item label="任务工时">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-time"/>
                      <span class="item-span">任务工时</span>
                    </span>
                  </span>
                </el-form-item>

                <el-form-item label="关联文件">
                  <span slot="label">
                    <span class="span-box">
                      <i class="el-icon-paperclip"/>
                      <span class="item-span">关联文件</span>
                    </span>
                  </span>
                  <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :before-remove="beforeRemove"
                    multiple
                    :limit="3"
                    :on-exceed="handleExceed"
                    :file-list="fileList">
                    <el-button icon="el-icon-plus" circle size="mini"></el-button>
<!--                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
                  </el-upload>
                </el-form-item>
              </el-form>
            </el-container>
          </el-card>
        </el-main>
        <el-aside width="300px">
          <el-card class="box-card" shadow="never">
            <div style="display: inline-grid">
              <div style="display: block">
                参与者 · 1
              </div>
              <div style="display: inline-table;width:100%;float:left;margin-top: 0px;">
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;" size="small"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-avatar style="margin-right: 5px;background:transparent;" size="small">
                  <el-button style="height: 20px!important;width: 20px!important;" icon="el-icon-plus" circle
                             size="mini"></el-button>
                </el-avatar>
              </div>
            </div>
            <el-divider></el-divider>
            <div>
              <div style="display: block">
                任务动态 · 1
              </div>
              <div style="height: 300px">
                <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
                  <li v-for="i in count" class="infinite-list-item">{{ i }}</li>
                </ul>
              </div>
            </div>
          </el-card>
        </el-aside>
      </el-container>
    </div>
  </el-dialog>
</template>

<script>
export default {
  name: "task-info",
  data() {
    return {
      visible: false,
      taskDetailVisible: false,
      count: 0,
      fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}],
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    init(data) {
      this.visible = true

    },
    load() {
      this.count += 2
    },
    onSubmit() {
      console.log('submit!');
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    closeDialog() {

    }
  }
}
</script>

<style scoped>
/*.el-header, .el-footer {*/
/*  background-color: #B3C0D1;*/
/*  color: #333;*/
/*  text-align: center;*/
/*  line-height: 60px;*/
/*}*/

/*.el-aside {*/
/*  background-color: #D3DCE6;*/
/*  color: #333;*/
/*  text-align: center;*/
/*  line-height: 200px;*/
/*  margin-bottom: 0px;*/
/*}*/

/*.el-main {*/
/*  background-color: #E9EEF3;*/
/*  color: #333;*/
/*  text-align: center;*/
/*  line-height: 160px;*/
/*}*/

/*body > .el-container {*/
/*  margin-bottom: 40px;*/
/*}*/

/*.el-container:nth-child(5) .el-aside,*/
/*.el-container:nth-child(6) .el-aside {*/
/*  line-height: 260px;*/
/*}*/

/*.el-container:nth-child(7) .el-aside {*/
/*  line-height: 320px;*/
/*}*/

aside {
  background: transparent !important;
  padding-top: 20px !important;
  padding-left: 0px !important;
}

>>> .el-dialog__body {
  padding-top: 0px !important;
}

>>> .el-main {
  padding-right: 0px !important;
}

.item-span {
  margin-left: 9px;
}

.el-button--mini.is-circle {
  padding: 3px !important;
}
</style>
