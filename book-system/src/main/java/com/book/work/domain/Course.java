package com.book.work.domain;

import com.book.common.annotation.Excel;
    import com.book.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 课程列对象 tb_course
 *
 * @author book
 * @date 2022-05-05
 */
@Data
public class Course extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** 课程ID */
            @Excel(name = "课程ID")
    private Long id;

    /** 课程名称 */
            @Excel(name = "课程名称")
    private String name;

    /** 课时 */
            @Excel(name = "课时")
    private Long hour;

    /** 学期 */
            @Excel(name = "学期")
    private String semester;

    /** 学年 */
            @Excel(name = "学年")
    private String year;

    /** 书籍列表 */
            @Excel(name = "书籍列表")
    private String books;

        }