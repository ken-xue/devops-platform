<template>
  <div>
  <el-drawer :with-header="false" title="Pod" size="70%" :visible.sync="open" :destroy-on-close="true" append-to-body>
    <el-card class="box-card" style="height: calc(100% - 10px)">
      <div style="width: 100%;height: calc(100% - 10px);">
         <AceEditor
           v-model="describe"
           width="100%"
           :height="'800px'"
           :fontSize="14"
           :showPrintMargin="true"
           :showGutter="true"
           :highlightActiveLine="true"
           mode="json"
           theme="monokai"
           :onChange="onChange"
           name="editor"
           :readOnly="true"
           :editorProps="{$blockScrolling: true}"
         />
       </div>
    </el-card>
  </el-drawer>
  </div>
</template>

<script>
import {describe} from "@/api/kubernetes/cluster";

import {Ace as AceEditor, Split as SplitEditor} from 'vue2-brace-editor';
import 'brace/mode/json';
import 'brace/theme/monokai';

export default {
  name: 'Describe',
  components: {
    AceEditor,
    SplitEditor,
  },
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
      describe: '',
      clusterId: 0,
      maxHeight: 0
    }
  },
  created() {
    // this.getList()
  },
  methods: {
    init(row) {
      const data = {
        'name': row.name,
        'clusterId': row.clusterId,
        'namespace': row.namespace,
      }
      describe(data).then(response => {
        if (response.code === 2000) {
          this.describe = JSON.stringify(response.data,null,4)
          this.open = true
        } else {
          this.msgError(response.msg)
        }
        this.searchLoading = false
      })
    },
    onChange() {

    },

  }
}
</script>
