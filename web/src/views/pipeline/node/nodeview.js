import request from '@/utils/request'
// 查询详细
export function get(loggerUuid,nodeUuid) {
  return request({
    url: 'pipeline/node/execute/logger/get',
    method: 'get',
    params: {
      'loggerUuid': loggerUuid,
      'nodeUuid': nodeUuid
    }
  })
}
