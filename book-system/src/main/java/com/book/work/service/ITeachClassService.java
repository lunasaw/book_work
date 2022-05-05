package com.book.work.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.domain.TeachClass;
import com.book.work.domain.vo.TeachClassVO;

/**
 * 班级列表Service接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface ITeachClassService  extends IService<TeachClass>
{
    /**
     * 查询班级列表
     * 
     * @param classId 班级列表主键
     * @return 班级列表
     */
    public TeachClassVO selectTeachClassByClassId(Long classId);

    /**
     * 查询班级列表列表
     * 
     * @param teachClass 班级列表
     * @return 班级列表集合
     */
    public List<TeachClassVO> selectTeachClassList(TeachClass teachClass);

    /**
     * 分页查询班级列表列表
     *
     * @param teachClass 班级列表
     * @return 班级列表集合
     */
    public IPage<TeachClass> selectList(IPage<TeachClass> page, TeachClass teachClass);


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
     * 批量删除班级列表
     * 
     * @param classIds 需要删除的班级列表主键集合
     * @return 结果
     */
    public int deleteTeachClassByClassIds(Long[] classIds);

    /**
     * 删除班级列表信息
     * 
     * @param classId 班级列表主键
     * @return 结果
     */
    public int deleteTeachClassByClassId(Long classId);
}
