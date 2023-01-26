import request from '@/utils/request'
// 查询列表
export function page(data) {
  return request({
    url: 'pipeline/execute/logger/page',
    method: 'get',
    params: data
  })
}
// 查询详细
export function info(id) {
  return request({
    url: 'pipeline/execute/logger/info',
    method: 'get',
    params: { 'id': id }
  })
}
// 新增
export function add(data) {
  return request({
    url: 'pipeline/execute/logger/add',
    method: 'post',
    data: data
  })
}
// 修改
export function update(data) {
  return request({
    url: 'pipeline/execute/logger/update',
    method: 'put',
    data: data
  })
}
// 删除
export function del(data) {
  return request({
    url: 'pipeline/execute/logger/delete',
    method: 'delete',
    data: data
  })
}
