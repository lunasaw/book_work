package com.book.work.service.impl;

import java.util.List;
import com.book.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.mapper.BookMapper;
import com.book.work.domain.Book;
import com.book.work.service.IBookService;

/**
 * 书籍Service业务层处理
 * 
 * @author book
 * @date 2022-05-05
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService
{
    @Autowired
    private BookMapper bookMapper;

    /**
     * 查询书籍
     * 
     * @param id 书籍主键
     * @return 书籍
     */
    @Override
    public Book selectBookById(Long id)
    {
        return bookMapper.selectBookById(id);
    }

    /**
     * 查询书籍列表
     * 
     * @param book 书籍
     * @return 书籍
     */
    @Override
    public List<Book> selectBookList(Book book)
    {
        return bookMapper.selectBookList(book);
    }

    /**
     * 分页查询书籍列表
     *
     * @param book 书籍
     * @return 书籍
     */
    @Override
    public IPage<Book> selectList(IPage<Book> page, Book book) {
        return bookMapper.selectBookPage(page, book);
    }


    /**
     * 新增书籍
     * 
     * @param book 书籍
     * @return 结果
     */
    @Override
    public int insertBook(Book book)
    {
        book.setCreateTime(DateUtils.getNowDate());
        return bookMapper.insertBook(book);
    }

    /**
     * 修改书籍
     * 
     * @param book 书籍
     * @return 结果
     */
    @Override
    public int updateBook(Book book)
    {
        book.setUpdateTime(DateUtils.getNowDate());
        return bookMapper.updateBook(book);
    }

    /**
     * 批量删除书籍
     * 
     * @param ids 需要删除的书籍主键
     * @return 结果
     */
    @Override
    public int deleteBookByIds(Long[] ids)
    {
        return bookMapper.deleteBookByIds(ids);
    }

    /**
     * 删除书籍信息
     * 
     * @param id 书籍主键
     * @return 结果
     */
    @Override
    public int deleteBookById(Long id)
    {
        return bookMapper.deleteBookById(id);
    }
}
