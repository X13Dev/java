package com.example.server.service;

import com.example.server.dataObject.Board;
import com.example.server.dataObject.BoardFull;
import com.example.server.dataObject.Message;
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
     * @param board 留言对象
     * @return 是否新增成功
     */
    Result<Board> add(Board board);

    /**
     * 查询留言列表
     *
     * @param board
     * @return
     */
    Result<List<BoardFull>> searchBoardTree(Board board);

    /**
     * 新增回复
     * 
     * @param message 回复对象
     * @return 是否新增成功
     */
    Result<Message> addMessage(Message message);
}
