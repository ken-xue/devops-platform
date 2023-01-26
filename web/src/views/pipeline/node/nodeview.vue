<template>
  <el-drawer title="数据" size="75%"  :visible.sync="nodeViewVisible" append-to-body :destroy-on-close="true">
    <el-card class="box-card">
      <div style="height: calc(100vh - 50px)">
        <h1>Node View</h1>
        <div style="height: 80%;overflow: scroll">
          <span style="overflow: scroll">{{data}}</span>
        </div>
      </div>
    </el-card>
  </el-drawer>
</template>

<script>

import { get as Info} from '@/views/pipeline/node/nodeview.js'

export default {
  name: "NodeView",
  data(){
    return{
      nodeViewVisible: false,
      data:{}
    }
  },
  methods: {
    init(nodeUuid, loggerUuid) {
      this.nodeViewVisible = true
      //查询节点数据
      Info(loggerUuid,nodeUuid).then(response => {
        if (response.code === 2000) {
            this.data = response.data
        }else {
          this.msgError(response.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
