package com.book.work.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.book.common.annotation.Excel;
import com.book.common.core.domain.BaseEntity;

import com.book.common.core.domain.entity.SysDept;
import com.book.system.domain.SysRoleDept;
import com.book.work.domain.Course;
import com.book.work.domain.TeachPlan;
import lombok.Data;

/**
 * 教学计划对象 tb_teach_plan
 *
 * @author book
 * @date 2022-05-05
 */
@Data
public class TeachPlanVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    @Excel(name = "计划ID")
    private Long              id;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long              courseId;

    /** 专业名 */
    @Excel(name = "专业名")
    private Long              deptId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer           status;

    /** 是否审核 */
    @Excel(name = "是否审核")
    private String            checkStatus;

    private CourseVO          courseVO;

    private SysDept           sysDept;

    public static TeachPlanVO convert(TeachPlan teachPlan) {
        if (teachPlan == null) {
            return new TeachPlanVO();
        }
        TeachPlanVO teachPlanVO = new TeachPlanVO();
        teachPlanVO.setId(teachPlan.getId());
        teachPlanVO.setCourseId(teachPlan.getCourseId());
        teachPlanVO.setDeptId(teachPlan.getDeptId());
        teachPlanVO.setStatus(teachPlan.getStatus());
        teachPlanVO.setCheckStatus(teachPlan.getCheckStatus());
        teachPlanVO.setSearchValue(teachPlan.getSearchValue());
        teachPlanVO.setCreateBy(teachPlan.getCreateBy());
        teachPlanVO.setCreateTime(teachPlan.getCreateTime());
        teachPlanVO.setUpdateBy(teachPlan.getUpdateBy());
        teachPlanVO.setUpdateTime(teachPlan.getUpdateTime());
        teachPlanVO.setRemark(teachPlan.getRemark());
        teachPlanVO.setParams(teachPlan.getParams());
        return teachPlanVO;
    }
}