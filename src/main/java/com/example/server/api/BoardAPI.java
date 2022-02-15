package com.example.server.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.server.dataObject.Board;
import com.example.server.model.Result;
import com.example.server.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardAPI {
    
    /**
     * board service
     */
    @Autowired
    private BoardService boardService;

    public  Result<Integer> add(@RequestBody @Valid Board board){
        Result<Integer> result;
        // 校验当前用户是否登录

        // 校验
        if (board == null) {
            result = new Result<>();
            result.setResultFailed("留言失败！");
            return result;
        }

        // 校验留言信息长度
        if(board.getContent() != null) {
            int length = board.getContent().length();
            if( length < 3 || length > 200) {
                result = new Result<>();
                result.setResultFailed("留言失败，长度在3~200字之间");
                return result;
            }
        }

        // 插入

        

        return result;
    }
}
