package com.book.work.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.book.common.annotation.Excel;
import com.book.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 班级列表对象 tb_teach_class
 *
 * @author book
 * @date 2022-05-05
 */
@Data
@TableName("tb_teach_class")
public class TeachClass extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 班级ID */
    @Excel(name = "班级ID")
    private Long              classId;

    /** 班级名 */
    @Excel(name = "班级名")
    private String            name;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long              deptId;

    /** 班级负责人的用户ID */
    @Excel(name = "班级负责人的用户ID")
    private Long              userId;

    /** 0表示未删除，1表示已删除 */
    @Excel(name = "0表示未删除，1表示已删除")
    private String            status;

}