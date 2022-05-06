package com.book.work.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.domain.Course;
import com.book.work.domain.vo.CourseVO;

/**
 * 课程列Service接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface ICourseService  extends IService<Course>
{
    /**
     * 查询课程列
     * 
     * @param id 课程列主键
     * @return 课程列
     */
    public CourseVO selectCourseById(Long id);



    /**
     * 查询课程列列表
     * 
     * @param course 课程列
     * @return 课程列集合
     */
    public List<CourseVO> selectCourseList(Course course);



    /**
     * 查询课程列列表
     *
     * @param course 课程列
     * @return 课程列集合
     */
    public List<CourseVO> selectCourseList(Course course, Integer pageNum, Integer pageSize);

    /**
     * 分页查询课程列列表
     *
     * @param course 课程列
     * @return 课程列集合
     */
    public IPage<Course> selectList(IPage<Course> page, Course course);


    /**
     * 新增课程列
     * 
     * @param course 课程列
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改课程列
     * 
     * @param course 课程列
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 批量删除课程列
     * 
     * @param ids 需要删除的课程列主键集合
     * @return 结果
     */
    public int deleteCourseByIds(Long[] ids);

    /**
     * 删除课程列信息
     * 
     * @param id 课程列主键
     * @return 结果
     */
    public int deleteCourseById(Long id);
}
