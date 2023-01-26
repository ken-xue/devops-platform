import request from '@/utils/request'

export function getRole(data) {
  return request({
    url: '/sys/role/info',
    method: 'get',
    params: data
  })
}

export function pageRole(data) {
  return request({
    url: '/sys/role/page',
    method: 'get',
    params: data
  })
}

export function listRole(data) {
  return request({
    url: '/sys/role/list',
    method: 'get',
    params: data
  })
}

export function addRole(data) {
  return request({
    url: '/sys/role/add',
    method: 'post',
    data
  })
}

export function updateRole(data) {
  return request({
    url: '/sys/role/update',
    method: 'put',
    data
  })
}

export function deleteRole(data) {
  return request({
    url: '/sys/role/delete',
    method: 'delete',
    data: data
  })
}
