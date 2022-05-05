package com.book.work.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.domain.TeachPlan;
import com.book.work.domain.vo.TeachPlanVO;

/**
 * 教学计划Service接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface ITeachPlanService  extends IService<TeachPlan>
{
    /**
     * 查询教学计划
     * 
     * @param id 教学计划主键
     * @return 教学计划
     */
    public TeachPlanVO selectTeachPlanById(Long id);

    /**
     * 查询教学计划列表
     * 
     * @param teachPlan 教学计划
     * @return 教学计划集合
     */
    public List<TeachPlanVO> selectTeachPlanList(TeachPlan teachPlan);

    /**
     * 分页查询教学计划列表
     *
     * @param teachPlan 教学计划
     * @return 教学计划集合
     */
    public IPage<TeachPlan> selectList(IPage<TeachPlan> page, TeachPlan teachPlan);


    /**
     * 新增教学计划
     * 
     * @param teachPlan 教学计划
     * @return 结果
     */
    public int insertTeachPlan(TeachPlan teachPlan);

    /**
     * 修改教学计划
     * 
     * @param teachPlan 教学计划
     * @return 结果
     */
    public int updateTeachPlan(TeachPlan teachPlan);

    /**
     * 批量删除教学计划
     * 
     * @param ids 需要删除的教学计划主键集合
     * @return 结果
     */
    public int deleteTeachPlanByIds(Long[] ids);

    /**
     * 删除教学计划信息
     * 
     * @param id 教学计划主键
     * @return 结果
     */
    public int deleteTeachPlanById(Long id);
}
