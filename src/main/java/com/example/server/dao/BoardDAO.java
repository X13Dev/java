package com.example.server.dao;

import com.example.server.dataObject.Board;
import org.apache.ibatis.annotations.Mapper;

/**
 * BoardDao类
 */
@Mapper
public interface BoardDAO {
    
    /**
     * 新增留言
     * @param board 留言对象
     * @return 新增成功记录条数
     */
    int add(Board board);
}
