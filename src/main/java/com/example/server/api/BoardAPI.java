package com.example.server.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.server.dataObject.Board;
import com.example.server.dataObject.BoardFull;
import com.example.server.dataObject.Message;
import com.example.server.dataObject.User;
import com.example.server.error.ServerBusinessException;
import com.example.server.error.ServerErrorTypeEnum;
import com.example.server.model.Result;
import com.example.server.service.BoardService;
import com.example.server.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/board")
public class BoardAPI {
    
    /**
     * board service
     */
    @Autowired
    private BoardService boardService;

    /**
     * user service
     */
    @Autowired
    private UserService userService;

    /**
     * 新增留言
     * 
     * @param board 留言信息对象
     * @param request session校验
     * @return 留言是否成功
     * @throws ServerBusinessException 异常处理
     */
    @PostMapping("/add")
    public  Result<Board> add(@RequestBody @Valid Board board, HttpServletRequest request) throws ServerBusinessException{
        Result<Board> result;
        // 校验当前用户是否登录
        if (request == null || request.getSession() == null) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_ISLOGIN_ERROR.getErrorDesc());
            return result;
        }

        Result<User> isLoginResult = userService.isLogin(request.getSession());
        if(isLoginResult == null) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_ISLOGIN_ERROR.getErrorDesc());
            return result;
        }


        // 校验留言信息
        if (board == null) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.BOARD_NULL_ERROR.getErrorDesc());
            return result;
        }

        // 校验留言信息长度
        if(board.getContent() != null) {
            int length = board.getContent().length();
            if( length < 3 || length > 200) {
                result = new Result<>();
                result.setResultFailed(ServerErrorTypeEnum.BOARD_NAME_LENGTH_ERROR.getErrorDesc());
                return result;
            }
        }

        // 插入并返回结果
        result = boardService.add(board);
        return result;
    }

    /**
     * 新增回复
     * 
     * @param messge 回复对象
     * @param request session校验
     * @return 回复是否新增成功
     * @throws ServerBusinessException 异常处理
     */
    @PostMapping("/Board/NewMessage")
    public Result<Message> addMessage(@RequestBody @Valid Message messge, HttpServletRequest request) throws ServerBusinessException {
        Result<Message> result;
        // 校验当前用户是否登录
        if (request == null || request.getSession() == null) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_ISLOGIN_ERROR.getErrorDesc());
            return result;
        }

        return null;
    }

    /**
     * 查询留言树（含回复）
     * 
     * @param board
     * @return
     */
    @PostMapping("/searchBoardTree")
    public  Result<List<BoardFull>> searchBoardTree(@RequestBody @Valid Board board){
        return boardService.searchBoardTree(board);
    }
}
