import request from '@/utils/request'

export function connect(data){
  return request({
    url: '/base/zookeeper/connect',
    method: 'post',
    data: data
  })
}

export function lazyLeaf(data){
  return request({
    url: '/base/zookeeper/lazyLeaf',
    method: 'get',
    params: data
  })
}

export function addZkNode(data){
  return request({
    url: '/base/zookeeper/addZkNode',
    method: 'post',
    data: data
  })
}
