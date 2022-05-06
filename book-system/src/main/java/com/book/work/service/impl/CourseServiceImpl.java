package com.book.work.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.book.common.utils.DateUtils;
import com.book.common.utils.PageUtils;
import com.book.common.utils.StringUtils;
import com.book.work.domain.*;
import com.book.work.domain.vo.CourseVO;
import com.book.work.mapper.StockInMapper;
import com.book.work.mapper.TeachClassMapper;
import com.book.work.mapper.TeachPlanMapper;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.mapper.CourseMapper;
import com.book.work.service.ICourseService;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private TeachPlanMapper teachPlanMapper;

    @Autowired
    private TeachClassMapper teachClassMapper;

    @Autowired
    private StockInMapper stockInMapper;

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
        if(CollectionUtils.isNotEmpty(convert.getBookIds())){
            bookList = bookService.selectListByIds(convert.getBookIds());
        }
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
            List<Long> bookIds = Arrays.stream(e.getBooks().split(",")).filter(StringUtils::isNotEmpty).map(Long::valueOf).collect(Collectors.toList());
            List<Book> books = new ArrayList<>();
            if (!CollectionUtils.isEmpty(bookIds)){
                books = bookService.selectListByIds(bookIds);
            }
            return CourseVO.convert(e, books);
        }).collect(Collectors.toList());
        PageUtils.startPage();
        return collect;
    }

    public List<CourseVO> selectCourseList(Course course, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Course> courses = courseMapper.selectCourseList(course);
        List<CourseVO> collect = courses.stream().map(e -> {
            List<Long> bookIds = Arrays.stream(e.getBooks().split(",")).filter(StringUtils::isNotEmpty).map(Long::valueOf).collect(Collectors.toList());
            List<Book> books = new ArrayList<>();
            if (!CollectionUtils.isEmpty(bookIds)){
                books = bookService.selectListByIds(bookIds);
            }
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
        Course course1 = courseMapper.selectCourseById(course.getId());

        String[] course1books = Optional.ofNullable(course1.getBooks()).map(e -> e.split(",")).orElse(new String[]{});
        List<Long> course1booksLong = Arrays.stream(course1books).filter(StringUtils::isNotEmpty).map(Long::valueOf).collect(Collectors.toList());

        String[] course2books = Optional.ofNullable(course.getBooks()).map(e -> e.split(",")).orElse(new String[]{});
        List<Long> course2booksLong = Arrays.stream(course2books).filter(StringUtils::isNotEmpty).map(Long::valueOf).collect(Collectors.toList());
        for (Long aLong : course2booksLong) {
            if (course1booksLong.contains(aLong)) {
                continue;
            }
            Book book = bookService.selectBookById(aLong);
            if (Objects.nonNull(book)){
                // 领取增加记录
                StockIn stockIn = new StockIn();
                stockIn.setBooks(aLong.toString());

                TeachPlan teachPlan = new TeachPlan();
                teachPlan.setCourseId(course.getId());
                List<TeachPlan> teachPlans = teachPlanMapper.selectTeachPlanList(teachPlan);
                for (TeachPlan plan : teachPlans) {
                    Long deptId = plan.getDeptId();
                    TeachClass teachClass = new TeachClass();
                    teachClass.setDeptId(deptId);
                    List<TeachClass> teachClasses = teachClassMapper.selectTeachClassList(teachClass);
                    for (TeachClass aClass : teachClasses) {

                        stockIn.setDeptId(deptId);
                        stockIn.setUserId(aClass.getUserId());
                        stockIn.setStockStatus("1");
                        stockInMapper.insertStockIn(stockIn);
                    }
                }
            }
        }

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
