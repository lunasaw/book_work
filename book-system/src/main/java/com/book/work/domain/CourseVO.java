package com.book.work.domain;

import com.book.common.annotation.Excel;
import com.book.common.core.domain.BaseEntity;
import com.book.common.utils.StringUtils;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 课程列对象 tb_course
 *
 * @author book
 * @date 2022-05-05
 */
@Data
public class CourseVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @Excel(name = "课程ID")
    private Long              id;

    /**
     * 课程名称
     */
    @Excel(name = "课程名称")
    private String            name;

    /**
     * 课时
     */
    @Excel(name = "课时")
    private Long              hour;

    /**
     * 学期
     */
    @Excel(name = "学期")
    private String            semester;

    /**
     * 学年
     */
    @Excel(name = "学年")
    private String            year;

    /**
     * 书籍列表
     */
    @Excel(name = "书籍列表")
    private String            books;

    /**
     * 书籍列表
     */
    private List<Long>        bookIds;

    /**
     * 书籍列表
     */
    private List<Book>        bookList;

    public static CourseVO convert(Course course, List<Book> bookList) {
        if (course == null) {
            return null;
        }
        CourseVO courseVO = new CourseVO();
        courseVO.setId(course.getId());
        courseVO.setName(course.getName());
        courseVO.setHour(course.getHour());
        courseVO.setSemester(course.getSemester());
        courseVO.setYear(course.getYear());
        courseVO.setBookList(bookList);
        if (StringUtils.isNotEmpty(course.getBooks())){
            List<Long> bookIds = Arrays.stream(course.getBooks().split(",")).map(Long::valueOf)
                    .collect(Collectors.toList());
            courseVO.setBookIds(bookIds);
        }
        courseVO.setSearchValue(course.getSearchValue());
        courseVO.setCreateBy(course.getCreateBy());
        courseVO.setCreateTime(course.getCreateTime());
        courseVO.setUpdateBy(course.getUpdateBy());
        courseVO.setUpdateTime(course.getUpdateTime());
        courseVO.setRemark(course.getRemark());
        courseVO.setParams(course.getParams());
        return courseVO;
    }
}