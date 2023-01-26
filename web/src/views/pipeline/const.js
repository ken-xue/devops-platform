// 连接线是通过sourceID和tagetID来表示， 下面这个变量是用来分隔sourceID和tagetID的分隔符
export const CONNECTORSEPARATESYMBOL = '&&';
export const PLACEHOLDER = 'placeholder';
export const initPipelineTemplate = {
  nodes: [
    {
      data: {
        icon: "el-icon-s-promotion",
        value: "开始"
      },
      id: "node-c40d8e60caef11ec9199672631f641b0",
      name: "START",
      points: {
        sources: [
          "source-c40d8e62caef11ec9199672631f691b0"
        ],
        targets: []
      },
      position: {
        left: 169,
        top: 52
      }
    },
    {
      data: {
        icon: "el-icon-finished",
        value: "结束"
      },
      id: "node-c6ece090caef11ec919967q631f691b0",
      name: "END",
      points: {
        sources: [],
        targets: [
          "target-c6ece091caef11ec9199672631f691b0",
          "target-c6ece093caef11ec9199672631f691b0",
          "target-c6ece094caef11ec9199672631f691b0"
        ]
      },
      position: {
        left: 186,
        top: 516
      }
    }
  ],
  edges: [],
  endpoints: [
    {
      data: {
        value: "输入"
      },
      id: "target-ff622550c9f711ecac18a585aafeb853"
    },
    {
      data: {
        value: "输出"
      },
      id: "source-00ec47c2c9f811ecac18a585aafeb853"
    },
    {
      data: {
        value: "输出"
      },
      id: "source-c40d8e62caef11ec9199672631f691b0"
    },
    {
      data: {
        value: "输入"
      },
      id: "target-c6ece091caef11ec9199672631f691b0"
    },
    {
      data: {
        value: "输入"
      },
      id: "target-c6ece093caef11ec9199672631f691b0"
    },
    {
      data: {
        value: "输入"
      },
      id: "target-c6ece094caef11ec9199672631f691b0"
    }
  ],
  head: ""
}
