package com.example.server.service;

import com.example.server.dataObject.Board;
import com.example.server.model.Result;
import org.springframework.stereotype.Service;

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
}
