import request from '@/utils/request'
// 查询列表
export function page(data) {
    return request({
        url: 'middleware/zookeeper/page',
        method: 'get',
        params: data
    })
}
export function list(data) {
    return request({
        url: 'middleware/zookeeper/list',
        method: 'get',
        params: data
    })
}
// 查询详细
export function info(id) {
    return request({
        url: 'middleware/zookeeper/info',
        method: 'get',
        params: { 'id': id }
    })
}
// 新增
export function add(data) {
    return request({
        url: 'middleware/zookeeper/add',
        method: 'post',
        data: data
    })
}
// 修改
export function update(data) {
    return request({
        url: 'middleware/zookeeper/update',
        method: 'put',
        data: data
    })
}
// 删除
export function del(data) {
    return request({
        url: 'middleware/zookeeper/delete',
        method: 'delete',
        data: data
    })
}

export function testConnect(data){
  return request({
    url: 'middleware/zookeeper/test/conn',
    method: 'post',
    data: data
  })
}

export function dataView(data){
  return request({
    url: 'middleware/zookeeper/tree',
    method: 'post',
    data: data
  })
}

export function lazyLeaf(data){
  return request({
    url: '/middleware/zookeeper/lazyLeaf',
    method: 'post',
    data: data
  })
}

export function addZkNode(data){
  return request({
    url: '/middleware/zookeeper/addZkNode',
    method: 'post',
    data: data
  })
}

export function loadNodeDataInfo(data){
  return request({
    url: '/middleware/zookeeper/zkNodeDetail',
    method: 'post',
    data: data
  })
}

export function deleteZkNode(data){
  return request({
    url: '/middleware/zookeeper/deleteZkNode',
    method: 'post',
    data: data
  })
}
