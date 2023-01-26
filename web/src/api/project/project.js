import request from '@/utils/request'

// 查询列表
export function page(data) {
  return request({
    url: '/project/page',
    method: 'get',
    params: data
  })
}
export function list(data) {
  return request({
    url: '/project/list',
    method: 'get',
    data: data
  })
}
// 查询详细
export function info(id) {
  return request({
    url: '/project/info',
    method: 'get',
    params: { 'id': id }
  })
}
// 新增
export function add(data) {
  return request({
    url: '/project/add',
    method: 'post',
    data: data
  })
}
// 修改
export function update(data) {
  return request({
    url: '/project/update',
    method: 'put',
    data: data
  })
}
// 删除User
export function del(data) {
  return request({
    url: '/project/delete',
    method: 'delete',
    data: data
  })
}
