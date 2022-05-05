package com.book.work.domain.vo;

import com.book.common.annotation.Excel;
import com.book.common.core.domain.BaseEntity;

import com.book.common.core.domain.entity.SysDept;
import com.book.common.core.domain.entity.SysUser;
import com.book.work.domain.Book;
import lombok.Data;

import java.util.List;

/**
 * 书籍领取列对象 tb_stock_in
 *
 * @author book
 * @date 2022-05-05
 */
@Data
public class StockInVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 领取记录 */
    private Long              id;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long              deptId;

    @Excel(name = "专业")
    private String            deptName;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long              userId;

    /** 学生ID */
    @Excel(name = "领取学生")
    private String            userName;

    /** 是否领取 */
    @Excel(name = "是否领取")
    private String            stockStatus;

    /** 书籍列表 */
    @Excel(name = "书籍列表")
    private String            books;

    private List<Long>        bookIds;

    private List<Book>        bookList;

    private SysDept           sysDept;

    private SysUser           sysUser;
}