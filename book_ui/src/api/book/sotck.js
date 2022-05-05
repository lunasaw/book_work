import request from '@/utils/request'

// 查询书籍领取列列表
export function listSotck(query) {
  return request({
    url: '/book/sotck/list',
    method: 'get',
    params: query
  })
}

// 显式分页查询书籍领取列列表
export function sotckPageList(query) {
    return request({
        url: '/book/sotck/pageList',
        method: 'get',
        params: query
    })
}


// 查询书籍领取列详细
export function getSotck(id) {
  return request({
    url: '/book/sotck/' + id,
    method: 'get'
  })
}

// 新增书籍领取列
export function addSotck(data) {
  return request({
    url: '/book/sotck',
    method: 'post',
    data: data
  })
}

// 修改书籍领取列
export function updateSotck(data) {
  return request({
    url: '/book/sotck',
    method: 'put',
    data: data
  })
}

// 删除书籍领取列
export function delSotck(id) {
  return request({
    url: '/book/sotck/' + id,
    method: 'delete'
  })
}
