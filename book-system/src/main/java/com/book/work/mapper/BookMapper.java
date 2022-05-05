package com.book.work.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.domain.Book;
/**
 * 书籍Mapper接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface BookMapper extends BaseMapper<Book> {
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
     * 删除书籍
     * 
     * @param id 书籍主键
     * @return 结果
     */
    public int deleteBookById(Long id);

    /**
     * 批量删除书籍
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookByIds(Long[] ids);

    /**
     * 分页查询书籍列表
     *
     * param page             分页信息
     * @param book 书籍信息
     * @return 书籍集合
     */
    public IPage<Book> selectBookPage(IPage<Book> page, Book book);

}
