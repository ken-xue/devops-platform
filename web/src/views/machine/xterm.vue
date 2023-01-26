<template>
  <div style="overflow: hidden" id="xterm" class="xterm" />
</template>
<script>
import 'xterm/css/xterm.css'
import { Terminal } from 'xterm'
import { FitAddon } from 'xterm-addon-fit'
import { AttachAddon } from 'xterm-addon-attach'

export default {
  name: 'xterm',
  props: {
    socketURI: {
      type: String,
      default: 'ws://127.0.0.1:8088/ws/terminal?f1944396bf43402aaa501b5856f67379'
    },
  },
  mounted() {
    // this.initSocket()
  },
  beforeDestroy() {
    this.socket.close()
    this.term.dispose()
  },
  methods: {
    initTerm() {
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
      term.open(document.getElementById('xterm'));
      fitAddon.fit();
      term.focus();
      this.term = term
    },
    initSocket(uuid) {
      // this.socket = new WebSocket(this.socketURI);
      this.socket = new WebSocket('ws://127.0.0.1:8088/ws/terminal?'+uuid);
      this.socketOnClose();
      this.socketOnOpen();
      this.socketOnError();
    },
    socketOnOpen() {
      this.socket.onopen = () => {
        // 链接成功后
        this.initTerm()
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
    }
  }
}
</script>
