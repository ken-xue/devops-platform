<template>
  <el-drawer title="日志" size="75%"  :visible.sync="nodeExecuteLogVisible" append-to-body :destroy-on-close="true">
    <el-card class="box-card">
      <div style="height: calc(100vh - 50px)">
        <div style="overflow: hidden" id="xterm" class="xterm" />
      </div>
    </el-card>
  </el-drawer>
</template>

<script>
import 'xterm/css/xterm.css'
import { Terminal } from 'xterm'
import { FitAddon } from 'xterm-addon-fit'
import { AttachAddon } from 'xterm-addon-attach'

export default {
  name: 'MinioCreateLogger',
  props: {
    socketURI: {
      type: String,
      default: 'ws://127.0.0.1:8088/terminal?f1944396bf43402aaa501b5856f67379'
    },
  },
  data(){
    return{
      nodeExecuteLogVisible: false,
      clusterCreateDTO: {}
    }
  },
  mounted() {
    // this.initSocket()
  },
  beforeDestroy() {
    this.socket.close()
    this.term.dispose()
  },
  methods: {
    initSocket() {
      const term = new Terminal({
        fontSize: 14,
        rows: 45,
        cursorBlink: true,
        windowsMode: true,
        disableStdin: true
      });
      const attachAddon = new AttachAddon(this.socket);
      const fitAddon = new FitAddon();
      term.loadAddon(attachAddon);
      term.loadAddon(fitAddon);
      term.open(document.getElementById('xterm'));
      fitAddon.fit();
      term.focus();
      this.term = term
    },
    init(data) {
      this.clusterCreateDTO = data;
      this.nodeExecuteLogVisible = true
      this.socket = new WebSocket(process.env.VUE_APP_SOCKET_API + '/ws/create/minio');
      this.socketOnClose();
      this.socketOnOpen();
      this.socketOnError();
    },
    socketOnOpen() {
      this.socket.onopen = () => {
        // 连接成功后
        this.initSocket()
        // 发送创建集群必要信息
        this.socket.send(JSON.stringify(this.clusterCreateDTO))
      }
    },
    socketOnClose() {
      this.socket.onclose = () => {
        console.log('close socket')
      }
    },
    socketOnError() {
      this.socket.onerror = () => {
        console.log('socket 链接失败')
      }
    },
    beforeDestroy() {
      this.socket.close()
      this.term.dispose()
    }
  }
}
</script>

<style scoped>
  /*.box-card {*/
  /*  height: 90%;*/
  /*}*/
</style>
