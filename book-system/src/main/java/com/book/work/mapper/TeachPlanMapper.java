package com.book.work.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.domain.TeachPlan;

/**
 * 教学计划Mapper接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface TeachPlanMapper extends BaseMapper<TeachPlan> {
    /**
     * 查询教学计划
     * 
     * @param id 教学计划主键
     * @return 教学计划
     */
    public TeachPlan selectTeachPlanById(Long id);

    /**
     * 查询教学计划列表
     * 
     * @param teachPlan 教学计划
     * @return 教学计划集合
     */
    public List<TeachPlan> selectTeachPlanList(TeachPlan teachPlan);

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
     * 删除教学计划
     * 
     * @param id 教学计划主键
     * @return 结果
     */
    public int deleteTeachPlanById(Long id);

    /**
     * 批量删除教学计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachPlanByIds(Long[] ids);

    /**
     * 分页查询教学计划列表
     *
     * param page             分页信息
     * @param teachPlan 教学计划信息
     * @return 教学计划集合
     */
    public IPage<TeachPlan> selectTeachPlanPage(IPage<TeachPlan> page, TeachPlan teachPlan);

}
