/* eslint-disable import/no-cycle */
import './editorcss';
import instance from './instance';
import extendsEvent, { EventCenter } from './eventcenter';
import panZoom from './moveandzoom';
import contentMenu from './contextmenu';
import editor from './editor';
import model from './model';
import exec, { undo, AddNodeCommand } from './command';
import Contextmenu from "./contextmenu";

const FlowChart = {
  /**
   * @description 设置插件容器
   * @param {string} id 容器id
   */
  setContainer(id,loggerView) {
    instance.setContainer(id);
    this.init(loggerView);
  },

  /**
   * @description 在容器内添加节点
   * @param {object} position {left:0; top:0}节点位置
   * @param {string} elId 记录生产节点的源节点id，便于传递原节点信息
   */
  addNode(position, elId) {
    exec(AddNodeCommand, position, elId);
  },

  /**
   * @description 撤销
   */
  undo() {
    undo();
  },

  /**
   * @description 初始化
   */
  init(loggerView) {
    editor.init(loggerView);
    panZoom.init();
    contentMenu.init();
  },

  /**
   * @description  缩小画布
   */
  zoomIn() {
    const x = instance.mainContainerWrap.clientWidth / 2;
    const y = instance.mainContainerWrap.clientHeight / 2;
    instance.pan.smoothZoom(x, y, 0.8);
  },

  /**
   * @description 放大画布
   */
  zoomOut() {
    const x = instance.mainContainerWrap.clientWidth / 2;
    const y = instance.mainContainerWrap.clientHeight / 2;
    instance.pan.smoothZoom(x, y, 1.2);
  },

  /**
   * @description 加载数据
   */
  loadData(data,loggerView) {
    model.setData(data);
    editor.render(loggerView);
  },

  /**
   * @description 获得模型数据
   */
  getModelData() {
    return model.getData();
  },

  /**
   * @description 插件扩展
   * @param {*} plugin 插件函数
   * @param  {...any} args 参数。暴露内部实例对象
   */
  use(plugin, ...args) {
    plugin.apply(this, [{
      instance,
      editor,
      model,
      contentMenu,
    }].concat(args));
  },

  getNodeDataByNodeId(nodeId) {
    return model.getNodeDataByNodeId(nodeId);
  },

  hideMenu(){
    Contextmenu.hide()
  }
};

extendsEvent(FlowChart);

document.addEventListener('click', (ev) => {
  EventCenter.emit('document.click', ev);
});

export default FlowChart;
