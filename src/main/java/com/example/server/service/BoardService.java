package com.example.server.service;

import com.example.server.dataObject.Board;
import com.example.server.dataObject.BoardFull;
import com.example.server.model.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Board Service Interface
 */
@Service
public interface BoardService {

    /**
     * 新增用户留言
     *
     * @param board
     * @return
     */
    Result<Integer> add(Board board);

    /**
     * 查询留言列表
     *
     * @param board
     * @return
     */
    Result<List<BoardFull>> searchBoardTree(Board board);
}
