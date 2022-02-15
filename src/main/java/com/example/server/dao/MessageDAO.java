package com.example.server.dao;

import java.util.List;

import com.example.server.dataObject.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * MessageDao
 */
@Mapper
public interface MessageDAO {
    
    /**
     * 新增回复信息
     * 
     * @param message 回复信息对象
     * @return 新增成功记录条数
     */
    Integer add(Message message);

    /**
     * 根据留言标识查询回复信息
     * 
     * @param ids 留言标识集合
     * @return 集合所属回复信息集合
     */
    List<Message> searchMessageByBoardIds(List<Integer> ids);
}
