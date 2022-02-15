package com.example.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.server.dao.BoardDAO;
import com.example.server.dataObject.Board;
import com.example.server.model.Result;
import com.example.server.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Board Service Implementation
 */
@Component
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDAO boardDAO;

    @Override
    public Result<Integer> add(Board board) {
        Result<Integer> result = new Result<>();
        // 时间问题要考虑到时间戳处理。暂时用系统时间记录
        // 取出时候需要转换格式
        Date date = new Date();   
        board.setCreattime(date);
        
        // 存入数据库
        Integer flag = boardDAO.add(board);
        // 返回存储结果
        if(flag > 0) {
            result.setResultSuccess("留言成功！", flag);
        } else {
            result.setResultFailed("留言失败！");
        }

        return result;
    }
    
}
