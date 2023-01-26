import request from '@/utils/request'

// 分页查询列表
export function pageTable(data) {
  return request({
    url: '/dev/code/page',
    method: 'get',
    params: data
  })
}
// 查询列表
export function listTable(data) {
  return request({
    url: '/dev/code/list',
    method: 'get',
    params: data
  })
}
// 查询User详细
export function getTable(id) {
  return request({
    url: '/dev/code/info',
    method: 'get',
    params: { 'id': id }
  })
}
// 生成代码
export function create(data) {
  return request({
    url: '/dev/code/create',
    method: 'post',
    data: data
  })
}
// DeleteRecent
export function DeleteRecent() {
  return request({
    url: '/dev/code/delete',
    method: 'delete'
  })
}
// 生成代码
export function download(data) {
  return request({
    url: '/dev/code/download',
    method: 'post',
    data: data,
    responseType: 'blob'
  })
}
