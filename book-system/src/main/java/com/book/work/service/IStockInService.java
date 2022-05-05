package com.book.work.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.domain.StockIn;
import com.book.work.domain.vo.StockInVO;

/**
 * 书籍领取列Service接口
 * 
 * @author book
 * @date 2022-05-05
 */
public interface IStockInService  extends IService<StockIn>
{
    /**
     * 查询书籍领取列
     * 
     * @param id 书籍领取列主键
     * @return 书籍领取列
     */
    public StockInVO selectStockInById(Long id);

    /**
     * 查询书籍领取列列表
     * 
     * @param stockIn 书籍领取列
     * @return 书籍领取列集合
     */
    public List<StockInVO> selectStockInList(StockIn stockIn);

    /**
     * 分页查询书籍领取列列表
     *
     * @param stockIn 书籍领取列
     * @return 书籍领取列集合
     */
    public IPage<StockIn> selectList(IPage<StockIn> page, StockIn stockIn);


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
     * 批量删除书籍领取列
     * 
     * @param ids 需要删除的书籍领取列主键集合
     * @return 结果
     */
    public int deleteStockInByIds(Long[] ids);

    /**
     * 删除书籍领取列信息
     * 
     * @param id 书籍领取列主键
     * @return 结果
     */
    public int deleteStockInById(Long id);
}
