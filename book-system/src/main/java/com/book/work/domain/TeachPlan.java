package com.book.work.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.book.common.annotation.Excel;
import com.book.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 教学计划对象 tb_teach_plan
 *
 * @author book
 * @date 2022-05-05
 */
@Data
@TableName("tb_teach_plan")
public class TeachPlan extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    @Excel(name = "计划ID")
    private Long              id;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long              courseId;

    /** 专业名 */
    @Excel(name = "专业名")
    @TableField(exist = false)
    private String            deptName;

    /** 专业名 */
    private Long              deptId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer           status;

    /** 是否审核 */
    @Excel(name = "是否审核")
    private String            checkStatus;
}