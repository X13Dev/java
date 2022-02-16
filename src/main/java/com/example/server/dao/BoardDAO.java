package com.example.server.dao;

import java.util.List;

import com.example.server.dataObject.Board;
import com.example.server.dataObject.BoardFull;

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


    /**
     * 查询所有留言
     * 
     * @return
     */
    List<BoardFull> searchBoards();
}
