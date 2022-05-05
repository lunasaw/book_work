package com.book.work.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.domain.TeachClass;

/**
 * 班级列表Mapper接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface TeachClassMapper extends BaseMapper<TeachClass> {
    /**
     * 查询班级列表
     * 
     * @param classId 班级列表主键
     * @return 班级列表
     */
    public TeachClass selectTeachClassByClassId(Long classId);

    /**
     * 查询班级列表列表
     * 
     * @param teachClass 班级列表
     * @return 班级列表集合
     */
    public List<TeachClass> selectTeachClassList(TeachClass teachClass);

    /**
     * 新增班级列表
     * 
     * @param teachClass 班级列表
     * @return 结果
     */
    public int insertTeachClass(TeachClass teachClass);

    /**
     * 修改班级列表
     * 
     * @param teachClass 班级列表
     * @return 结果
     */
    public int updateTeachClass(TeachClass teachClass);

    /**
     * 删除班级列表
     * 
     * @param classId 班级列表主键
     * @return 结果
     */
    public int deleteTeachClassByClassId(Long classId);

    /**
     * 批量删除班级列表
     * 
     * @param classIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeachClassByClassIds(Long[] classIds);

    /**
     * 分页查询班级列表列表
     *
     * param page             分页信息
     * @param teachClass 班级列表信息
     * @return 班级列表集合
     */
    public IPage<TeachClass> selectTeachClassPage(IPage<TeachClass> page, TeachClass teachClass);

}
