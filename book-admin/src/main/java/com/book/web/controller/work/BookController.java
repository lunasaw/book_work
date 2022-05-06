package com.book.web.controller.work;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.common.utils.poi.ExcelUtil;
import com.book.work.domain.Book;
import com.book.work.service.IBookService;
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
import com.book.common.core.page.TableDataInfo;

/**
 * 书籍Controller
 *
 * @author book
 * @date 2022-05-05
 */
@RestController
@RequestMapping("/book/work")
@Api(tags = "书籍")
public class BookController extends BaseController {
    @Autowired
    private IBookService bookService;

    /**
     * 查询书籍列表
     */
    @ApiOperation(value = "查询书籍列表")
    @PreAuthorize("@ss.hasPermi('book:work:list')")
    @GetMapping("/list")
    public TableDataInfo list(Book book) {
        startPage();
        List<Book> list = bookService.selectBookList(book);
        return getDataTable(list);
    }

    /**
     * 查询审核过的书籍列表
     */
    @ApiOperation(value = "查询审核过的列表")
    @PreAuthorize("@ss.hasPermi('book:work:list')")
    @GetMapping("/listWithChecked")
    public TableDataInfo listWithChecked(Book book) {
        book.setCheckStatus("0");
        List<Book> list = bookService.selectBookList(book);
        return getDataTable(list);
    }

    /**
     * 分页查询书籍列表
     */
    @ApiOperation(value = "分页查询书籍列表")
    @PreAuthorize("@ss.hasPermi('book:work:list')")
    @GetMapping("/pageList")
    public TableDataInfo pageList(Book book) {
        Page<Book> page = startPageList();
        IPage<Book> list = bookService.selectList(page, book);
        return getDataTable(list.getRecords());
    }

    /**
     * 导出书籍列表
     */
    @ApiOperation(value = "导出书籍列表")
    @PreAuthorize("@ss.hasPermi('book:work:export')")
    @Log(title = "书籍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Book book) {
        List<Book> list = bookService.selectBookList(book);
        ExcelUtil<Book> util = new ExcelUtil<Book>(Book.class);
        util.exportExcel(response, list, "书籍数据");
    }

    /**
     * 获取书籍详细信息
     */
    @ApiOperation(value = "获取书籍详细信息")
    @PreAuthorize("@ss.hasPermi('book:work:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(bookService.selectBookById(id));
    }

    /**
     * 新增书籍
     */
    @ApiOperation(value = "新增书籍")
    @PreAuthorize("@ss.hasPermi('book:work:add')")
    @Log(title = "书籍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Book book) {
        return toAjax(bookService.insertBook(book));
    }

    /**
     * 修改书籍
     */
    @ApiOperation(value = "修改书籍")
    @PreAuthorize("@ss.hasPermi('book:work:edit')")
    @Log(title = "书籍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Book book) {
        return toAjax(bookService.updateBook(book));
    }

    /**
     * 删除书籍
     */
    @ApiOperation(value = "删除书籍")
    @PreAuthorize("@ss.hasPermi('book:work:remove')")
    @Log(title = "书籍", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(bookService.deleteBookByIds(ids));
    }
}
