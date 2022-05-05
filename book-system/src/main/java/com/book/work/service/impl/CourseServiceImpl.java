package com.book.work.service.impl;

import java.util.List;
import com.book.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.mapper.CourseMapper;
import com.book.work.domain.Course;
import com.book.work.service.ICourseService;

/**
 * 课程列Service业务层处理
 * 
 * @author book
 * @date 2022-05-05
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService
{
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询课程列
     * 
     * @param id 课程列主键
     * @return 课程列
     */
    @Override
    public Course selectCourseById(Long id)
    {
        return courseMapper.selectCourseById(id);
    }

    /**
     * 查询课程列列表
     * 
     * @param course 课程列
     * @return 课程列
     */
    @Override
    public List<Course> selectCourseList(Course course)
    {
        return courseMapper.selectCourseList(course);
    }

    /**
     * 分页查询课程列列表
     *
     * @param course 课程列
     * @return 课程列
     */
    @Override
    public IPage<Course> selectList(IPage<Course> page, Course course) {
        return courseMapper.selectCoursePage(page, course);
    }


    /**
     * 新增课程列
     * 
     * @param course 课程列
     * @return 结果
     */
    @Override
    public int insertCourse(Course course)
    {
        course.setCreateTime(DateUtils.getNowDate());
        return courseMapper.insertCourse(course);
    }

    /**
     * 修改课程列
     * 
     * @param course 课程列
     * @return 结果
     */
    @Override
    public int updateCourse(Course course)
    {
        course.setUpdateTime(DateUtils.getNowDate());
        return courseMapper.updateCourse(course);
    }

    /**
     * 批量删除课程列
     * 
     * @param ids 需要删除的课程列主键
     * @return 结果
     */
    @Override
    public int deleteCourseByIds(Long[] ids)
    {
        return courseMapper.deleteCourseByIds(ids);
    }

    /**
     * 删除课程列信息
     * 
     * @param id 课程列主键
     * @return 结果
     */
    @Override
    public int deleteCourseById(Long id)
    {
        return courseMapper.deleteCourseById(id);
    }
}
