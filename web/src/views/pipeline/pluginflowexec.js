import { CONNECTORSEPARATESYMBOL } from './const';

export default function flowExec({ instance }) {

  function changeStateByNodeId(nodeId, state) {
    const nodeEl = document.getElementById(nodeId);
    nodeEl.vNode.$children[0].state = state;
  }

  function getConnectorByUuids(uuids) {
    const edge = uuids.join(CONNECTORSEPARATESYMBOL);
    const connectors = instance.getAllConnections();
    const connector = connectors.find(c => c.getUuids().join(CONNECTORSEPARATESYMBOL) === edge);
    return connector;
  }

  function blingConnectors(edges) {
    const connectors = instance.getAllConnections();
    connectors.forEach((c) => {
      c.canvas.classList.remove('active');
    });

    edges.forEach((edge) => {
      const c = getConnectorByUuids(edge.split(CONNECTORSEPARATESYMBOL));
      if (c&&c.canvas)c.canvas.classList.add('active');
    });
  }

  this.execModel = (data)=> {
    changeStateByNodeId(data.nodes.id,data.nodes.data.nodeState)
    blingConnectors(data.edges)
  };

  this.changeNodeStatus = (nodeId,nodeStatus)=> {
    changeStateByNodeId(nodeId,nodeStatus)
  };
}
