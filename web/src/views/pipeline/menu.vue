<template>
  <div>
    <div>
    </div>
  <el-aside width="240px" class="left">
    <div class="search">
      <div class="title">节点列表</div>
<!--      <el-input placeholder="搜索节点"-->
<!--                size="small"-->
<!--                v-model="filterText">-->
<!--        <i slot="prefix"-->
<!--           class="el-input__icon el-icon-search"></i>-->
<!--      </el-input>-->
    </div>
<!--    <el-tree :data="nodeData"-->
<!--             node-key="id"-->
<!--             :default-expanded-keys="['source','preHandle','sign','learn']"-->
<!--             icon-class="el-icon-arrow-right"-->
<!--             :render-content="renderContentFunction"-->
<!--             :filter-node-method="filterNode"-->
<!--             ref="tree2"-->
<!--             :props="defaultProps"></el-tree>-->
    <div class="node-tree">
      <el-tree :data="nodeData"
               :accordion="true"
               node-key="id"
               :default-expanded-keys="[10]"
               icon-class="el-icon-arrow-right"
               :render-content="renderContentFunction"
               :filter-node-method="filterNode"
               ref="tree2"
               :props="defaultProps"></el-tree>
    </div>
  </el-aside>
  </div>
</template>

<script>
import Vue from 'vue';
import {list} from '@/api/app/node'

export default Vue.extend({
  data() {
    return {
      nodeData: [],
      filterText: '',
      defaultProps: {
        children: 'children',
        label: 'label',
      },
    };
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val);
    },
  },
  created() {
    list().then(response => {
      this.nodeData = response.data
    })
  },
  methods: {
    renderContentFunction(h, {node, data, store}) {
      const className = node.expanded ? 'el-icon-folder-opened' : 'el-icon-folder';
      const classNameChild = (!data.children && data.icon) ? data.icon : '';
      return h('div', {
        class: {
          leafNode: !data.children
        },
        style: {
          height: '38px',
          lineHeight: '38px',
          fontSize: '12px',
          color: '#1b1c23',
        },
      }, [
        h('el-tooltip', {
          attrs: {
            content: data.label,
            placement: 'top-end',
            disabled: !!data.children,
          },
        }, [
          h('span', {
            attrs: {
              draggable: !data.children,
              id: data.id,
              name: data.name,
              'view': data.view
            },
            on: {
              dragstart: this.dragHandle,
            },
            class: 'node',
            style: {
              display: 'inline-block',
              marginTop: '4px',
              height: '30px',
              lineHeight: '30px',
              width: '140px',
              borderRadius: '4px',
              position: 'relative',
              outline: 'none',
              border: !data.children ? '1px solid transparent' : 'none',
            },
          }, [
            h('i', {
              class: {
                [className]: !!data.children,
                [classNameChild]: !data.children,
              },
              style: {
                color: '#00cdea',
                marginLeft: data.children ? '10px' : '15px',
              },
            }),
            h('span', {
              style: {
                fontSize: '13px',
                marginLeft: '10px',
              },
            }, data.label),
          ]),
        ]),
      ]);
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    dragHandle(ev) {
      ev.dataTransfer.setData('target', ev.target.id);
    },
  },
});
</script>

<style lang="scss">
.el-tree-node__content{
  padding-left: 0px!important;
}

.node-tree{
  height: calc(100vh - 120px);
  overflow-y: auto
}
.title {
  -webkit-text-size-adjust: 100%;
  text-rendering: optimizeLegibility;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
  color: #2c3e50;
  -webkit-font-smoothing: antialiased;
  -webkit-box-direction: normal;
  box-sizing: inherit;
  height: 40px;
  font-size: 12px;
  line-height: 40px;
  text-align: center;
}
.left{
    border-right: 1px solid #e5e5e5;
    height: 100%;

    //.el-tree {
    //  height: 100%;
      //overflow-y: auto;
    //}
}
.el-aside {
  background: #f8f8f8;
  padding: 0px 0px;

  .search {
    height: 41px;
    background-color: #f3f3f3;
    //padding: 8px 12px;
    box-sizing: border-box;
    border-bottom: 1px solid #e5e5e5;
    border-top: 1px solid #e5e5e5;

    .el-input {
      height: 24px;

      .el-input__inner {
        height: 42px;
      }
    }

    .el-input__prefix {
      top: 5px;
    }

    .el-input--small .el-input__icon {
      line-height: 24px;
    }
  }

  .el-tree {
    background: transparent;
    margin-left: 2px;
  }
}
.el-tree-node__content,
.el-tree-node {
  min-height: 38px !important;
}

.node{
  width: 100%!important;
  min-width: 150px!important;
}
.leafNode {
  .node::before {
    content: "";
    position: absolute;
    top: 2px;
    left: 3px;
    border-radius: 2px;
    padding: 13px 2px;
    background: transparent;
  }

  &:hover span.node {
    border: 1px solid #1c9bec !important;
    background: #fff;

    &::before {
      background: #1c9bec;
    }
  }
}
</style>
