import request from '@/utils/request'

// 查询教学计划列表
export function listPlan(query) {
  return request({
    url: '/book/plan/list',
    method: 'get',
    params: query
  })
}

// 显式分页查询教学计划列表
export function planPageList(query) {
    return request({
        url: '/book/plan/pageList',
        method: 'get',
        params: query
    })
}


// 查询教学计划详细
export function getPlan(id) {
  return request({
    url: '/book/plan/' + id,
    method: 'get'
  })
}

// 新增教学计划
export function addPlan(data) {
  return request({
    url: '/book/plan',
    method: 'post',
    data: data
  })
}

// 修改教学计划
export function updatePlan(data) {
  return request({
    url: '/book/plan',
    method: 'put',
    data: data
  })
}

// 删除教学计划
export function delPlan(id) {
  return request({
    url: '/book/plan/' + id,
    method: 'delete'
  })
}
