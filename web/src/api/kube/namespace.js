import request from "@/utils/request";

export function listNamespace(data){
  return request({
    url: '/kubernetes/namespace/list',
    method: 'get',
    data: data
  })
}
