import request from '@/utils/request'

// 查询书籍列表
export function listWork(query) {
  return request({
    url: '/book/work/list',
    method: 'get',
    params: query
  })
}

// 显式分页查询书籍列表
export function workPageList(query) {
    return request({
        url: '/book/work/pageList',
        method: 'get',
        params: query
    })
}


// 查询书籍详细
export function getWork(id) {
  return request({
    url: '/book/work/' + id,
    method: 'get'
  })
}

// 新增书籍
export function addWork(data) {
  return request({
    url: '/book/work',
    method: 'post',
    data: data
  })
}

// 修改书籍
export function updateWork(data) {
  return request({
    url: '/book/work',
    method: 'put',
    data: data
  })
}

// 删除书籍
export function delWork(id) {
  return request({
    url: '/book/work/' + id,
    method: 'delete'
  })
}
