package com.book.web.controller.work;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.work.domain.vo.StockInVO;
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
import com.book.work.domain.StockIn;
import com.book.work.service.IStockInService;
import com.book.common.utils.poi.ExcelUtil;
import com.book.common.core.page.TableDataInfo;

/**
 * 书籍领取列Controller
 * 
 * @author book
 * @date 2022-05-05
 */
@RestController
@RequestMapping("/book/sotck")
@Api(tags = "书籍领取列")
public class StockInController extends BaseController
{
    @Autowired
    private IStockInService stockInService;

    /**
     * 查询书籍领取列列表
     */
    @ApiOperation(value = "查询书籍领取列列表")
    @PreAuthorize("@ss.hasPermi('book:sotck:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockIn stockIn)
    {
        startPage();
        List<StockInVO> list = stockInService.selectStockInList(stockIn);
        return getDataTable(list);
    }

    /**
     * 分页查询书籍领取列列表
     */
    @ApiOperation(value = "分页查询书籍领取列列表")
    @PreAuthorize("@ss.hasPermi('book:sotck:list')")
    @GetMapping("/pageList")
    public TableDataInfo pageList(StockIn stockIn) {
        Page<StockIn> page = startPageList();
        IPage<StockIn> list = stockInService.selectList(page, stockIn);
        return getDataTable(list.getRecords());
    }

    /**
     * 导出书籍领取列列表
     */
    @ApiOperation(value = "导出书籍领取列列表")
    @PreAuthorize("@ss.hasPermi('book:sotck:export')")
    @Log(title = "书籍领取列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockIn stockIn)
    {
        List<StockInVO> list = stockInService.selectStockInList(stockIn);
        ExcelUtil<StockInVO> util = new ExcelUtil<StockInVO>(StockInVO.class);
        util.exportExcel(response, list, "书籍领取列数据");
    }

    /**
     * 获取书籍领取列详细信息
     */
    @ApiOperation(value = "获取书籍领取列详细信息")
    @PreAuthorize("@ss.hasPermi('book:sotck:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stockInService.selectStockInById(id));
    }

    /**
     * 新增书籍领取列
     */
    @ApiOperation(value = "新增书籍领取列")
    @PreAuthorize("@ss.hasPermi('book:sotck:add')")
    @Log(title = "书籍领取列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockIn stockIn)
    {
        return toAjax(stockInService.insertStockIn(stockIn));
    }

    /**
     * 修改书籍领取列
     */
    @ApiOperation(value = "修改书籍领取列")
    @PreAuthorize("@ss.hasPermi('book:sotck:edit')")
    @Log(title = "书籍领取列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockIn stockIn)
    {
        return toAjax(stockInService.updateStockIn(stockIn));
    }

    /**
     * 删除书籍领取列
     */
    @ApiOperation(value = "删除书籍领取列")
    @PreAuthorize("@ss.hasPermi('book:sotck:remove')")
    @Log(title = "书籍领取列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stockInService.deleteStockInByIds(ids));
    }
}
