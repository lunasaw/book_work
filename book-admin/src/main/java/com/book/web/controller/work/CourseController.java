package com.book.web.controller.work;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.book.work.domain.Course;
import com.book.work.service.ICourseService;
import com.book.common.utils.poi.ExcelUtil;
import com.book.common.core.page.TableDataInfo;

/**
 * 课程列Controller
 *
 * @author book
 * @date 2022-05-05
 */
@RestController
@RequestMapping("/book/course")
@Api(tags = "课程列")
public class CourseController extends BaseController {
    @Autowired
    private ICourseService courseService;

    /**
     * 查询课程列列表
     */
    @ApiOperation(value = "查询课程列列表")
    @PreAuthorize("@ss.hasPermi('book:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(Course course) {
        startPage();
        List<Course> list = courseService.selectCourseList(course);
        return getDataTable(list);
    }

    /**
     * 分页查询课程列列表
     */
    @ApiOperation(value = "分页查询课程列列表")
    @PreAuthorize("@ss.hasPermi('book:course:list')")
    @GetMapping("/pageList")
    public TableDataInfo pageList(Course course) {
        Page<Course> page = startPageList();
        IPage<Course> list = courseService.selectList(page, course);
        return getDataTable(list.getRecords());
    }

    /**
     * 导出课程列列表
     */
    @ApiOperation(value = "导出课程列列表")
    @PreAuthorize("@ss.hasPermi('book:course:export')")
    @Log(title = "课程列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Course course) {
        List<Course> list = courseService.selectCourseList(course);
        ExcelUtil<Course> util = new ExcelUtil<Course>(Course.class);
        util.exportExcel(response, list, "课程列数据");
    }

    /**
     * 获取课程列详细信息
     */
    @ApiOperation(value = "获取课程列详细信息")
    @PreAuthorize("@ss.hasPermi('book:course:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(courseService.selectCourseById(id));
    }

    /**
     * 新增课程列
     */
    @ApiOperation(value = "新增课程列")
    @PreAuthorize("@ss.hasPermi('book:course:add')")
    @Log(title = "课程列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Course course) {
        return toAjax(courseService.insertCourse(course));
    }

    /**
     * 修改课程列
     */
    @ApiOperation(value = "修改课程列")
    @PreAuthorize("@ss.hasPermi('book:course:edit')")
    @Log(title = "课程列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Course course) {
        return toAjax(courseService.updateCourse(course));
    }

    /**
     * 删除课程列
     */
    @ApiOperation(value = "删除课程列")
    @PreAuthorize("@ss.hasPermi('book:course:remove')")
    @Log(title = "课程列", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(courseService.deleteCourseByIds(ids));
    }
}
