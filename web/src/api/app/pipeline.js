import request from '@/utils/request'
// 查询列表
export function page(data) {
  return request({
    url: '/app/pipeline/page',
    method: 'get',
    params: data
  })
}
// 查询详细
export function info(id) {
  return request({
    url: '/app/pipeline/info',
    method: 'get',
    params: { 'id': id }
  })
}
// 新增
export function add(data) {
  return request({
    url: '/app/pipeline/add',
    method: 'post',
    data: data
  })
}
// 修改
export function update(data) {
  return request({
    url: '/app/pipeline/update',
    method: 'put',
    data: data
  })
}
// 删除
export function del(data) {
  return request({
    url: '/app/pipeline/delete',
    method: 'delete',
    data: data
  })
}
// 节点详情
export function nodeInfo(uuid) {
  return request({
    url: '/pipeline/node/info/info',
    method: 'get',
    params: { 'nodeUuid': uuid }
  })
}
// 新增
export function addNodeInfo(data) {
  return request({
    url: '/pipeline/node/info/add',
    method: 'post',
    data: data
  })
}
// 修改
export function updateInfo(data) {
  return request({
    url: '/pipeline/node/info/update',
    method: 'put',
    data: data
  })
}

// 部署
export function deploy(data) {
  return request({
    url: '/app/pipeline/deploy',
    method: 'post',
    data: data
  })
}

// 执行
export function execute(data) {
  return request({
    url: '/app/pipeline/execute',
    method: 'post',
    data: data
  })
}
