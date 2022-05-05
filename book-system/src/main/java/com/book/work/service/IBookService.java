package com.book.work.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.work.domain.Book;

import java.util.List;

/**
 * 书籍Service接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface IBookService  extends IService<Book>
{
    /**
     * 查询书籍
     * 
     * @param id 书籍主键
     * @return 书籍
     */
    public Book selectBookById(Long id);

    /**
     * 查询书籍列表
     * 
     * @param book 书籍
     * @return 书籍集合
     */
    public List<Book> selectBookList(Book book);

    /**
     * 分页查询书籍列表
     *
     * @param book 书籍
     * @return 书籍集合
     */
    public IPage<Book> selectList(IPage<Book> page, Book book);


    /**
     * 新增书籍
     * 
     * @param book 书籍
     * @return 结果
     */
    public int insertBook(Book book);

    /**
     * 修改书籍
     * 
     * @param book 书籍
     * @return 结果
     */
    public int updateBook(Book book);

    /**
     * 批量删除书籍
     * 
     * @param ids 需要删除的书籍主键集合
     * @return 结果
     */
    public int deleteBookByIds(Long[] ids);

    /**
     * 删除书籍信息
     * 
     * @param id 书籍主键
     * @return 结果
     */
    public int deleteBookById(Long id);
}
