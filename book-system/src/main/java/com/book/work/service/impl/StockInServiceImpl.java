package com.book.work.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.book.common.core.domain.entity.SysDept;
import com.book.common.core.domain.entity.SysUser;
import com.book.common.utils.DateUtils;
import com.book.common.utils.StringUtils;
import com.book.system.mapper.SysDeptMapper;
import com.book.system.mapper.SysUserMapper;
import com.book.work.domain.Book;
import com.book.work.domain.vo.StockInVO;
import com.book.work.mapper.BookMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.book.work.mapper.StockInMapper;
import com.book.work.domain.StockIn;
import com.book.work.service.IStockInService;

/**
 * 书籍领取列Service业务层处理
 * 
 * @author book
 * @date 2022-05-05
 */
@Service
public class StockInServiceImpl extends ServiceImpl<StockInMapper, StockIn> implements IStockInService {
    @Autowired
    private StockInMapper stockInMapper;

    @Autowired
    private BookMapper    bookMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询书籍领取列
     * 
     * @param id 书籍领取列主键
     * @return 书籍领取列
     */
    @Override
    public StockInVO selectStockInById(Long id) {
        StockIn stockIn = stockInMapper.selectStockInById(id);
        StockInVO stockInVO = new StockInVO();
        BeanUtils.copyProperties(stockIn,stockInVO);
        List<Long> collect = Arrays.stream(stockIn.getBooks().split(",")).map(Long::valueOf).collect(Collectors.toList());
        List<Book> books = bookMapper.selectBatchIds(collect);
        stockInVO.setBookIds(collect);
        stockInVO.setBookList(books);

        SysDept sysDept = sysDeptMapper.selectDeptById(stockIn.getDeptId());
        stockInVO.setDeptName(sysDept.getDeptName());
        stockInVO.setSysDept(sysDept);

        SysUser sysUser = sysUserMapper.selectUserById(stockIn.getUserId());
        stockInVO.setUserName(sysUser.getUserName());
        stockInVO.setSysUser(sysUser);
        return stockInVO;
    }

    /**
     * 查询书籍领取列列表
     * 
     * @param stockIn 书籍领取列
     * @return 书籍领取列
     */
    @Override
    public List<StockInVO> selectStockInList(StockIn stockIn) {
        ArrayList<StockInVO> list = Lists.newArrayList();
        List<StockIn> stockIns = stockInMapper.selectStockInList(stockIn);
        for (StockIn in : stockIns) {
            StockInVO stockInVO = new StockInVO();
            BeanUtils.copyProperties(in,stockInVO);
            List<Long> collect = Arrays.stream(in.getBooks().split(",")).map(Long::valueOf).collect(Collectors.toList());
            List<Book> books = bookMapper.selectBatchIds(collect);
            stockInVO.setBookIds(collect);
            stockInVO.setBookList(books);

            SysDept sysDept = sysDeptMapper.selectDeptById(in.getDeptId());
            stockInVO.setDeptName(sysDept.getDeptName());
            stockInVO.setSysDept(sysDept);

            SysUser sysUser = sysUserMapper.selectUserById(in.getUserId());
            stockInVO.setUserName(StringUtils.isEmpty(sysUser.getNickName()) ? sysUser.getUserName() : sysUser.getNickName());

            stockInVO.setSysUser(sysUser);

            list.add(stockInVO);
        }
        return list;
    }

    /**
     * 分页查询书籍领取列列表
     *
     * @param stockIn 书籍领取列
     * @return 书籍领取列
     */
    @Override
    public IPage<StockIn> selectList(IPage<StockIn> page, StockIn stockIn) {
        IPage<StockIn> stockInIPage = stockInMapper.selectStockInPage(page, stockIn);

        return stockInIPage;
    }

    /**
     * 新增书籍领取列
     * 
     * @param stockIn 书籍领取列
     * @return 结果
     */
    @Override
    public int insertStockIn(StockIn stockIn) {
        stockIn.setCreateTime(DateUtils.getNowDate());
        return stockInMapper.insertStockIn(stockIn);
    }

    /**
     * 修改书籍领取列
     * 
     * @param stockIn 书籍领取列
     * @return 结果
     */
    @Override
    public int updateStockIn(StockIn stockIn) {
        stockIn.setUpdateTime(DateUtils.getNowDate());
        return stockInMapper.updateStockIn(stockIn);
    }

    /**
     * 批量删除书籍领取列
     * 
     * @param ids 需要删除的书籍领取列主键
     * @return 结果
     */
    @Override
    public int deleteStockInByIds(Long[] ids) {
        return stockInMapper.deleteStockInByIds(ids);
    }

    /**
     * 删除书籍领取列信息
     * 
     * @param id 书籍领取列主键
     * @return 结果
     */
    @Override
    public int deleteStockInById(Long id) {
        return stockInMapper.deleteStockInById(id);
    }
}
