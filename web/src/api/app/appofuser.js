import request from '@/utils/request'

// 查询User列表
export function pageAppInfo(data) {
  return request({
    url: '/app/info/page',
    method: 'get',
    data: data
  })
}
// 查询详细
export function getAppInfo(id) {
  return request({
    url: '/app/info/info',
    method: 'get',
    params: { 'id': id }
  })
}
// 新增
export function addAppInfo(data) {
  return request({
    url: '/app/info/add',
    method: 'post',
    data: data
  })
}
// 修改
export function updateAppInfo(data) {
  return request({
    url: '/app/info/update',
    method: 'put',
    data: data
  })
}
// 删除User
export function delAppInfo(data) {
  return request({
    url: '/app/info/delete',
    method: 'delete',
    data: data
  })
}
