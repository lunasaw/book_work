package com.book.work.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.domain.Course;

/**
 * 课程列Mapper接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 查询课程列
     * 
     * @param id 课程列主键
     * @return 课程列
     */
    public Course selectCourseById(Long id);

    /**
     * 查询课程列列表
     * 
     * @param course 课程列
     * @return 课程列集合
     */
    public List<Course> selectCourseList(Course course);

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
     * 删除课程列
     * 
     * @param id 课程列主键
     * @return 结果
     */
    public int deleteCourseById(Long id);

    /**
     * 批量删除课程列
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseByIds(Long[] ids);

    /**
     * 分页查询课程列列表
     *
     * param page             分页信息
     * @param course 课程列信息
     * @return 课程列集合
     */
    public IPage<Course> selectCoursePage(IPage<Course> page, Course course);

}
