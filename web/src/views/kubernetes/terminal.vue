<template>
  <el-dialog title="终端" :visible.sync="open" width="80%"  top="10vh" append-to-body @close="beforeDestroy">
    <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
      <el-tab-pane
        :key="item.name"
        v-for="(item, index) in editableTabs"
        :label="item.title"
        :name="item.name">
        <div style="overflow: hidden" :id="item.name" class="xterm"/>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>
<script>
import 'xterm/css/xterm.css'
import { Terminal } from 'xterm'
import { FitAddon } from 'xterm-addon-fit'
import { AttachAddon } from 'xterm-addon-attach'

export default {
  name: 'terminal',
  props: {
    socketURI: {
      type: String,
      default: 'ws://127.0.0.1:8088/ws/terminal?f1944396bf43402aaa501b5856f67379'
    },
  },
  data() {
    return {
      open: false,
      terminalUuid: '',
      editableTabsValue: '1',
      editableTabs: [],
      tabIndex: 1
    }
  },
  beforeDestroy() {
    this.socket.close()
    this.term.dispose()
  },
  methods: {
    initTerm(id) {
      const term = new Terminal({
        fontSize: 14,
        rows: 33,
        cursorBlink: true,
        windowsMode: true,
        disableStdin: false
      });
      const attachAddon = new AttachAddon(this.socket);
      const fitAddon = new FitAddon();
      term.loadAddon(attachAddon);
      term.loadAddon(fitAddon);
      term.open(document.getElementById(id));
      fitAddon.fit();
      term.focus();
      this.term = term
    },
    init(uuid) {
      this.open = true
      this.initSocket(uuid,1)
      this.terminalUuid = uuid
      this.editableTabs = [{
        title: '终端1',
        name: '1',
        content: 'Tab 1 content'
      }]
    },
    initSocket(uuid,elementId) {
      this.socket = new WebSocket('ws://127.0.0.1:8088/ws/terminal?'+uuid);
      this.socketOnClose();
      this.socketOnOpen(elementId);
      this.socketOnError();
    },
    socketOnOpen(id) {
      this.socket.onopen = () => {
        // 链接成功后
        this.initTerm(id)
      }
    },
    socketOnClose() {
      this.socket.onclose = () => {
        // console.log('close socket')
      }
    },
    socketOnError() {
      this.socket.onerror = () => {
        // console.log('socket 链接失败')
      }
    },
    beforeDestroy() {
      this.socket.close()
      this.term.dispose()
    },
    handleTabsEdit(targetName, action) {
      if (action === 'add') {
        if (this.editableTabs.length >= 12) {
          this.msgError('最大只能开12个终端')
          return
        }
        let newTabName = ++this.tabIndex + '';
        let title = '终端' + (this.tabIndex);
        this.editableTabs.push({
          title: title,
          name: newTabName,
          content: title
        });
        this.editableTabsValue = newTabName;
        this.initSocket(this.terminalUuid, newTabName)
      }
      if (action === 'remove') {
        let tabs = this.editableTabs;
        let activeName = this.editableTabsValue;
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
              }
            }
          });
        }
        this.editableTabsValue = activeName;
        this.editableTabs = tabs.filter(tab => tab.name !== targetName);
      }
    }
  }
}
</script>
