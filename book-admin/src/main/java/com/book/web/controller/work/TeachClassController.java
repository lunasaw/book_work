package com.book.web.controller.work;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.common.core.domain.entity.SysUser;
import com.book.system.service.ISysUserService;
import com.book.work.domain.vo.TeachClassVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.book.common.annotation.Log;
import com.book.common.core.controller.BaseController;
import com.book.common.core.domain.AjaxResult;
import com.book.common.enums.BusinessType;
import com.book.work.domain.TeachClass;
import com.book.work.service.ITeachClassService;
import com.book.common.utils.poi.ExcelUtil;
import com.book.common.core.page.TableDataInfo;

/**
 * 班级列表Controller
 * 
 * @author book
 * @date 2022-05-05
 */
@RestController
@RequestMapping("/book/teachclass")
@Api(tags = "班级列表")
public class TeachClassController extends BaseController {
    @Autowired
    private ITeachClassService teachClassService;

    @Autowired
    private ISysUserService    userService;

    /**
     * 查询班级列表列表
     */
    @ApiOperation(value = "查询班级列表列表")
    @PreAuthorize("@ss.hasPermi('book:teachclass:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeachClass teachClass) {
        startPage();
        List<TeachClassVO> list = teachClassService.selectTeachClassList(teachClass);

        TableDataInfo dataTable = getDataTable(list);
        long count = teachClassService.count(new QueryWrapper<>(teachClass));
        dataTable.setTotal(count);
        return dataTable;
    }

    /**
     * 查询班级学生列表
     */
    @ApiOperation(value = "查询班级学生列表")
    @PreAuthorize("@ss.hasPermi('book:teachclass:list')")
    @GetMapping("/stuList/{classId}")
    public List<SysUser> stuList(@PathVariable(value = "classId") Long classId) {
        SysUser sysUser = new SysUser();
        sysUser.setClassId(classId);
        List<SysUser> sysUsers = userService.selectUserList(sysUser);
        return sysUsers;
    }

    /**
     * 分页查询班级列表列表
     */
    @ApiOperation(value = "分页查询班级列表列表")
    @PreAuthorize("@ss.hasPermi('book:teachclass:list')")
    @GetMapping("/pageList")
    public TableDataInfo pageList(TeachClass teachClass) {
        Page<TeachClass> page = startPageList();
        IPage<TeachClass> list = teachClassService.selectList(page, teachClass);
        return getDataTable(list.getRecords());
    }

    /**
     * 导出班级列表列表
     */
    @ApiOperation(value = "导出班级列表列表")
    @PreAuthorize("@ss.hasPermi('book:teachclass:export')")
    @Log(title = "班级列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeachClass teachClass) {
        List<TeachClassVO> list = teachClassService.selectTeachClassList(teachClass);
        ExcelUtil<TeachClassVO> util = new ExcelUtil<TeachClassVO>(TeachClassVO.class);
        util.exportExcel(response, list, "班级列表数据");
    }

    /**
     * 获取班级列表详细信息
     */
    @ApiOperation(value = "获取班级列表详细信息")
    @PreAuthorize("@ss.hasPermi('book:teachclass:query')")
    @GetMapping(value = "/{classId}")
    public AjaxResult getInfo(@PathVariable("classId") Long classId) {
        return AjaxResult.success(teachClassService.selectTeachClassByClassId(classId));
    }

    /**
     * 新增班级列表
     */
    @ApiOperation(value = "新增班级列表")
    @PreAuthorize("@ss.hasPermi('book:teachclass:add')")
    @Log(title = "班级列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeachClass teachClass) {
        return toAjax(teachClassService.insertTeachClass(teachClass));
    }

    /**
     * 修改班级列表
     */
    @ApiOperation(value = "修改班级列表")
    @PreAuthorize("@ss.hasPermi('book:teachclass:edit')")
    @Log(title = "班级列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeachClass teachClass) {
        return toAjax(teachClassService.updateTeachClass(teachClass));
    }

    /**
     * 删除班级列表
     */
    @ApiOperation(value = "删除班级列表")
    @PreAuthorize("@ss.hasPermi('book:teachclass:remove')")
    @Log(title = "班级列表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{classIds}")
    public AjaxResult remove(@PathVariable Long[] classIds) {
        return toAjax(teachClassService.deleteTeachClassByClassIds(classIds));
    }
}
