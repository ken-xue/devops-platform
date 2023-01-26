import request from '@/utils/request'
// 查询列表
export function list(data) {
  return request({
    url: '/pipeline/node/list',
    method: 'get',
    params: data
  })
}
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
