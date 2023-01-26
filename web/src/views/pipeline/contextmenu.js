/* eslint-disable import/no-cycle */
import { MessageBox, Message } from 'element-ui';
import { EventCenter } from './eventcenter';
import exec, {
  RenameNodeCommand, PasteNodeCommand, RemoveNodeCommand, RemoveConnectorCommand,
} from './command';
import editor from './editor';
import model from "@/views/pipeline/model";

class MenuItem {
  constructor(opt) {
    this.text = opt.text;
    this.icon = opt.icon;
    this.role = opt.role;
    this.clickHandle = opt.clickHandle;
    this.init();
  }

  init() {
    const li = document.createElement('li');
    li.className = 'fy_contextMenu_item';
    li.dataset.role = this.role;
    li.innerHTML = `<i class="${this.icon}"></i><span>${this.text}</span>`;
    this.el = li;
    this.bind();
  }

  bind() {
    this.el.onclick = (e) => {
      e.stopPropagation();
      this.clickHandle(e);
    };
  }

  getEl() {
    return this.el;
  }
}

// 右键菜单栏容器
let ul = null;
// 触发右键菜单的组件（Connector或者Node）
let currentComponent = null;
// 当前复制的节点Id
let copiedNodeId = '';
// 记录当前菜单栏位置
let contextMenuPos = {};

/**
   * @description 删除连接线
   * @param {Connection} comp 连接线对象
   */
function deleteHandle(comp) {
  if (comp) {
    if (typeof comp === 'object') {
      exec(RemoveConnectorCommand, comp);
    } else if (typeof comp === 'string') {
      exec(RemoveNodeCommand, comp);
    }
  }
}

/**
 * @description 隐藏右键菜单
 */
function hide() {
  ul.style.display = 'none';
}

/**
 * @description 生成分割线菜单项
 */
function generateMenuDivideLine() {
  const divide = document.createElement('div');
  divide.style.cssText = 'height:1px;background:#efefef;';
  ul.appendChild(divide);
}

/**
 * @description 生成单个菜单项并插入右键菜单中
 * @param {object} opt
 */
function generateMenuItem(opt) {
  const item = new MenuItem(opt);
  ul.appendChild(item.getEl());
}

/**
 * @description 生成节点的右键菜单
 */
function generateNodeMenu(logger) {
  // generateMenuItem({
  //   text: '重命名',
  //   icon: 'el-icon-edit',
  //   role: 'rename',
  //   clickHandle: () => {
  //     const nodeEl = document.getElementById(currentComponent);
  //     Contextmenu.hide()
  //     const cComp = nodeEl.vNode.$children[0];
  //     MessageBox.prompt('请输入新名称', '重命名', {
  //       confirmButtonText: '确定',
  //       inputValue: cComp.text,
  //       cancelButtonText: '取消',
  //     }).then(({ value }) => {
  //       exec(RenameNodeCommand, currentComponent, cComp.text, value);
  //     }).catch(() => {});
  //   },
  // });
  const node = model.getNodeDataByNodeId(currentComponent);
  //start和end节点不支持复制删除
  if('START' !== node.name && 'END' !== node.name && logger === undefined) {
    generateMenuItem({
      text: '复制节点',
      icon: 'el-icon-copy-document',
      role: 'delete',
      clickHandle: () => {
        Contextmenu.hide()
        copiedNodeId = currentComponent;
        Message.success({
          message: '复制成功',
        });
      },
    });
    generateMenuItem({
      text: '删除节点',
      icon: 'el-icon-delete',
      role: 'delete',
      clickHandle: () => {
        Contextmenu.hide()
        deleteHandle(currentComponent);
      },
    });
    generateMenuDivideLine();
    generateMenuItem({
      text: '重启节点',
      icon: 'el-icon-refresh',
      role: 'view',
      clickHandle: () => {
        Contextmenu.hide()
        editor.emitRestartNode(currentComponent);
      },
    });
  }
  generateMenuItem({
    text: '执行日志',
    icon: 'el-icon-view',
    role: 'view',
    clickHandle: () => {
      Contextmenu.hide()
      // const nodeEl = document.getElementById(currentComponent);
      // const cComp = nodeEl.vNode.$children[0];
      editor.emitShowNodeLogger(currentComponent);
    },
  });
  if (node.data.view === 'true'||node.data.view === true) {
    generateMenuItem({
      text: '结果数据',
      icon: 'el-icon-data-analysis',
      role: 'view',
      clickHandle: () => {
        Contextmenu.hide()
        // const nodeEl = document.getElementById(currentComponent);
        // const cComp = nodeEl.vNode.$children[0];
        editor.emitShowNodeData(currentComponent);
      },
    });
  }
}

/**
 * @description 生成连接线的右键菜单
 */
function generateConnectorMenu(logger) {
  //如果是历史日志模式下查看无需展示
  if (logger === undefined) {
    generateMenuItem({
      text: '删除',
      icon: 'el-icon-delete',
      role: 'delete',
      clickHandle: () => {
        Contextmenu.hide()
        deleteHandle(currentComponent);
      },
    });
  }else {
    hide()
  }
}

/**
 * @description 生成背景的菜单
 */
function generateBgMenu(loggerView) {
  if (loggerView===undefined) {
    generateMenuItem({
      text: '粘贴节点',
      icon: 'el-icon-download',
      role: 'paste',
      clickHandle: () => {
        Contextmenu.hide()
        if (copiedNodeId) {
          exec(
            PasteNodeCommand,
            {pageX: contextMenuPos.left, pageY: contextMenuPos.top},
            copiedNodeId,
          );
        } else {
          Message({
            type: 'warning',
            message: '请复制节点后再粘贴。',
          });
        }
      },
    });
  }else {
    hide()
  }
}

/**
 * @description 生成菜单项
 */
function generateMenuItemsBycurrentComponent(logger) {
  ul.innerHTML = '';
  if (currentComponent && typeof currentComponent === 'string')generateNodeMenu(logger);
  if (currentComponent && typeof currentComponent === 'object') generateConnectorMenu(logger);
  if (typeof currentComponent === 'undefined') generateBgMenu(logger);
}

/**
 * @description 生成菜单容器
 */
function generateMenuContainer() {
  ul = document.createElement('ul');
  ul.className = 'fy_contextMenu';
  document.body.appendChild(ul);
  // ul.onmouseup = hide; // TODO: 运行时机
}

/**
 * @description 显示右键菜单
 * @param {object} position {left:0,top:0}
 * @param {*} component 组件，传入当前组件，用于在点击菜单按钮时，绑定操作对象
 * @param {*} loggerView 是否是日志视图，如果是部分菜单不显示
 */
function show(position, component,loggerView) {
  ul.style.top = `${position.top}px`;
  ul.style.left = `${position.left}px`;
  ul.style.display = 'block';
  contextMenuPos = position;
  currentComponent = component;
  generateMenuItemsBycurrentComponent(loggerView);
}

/**
 * @description 初始化右键菜单
 */
function init() {

  generateMenuContainer();

  hide();

  EventCenter.on('document.click', (ev) => {
    if (!ev.target.classList.contains('fy_contextMenu_item')) hide();
  });
}


const Contextmenu = {
  init,
  show,
  hide,
};

export default Contextmenu;
