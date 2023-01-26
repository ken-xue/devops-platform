import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/sys/user/self',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 分页查询User列表
export function pageUser(data) {
  return request({
    url: '/sys/user/page',
    method: 'get',
    params: data
  })
}
//查询列表
export function list(data) {
  return request({
    url: '/sys/user/list',
    method: 'get',
    params: data
  })
}
// 查询User详细
export function getUser(id) {
  return request({
    url: '/sys/user/info',
    method: 'get',
    params: { 'id': id }
  })
}
// 新增User
export function addUser(data) {
  return request({
    url: '/sys/user/add',
    method: 'post',
    data: data
  })
}
// 修改User
export function updateUser(data) {
  return request({
    url: '/sys/user/update',
    method: 'put',
    data: data
  })
}
// 删除User
export function delUser(data) {
  return request({
    url: '/sys/user/delete',
    method: 'delete',
    data: data
  })
}
/**
 * 获取授权相关数据菜单
 * @returns {AxiosPromise}
 */
export function getAuth() {
  return request({
    url: '/sys/menu/auth',
    method: 'get'
  })
}

export function captcha() {
  return request({
    url: '/captcha',
    method: 'get'
  })
}

