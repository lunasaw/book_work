package com.book.work.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.domain.StockIn;

/**
 * 书籍领取列Mapper接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface StockInMapper extends BaseMapper<StockIn> {
    /**
     * 查询书籍领取列
     * 
     * @param id 书籍领取列主键
     * @return 书籍领取列
     */
    public StockIn selectStockInById(Long id);

    /**
     * 查询书籍领取列列表
     * 
     * @param stockIn 书籍领取列
     * @return 书籍领取列集合
     */
    public List<StockIn> selectStockInList(StockIn stockIn);

    /**
     * 新增书籍领取列
     * 
     * @param stockIn 书籍领取列
     * @return 结果
     */
    public int insertStockIn(StockIn stockIn);

    /**
     * 修改书籍领取列
     * 
     * @param stockIn 书籍领取列
     * @return 结果
     */
    public int updateStockIn(StockIn stockIn);

    /**
     * 删除书籍领取列
     * 
     * @param id 书籍领取列主键
     * @return 结果
     */
    public int deleteStockInById(Long id);

    /**
     * 批量删除书籍领取列
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockInByIds(Long[] ids);

    /**
     * 分页查询书籍领取列列表
     *
     * param page             分页信息
     * @param stockIn 书籍领取列信息
     * @return 书籍领取列集合
     */
    public IPage<StockIn> selectStockInPage(IPage<StockIn> page, StockIn stockIn);

}
