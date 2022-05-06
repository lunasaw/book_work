package com.book.work.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.book.common.annotation.Excel;
import com.book.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 书籍领取列对象 tb_stock_in
 *
 * @author book
 * @date 2022-05-05
 */
@Data
public class StockIn extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 领取记录 */
    private Long              id;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long              deptId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long              userId;

    /** 是否领取 */
    @Excel(name = "是否领取")
    private String            stockStatus;

    /** 书籍列表 */
    @Excel(name = "书籍列表")
    private String            books;

    @TableField(exist = false)
    private List<Long>        bookIds;

}