import request from '@/utils/request'
// 查询日志列表
export function pageLog(data) {
  return request({
    url: '/sys/log/page',
    method: 'get',
    data: data
  })
}
// 查询详细
export function getLog(userId) {
  return request({
    url: '/sys/log/info',
    method: 'get',
    params: { 'userId': userId }
  })
}
// 删除
export function delLog(data) {
  return request({
    url: '/sys/log/delete',
    method: 'delete',
    data: data
  })
}
