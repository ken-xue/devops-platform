import request from '@/utils/request'

// 分页查询{{.Comment}}列表
export function Page(data) {
  return request({
    url: '/{{.Module}}/{{.AllLowName}}/page',
    method: 'get',
    params: data
  })
}
//查询列表
export function List(data) {
  return request({
    url: '/{{.Module}}/{{.AllLowName}}/list',
    method: 'get',
    params: data
  })
}
// 查询User详细
export function Info(id) {
  return request({
    url: '/{{.Module}}/{{.AllLowName}}/info',
    method: 'get',
    params: { 'id': id }
  })
}
// 新增User
export function Add(data) {
  return request({
    url: '/{{.Module}}/{{.AllLowName}}/add',
    method: 'post',
    data: data
  })
}
// 修改User
export function Update(data) {
  return request({
    url: '/{{.Module}}/{{.AllLowName}}/update',
    method: 'put',
    data: data
  })
}
// 删除User
export function Del(data) {
  return request({
    url: '/{{.Module}}/{{.AllLowName}}/delete',
    method: 'delete',
    data: data
  })
}