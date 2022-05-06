package com.book.work.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.book.common.core.domain.entity.SysDept;
import com.book.common.core.domain.entity.SysUser;
import com.book.common.utils.DateUtils;
import com.book.common.utils.StringUtils;
import com.book.system.service.ISysDeptService;
import com.book.system.service.ISysUserService;
import com.book.work.domain.vo.TeachClassVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.mapper.TeachClassMapper;
import com.book.work.domain.TeachClass;
import com.book.work.service.ITeachClassService;

/**
 * 班级列表Service业务层处理
 * 
 * @author book
 * @date 2022-05-05
 */
@Service
public class TeachClassServiceImpl extends ServiceImpl<TeachClassMapper, TeachClass> implements ITeachClassService {
    @Autowired
    private TeachClassMapper teachClassMapper;

    @Autowired
    private ISysUserService  userService;

    @Autowired
    private ISysDeptService  deptService;

    /**
     * 查询班级列表
     * 
     * @param classId 班级列表主键
     * @return 班级列表
     */
    @Override
    public TeachClassVO selectTeachClassByClassId(Long classId) {
        TeachClassVO teachClassVO = new TeachClassVO();
        TeachClass teachClass = teachClassMapper.selectTeachClassByClassId(classId);
        BeanUtils.copyProperties(teachClass, teachClassVO);
        SysUser sysUser = userService.selectUserById(teachClass.getUserId());
        teachClassVO.setSysUser(sysUser);
        teachClassVO.setUserName(StringUtils.isEmpty(sysUser.getNickName()) ? sysUser.getUserName() : sysUser.getNickName());

        SysDept sysDept = deptService.selectDeptById(teachClass.getDeptId());
        teachClassVO.setDeptName(sysDept.getDeptName());
        teachClassVO.setSysDept(sysDept);

        teachClassVO.setPayCost(new BigDecimal(teachClass.getPayCost()));
        teachClassVO.setClassSize(teachClass.getClassSize());
        return teachClassVO;
    }

    /**
     * 查询班级列表列表
     * 
     * @param teachClass 班级列表
     * @return 班级列表
     */
    @Override
    public List<TeachClassVO> selectTeachClassList(TeachClass teachClass) {
        ArrayList<TeachClassVO> list = Lists.newArrayList();
        List<TeachClass> teachClasses = teachClassMapper.selectTeachClassList(teachClass);
        for (TeachClass clazz : teachClasses) {
            TeachClassVO teachClassVO = new TeachClassVO();
            BeanUtils.copyProperties(clazz, teachClassVO);
            SysUser sysUser = userService.selectUserById(clazz.getUserId());
            teachClassVO.setSysUser(sysUser);
            teachClassVO.setUserName(StringUtils.isEmpty(sysUser.getNickName()) ? sysUser.getUserName() : sysUser.getNickName());

            SysDept sysDept = deptService.selectDeptById(clazz.getDeptId());
            teachClassVO.setDeptName(sysDept.getDeptName());
            teachClassVO.setSysDept(sysDept);

            teachClassVO.setPayCost(new BigDecimal(clazz.getPayCost()));
            teachClassVO.setClassSize(clazz.getClassSize());
            list.add(teachClassVO);
        }
        return list;
    }

    /**
     * 分页查询班级列表列表
     *
     * @param teachClass 班级列表
     * @return 班级列表
     */
    @Override
    public IPage<TeachClass> selectList(IPage<TeachClass> page, TeachClass teachClass) {
        return teachClassMapper.selectTeachClassPage(page, teachClass);
    }

    /**
     * 新增班级列表
     * 
     * @param teachClass 班级列表
     * @return 结果
     */
    @Override
    public int insertTeachClass(TeachClass teachClass) {
        teachClass.setCreateTime(DateUtils.getNowDate());
        return teachClassMapper.insertTeachClass(teachClass);
    }

    /**
     * 修改班级列表
     * 
     * @param teachClass 班级列表
     * @return 结果
     */
    @Override
    public int updateTeachClass(TeachClass teachClass) {
        teachClass.setUpdateTime(DateUtils.getNowDate());
        return teachClassMapper.updateTeachClass(teachClass);
    }

    /**
     * 批量删除班级列表
     * 
     * @param classIds 需要删除的班级列表主键
     * @return 结果
     */
    @Override
    public int deleteTeachClassByClassIds(Long[] classIds) {
        return teachClassMapper.deleteTeachClassByClassIds(classIds);
    }

    /**
     * 删除班级列表信息
     * 
     * @param classId 班级列表主键
     * @return 结果
     */
    @Override
    public int deleteTeachClassByClassId(Long classId) {
        return teachClassMapper.deleteTeachClassByClassId(classId);
    }
}
