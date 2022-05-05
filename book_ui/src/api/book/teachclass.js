import request from '@/utils/request'

// 查询班级列表列表
export function listTeachclass(query) {
  return request({
    url: '/book/teachclass/list',
    method: 'get',
    params: query
  })
}

// 查询班级学生列表
export function listTeachClassStuList(classId) {
  return request({
    url: '/book/teachclass/stuList/' + classId,
    method: 'get'
  })
}

// 显式分页查询班级列表列表
export function teachclassPageList(query) {
  return request({
    url: '/book/teachclass/pageList',
    method: 'get',
    params: query
  })
}

// 查询班级列表详细
export function getTeachclass(classId) {
  return request({
    url: '/book/teachclass/' + classId,
    method: 'get'
  })
}

// 新增班级列表
export function addTeachclass(data) {
  return request({
    url: '/book/teachclass',
    method: 'post',
    data: data
  })
}

// 修改班级列表
export function updateTeachclass(data) {
  return request({
    url: '/book/teachclass',
    method: 'put',
    data: data
  })
}

// 删除班级列表
export function delTeachclass(classId) {
  return request({
    url: '/book/teachclass/' + classId,
    method: 'delete'
  })
}
