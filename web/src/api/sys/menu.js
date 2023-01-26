import request from '@/utils/request'
import {nestedQuery} from "../../utils";

// 查询menu列表
export function pageMenu(data) {
  // nestedQuery(data)
  return request({
    url: '/sys/menu/page',
    method: 'get',
    params: data
  })
}
// 查询menu列表
export function listMenu(data) {
  return request({
    url: '/sys/menu/list',
    method: 'get',
    params: data
  })
}
export function getMenuSelect(data) {
  return request({
    url: '/sys/menu/list',
    method: 'get',
    params: data
  })
}
// 查询menu详细
export function getMenu(data) {
  return request({
    url: '/sys/menu/info',
    method: 'get',
    params: data
  })
}
// 新增menu
export function addMenu(data) {
  return request({
    url: '/sys/menu/add',
    method: 'post',
    data: {'menuDTO': data}
  })
}
// 修改menu
export function updateMenu(data) {
  return request({
    url: '/sys/menu/update',
    method: 'put',
    data: {'menuDTO': data}
  })
}
// 删除menu
export function delMenu(data) {
  return request({
    url: '/sys/menu/delete',
    method: 'delete',
    data: data
  })
}
