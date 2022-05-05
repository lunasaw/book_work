package com.book.work.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.book.common.utils.DateUtils;
import com.book.work.domain.Book;
import com.book.work.domain.CourseVO;
import com.book.work.mapper.BookMapper;
import net.sf.jsqlparser.statement.select.Join;
import org.apache.commons.compress.utils.Lists;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询课程列
     *
     * @param id 课程列主键
     * @return 课程列
     */
    @Override
    public CourseVO selectCourseById(Long id) {
        Course course = courseMapper.selectCourseById(id);
        List<Book> bookList = Lists.newArrayList();
        CourseVO convert = CourseVO.convert(course, bookList);
        bookList = bookService.selectBookList(new Book());
        convert.setBookList(bookList);
        return convert;
    }

    @Autowired
    private BookServiceImpl bookService;

    /**
     * 查询课程列列表
     *
     * @param course 课程列
     * @return 课程列
     */
    @Override
    public List<CourseVO> selectCourseList(Course course) {
        List<Course> courses = courseMapper.selectCourseList(course);
        List<CourseVO> collect = courses.stream().map(e -> {
            List<Long> bookIds = Arrays.stream(e.getBooks().split(",")).map(Long::valueOf).collect(Collectors.toList());
            List<Book> books = bookService.selectListByIds(bookIds);
            return CourseVO.convert(e, books);
        }).collect(Collectors.toList());

        return collect;
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
    public int insertCourse(Course course) {
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
    public int updateCourse(Course course) {
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
    public int deleteCourseByIds(Long[] ids) {
        return courseMapper.deleteCourseByIds(ids);
    }

    /**
     * 删除课程列信息
     *
     * @param id 课程列主键
     * @return 结果
     */
    @Override
    public int deleteCourseById(Long id) {
        return courseMapper.deleteCourseById(id);
    }
}
