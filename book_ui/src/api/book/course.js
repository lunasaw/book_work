import request from '@/utils/request'

// 查询课程列列表
export function listCourse(query) {
  return request({
    url: '/book/course/list',
    method: 'get',
    params: query
  })
}

// 查询课程列列表
export function listAllCourse(query) {
  return request({
    url: '/book/course/listAll',
    method: 'get',
    params: query
  })
}

// 显式分页查询课程列列表
export function coursePageList(query) {
    return request({
        url: '/book/course/pageList',
        method: 'get',
        params: query
    })
}


// 查询课程列详细
export function getCourse(id) {
  return request({
    url: '/book/course/' + id,
    method: 'get'
  })
}

// 新增课程列
export function addCourse(data) {
  return request({
    url: '/book/course',
    method: 'post',
    data: data
  })
}

// 修改课程列
export function updateCourse(data) {
  return request({
    url: '/book/course',
    method: 'put',
    data: data
  })
}

// 删除课程列
export function delCourse(id) {
  return request({
    url: '/book/course/' + id,
    method: 'delete'
  })
}
