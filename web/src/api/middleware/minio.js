import request from '@/utils/request'
// 查询列表
export function page(data) {
    return request({
        url: 'middleware/minio/page',
        method: 'get',
        params: data
    })
}
export function list(data) {
    return request({
        url: 'middleware/minio/list',
        method: 'get',
        params: data
    })
}
// 查询详细
export function info(id) {
    return request({
        url: 'middleware/minio/info',
        method: 'get',
        params: { 'id': id }
    })
}
// 新增
export function add(data) {
    return request({
        url: 'middleware/minio/add',
        method: 'post',
        data: data
    })
}
// 修改
export function update(data) {
    return request({
        url: 'middleware/minio/update',
        method: 'put',
        data: data
    })
}
// 删除
export function del(data) {
    return request({
        url: 'middleware/minio/delete',
        method: 'delete',
        data: data
    })
}
