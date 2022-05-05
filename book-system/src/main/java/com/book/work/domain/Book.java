package com.book.work.domain;

    import java.util.Date;
    import com.fasterxml.jackson.annotation.JsonFormat;
import com.book.common.annotation.Excel;
    import com.book.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 书籍对象 tb_book
 *
 * @author book
 * @date 2022-05-05
 */
@Data
public class Book extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** 书籍ID */
    private Long id;

    /** 书名 */
            @Excel(name = "书名")
    private String name;

    /** 作者 */
            @Excel(name = "作者")
    private String author;

    /** 书籍单价 */
            @Excel(name = "书籍单价")
    private Long price;

    /** 出版社名称 */
            @Excel(name = "出版社名称")
    private String publisher;

    /** 教材出版日期 */
            @JsonFormat(pattern = "yyyy-MM-dd")
            @Excel(name = "教材出版日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 库存数量 */
            @Excel(name = "库存数量")
    private Long stockSum;

    /** 教材封面 */
            @Excel(name = "教材封面")
    private String imageUrl;

    /** 审核状态 */
            @Excel(name = "审核状态")
    private String checkStatus;

    /** 记录状态 */
            @Excel(name = "记录状态")
    private String status;

        }