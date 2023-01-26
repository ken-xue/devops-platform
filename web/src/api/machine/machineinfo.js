import request from '@/utils/request'

// 分页查询服务器节点列表
export function Page(data) {
  return request({
    url: '/machine/machineinfo/page',
    method: 'get',
    params: data
  })
}
//查询列表
export function List(data) {
  return request({
    url: '/machine/machineinfo/list',
    method: 'get',
    params: data
  })
}
// 查询User详细
export function Info(id) {
  return request({
    url: '/machine/machineinfo/info',
    method: 'get',
    params: { 'id': id }
  })
}
// 新增User
export function Add(data) {
  return request({
    url: '/machine/machineinfo/add',
    method: 'post',
    data: data
  })
}
// 修改User
export function Update(data) {
  return request({
    url: '/machine/machineinfo/update',
    method: 'put',
    data: data
  })
}
// 删除User
export function Del(data) {
  return request({
    url: '/machine/machineinfo/delete',
    method: 'delete',
    data: data
  })
}