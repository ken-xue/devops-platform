<template>
  <el-tabs :stretch=true class="" v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="任务" name="task">
      <task v-if="activeName==='task'"></task>
    </el-tab-pane>
    <el-tab-pane label="文件" name="file">
      <file v-if="activeName==='file'"></file>
    </el-tab-pane>
    <el-tab-pane label="概览" name="overview">
      <overview v-if="activeName==='overview'"></overview>
    </el-tab-pane>
    <el-tab-pane label="版本" name="version">
      <version v-if="activeName==='version'"></version>
    </el-tab-pane>
    <el-tab-pane label="甘特图" name="gantt">
      <gantt v-if="activeName==='gantt'"></gantt>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import Task from "@/views/project/view/compont/task/task";
import File from "@/views/project/view/compont/file";
import Overview from "@/views/project/view/compont/overview";
import Version from "@/views/project/view/compont/version";
import Gantt from "@/views/project/view/compont/gantt";
export default {
  components: {Gantt, Version, Overview, File, Task},
  data() {
    return {
      activeName: 'task'
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
};
</script>
<style scoped lang="scss">
::v-deep .el-tabs__nav-scroll{
  width: 50%!important;
  margin: 0 auto!important;
}
</style>
