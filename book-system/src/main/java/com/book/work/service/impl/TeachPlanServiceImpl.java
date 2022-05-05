package com.book.work.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.book.common.core.domain.entity.SysDept;
import com.book.common.utils.DateUtils;
import com.book.system.service.ISysDeptService;
import com.book.work.domain.vo.CourseVO;
import com.book.work.domain.vo.TeachPlanVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.mapper.TeachPlanMapper;
import com.book.work.domain.TeachPlan;
import com.book.work.service.ITeachPlanService;

/**
 * 教学计划Service业务层处理
 * 
 * @author book
 * @date 2022-05-05
 */
@Service
public class TeachPlanServiceImpl extends ServiceImpl<TeachPlanMapper, TeachPlan> implements ITeachPlanService
{
    @Autowired
    private TeachPlanMapper teachPlanMapper;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private ISysDeptService deptService;
    /**
     * 查询教学计划
     * 
     * @param id 教学计划主键
     * @return 教学计划
     */
    @Override
    public TeachPlanVO selectTeachPlanById(Long id)
    {
        TeachPlan teachPlan = teachPlanMapper.selectTeachPlanById(id);
        TeachPlanVO teachPlanVO = new TeachPlanVO();
        BeanUtils.copyProperties(teachPlan,teachPlanVO);
        CourseVO courseVO = courseService.selectCourseById(teachPlan.getCourseId());
        teachPlanVO.setCourseVO(courseVO);

        SysDept sysDept = deptService.selectDeptById(teachPlan.getDeptId());
        teachPlanVO.setSysDept(sysDept);
        return teachPlanVO;
    }

    /**
     * 查询教学计划列表
     * 
     * @param teachPlan 教学计划
     * @return 教学计划
     */
    @Override
    public List<TeachPlanVO> selectTeachPlanList(TeachPlan teachPlan)
    {
        List<TeachPlan> teachPlans = teachPlanMapper.selectTeachPlanList(teachPlan);
        ArrayList<TeachPlanVO> list = Lists.newArrayList();
        for (TeachPlan plan : teachPlans) {
            TeachPlanVO teachPlanVO = new TeachPlanVO();
            BeanUtils.copyProperties(plan,teachPlanVO);
            CourseVO courseVO = courseService.selectCourseById(plan.getCourseId());
            teachPlanVO.setCourseVO(courseVO);

            SysDept sysDept = deptService.selectDeptById(plan.getDeptId());
            teachPlanVO.setSysDept(sysDept);
            list.add(teachPlanVO);
        }
        return list;
    }

    /**
     * 分页查询教学计划列表
     *
     * @param teachPlan 教学计划
     * @return 教学计划
     */
    @Override
    public IPage<TeachPlan> selectList(IPage<TeachPlan> page, TeachPlan teachPlan) {
        return teachPlanMapper.selectTeachPlanPage(page, teachPlan);
    }


    /**
     * 新增教学计划
     * 
     * @param teachPlan 教学计划
     * @return 结果
     */
    @Override
    public int insertTeachPlan(TeachPlan teachPlan)
    {
        teachPlan.setCreateTime(DateUtils.getNowDate());
        return teachPlanMapper.insertTeachPlan(teachPlan);
    }

    /**
     * 修改教学计划
     * 
     * @param teachPlan 教学计划
     * @return 结果
     */
    @Override
    public int updateTeachPlan(TeachPlan teachPlan)
    {
        teachPlan.setUpdateTime(DateUtils.getNowDate());
        return teachPlanMapper.updateTeachPlan(teachPlan);
    }

    /**
     * 批量删除教学计划
     * 
     * @param ids 需要删除的教学计划主键
     * @return 结果
     */
    @Override
    public int deleteTeachPlanByIds(Long[] ids)
    {
        return teachPlanMapper.deleteTeachPlanByIds(ids);
    }

    /**
     * 删除教学计划信息
     * 
     * @param id 教学计划主键
     * @return 结果
     */
    @Override
    public int deleteTeachPlanById(Long id)
    {
        return teachPlanMapper.deleteTeachPlanById(id);
    }
}
