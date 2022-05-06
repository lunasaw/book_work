package com.book.web.controller.work;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.work.domain.vo.TeachPlanVO;
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
import com.book.work.domain.TeachPlan;
import com.book.work.service.ITeachPlanService;
import com.book.common.utils.poi.ExcelUtil;
import com.book.common.core.page.TableDataInfo;

/**
 * 教学计划Controller
 * 
 * @author book
 * @date 2022-05-05
 */
@RestController
@RequestMapping("/book/plan")
@Api(tags = "教学计划")
public class TeachPlanController extends BaseController
{
    @Autowired
    private ITeachPlanService teachPlanService;

    /**
     * 查询教学计划列表
     */
    @ApiOperation(value = "查询教学计划列表")
    @PreAuthorize("@ss.hasPermi('book:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeachPlan teachPlan)
    {
        startPage();
        List<TeachPlanVO> list = teachPlanService.selectTeachPlanList(teachPlan);

        TableDataInfo dataTable = getDataTable(list);
        long count = teachPlanService.count(new QueryWrapper<>(teachPlan));
        dataTable.setTotal(count);
        return dataTable;
    }

    /**
     * 分页查询教学计划列表
     */
    @ApiOperation(value = "分页查询教学计划列表")
    @PreAuthorize("@ss.hasPermi('book:plan:list')")
    @GetMapping("/pageList")
    public TableDataInfo pageList(TeachPlan teachPlan) {
        Page<TeachPlan> page = startPageList();
        IPage<TeachPlan> list = teachPlanService.selectList(page, teachPlan);
        return getDataTable(list.getRecords());
    }

    /**
     * 导出教学计划列表
     */
    @ApiOperation(value = "导出教学计划列表")
    @PreAuthorize("@ss.hasPermi('book:plan:export')")
    @Log(title = "教学计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeachPlan teachPlan)
    {
        List<TeachPlanVO> list = teachPlanService.selectTeachPlanList(teachPlan);
        ExcelUtil<TeachPlanVO> util = new ExcelUtil<TeachPlanVO>(TeachPlanVO.class);
        util.exportExcel(response, list, "教学计划数据");
    }

    /**
     * 获取教学计划详细信息
     */
    @ApiOperation(value = "获取教学计划详细信息")
    @PreAuthorize("@ss.hasPermi('book:plan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teachPlanService.selectTeachPlanById(id));
    }

    /**
     * 新增教学计划
     */
    @ApiOperation(value = "新增教学计划")
    @PreAuthorize("@ss.hasPermi('book:plan:add')")
    @Log(title = "教学计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeachPlan teachPlan)
    {
        return toAjax(teachPlanService.insertTeachPlan(teachPlan));
    }

    /**
     * 修改教学计划
     */
    @ApiOperation(value = "修改教学计划")
    @PreAuthorize("@ss.hasPermi('book:plan:edit')")
    @Log(title = "教学计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeachPlan teachPlan)
    {
        return toAjax(teachPlanService.updateTeachPlan(teachPlan));
    }

    /**
     * 删除教学计划
     */
    @ApiOperation(value = "删除教学计划")
    @PreAuthorize("@ss.hasPermi('book:plan:remove')")
    @Log(title = "教学计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachPlanService.deleteTeachPlanByIds(ids));
    }
}
