package com.example.server.service.impl;

import com.example.server.dao.BoardDAO;
import com.example.server.dao.MessageDAO;
import com.example.server.dataObject.Board;
import com.example.server.dataObject.BoardFull;
import com.example.server.dataObject.Message;
import com.example.server.model.Result;
import com.example.server.service.BoardService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Board Service Implementation
 */
@Component
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public Result<Board> add(Board board) {
        Result<Board> result = new Result<>();
        // 时间问题要考虑到时间戳处理。暂时用系统时间记录
        // 取出时候需要转换格式
        Date date = new Date();
        board.setCreattime(date);

        // 存入数据库
        Integer flag = boardDAO.add(board);
        // 返回存储结果
        if (flag > 0) {
            result.setResultSuccess("留言成功！", board);
        } else {
            result.setResultFailed("留言失败！");
        }

        return result;
    }

    @Override
    public Result<List<BoardFull>> searchBoardTree(Board board) {
        Result<List<BoardFull>> result = new Result<>();
        List<BoardFull> boards = boardDAO.searchBoards();
        result.setResultSuccess("成功", boards);
        if (CollectionUtils.isEmpty(boards)) {
            return result;
        }
        //留言ids预留分页时适用，无需全量查询
        List<Message> messages = messageDAO.searchMessageByBoardIds(null);
        if (CollectionUtils.isEmpty(messages)) {
            return result;
        }
        boards.forEach(info -> {
            info.setMessages(this.getChilden(info.getId(), messages));
        });
        return result;
    }

    /**
     * 递归组装属性
     *
     * @param pId
     * @param childen
     * @return
     */
    private List<Message> getChilden(Integer pId, List<Message> childen) {
        List<Message> thisChildenList = new ArrayList<>();
        List<Message> notThisChildenList = new ArrayList<>();
        childen.forEach(info -> {
            boolean ifChild = (ObjectUtils.isNotEmpty(info.getPid()) && pId.equals(info.getPid()))
                    || (ObjectUtils.isEmpty(info.getPid()) && pId.equals(info.getBid()));
            if (ifChild) {
                thisChildenList.add(info);
            } else {
                notThisChildenList.add(info);
            }
        });
        if (CollectionUtils.isNotEmpty(thisChildenList)) {
            thisChildenList.forEach(info -> info.setMessages(this.getChilden(info.getId(), notThisChildenList)));
        }
        return thisChildenList;
    }

}
